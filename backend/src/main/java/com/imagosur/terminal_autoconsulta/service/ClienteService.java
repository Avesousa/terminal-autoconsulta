package com.imagosur.terminal_autoconsulta.service;

import java.security.GeneralSecurityException;
import java.security.KeyStoreException;

import com.imagosur.terminal_autoconsulta.model.response.Cliente;
import com.imagosur.terminal_autoconsulta.model.response.Link;
import com.imagosur.terminal_autoconsulta.request.BSF.DocumentationBSF;
import com.imagosur.terminal_autoconsulta.request.BSF.UserBSF;

public interface ClienteService {

	Cliente getCliente(DocumentationBSF request, UserBSF cliente) throws KeyStoreException, GeneralSecurityException;
	Link getTokenCuentaOnline(UserBSF token) throws KeyStoreException, GeneralSecurityException;
	
}
