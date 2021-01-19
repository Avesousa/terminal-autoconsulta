package com.imagosur.terminal_autoconsulta.service;

import java.security.GeneralSecurityException;
import java.security.KeyStoreException;

import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.model.*;
import com.imagosur.terminal_autoconsulta.model.response.*;
import com.imagosur.terminal_autoconsulta.request.BSF.*;

public interface BSFService {
	
	ClienteFront getCliente(DocumentationBSF doc,UserBSF client) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
	Link getConnection(UserBSF cliente) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
	ListInfoSummaryFront getResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
	Descarga descargarResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
	Registro registrar(UserNewBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
}
