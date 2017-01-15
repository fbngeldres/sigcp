package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipoproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipoproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipoproducto;
	private String nombreTipoproducto;
	private String siglasTipoproducto;
	private Set productos = new HashSet(0);
	private Set procesos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipoproducto() {
	}

	/** minimal constructor */
	public Tipoproducto(String nombreTipoproducto, String siglasTipoproducto) {
		this.nombreTipoproducto = nombreTipoproducto;
		this.siglasTipoproducto = siglasTipoproducto;
	}

	/** full constructor */
	public Tipoproducto(String nombreTipoproducto, String siglasTipoproducto, Set productos, Set procesos) {
		this.nombreTipoproducto = nombreTipoproducto;
		this.siglasTipoproducto = siglasTipoproducto;
		this.productos = productos;
		this.procesos = procesos;
	}

	// Property accessors

	public Long getPkCodigoTipoproducto() {
		return this.pkCodigoTipoproducto;
	}

	public void setPkCodigoTipoproducto(Long pkCodigoTipoproducto) {
		this.pkCodigoTipoproducto = pkCodigoTipoproducto;
	}

	public String getNombreTipoproducto() {
		return this.nombreTipoproducto;
	}

	public void setNombreTipoproducto(String nombreTipoproducto) {
		this.nombreTipoproducto = nombreTipoproducto;
	}

	public String getSiglasTipoproducto() {
		return this.siglasTipoproducto;
	}

	public void setSiglasTipoproducto(String siglasTipoproducto) {
		this.siglasTipoproducto = siglasTipoproducto;
	}

	public Set getProductos() {
		return this.productos;
	}

	public void setProductos(Set productos) {
		this.productos = productos;
	}

	public Set getProcesos() {
		return this.procesos;
	}

	public void setProcesos(Set procesos) {
		this.procesos = procesos;
	}

}