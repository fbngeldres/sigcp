package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Operacionrecurso entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Operacionrecurso implements java.io.Serializable {

	// Fields

	private Long pkCodigoOperacionrecurso;
	private Recurso recurso;
	private Operacion operacion;

	// Constructors

	/** default constructor */
	public Operacionrecurso() {
	}

	/** full constructor */
	public Operacionrecurso(Recurso recurso, Operacion operacion) {
		this.recurso = recurso;
		this.operacion = operacion;
	}

	// Property accessors

	public Long getPkCodigoOperacionrecurso() {
		return this.pkCodigoOperacionrecurso;
	}

	public void setPkCodigoOperacionrecurso(Long pkCodigoOperacionrecurso) {
		this.pkCodigoOperacionrecurso = pkCodigoOperacionrecurso;
	}

	public Recurso getRecurso() {
		return this.recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public Operacion getOperacion() {
		return this.operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

}