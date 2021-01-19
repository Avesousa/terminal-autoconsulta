package com.imagosur.terminal_autoconsulta.service;

import java.security.GeneralSecurityException;
import java.security.KeyStoreException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.model.*;
import com.imagosur.terminal_autoconsulta.model.response.*;
import com.imagosur.terminal_autoconsulta.request.BSF.*;
import com.imagosur.terminal_autoconsulta.service.ws.BSFServiceWebService;
import com.imagosur.terminal_autoconsulta.utils.Mapeo;

@Service
public class BSFServiceImpl implements BSFService{
	
	@Autowired
	private ResumenService resumenService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	BSFServiceWebService webService;

	@Override
	public ClienteFront getCliente(DocumentationBSF doc, UserBSF cliente) throws KeyStoreException, GeneralSecurityException{
		return Mapeo.getCliente(clienteService.getCliente(doc,cliente));
	}
	
	@Override
	public ListInfoSummaryFront getResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException{
		return Mapeo.getInfoSummary(resumenService.getResumen(request));
	}

	@Override
	public Descarga descargarResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException {
		return resumenService.descargarResumen(request);
	}
	@Override
	public Link getConnection(UserBSF cliente) throws KeyStoreException, GeneralSecurityException {
		return clienteService.getTokenCuentaOnline(cliente);
	}

	@Override
	public Registro registrar(UserNewBSF request) throws KeyStoreException, GeneralSecurityException {
		return webService.registrar(request);
	}
	
	
	
	
	
	
}
