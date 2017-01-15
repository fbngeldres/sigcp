package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Recurso entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Recurso implements java.io.Serializable {

	// Fields

	private Long pkCodigoRecurso;
	private Unidadmedida unidadmedida;
	private String nombreRecurso;
	private Set recursoregistromensuals = new HashSet(0);
	private Set consumorecursomanuals = new HashSet(0);
	private Set operacionrecursos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Recurso() {
	}

	/** minimal constructor */
	public Recurso(Unidadmedida unidadmedida, String nombreRecurso) {
		this.unidadmedida = unidadmedida;
		this.nombreRecurso = nombreRecurso;
	}

	/** full constructor */
	public Recurso(Unidadmedida unidadmedida, String nombreRecurso, Set recursoregistromensuals, Set consumorecursomanuals,
			Set operacionrecursos) {
		this.unidadmedida = unidadmedida;
		this.nombreRecurso = nombreRecurso;
		this.recursoregistromensuals = recursoregistromensuals;
		this.consumorecursomanuals = consumorecursomanuals;
		this.operacionrecursos = operacionrecursos;
	}

	// Property accessors

	public Long getPkCodigoRecurso() {
		return this.pkCodigoRecurso;
	}

	public void setPkCodigoRecurso(Long pkCodigoRecurso) {
		this.pkCodigoRecurso = pkCodigoRecurso;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public String getNombreRecurso() {
		return this.nombreRecurso;
	}

	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}

	public Set getRecursoregistromensuals() {
		return this.recursoregistromensuals;
	}

	public void setRecursoregistromensuals(Set recursoregistromensuals) {
		this.recursoregistromensuals = recursoregistromensuals;
	}

	public Set getConsumorecursomanuals() {
		return this.consumorecursomanuals;
	}

	public void setConsumorecursomanuals(Set consumorecursomanuals) {
		this.consumorecursomanuals = consumorecursomanuals;
	}

	public Set getOperacionrecursos() {
		return this.operacionrecursos;
	}

	public void setOperacionrecursos(Set operacionrecursos) {
		this.operacionrecursos = operacionrecursos;
	}

}