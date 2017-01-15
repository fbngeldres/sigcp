package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Columnareporte entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Columnareporte implements java.io.Serializable {

	// Fields

	private Long pkCodigoColumnareporte;
	private Estadocolumnareporte estadocolumnareporte;
	private Plantillareporte plantillareporte;
	private String nombreColumnareporte;
	private Short posicionColumnareporte;
	private Set columnaplantillaproductos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Columnareporte() {
	}

	/** minimal constructor */
	public Columnareporte(Estadocolumnareporte estadocolumnareporte, Plantillareporte plantillareporte,
			String nombreColumnareporte, Short posicionColumnareporte) {
		this.estadocolumnareporte = estadocolumnareporte;
		this.plantillareporte = plantillareporte;
		this.nombreColumnareporte = nombreColumnareporte;
		this.posicionColumnareporte = posicionColumnareporte;
	}

	/** full constructor */
	public Columnareporte(Estadocolumnareporte estadocolumnareporte, Plantillareporte plantillareporte,
			String nombreColumnareporte, Short posicionColumnareporte, Set columnaplantillaproductos) {
		this.estadocolumnareporte = estadocolumnareporte;
		this.plantillareporte = plantillareporte;
		this.nombreColumnareporte = nombreColumnareporte;
		this.posicionColumnareporte = posicionColumnareporte;
		this.columnaplantillaproductos = columnaplantillaproductos;
	}

	// Property accessors

	public Long getPkCodigoColumnareporte() {
		return this.pkCodigoColumnareporte;
	}

	public void setPkCodigoColumnareporte(Long pkCodigoColumnareporte) {
		this.pkCodigoColumnareporte = pkCodigoColumnareporte;
	}

	public Estadocolumnareporte getEstadocolumnareporte() {
		return this.estadocolumnareporte;
	}

	public void setEstadocolumnareporte(Estadocolumnareporte estadocolumnareporte) {
		this.estadocolumnareporte = estadocolumnareporte;
	}

	public Plantillareporte getPlantillareporte() {
		return this.plantillareporte;
	}

	public void setPlantillareporte(Plantillareporte plantillareporte) {
		this.plantillareporte = plantillareporte;
	}

	public String getNombreColumnareporte() {
		return this.nombreColumnareporte;
	}

	public void setNombreColumnareporte(String nombreColumnareporte) {
		this.nombreColumnareporte = nombreColumnareporte;
	}

	public Short getPosicionColumnareporte() {
		return this.posicionColumnareporte;
	}

	public void setPosicionColumnareporte(Short posicionColumnareporte) {
		this.posicionColumnareporte = posicionColumnareporte;
	}

	public Set getColumnaplantillaproductos() {
		return this.columnaplantillaproductos;
	}

	public void setColumnaplantillaproductos(Set columnaplantillaproductos) {
		this.columnaplantillaproductos = columnaplantillaproductos;
	}

}