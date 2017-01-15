package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Nivelmenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Nivelmenu implements java.io.Serializable {

	// Fields

	private Long pkCodigoNivelmenu;
	private Short numeroNivelmenu;
	private String nombreNivelmenu;
	private Set menus = new HashSet(0);

	// Constructors

	/** default constructor */
	public Nivelmenu() {
	}

	/** minimal constructor */
	public Nivelmenu(Short numeroNivelmenu, String nombreNivelmenu) {
		this.numeroNivelmenu = numeroNivelmenu;
		this.nombreNivelmenu = nombreNivelmenu;
	}

	/** full constructor */
	public Nivelmenu(Short numeroNivelmenu, String nombreNivelmenu, Set menus) {
		this.numeroNivelmenu = numeroNivelmenu;
		this.nombreNivelmenu = nombreNivelmenu;
		this.menus = menus;
	}

	// Property accessors

	public Long getPkCodigoNivelmenu() {
		return this.pkCodigoNivelmenu;
	}

	public void setPkCodigoNivelmenu(Long pkCodigoNivelmenu) {
		this.pkCodigoNivelmenu = pkCodigoNivelmenu;
	}

	public Short getNumeroNivelmenu() {
		return this.numeroNivelmenu;
	}

	public void setNumeroNivelmenu(Short numeroNivelmenu) {
		this.numeroNivelmenu = numeroNivelmenu;
	}

	public String getNombreNivelmenu() {
		return this.nombreNivelmenu;
	}

	public void setNombreNivelmenu(String nombreNivelmenu) {
		this.nombreNivelmenu = nombreNivelmenu;
	}

	public Set getMenus() {
		return this.menus;
	}

	public void setMenus(Set menus) {
		this.menus = menus;
	}

}