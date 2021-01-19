package com.imagosur.terminal_autoconsulta.service;

import java.security.GeneralSecurityException;
import java.security.KeyStoreException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.model.response.Cliente;
import com.imagosur.terminal_autoconsulta.model.response.Link;
import com.imagosur.terminal_autoconsulta.request.BSF.DocumentationBSF;
import com.imagosur.terminal_autoconsulta.request.BSF.UserBSF;
import com.imagosur.terminal_autoconsulta.service.ws.BSFServiceWebService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private BSFServiceWebService webService;
	

	@Override
	public Link getTokenCuentaOnline(UserBSF token) throws KeyStoreException, GeneralSecurityException{
		return webService.getTokenCuentaOnline(token);
	}

	@Override
	public Cliente getCliente(DocumentationBSF request, UserBSF user) throws BSFHTTPException, KeyStoreException, GeneralSecurityException {
		Cliente cliente = webService.getCliente(request, user);
		cliente.mainCorreo();
		return cliente;
	}

}
