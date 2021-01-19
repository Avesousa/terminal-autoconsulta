package com.imagosur.terminal_autoconsulta.service;

import java.security.GeneralSecurityException;
import java.security.KeyStoreException;

import com.imagosur.terminal_autoconsulta.exception.BSFHTTPException;
import com.imagosur.terminal_autoconsulta.model.response.ListaTarjetas;
import com.imagosur.terminal_autoconsulta.request.BSF.SummaryBSF;

public interface TarjetaDisponibleService {

	ListaTarjetas getTarjetaDisponible(SummaryBSF request) throws BSFHTTPException, KeyStoreException, GeneralSecurityException;
	
}
