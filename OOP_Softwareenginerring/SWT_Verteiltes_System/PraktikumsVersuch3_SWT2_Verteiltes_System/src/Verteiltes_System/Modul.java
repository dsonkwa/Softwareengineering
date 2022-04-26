package Verteiltes_System;

import java.io.Serializable;

public class Modul implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1569244556157394905L;
	private String key;
	private String bezeichnung;
	
	public Modul(String key, String bezeichnung) {
		this.key= key;
		this.bezeichnung= bezeichnung;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	@Override
	public String toString() {
		return " Modul [key=" + key + ", bezeichnung=" + bezeichnung + "]\n";
	}
	

}
