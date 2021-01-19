package com.imagosur.terminal_autoconsulta.service.ws;

import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.xml.bind.DatatypeConverter;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.imagosur.commons.mailsender.Mail;
import com.imagosur.commons.mailsender.MailSenderSmtpImpl;
import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.model.response.*;
import com.imagosur.terminal_autoconsulta.request.BSF.*;
import com.imagosur.terminal_autoconsulta.service.ClienteService;
import com.imagosur.terminal_autoconsulta.service.TarjetaDisponibleService;

@Service
public class BSFServiceWebServiceImpl implements BSFServiceWebService {

	public static final Logger logger = LoggerFactory.getLogger(BSFServiceWebServiceImpl.class);
	
	@Value("${bsf.webservice}")
	private String url;
	
	@Value("${bsf.service.registro}")
	private String urlRegistro;
	
	@Value("${bsf.api-key}")
	private String key;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private Mensaje mensaje;
	
	@Autowired
	private TarjetaDisponibleService tarjetaService;
	
	@Autowired
	private BSFExceptionErrorHandler manejoDeErrores;
	private LogginWebService loggin;
	
	@Override
	public Cliente getCliente(DocumentationBSF request, UserBSF user) throws KeyStoreException, GeneralSecurityException{
		Link verificacion = user.getOption().equals("correo" )? new Link(user.getOption()) : clienteService.getTokenCuentaOnline(user);
		Cliente cliente;
		if(verificacion.getConnect()) {
			RestTemplate rest = armarConexion("login");
			HttpEntity<DocumentationBSF> entidad = new HttpEntity(request,getHeader(MediaType.APPLICATION_JSON));
			ResponseEntity<Cliente> response = rest.exchange(darUrl("public/datos-filiatorios-cliente"),HttpMethod.POST,entidad,Cliente.class);
			cliente = response.getBody();
			cliente.setTipoDocumento(request.getTipoDocumento());
			cliente.setUrl(verificacion.getUrl());
			if(user.isAdhesion())
				try {
					this.adherirResumen(cliente);
				} catch (Exception e) {
					logger.error("Error al intentar adherir al resumen");
				}
		}else {
			throw new BSFHTTPException(mensaje.ERROR_LOGIN,409);
		}
		return cliente;
		
	}
	@Override
	public List<Summary> getResumen(SummaryBSF request) throws KeyStoreException, GeneralSecurityException{
		List<Summary> lista;
		ListaTarjetas tarjetas = tarjetaService.getTarjetaDisponible(request);
		request.setTarjeta(tarjetas.getMainNumberCard());
		logger.info("Se escoge la tarjeta con número: " + request.getTarjeta());
		RestTemplate rest = armarConexion("resumen");
		HttpEntity<SummaryBSF> entidad = new HttpEntity(request,getHeader(MediaType.APPLICATION_JSON));
		ResponseEntity<ListaResumenes> response = rest.exchange(darUrl("public/resumenes"),HttpMethod.POST,entidad,ListaResumenes.class);
		lista = response.getBody().getListaResumenes();
		return lista;
	}
	
	@Override
	public Descarga descargarResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException {
		Descarga descarga;
		RestTemplate rest = armarConexion("Descargando resumen");
		HttpEntity<SummaryBSF> entidad = new HttpEntity<SummaryBSF>(request,getHeader(MediaType.APPLICATION_JSON));
		ResponseEntity<Descarga> response = rest.exchange(darUrl("public/resumen-tarjeta"),HttpMethod.POST,entidad,Descarga.class);
		descarga = response.getBody();
		if(descarga.getArchivo().isEmpty()) {
			throw new BSFHTTPException(mensaje.ERROR_SIN_INFORMACION,511);
		}
		return descarga;
	}
	
	@Override
	public Registro registrar(UserNewBSF user) throws KeyStoreException, GeneralSecurityException {
		System.out.println("OBJETO: " + user);
		RestTemplate rest = armarConexion("registro");
		HttpEntity<UserNewBSF> entidad = new HttpEntity(user,getHeader(MediaType.APPLICATION_JSON));
		ResponseEntity<Registro> response = rest.exchange(getUrl(),HttpMethod.POST,entidad,Registro.class);
		if(response.getStatusCodeValue() == 409) {
			throw new BSFHTTPException(response.getBody().getMessage(),409);
		}else if(response.getStatusCodeValue() == 200) {
			response.getBody().setMessage(mensaje.REGISTRO_OK);
		}
		return response.getBody();
	}

