package br.com.arcasoftware.comercialapi.application.enums;

import org.springframework.http.HttpStatus;

public enum EnumException {

	CLIENTE_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Cliente informado para a pesquisa não encontrado."),
	CLASS_NOT_IMPLEMENTED(HttpStatus.INTERNAL_SERVER_ERROR, "Classe ainda nao disponível para operação");

	private final HttpStatus httpStatus;
	private final String description;

	EnumException(HttpStatus httpStatus, String description) {
		this.httpStatus = httpStatus;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
