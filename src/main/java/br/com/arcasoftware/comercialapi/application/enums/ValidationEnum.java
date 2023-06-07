package br.com.arcasoftware.comercialapi.application.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ValidationEnum {
    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "Customer not found!"),
    CREDIT_NOT_FOUND(HttpStatus.NOT_FOUND, "Credit not found!"),
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "Credit not found!"),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "Credit not found!"),
    NFE_DANFE_NAME_INVALID_FORMAT(HttpStatus.BAD_REQUEST, "Invalid Danfe name format. Please consider using the Danfe's Access Key as its file name"),
    INVALID_FILE_FORMAT(HttpStatus.NOT_FOUND, "Wrong file format"),
    FILE_NOT_FOUND_AT_S3(HttpStatus.NOT_FOUND, "File is not yet available for downloading.");

    private final HttpStatus httpStatus;
    private final String description;

    ValidationEnum(HttpStatus httpStatus, String description) {
        this.httpStatus = httpStatus;
        this.description = description;
    }
}
