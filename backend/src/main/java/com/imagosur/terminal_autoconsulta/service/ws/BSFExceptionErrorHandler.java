package com.imagosur.terminal_autoconsulta.service.ws;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.imagosur.terminal_autoconsulta.controller.Controller;
import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.model.response.Registro;


@Component
public class BSFExceptionErrorHandler implements ResponseErrorHandler{

	private String option = "";
	public static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private Mensaje mensaje;
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getRawStatusCode() != 200 && response.getRawStatusCode() != 409;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		String resultado = "";
		
		switch(response.getRawStatusCode()) {
			
			//Start error 400
			case 400: 
				switch(option) {
					case "login": 
						break;
					default: 
						resultado = mensaje.ERROR;
						break;
				}
				break;
			//Finally error 400
				
			//Start error 403
			case 403:
				System.err.println("Está tirando un error 403, y no lo tenemos definido");
				resultado = mensaje.ERROR;
				break;
			//Finally error 403
				
			//Start error 506
			case 506:
				System.err.println("Está tirando un error 506, y no lo tenemos definido");
				resultado = mensaje.ERROR;
				break;
			//Finally error 506
			
			//Start error 500
			case 500:
				System.err.println("Está tirando un error 500, y no lo tenemos definido");
				resultado = mensaje.ERROR;
				break;
			//Finally error 500
				
			//Start error 406
			case 406:
				switch(option) {
				case "login":
					resultado = response.toString();
					break;
				case "registro":
					resultado = response.toString();
					break;
				default:
					resultado = mensaje.ERROR;
					break;
				}
			//Finally error 406
				
			//Start error 409
			case 409:
				switch(option) {
					case "login":
						resultado = mensaje.ERROR_LOGIN;
						break;
					case "registro":
						resultado = response.toString();
						break;
					default:
						resultado = mensaje.ERROR;
						break;
				}
				break;
			//Finally error 409
				
			//Start default
			default:
				resultado = mensaje.ERROR;
				break;
			//Finally default
		}
		
		logger.error("["+option+": " + response.getRawStatusCode() + "] " + resultado);
		
		if(option != "Adhesion Resumen" || option != "Enviando Adhesion")
			throw new BSFHTTPException(resultado,response.getRawStatusCode());
	}
	
	public void setOption(String option) {
		System.out.println("envio la ventana: " + option);
		this.option = option;
	}
	

}
