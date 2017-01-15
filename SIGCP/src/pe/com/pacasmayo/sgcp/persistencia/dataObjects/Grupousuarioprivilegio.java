package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Grupousuarioprivilegio entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Grupousuarioprivilegio implements java.io.Serializable {

	// Fields

	private Long pkCodigoGrupousuarioprivilegi;
	private Privilegio privilegio;
	private Grupousuario grupousuario;

	// Constructors

	/** default constructor */
	public Grupousuarioprivilegio() {
	}

	/** full constructor */
	public Grupousuarioprivilegio(Privilegio privilegio, Grupousuario grupousuario) {
		this.privilegio = privilegio;
		this.grupousuario = grupousuario;
	}

	// Property accessors

	public Long getPkCodigoGrupousuarioprivilegi() {
		return this.pkCodigoGrupousuarioprivilegi;
	}

	public void setPkCodigoGrupousuarioprivilegi(Long pkCodigoGrupousuarioprivilegi) {
		this.pkCodigoGrupousuarioprivilegi = pkCodigoGrupousuarioprivilegi;
	}

	public Privilegio getPrivilegio() {
		return this.privilegio;
	}

	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
	}

	public Grupousuario getGrupousuario() {
		return this.grupousuario;
	}

	public void setGrupousuario(Grupousuario grupousuario) {
		this.grupousuario = grupousuario;
	}

}