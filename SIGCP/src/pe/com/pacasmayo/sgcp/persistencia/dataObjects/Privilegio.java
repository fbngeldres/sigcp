package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Privilegio entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Privilegio implements java.io.Serializable {

	// Fields

	private Long pkCodigoPrivilegio;
	private Estadoprivilegio estadoprivilegio;
	private String nombrePrivilegio;
	private String descripcionPrivilegio;
	private Set menus = new HashSet(0);
	private Set grupousuarioprivilegios = new HashSet(0);

	// Constructors

	/** default constructor */
	public Privilegio() {
	}

	/** minimal constructor */
	public Privilegio(Estadoprivilegio estadoprivilegio, String nombrePrivilegio) {
		this.estadoprivilegio = estadoprivilegio;
		this.nombrePrivilegio = nombrePrivilegio;
	}

	/** full constructor */
	public Privilegio(Estadoprivilegio estadoprivilegio, String nombrePrivilegio, String descripcionPrivilegio, Set menus,
			Set grupousuarioprivilegios) {
		this.estadoprivilegio = estadoprivilegio;
		this.nombrePrivilegio = nombrePrivilegio;
		this.descripcionPrivilegio = descripcionPrivilegio;
		this.menus = menus;
		this.grupousuarioprivilegios = grupousuarioprivilegios;
	}

	// Property accessors

	public Long getPkCodigoPrivilegio() {
		return this.pkCodigoPrivilegio;
	}

	public void setPkCodigoPrivilegio(Long pkCodigoPrivilegio) {
		this.pkCodigoPrivilegio = pkCodigoPrivilegio;
	}

	public Estadoprivilegio getEstadoprivilegio() {
		return this.estadoprivilegio;
	}

	public void setEstadoprivilegio(Estadoprivilegio estadoprivilegio) {
		this.estadoprivilegio = estadoprivilegio;
	}

	public String getNombrePrivilegio() {
		return this.nombrePrivilegio;
	}

	public void setNombrePrivilegio(String nombrePrivilegio) {
		this.nombrePrivilegio = nombrePrivilegio;
	}

	public String getDescripcionPrivilegio() {
		return this.descripcionPrivilegio;
	}

	public void setDescripcionPrivilegio(String descripcionPrivilegio) {
		this.descripcionPrivilegio = descripcionPrivilegio;
	}

	public Set getMenus() {
		return this.menus;
	}

	public void setMenus(Set menus) {
		this.menus = menus;
	}

	public Set getGrupousuarioprivilegios() {
		return this.grupousuarioprivilegios;
	}

	public void setGrupousuarioprivilegios(Set grupousuarioprivilegios) {
		this.grupousuarioprivilegios = grupousuarioprivilegios;
	}

}