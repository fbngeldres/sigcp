package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Factordosificacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Factordosificacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoFactordosificacion;
	private Unidadmedida unidadmedida;
	private Componente componente;
	private Hojaruta hojaruta;
	private Set dosificacions = new HashSet(0);
	private Set factordosificacionregistromensus = new HashSet(0);

	// Constructors

	/** default constructor */
	public Factordosificacion() {
	}

	/** minimal constructor */
	public Factordosificacion(Unidadmedida unidadmedida, Componente componente, Hojaruta hojaruta) {
		this.unidadmedida = unidadmedida;
		this.componente = componente;
		this.hojaruta = hojaruta;
	}

	/** full constructor */
	public Factordosificacion(Unidadmedida unidadmedida, Componente componente, Hojaruta hojaruta, Set dosificacions,
			Set factordosificacionregistromensus) {
		this.unidadmedida = unidadmedida;
		this.componente = componente;
		this.hojaruta = hojaruta;
		this.dosificacions = dosificacions;
		this.factordosificacionregistromensus = factordosificacionregistromensus;
	}

	// Property accessors

	public Long getPkCodigoFactordosificacion() {
		return this.pkCodigoFactordosificacion;
	}

	public void setPkCodigoFactordosificacion(Long pkCodigoFactordosificacion) {
		this.pkCodigoFactordosificacion = pkCodigoFactordosificacion;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public Hojaruta getHojaruta() {
		return this.hojaruta;
	}

	public void setHojaruta(Hojaruta hojaruta) {
		this.hojaruta = hojaruta;
	}

	public Set getDosificacions() {
		return this.dosificacions;
	}

	public void setDosificacions(Set dosificacions) {
		this.dosificacions = dosificacions;
	}

	public Set getFactordosificacionregistromensus() {
		return this.factordosificacionregistromensus;
	}

	public void setFactordosificacionregistromensus(Set factordosificacionregistromensus) {
		this.factordosificacionregistromensus = factordosificacionregistromensus;
	}

}