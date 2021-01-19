package com.imagosur.terminal_autoconsulta.utils;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.imagosur.terminal_autoconsulta.model.*;
import com.imagosur.terminal_autoconsulta.model.response.*;
import com.imagosur.terminal_autoconsulta.request.BSF.*;
import com.imagosur.terminal_autoconsulta.request.front.*;

public class Mapeo {
	
	//Request
	
	public static DocumentationBSF getDoc(UserFront user) {
		DocumentationBSF doc = map().map(user,DocumentationBSF.class);
		doc.setTipoDocumento(user.getTipoDocumento());
		return doc;
	}
	
	public static UserBSF getUser(UserFront user) {
		UserBSF us = map().map(user,UserBSF.class);
		us.setIdTipoDocumento(user.getTipoDocumento());
		us.setAdhesion(user.isAdhesion());
		return us;	
	}
	
	public static SummaryBSF getSummary(SummaryFront summary) {
		SummaryBSF summaryBSF = map().map(summary,SummaryBSF.class);
		summaryBSF.setNumerodoc(summary.getDocumento());
		summaryBSF.setTipodoc(summary.getTipoDocumento());
		System.out.println(summaryBSF);
		return summaryBSF;
	}
	
	//Response
	
	public static ClienteFront getCliente(Cliente cliente) {
		ClienteFront clienteFront = map().map(cliente,ClienteFront.class);
		clienteFront.setDni(cliente.getNumeroDocumento());
		clienteFront.parsearFecha(cliente.getFechaNacimiento());
		System.out.println(clienteFront);
		return clienteFront;
	}
	
	public static ListInfoSummaryFront getInfoSummary(List<Summary> resumenes){
		ListInfoSummaryFront lista = new ListInfoSummaryFront();
		for(Summary resumen: resumenes) {
			lista.setSummary(map().map(resumen,InfoSummaryFront.class));
		}
		System.out.println(lista);
		return lista;
	}
	
	
	//Utils
	private static ModelMapper map() {
		ModelMapper mapper = new ModelMapper();
		//mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}

}
