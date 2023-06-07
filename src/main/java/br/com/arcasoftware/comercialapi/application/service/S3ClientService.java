package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.enums.ValidationEnum;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3ClientService {

    private final AmazonS3 amazonS3;
    private final String BUCKET_NAME = "emerion-cliente-api";

    public S3ClientService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void saveNfeDanfeFile(String cnpjEmpresa, String codcli, String nronfe, MultipartFile nfe) {
        saveFileToS3(cnpjEmpresa, codcli, nronfe, nfe, "pdf");
    }

    public void saveNfeXmlFile(String cnpjEmpresa, String codcli, String nronfe, MultipartFile nfe) {
        saveFileToS3(cnpjEmpresa, codcli, nronfe, nfe, "xml");
    }

    public void saveFileToS3(String cnpjEmpresa, String codcli, String nronfe, MultipartFile nfe, String fileExtension) {
        String fileName = nfe.getOriginalFilename();
        validateFileExtension(fileName, fileExtension);
        String finalFileName = String.format("%s/%s/%s/%s.%s", fileExtension, cnpjEmpresa, codcli, nronfe, fileExtension);
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


    private void validateFileExtension(String fileName, String extension) {
        if (!fileName.endsWith(extension)) {
            throw new ValidationException(ValidationEnum.INVALID_FILE_FORMAT);
        }
    }
}
