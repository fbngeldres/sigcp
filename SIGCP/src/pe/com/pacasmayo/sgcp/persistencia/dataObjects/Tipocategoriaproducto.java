package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipocategoriaproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipocategoriaproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipocategoriaproducto;
	private String nombreTipocategoriaproducto;
	private Set productos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipocategoriaproducto() {
	}

	/** minimal constructor */
	public Tipocategoriaproducto(String nombreTipocategoriaproducto) {
		this.nombreTipocategoriaproducto = nombreTipocategoriaproducto;
	}

	/** full constructor */
	public Tipocategoriaproducto(String nombreTipocategoriaproducto, Set productos) {
		this.nombreTipocategoriaproducto = nombreTipocategoriaproducto;
		this.productos = productos;
	}

	// Property accessors

	public Long getPkCodigoTipocategoriaproducto() {
		return this.pkCodigoTipocategoriaproducto;
	}

	public void setPkCodigoTipocategoriaproducto(Long pkCodigoTipocategoriaproducto) {
		this.pkCodigoTipocategoriaproducto = pkCodigoTipocategoriaproducto;
	}

	public String getNombreTipocategoriaproducto() {
		return this.nombreTipocategoriaproducto;
	}

	public void setNombreTipocategoriaproducto(String nombreTipocategoriaproducto) {
		this.nombreTipocategoriaproducto = nombreTipocategoriaproducto;
	}

	public Set getProductos() {
		return this.productos;
	}

	public void setProductos(Set productos) {
		this.productos = productos;
	}

}