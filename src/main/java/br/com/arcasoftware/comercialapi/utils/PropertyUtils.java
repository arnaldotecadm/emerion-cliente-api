package br.com.arcasoftware.comercialapi.utils;

import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import org.springframework.http.HttpStatus;

import java.io.FileInputStream;
import java.util.Properties;

public abstract class PropertyUtils {

	private static final Properties properties;

	private PropertyUtils(){
		/*
		Just to hide the public one
		 */
	}
	static {
		try {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("src/main/resources/transparencia-api.properties");
		props.load(file);
		properties = props;
		}catch(Exception e) {
			throw new ValidationException("Erro ao carregar o arquivo de Propriedades.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Retorna o valor da propriedade desejada ou vazio, caso ela nao exista.
	 */
	public static String getProperty(String prop) {
		return properties.getProperty(prop, "");
	}
}
