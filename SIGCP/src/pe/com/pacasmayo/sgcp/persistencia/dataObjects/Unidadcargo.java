package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Unidadcargo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Unidadcargo implements java.io.Serializable {

	// Fields

	private Long pkCodigoUnidadcargo;
	private Cargo cargo;
	private Unidad unidad;

	// Constructors

	/** default constructor */
	public Unidadcargo() {
	}

	/** full constructor */
	public Unidadcargo(Cargo cargo, Unidad unidad) {
		this.cargo = cargo;
		this.unidad = unidad;
	}

	// Property accessors

	public Long getPkCodigoUnidadcargo() {
		return this.pkCodigoUnidadcargo;
	}

	public void setPkCodigoUnidadcargo(Long pkCodigoUnidadcargo) {
		this.pkCodigoUnidadcargo = pkCodigoUnidadcargo;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

}