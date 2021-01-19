package com.imagosur.terminal_autoconsulta.service.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Mensaje {
	
	@Value("${badrequestLogin}")
	public String ERROR_BADREQUEST_LOGIN;
	
	@Value("${errorLogin}")
	public String ERROR_LOGIN;

	@Value("${errorWebService}")
	public String ERROR_WEBSERVICE;
	
	@Value("${error}")
	public String ERROR;
	
	@Value("${errorServidor}")
	public String ERROR_SERVIDOR;
	
	@Value("${errorSinInformacion}")
	public String ERROR_SIN_INFORMACION;
	
	@Value("${errorDesconocido}")
	public String ERROR_DESCONOCIDO;
	
	@Value("${correoOk}")
	public String CORREO_OK;
	
	@Value("${correoErrorPdf}")
	public String CORREO_ERROR_PDF;
	
	@Value("${correoError}")
	public String CORREO_ERROR;
	
	@Value("${correoErrorDesconocido}")
	public String CORREO_ERROR_DESCONOCIDO;
	
	@Value("${correoErrorGuardado}")
	public String CORREO_ERROR_GUARDADO;
	
	@Value("${correoErrorService}")
	public String CORREO_ERROR_SERVICE;
	
	@Value("${registroOk}")
	public String REGISTRO_OK;
	
	
}
