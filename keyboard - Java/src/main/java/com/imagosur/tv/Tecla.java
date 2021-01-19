package com.imagosur.tv;

import javax.swing.JLabel;

public class Tecla {
	
	private JLabel label;
	private String text;
	private String textAlt;
	private String name;
	private int heigth;
	private int width;
	private int widthMax;
	
	public int getWidthMax() {
		return widthMax;
	}

	public void setWidthMax(int widthMax) {
		this.widthMax = widthMax;
	}

	public Tecla(JLabel label, String text, String name) {
		super();
		this.label = label;
		this.text = text;
		this.name = name;
		this.heigth = 50;
		this.width = 55;
		this.widthMax = 100;
		this.textAlt = text;
	}
	
	public Tecla(JLabel label, String text, String name, String textAlt) {
		super();
		this.label = label;
		this.text = text;
		this.name = name;
		this.heigth = 50;
		this.width = 55;
		this.widthMax = 100;
		this.textAlt = textAlt;
	}
	
	
	public Tecla(JLabel label, String text, String name, int width, int heigth, int widthMax) {
		super();
		this.label = label;
		this.name = name;
		this.text = text;
		this.heigth= heigth+10;
		this.width= width+10;
		this.widthMax = widthMax+10;
		this.textAlt = text;
	}
	
	public Tecla(JLabel label, String text, String name, int width, int heigth, int widthMax, String textAlt) {
		super();
		this.label = label;
		this.name = name;
		this.text = text;
		this.heigth= heigth;
		this.width= width+10;
		this.widthMax = widthMax+10;
		this.textAlt = textAlt;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getTextAlt() {
		return textAlt;
	}

	public void setTextAlt(String textAlt) {
		this.textAlt = textAlt;
	}
}
