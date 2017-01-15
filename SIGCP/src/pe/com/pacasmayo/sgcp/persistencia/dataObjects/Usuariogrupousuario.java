package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Usuariogrupousuario entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Usuariogrupousuario implements java.io.Serializable {

	// Fields

	private Long pkCodigoUsuariogrupousuario;
	private Usuario usuario;
	private Grupousuario grupousuario;

	// Constructors

	/** default constructor */
	public Usuariogrupousuario() {
	}

	/** full constructor */
	public Usuariogrupousuario(Usuario usuario, Grupousuario grupousuario) {
		this.usuario = usuario;
		this.grupousuario = grupousuario;
	}

	// Property accessors

	public Long getPkCodigoUsuariogrupousuario() {
		return this.pkCodigoUsuariogrupousuario;
	}

	public void setPkCodigoUsuariogrupousuario(Long pkCodigoUsuariogrupousuario) {
		this.pkCodigoUsuariogrupousuario = pkCodigoUsuariogrupousuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Grupousuario getGrupousuario() {
		return this.grupousuario;
	}

	public void setGrupousuario(Grupousuario grupousuario) {
		this.grupousuario = grupousuario;
	}

}