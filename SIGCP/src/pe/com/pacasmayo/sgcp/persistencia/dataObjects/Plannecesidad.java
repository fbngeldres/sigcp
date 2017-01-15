package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Plannecesidad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Plannecesidad implements java.io.Serializable {

	// Fields

	private Long pkCodigoPlannecesidad;
	private Plananual plananual;
	private Hojaruta hojaruta;
	private Set conceptoregistromensuals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Plannecesidad() {
	}

	/** minimal constructor */
	public Plannecesidad(Plananual plananual, Hojaruta hojaruta) {
		this.plananual = plananual;
		this.hojaruta = hojaruta;
	}

	/** full constructor */
	public Plannecesidad(Plananual plananual, Hojaruta hojaruta, Set conceptoregistromensuals) {
		this.plananual = plananual;
		this.hojaruta = hojaruta;
		this.conceptoregistromensuals = conceptoregistromensuals;
	}

	// Property accessors

	public Long getPkCodigoPlannecesidad() {
		return this.pkCodigoPlannecesidad;
	}

	public void setPkCodigoPlannecesidad(Long pkCodigoPlannecesidad) {
		this.pkCodigoPlannecesidad = pkCodigoPlannecesidad;
	}

	public Plananual getPlananual() {
		return this.plananual;
	}

	public void setPlananual(Plananual plananual) {
		this.plananual = plananual;
	}

	public Hojaruta getHojaruta() {
		return this.hojaruta;
	}

	public void setHojaruta(Hojaruta hojaruta) {
		this.hojaruta = hojaruta;
	}

	public Set getConceptoregistromensuals() {
		return this.conceptoregistromensuals;
	}

	public void setConceptoregistromensuals(Set conceptoregistromensuals) {
		this.conceptoregistromensuals = conceptoregistromensuals;
	}

}