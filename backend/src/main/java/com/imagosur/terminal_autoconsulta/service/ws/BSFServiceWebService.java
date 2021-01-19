package com.imagosur.terminal_autoconsulta.service.ws;

import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.util.List;


import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.model.response.*;
import com.imagosur.terminal_autoconsulta.request.BSF.*;

public interface BSFServiceWebService {
	
	ListaTarjetas getTarjetasDisponibles(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
	List<Summary> getResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
	Cliente getCliente(DocumentationBSF request, UserBSF cliente) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
	Link getTokenCuentaOnline(UserBSF token) throws KeyStoreException, GeneralSecurityException;
	Descarga descargarResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
	Registro registrar(UserNewBSF user) throws KeyStoreException, GeneralSecurityException;
}
