package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.enums.ValidationEnum;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
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

    public byte[] getXMLFileFromS3(String cnpjEmpresa, String codcli, String nronfe) {
        String fileName = String.format("%s/%s/%s/%s.xml", "xml", cnpjEmpresa, codcli, nronfe);
        return getFileFromS3(fileName);
    }

    public byte[] getFileFromS3(String fileName) {
        this.verifyIfFileExistsInS3(fileName);
        S3Object s3Object = this.amazonS3.getObject(BUCKET_NAME, fileName);
        final S3ObjectInputStream stream = s3Object.getObjectContent();
        byte[] content = null;
        try {
            content = IOUtils.toByteArray(stream);
            log.info("File downloaded successfully.");
            s3Object.close();
        } catch (final IOException ex) {
            log.info("IO Error Message= " + ex.getMessage());
        }
        return content;
    }

    private void verifyIfFileExistsInS3(String fileName) {
        boolean doesObjectExist = this.amazonS3.doesObjectExist(BUCKET_NAME, fileName);
        if (!doesObjectExist) {
            throw new ValidationException(ValidationEnum.FILE_NOT_FOUND_AT_S3);
        }
    }

    private void validateFileExtension(String fileName, String extension) {
        if (!fileName.endsWith(extension)) {
            throw new ValidationException(ValidationEnum.INVALID_FILE_FORMAT);
        }
    }
}
