package com.imagosur.terminal_autoconsulta.service;

import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.util.List;

import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.model.response.*;
import com.imagosur.terminal_autoconsulta.request.BSF.*;
import com.imagosur.terminal_autoconsulta.service.ws.BSFExceptionErrorHandler;

public interface ResumenService {
	
	List<Summary> getResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
	Descarga descargarResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
}
