package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.enums.ValidationEnum;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class S3ClientService {

    private final AmazonS3 amazonS3;
    private final String BUCKET_NAME = "emerion-cliente-api";

    public S3ClientService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void saveNfeDanfeFile(String cnpjEmpresa, String codcli, MultipartFile nfe) {
        saveFileToS3(cnpjEmpresa, codcli, nfe, "pdf");
    }

    public void saveNfeXmlFile(String cnpjEmpresa, String codcli, MultipartFile nfe) {
        saveFileToS3(cnpjEmpresa, codcli, nfe, "xml");
    }

    public void saveFileToS3(String cnpjEmpresa, String codcli, MultipartFile nfe, String fileExtension) {
        String fileName = nfe.getOriginalFilename();
        validateFileExtension(fileName, fileExtension);
        fileName = getNFeAccessKey(fileName);
        String finalFileName = String.format("%s/%s/%s/%s.%s", fileExtension, cnpjEmpresa, codcli, fileName, fileExtension);
        saveFileToS3(nfe, finalFileName);
    }

    private void saveFileToS3(MultipartFile nfe, String fileName) {
        try {
            amazonS3.putObject(
                    BUCKET_NAME,
                    fileName,
                    nfe.getInputStream(),
                    null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getNFeAccessKey(String fileName) {
        Pattern p = Pattern.compile("(\\d{44})");
        Matcher m = p.matcher(fileName);
        if (!m.find()) {
            throw new ValidationException(ValidationEnum.NFE_DANFE_NAME_INVALID_FORMAT);
        }
        return m.group(0);
    }

    private void validateFileExtension(String fileName, String extension) {
        if (!fileName.endsWith(extension)) {
            throw new ValidationException(ValidationEnum.INVALID_FILE_FORMAT);
        }
    }
}
