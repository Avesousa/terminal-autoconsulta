package com.imagosur.terminal_autoconsulta.exception;

public class BSFHTTPException extends RuntimeException{

	private int numeroDeError;
	
	public BSFHTTPException(){
		super();
	}
	
	public BSFHTTPException(String message,int error) {
		super(message);
		this.numeroDeError = error;
	}

	public int getNumeroDeError() {
		return this.numeroDeError;
	}

	public void setError(int error) {
		this.numeroDeError = error;
	}
	
	
	
}

