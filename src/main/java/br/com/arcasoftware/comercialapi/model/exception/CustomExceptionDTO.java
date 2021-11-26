package br.com.arcasoftware.comercialapi.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomExceptionDTO {
    private final String message;
}
