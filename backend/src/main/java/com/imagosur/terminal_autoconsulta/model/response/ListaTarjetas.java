package com.imagosur.terminal_autoconsulta.model.response;

import java.util.List;

public class ListaTarjetas {
	
	private List<TarjetaDisponible> listaTarjetas;

	public List<TarjetaDisponible> getListaTarjetas() {
		return listaTarjetas;
	}

	public void setListaTarjetas(List<TarjetaDisponible> listaTarjetas) {
		this.listaTarjetas = listaTarjetas;
	}
	
	public 	String getMainNumberCard() {
		return this.listaTarjetas.get(0).getCard();
	}
	
	public String getNumberCard(int index) {
		return this.listaTarjetas.get(index).getCard();
	}
	
	public TarjetaDisponible getMainCard() {
		return this.listaTarjetas.get(0);
	}
	
	public TarjetaDisponible getCard(int index) {
		return this.listaTarjetas.get(index);
	}

	@Override
	public String toString() {
		return "ListaTarjetas [listaTarjetas=" + listaTarjetas + "]";
	}
	
}
