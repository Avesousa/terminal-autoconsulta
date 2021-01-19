package com.imagosur.terminal_autoconsulta.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

import com.sun.mail.util.BASE64DecoderStream;

@Component
public class ManejoArchivo {
	
	@Value("${path.archivo}")
	private String ruta;
	
	private String path;
	private String formato;
	private String fileName;
	
	public void saveFile(String base64, String formato, String fileName) throws UnsupportedEncodingException, IOException{
		this.formato = formato;
		this.fileName = fileName;
		this.path = this.ruta+"\\"+this.fileName+"."+this.formato;
		FileOutputStream file = new FileOutputStream(this.path);
		file.write(Base64.getDecoder().decode(base64.getBytes("UTF-8")));
		file.close();
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
