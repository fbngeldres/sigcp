package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Operacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Operacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoOperacion;
	private Actividad actividad;
	private Puestotrabajo puestotrabajo;
	private Hojaruta hojaruta;
	private String nombreOperacion;
	private Long ordenEjecucionOperacion;
	private Set recursoregistromensuals = new HashSet(0);
	private Set capacidadoperativas = new HashSet(0);
	private Set operacionrecursos = new HashSet(0);
	private Set operacioncomponentes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Operacion() {
	}

	/** minimal constructor */
	public Operacion(Actividad actividad, Puestotrabajo puestotrabajo, Hojaruta hojaruta, String nombreOperacion) {
		this.actividad = actividad;
		this.puestotrabajo = puestotrabajo;
		this.hojaruta = hojaruta;
		this.nombreOperacion = nombreOperacion;
	}

	/** full constructor */
	public Operacion(Actividad actividad, Puestotrabajo puestotrabajo, Hojaruta hojaruta, String nombreOperacion,
			Long ordenEjecucionOperacion, Set recursoregistromensuals, Set capacidadoperativas, Set operacionrecursos,
			Set operacioncomponentes) {
		this.actividad = actividad;
		this.puestotrabajo = puestotrabajo;
		this.hojaruta = hojaruta;
		this.nombreOperacion = nombreOperacion;
		this.ordenEjecucionOperacion = ordenEjecucionOperacion;
		this.recursoregistromensuals = recursoregistromensuals;
		this.capacidadoperativas = capacidadoperativas;
		this.operacionrecursos = operacionrecursos;
		this.operacioncomponentes = operacioncomponentes;
	}

	// Property accessors

	public Long getPkCodigoOperacion() {
		return this.pkCodigoOperacion;
	}

	public void setPkCodigoOperacion(Long pkCodigoOperacion) {
		this.pkCodigoOperacion = pkCodigoOperacion;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Puestotrabajo getPuestotrabajo() {
		return this.puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public Hojaruta getHojaruta() {
		return this.hojaruta;
	}

	public void setHojaruta(Hojaruta hojaruta) {
		this.hojaruta = hojaruta;
	}

	public String getNombreOperacion() {
		return this.nombreOperacion;
	}

	public void setNombreOperacion(String nombreOperacion) {
		this.nombreOperacion = nombreOperacion;
	}

	public Long getOrdenEjecucionOperacion() {
		return this.ordenEjecucionOperacion;
	}

	public void setOrdenEjecucionOperacion(Long ordenEjecucionOperacion) {
		this.ordenEjecucionOperacion = ordenEjecucionOperacion;
	}

	public Set getRecursoregistromensuals() {
		return this.recursoregistromensuals;
	}

	public void setRecursoregistromensuals(Set recursoregistromensuals) {
		this.recursoregistromensuals = recursoregistromensuals;
	}

	public Set getCapacidadoperativas() {
		return this.capacidadoperativas;
	}

	public void setCapacidadoperativas(Set capacidadoperativas) {
		this.capacidadoperativas = capacidadoperativas;
	}

	public Set getOperacionrecursos() {
		return this.operacionrecursos;
	}

	public void setOperacionrecursos(Set operacionrecursos) {
		this.operacionrecursos = operacionrecursos;
	}

	public Set getOperacioncomponentes() {
		return this.operacioncomponentes;
	}

	public void setOperacioncomponentes(Set operacioncomponentes) {
		this.operacioncomponentes = operacioncomponentes;
	}

}