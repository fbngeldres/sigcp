package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Sociedad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Sociedad implements java.io.Serializable {

	// Fields

	private Long pkCodigoSociedad;
	private Division division;
	private String nombreSociedad;
	private String siglasSociedad;
	private String descripcionSociedad;
	private String codigoSapSociedad;

	public Long getPkCodigoSociedad() {
		return pkCodigoSociedad;
	}

	public void setPkCodigoSociedad(Long pkCodigoSociedad) {
		this.pkCodigoSociedad = pkCodigoSociedad;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}



	public String getNombreSociedad() {
		return nombreSociedad;
	}

	public void setNombreSociedad(String nombreSociedad) {
		this.nombreSociedad = nombreSociedad;
	}

	public String getSiglasSociedad() {
		return siglasSociedad;
	}

	public void setSiglasSociedad(String siglasSociedad) {
		this.siglasSociedad = siglasSociedad;
	}

	public String getDescripcionSociedad() {
		return descripcionSociedad;
	}

	public void setDescripcionSociedad(String descripcionSociedad) {
		this.descripcionSociedad = descripcionSociedad;
	}

	public String getCodigoSapSociedad() {
		return codigoSapSociedad;
	}

	public void setCodigoSapSociedad(String codigoSapSociedad) {
		this.codigoSapSociedad = codigoSapSociedad;
	}


	public Sociedad() {
		// TODO Auto-generated constructor stub
	}

	public Sociedad(Long pkCodigoSociedad, Division division,
			String nombreSociedad, String siglasSociedad,
			String descripcionSociedad, String codigoSapSociedad) {
		super();
		this.pkCodigoSociedad = pkCodigoSociedad;
		this.division = division;
		this.nombreSociedad = nombreSociedad;
		this.siglasSociedad = siglasSociedad;
		this.descripcionSociedad = descripcionSociedad;
		this.codigoSapSociedad = codigoSapSociedad;
	}
}