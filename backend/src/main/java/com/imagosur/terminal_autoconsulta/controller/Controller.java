package com.imagosur.terminal_autoconsulta.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.SendFailedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.mail.*;
import com.imagosur.terminal_autoconsulta.model.response.Descarga;
import com.imagosur.terminal_autoconsulta.request.BSF.SummaryBSF;
import com.imagosur.terminal_autoconsulta.request.BSF.UserNewBSF;
import com.imagosur.terminal_autoconsulta.request.front.*;
import com.imagosur.terminal_autoconsulta.service.*;
import com.imagosur.terminal_autoconsulta.service.ws.Mensaje;
import com.imagosur.terminal_autoconsulta.utils.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rest")
public class Controller {

	public static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private BSFService service;
	
	@Autowired
	private Mensaje mensaje;

	@Autowired
	private MailSender enviar;
	
	@Autowired
	private ManejoArchivo file;
	
	@Value("${bsf.url.contacto}")
	private String urlContacto;

	@PostMapping("/getClienteAll")
	public ResponseEntity<?> getCliente(Principal principal, @RequestBody UserFront request) {
		logger.info(String.format("Validando ingreso del usuario %s con el documento %d", request.getUsuario(),
				request.getDocumento()));
		try {
			return ResponseEntity.ok(service.getCliente(Mapeo.getDoc(request),Mapeo.getUser(request)));
		} catch (ResourceAccessException e) {
			logger.error("Error ResourceAccessException: No hay comunicación con servidor");
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.ERROR_WEBSERVICE,511));
		} catch (BSFHTTPException e) {
			logger.error("Error BSFHTTPException: " +  e.getCause());
			return ResponseEntity.badRequest().body(getErrorResponse(e.getMessage(),e.getNumeroDeError()));
		} catch (Exception e) {
			logger.error("Error desconocido: " + mensaje.ERROR_DESCONOCIDO + " " + e.getCause());
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.ERROR_DESCONOCIDO,511));
		}
	}
	@PostMapping("/getResumen")
	public ResponseEntity<?> getResumen(@RequestBody SummaryFront request) {
		try {
			logger.info("Comenzando a generar la lista de resumenes " + request.getDocumento());
			return ResponseEntity.ok(service.getResumen(Mapeo.getSummary(request)).getList());
		} catch (BSFHTTPException e) {
			logger.error("Error " +  e.getMessage());
			return ResponseEntity.badRequest().body(getErrorResponse(e.getMessage(),e.getNumeroDeError()));
		} catch (Exception e) {
			logger.error("Error desconocido: " + mensaje.ERROR_DESCONOCIDO + " " + e.getCause());
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.ERROR_DESCONOCIDO,511));
		}
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody UserNewBSF request) {
		try {
			logger.info("Comenzando a registrar a persona con documento " + request.getDocumento());
			return ResponseEntity.ok(service.registrar(request));
		} catch (BSFHTTPException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + " " + e.getNumeroDeError());
			return ResponseEntity.badRequest().body(getErrorResponse(e.getMessage(),e.getNumeroDeError()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error desconocido: " + e.getCause());
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.ERROR_DESCONOCIDO,511));
		}
	}
	
	@GetMapping("/getResumenTarjeta")
	public ResponseEntity<?> getResumenTarjeta(@RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date nacimiento, @RequestParam String tarjeta, @RequestParam String fecha) {
		SummaryBSF request = new SummaryBSF(nacimiento,tarjeta,fecha);
		try {
			logger.info("Comienza descarga de resumen de tarjeta: " + tarjeta);
			return ResponseEntity.ok(service.descargarResumen(request).getArchivo());
		} catch (BSFHTTPException e) {
			logger.error("Error BSFHTTPException: ", e.getCause());
			return ResponseEntity.badRequest().body(getErrorResponse(e.getMessage(),e.getNumeroDeError()));
		} catch (Exception e) {
			logger.error("Error desconocido: " + mensaje.ERROR_DESCONOCIDO + " " + e.getCause());
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.ERROR_DESCONOCIDO,511));
		}
	}
	
	
	@GetMapping("/enviar")
	public ResponseEntity<?> enviar(@RequestParam String remitente, 
			@RequestParam String fecha,
			@RequestParam String tarjeta, 
			@RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date fechaNacimiento, 
			@RequestParam BigInteger doc, 
			@RequestParam String cliente){
		Mail correo = new Mail();
		SummaryBSF summary = new SummaryBSF(fechaNacimiento, tarjeta, fecha);
		correo.setRecipients(remitente);
		correo.setContentType(enviar.getContent());
		correo.setSubject(enviar.getSubject());
		correo.setText(enviar.getMessage());
		correo.updateText("{{##NOMBRE.CLIENTE##}}",cliente);
		try {
			Descarga des = service.descargarResumen(summary);
			this.file.saveFile(des.getArchivo(), "pdf",String.valueOf(doc));
			correo.setFiles(new SourceFile(this.file.getPath(),this.file.getFileName(),this.file.getFormato()));
			enviar.send(correo);
			logger.info("Correo enviado: " + mensaje.CORREO_OK + " " + remitente);
			return ResponseEntity.ok(mensaje.CORREO_OK);
		} catch (MailSenderException e) {
			logger.error("Error MailSenderException - enviando corrreo: " + e.getCause());
			return ResponseEntity.badRequest().body(getErrorResponse(e.getMessage(),e.getNumberError()));
		} catch (UnsupportedEncodingException e) {
			logger.error("Error UnsupportedEncodingException - enviando corrreo: " + e.getCause());
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.CORREO_ERROR_PDF,511));
		} catch (IOException e) {
			logger.error("Error IOException - enviando corrreo: " + e.getCause());
			e.printStackTrace();
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.CORREO_ERROR_GUARDADO,511));
		} catch (SendFailedException e) {
			logger.error("Error SendFailedException - enviando corrreo: "+ e.getCause());
			e.printStackTrace();
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.CORREO_ERROR_SERVICE,511));
		} catch (BSFHTTPException e) {
			logger.error("Error BSFHTTPException - enviando correo: " + e.getMessage());
			return ResponseEntity.badRequest().body(getErrorResponse(e.getMessage(),e.getNumeroDeError()));
		} catch (KeyStoreException e) {
			logger.error("Error KeyStoreException - enviando corrreo: "+ e.getCause());
			e.printStackTrace();
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.CORREO_ERROR_SERVICE,513));
		} catch (GeneralSecurityException e) {
			logger.error("Error GeneralSecurityException - enviando corrreo: "+ e.getCause());
			e.printStackTrace();
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.CORREO_ERROR_SERVICE,513));
		} catch (Exception e) {
			logger.error("Error desconocido: " + mensaje.ERROR_DESCONOCIDO + " " + e.getCause());
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.ERROR_DESCONOCIDO,511));
		}
	}
	
	@PostMapping("/getContacto")
	private ResponseEntity<?> getContacto(){
		try {
			logger.info("Comenzó a realizar la busqueda de la url de contacto");
			return ResponseEntity.ok(urlContacto);
		} catch (Exception e) {
			logger.error("Error desconocido: " + mensaje.ERROR_DESCONOCIDO + " " + e.getCause());
			return ResponseEntity.badRequest().body(getErrorResponse(mensaje.ERROR_DESCONOCIDO,511));
		}
	}
	
	
	private Map<String, Object> getErrorResponse(String error, int num) {
		Map<String, Object> ob = new HashMap<>();
		ob.put("error", num);
		ob.put("mensaje",error);
		return ob;

	}
}
