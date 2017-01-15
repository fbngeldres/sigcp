package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Almacen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Almacen implements java.io.Serializable {

	// Fields

	private Long pkCodigoAlmacen;
	private Unidad unidad;
	private String codigoSapAlmacen;
	private String nombreAlmacen;
	private String descripcionAlmacen;
	private Set ubicacions = new HashSet(0);
	private Set tablakardexes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Almacen() {
	}

	/** minimal constructor */
	public Almacen(Unidad unidad, String nombreAlmacen) {
		this.unidad = unidad;
		this.nombreAlmacen = nombreAlmacen;
	}

	/** full constructor */
	public Almacen(Unidad unidad, String codigoSapAlmacen, String nombreAlmacen, String descripcionAlmacen, Set ubicacions,
			Set tablakardexes) {
		this.unidad = unidad;
		this.codigoSapAlmacen = codigoSapAlmacen;
		this.nombreAlmacen = nombreAlmacen;
		this.descripcionAlmacen = descripcionAlmacen;
		this.ubicacions = ubicacions;
		this.tablakardexes = tablakardexes;
	}

	// Property accessors

	public Long getPkCodigoAlmacen() {
		return this.pkCodigoAlmacen;
	}

	public void setPkCodigoAlmacen(Long pkCodigoAlmacen) {
		this.pkCodigoAlmacen = pkCodigoAlmacen;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public String getCodigoSapAlmacen() {
		return this.codigoSapAlmacen;
	}

	public void setCodigoSapAlmacen(String codigoSapAlmacen) {
		this.codigoSapAlmacen = codigoSapAlmacen;
	}

	public String getNombreAlmacen() {
		return this.nombreAlmacen;
	}

	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}

	public String getDescripcionAlmacen() {
		return this.descripcionAlmacen;
	}

	public void setDescripcionAlmacen(String descripcionAlmacen) {
		this.descripcionAlmacen = descripcionAlmacen;
	}

	public Set getUbicacions() {
		return this.ubicacions;
	}

	public void setUbicacions(Set ubicacions) {
		this.ubicacions = ubicacions;
	}

	public Set getTablakardexes() {
		return this.tablakardexes;
	}

	public void setTablakardexes(Set tablakardexes) {
		this.tablakardexes = tablakardexes;
	}

}