package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Opcionaccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Opcionaccion implements java.io.Serializable {

	// Fields

	private Long pkCodigoOpcionaccion;
	private Opcion opcion;
	private Accion accion;

	// Constructors

	/** default constructor */
	public Opcionaccion() {
	}

	/** full constructor */
	public Opcionaccion(Opcion opcion, Accion accion) {
		this.opcion = opcion;
		this.accion = accion;
	}

	// Property accessors

	public Long getPkCodigoOpcionaccion() {
		return this.pkCodigoOpcionaccion;
	}

	public void setPkCodigoOpcionaccion(Long pkCodigoOpcionaccion) {
		this.pkCodigoOpcionaccion = pkCodigoOpcionaccion;
	}

	public Opcion getOpcion() {
		return this.opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public Accion getAccion() {
		return this.accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

}