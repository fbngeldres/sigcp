package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Lineanegocio entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lineanegocio implements java.io.Serializable {

	// Fields

	private Long pkCodigoLineanegocio;
	private Unidad unidad;
	private String nombreLineanegocio;
	private String descripcionLineanegocio;
	private String codigoSapLineanegocio;

	

	public Lineanegocio() {
		// TODO Auto-generated constructor stub
	}

	public Long getPkCodigoLineanegocio() {
		return pkCodigoLineanegocio;
	}

	public void setPkCodigoLineanegocio(Long pkCodigoLineanegocio) {
		this.pkCodigoLineanegocio = pkCodigoLineanegocio;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public String getNombreLineanegocio() {
		return nombreLineanegocio;
	}

	public void setNombreLineanegocio(String nombreLineanegocio) {
		this.nombreLineanegocio = nombreLineanegocio;
	}

	public String getDescripcionLineanegocio() {
		return descripcionLineanegocio;
	}

	public void setDescripcionLineanegocio(String descripcionLineanegocio) {
		this.descripcionLineanegocio = descripcionLineanegocio;
	}

	public String getCodigoSapLineanegocio() {
		return codigoSapLineanegocio;
	}

	public void setCodigoSapLineanegocio(String codigoSapLineanegocio) {
		this.codigoSapLineanegocio = codigoSapLineanegocio;
	}

	

	public Lineanegocio(Long pkCodigoLineanegocio, Unidad unidad,
			String nombreLineanegocio, String descripcionLineanegocio,
			String codigoSapLineanegocio) {
		super();
		this.pkCodigoLineanegocio = pkCodigoLineanegocio;
		this.unidad = unidad;
		this.nombreLineanegocio = nombreLineanegocio;
		this.descripcionLineanegocio = descripcionLineanegocio;
		this.codigoSapLineanegocio = codigoSapLineanegocio;
	
	}

}