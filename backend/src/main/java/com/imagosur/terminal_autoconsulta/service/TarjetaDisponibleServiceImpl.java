package com.imagosur.terminal_autoconsulta.service;

import java.security.GeneralSecurityException;
import java.security.KeyStoreException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.model.response.ListaTarjetas;
import com.imagosur.terminal_autoconsulta.request.BSF.SummaryBSF;
import com.imagosur.terminal_autoconsulta.service.ws.BSFServiceWebService;

@Service
public class TarjetaDisponibleServiceImpl implements TarjetaDisponibleService{

	@Autowired
	private BSFServiceWebService webService;

	@Override
	public ListaTarjetas getTarjetaDisponible(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException {
		return webService.getTarjetasDisponibles(request);
	}

	
}
