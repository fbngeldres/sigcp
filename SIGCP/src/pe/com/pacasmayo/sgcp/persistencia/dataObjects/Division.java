package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Division entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Division implements java.io.Serializable {

	// Fields

	private Long pkCodigoDivision;
	private String nombreDivision;
	private String descripcionDivision;
	private String codigoSapDivision;

	public Division() {
		// TODO Auto-generated constructor stub
	}

	public Long getPkCodigoDivision() {
		return pkCodigoDivision;
	}

	public void setPkCodigoDivision(Long pkCodigoDivision) {
		this.pkCodigoDivision = pkCodigoDivision;
	}

	public String getNombreDivision() {
		return nombreDivision;
	}

	public void setNombreDivision(String nombreDivision) {
		this.nombreDivision = nombreDivision;
	}

	public String getDescripcionDivision() {
		return descripcionDivision;
	}

	public void setDescripcionDivision(String descripcionDivision) {
		this.descripcionDivision = descripcionDivision;
	}

	public String getCodigoSapDivision() {
		return codigoSapDivision;
	}

	public void setCodigoSapDivision(String codigoSapDivision) {
		this.codigoSapDivision = codigoSapDivision;
	}

	public Division(Long pkCodigoDivision, String nombreDivision,
			String descripcionDivision, String codigoSapDivision) {
		super();
		this.pkCodigoDivision = pkCodigoDivision;
		this.nombreDivision = nombreDivision;
		this.descripcionDivision = descripcionDivision;
		this.codigoSapDivision = codigoSapDivision;

	}

}