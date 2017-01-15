package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipobalance entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipoconsumo implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipoconsumo;
	private String nombreTipoconsumo;
	private Set productos = new HashSet(0);
	
	public Tipoconsumo() {
		
	}

	public Tipoconsumo(Long pkCodigoTipoconsumo, String nombreTipoconsumo, Set productos) {
		super();
		this.pkCodigoTipoconsumo = pkCodigoTipoconsumo;
		this.nombreTipoconsumo = nombreTipoconsumo;
		this.productos = productos;
	}

	public Long getPkCodigoTipoconsumo() {
		return pkCodigoTipoconsumo;
	}

	public void setPkCodigoTipoconsumo(Long pkCodigoTipoconsumo) {
		this.pkCodigoTipoconsumo = pkCodigoTipoconsumo;
	}

	public String getNombreTipoconsumo() {
		return nombreTipoconsumo;
	}

	public void setNombreTipoconsumo(String nombreTipoconsumo) {
		this.nombreTipoconsumo = nombreTipoconsumo;
	}

	public Set getProductos() {
		return productos;
	}

	public void setProductos(Set productos) {
		this.productos = productos;
	}

}