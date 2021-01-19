package com.imagosur.terminal_autoconsulta.model;

import java.util.ArrayList;
import java.util.List;

public class ListInfoSummaryFront {
	
	private List<InfoSummaryFront> lista = new ArrayList<InfoSummaryFront>();
	public void setSummary(InfoSummaryFront summary) {
		lista.add(summary);
	}
	public List<InfoSummaryFront> getList() {
		return lista;
	}
	@Override
	public String toString() {
		return "ListInfoSummaryFront [lista=" + lista + "]";
	}
}
