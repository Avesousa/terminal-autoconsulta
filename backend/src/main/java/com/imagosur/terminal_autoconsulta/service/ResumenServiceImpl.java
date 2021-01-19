package com.imagosur.terminal_autoconsulta.service;

import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.model.response.Descarga;
import com.imagosur.terminal_autoconsulta.model.response.ESummary;
import com.imagosur.terminal_autoconsulta.model.response.Summary;
import com.imagosur.terminal_autoconsulta.request.BSF.ESummaryBSF;
import com.imagosur.terminal_autoconsulta.request.BSF.SummaryBSF;
import com.imagosur.terminal_autoconsulta.service.ws.BSFServiceWebService;

@Service
public class ResumenServiceImpl implements ResumenService {

	@Autowired
	BSFServiceWebService webService;
	

	@Override
	public List<Summary> getResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException {
		return webService.getResumen(request);
	}
	
	@Override
	public Descarga descargarResumen(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException {
		return webService.descargarResumen(request);
	}

}
