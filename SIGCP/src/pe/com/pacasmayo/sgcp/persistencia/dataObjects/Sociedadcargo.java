package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Sociedadcargo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Sociedadcargo implements java.io.Serializable {

	// Fields

	private Long pkCodigoSociedadcargo;
	private Cargo cargo;
	private Sociedad sociedad;

	// Constructors

	/** default constructor */
	public Sociedadcargo() {
	}

	/** full constructor */
	public Sociedadcargo(Cargo cargo, Sociedad sociedad) {
		this.cargo = cargo;
		this.sociedad = sociedad;
	}

	// Property accessors

	public Long getPkCodigoSociedadcargo() {
		return this.pkCodigoSociedadcargo;
	}

	public void setPkCodigoSociedadcargo(Long pkCodigoSociedadcargo) {
		this.pkCodigoSociedadcargo = pkCodigoSociedadcargo;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Sociedad getSociedad() {
		return this.sociedad;
	}

	public void setSociedad(Sociedad sociedad) {
		this.sociedad = sociedad;
	}

}