	@Override
	public Link getTokenCuentaOnline(UserBSF user) throws KeyStoreException, GeneralSecurityException {
		RestTemplate rest = armarConexion("login");
		HttpEntity<UserBSF> entidad = new HttpEntity(user,getHeader(MediaType.APPLICATION_JSON));
		ResponseEntity<Link> response = rest.exchange(darUrl("public/mco-autologin"), HttpMethod.POST,entidad,Link.class);
		return response.getBody();
	}
	
	@Override
	public ListaTarjetas getTarjetasDisponibles(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException {
		ListaTarjetas tarjetas;
		RestTemplate rest = armarConexion("tarjetas");
		HttpEntity<SummaryBSF> entidad = new HttpEntity(request,getHeader(MediaType.APPLICATION_JSON));
		ResponseEntity<ListaTarjetas> response = rest.exchange(darUrl("public/tarjetas-disponibles"), HttpMethod.POST,entidad,ListaTarjetas.class);
		return response.getBody();
	}
	//Method add
	
	private void adherirResumen(Cliente documentacion) throws KeyStoreException, GeneralSecurityException {
		RestTemplate rest = armarConexion("Adhesion Resumen");
		HttpEntity<Cliente> entidad = new HttpEntity(documentacion,getHeader(MediaType.APPLICATION_JSON));
		ResponseEntity<ClientBSF> response = rest.exchange(darUrl("public/documentacion-cliente"), HttpMethod.POST,entidad,ClientBSF.class);
		if(response.getBody().isEnabled()) {
			ESummaryBSF adhesion = new ESummaryBSF(documentacion.getCorreo(),response.getBody());
			rest = armarConexion("Enviando Adhesion");
			HttpEntity<ESummaryBSF> entidadAdhesion = new HttpEntity(adhesion,getHeader(MediaType.APPLICATION_JSON));
			ResponseEntity<ClientBSF> responseAdhesion = rest.exchange(darUrl("/public/adhesion-eresumen"),HttpMethod.POST,entidadAdhesion,ClientBSF.class);
		}
	}
	
	//tools
	
	private HttpHeaders getHeader(MediaType formato)throws BSFHTTPException{
		logger.info("getHeader() Ha comenzado a generar la cabecera");
		HttpHeaders headers = new HttpHeaders();
		headers.add("MCO-API-Key", key);
		headers.setContentType(formato);
		return headers;
	}
	
	private HttpComponentsClientHttpRequestFactory armarContextoSSL() throws GeneralSecurityException, KeyStoreException{
		logger.info("armarContextoSSL() Ha comenzado a generar la certificaciÃ³n del SSL");
		TrustStrategy estrategiaAceptada = (X509Certificate[] chain, String type) -> true;
		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null,estrategiaAceptada).build();
		CloseableHttpClient cliente = HttpClients.custom().setSSLContext(sslContext).build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(cliente);
		logger.info("armarContextoSSL() Ha terminado de generar la certificaciÃ³n del SSL");
		return factory;
	}
	
	private List<ClientHttpRequestInterceptor> armarInterceptor(){
		logger.info("armarInterceptor() Ha comenzado a crear el interceptor");
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		loggin = new LogginWebService();
		interceptors.add(loggin);
		logger.info("armarInterceptor() Ha finalizado de crear el interceptor");
		return interceptors;
	}
	
	private RestTemplate armarConexion(String ventana) throws KeyStoreException, GeneralSecurityException {
		RestTemplate rest = new RestTemplate(new BufferingClientHttpRequestFactory(armarContextoSSL()));
		manejoDeErrores.setOption(ventana);
		rest.setErrorHandler(manejoDeErrores);
		rest.setInterceptors(armarInterceptor());
		logger.info("armarConexion() Ha terminado de crear template de consulta");
		return rest;
	}
	
	private String darUrl(String ubicacion) {
		return String.format("%s/%s", url, ubicacion);
	}
	
	private String getUrl() {
		return urlRegistro;
	}

	


}
