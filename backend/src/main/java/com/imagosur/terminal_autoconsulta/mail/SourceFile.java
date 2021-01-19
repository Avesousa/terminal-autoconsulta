package com.imagosur.terminal_autoconsulta.mail;

public class SourceFile {

	private String path;
	private String name;
	private String type;
	
	public SourceFile(String path, String name, String type) {
		super();
		this.path = path;
		this.name = name + "." + type;
		this.type = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
