package br.com.arcasoftware.comercialapi.application.enums;

import org.springframework.http.HttpStatus;

public enum EnumException {

	UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error found."),
	FORBIDDEN(HttpStatus.FORBIDDEN, "Operação não permitida."),
	ITEM_CREATED(HttpStatus.CREATED, "Item inserido com sucesso."),
	CLIENTE_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Cliente informado para a pesquisa não encontrado."),

	ESTPFA_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "Padrão de Faturamento não encontrado."),
	ESTICM_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "Regra de ICMS não encontrada."),
	ESTIPI_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "Regra de IPI não encontrada."),
	ESTSTR_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "Regra de ST não encontrada."),
	ITEM_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "Item não encontrado."),
	ENUM_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "Enum não encontrado."),
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
