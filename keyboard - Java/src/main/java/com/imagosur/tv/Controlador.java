package com.imagosur.tv;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
	
	private List<KeyboardUI> teclados = new ArrayList();
	
	public Controlador() {
	}
	
	public void setTeclado(KeyboardUI teclado) {
		teclados.add(teclado);
		teclado.setControlador(this);
	}
	
	public void controlarTeclas(int key) {
		for(KeyboardUI teclado : teclados) {
			teclado.actualizar(key);
		}
	}
}
