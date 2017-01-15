package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Menu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields

	private Long pkCodigoMenu;
	private Privilegio privilegio;
	private Menu menu;
	private Nivelmenu nivelmenu;
	private String nombreMenu;
	private String descripcionMenu;
	private Short ordenMenu;
	private Boolean estadoMenu;
	private Set menus = new HashSet(0);
	private Set accions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(Privilegio privilegio, Nivelmenu nivelmenu, String nombreMenu, Short ordenMenu) {
		this.privilegio = privilegio;
		this.nivelmenu = nivelmenu;
		this.nombreMenu = nombreMenu;
		this.ordenMenu = ordenMenu;
	}

	/** full constructor */
	public Menu(Privilegio privilegio, Menu menu, Nivelmenu nivelmenu, String nombreMenu, String descripcionMenu,
			Short ordenMenu, Set menus, Set accions) {
		this.privilegio = privilegio;
		this.menu = menu;
		this.nivelmenu = nivelmenu;
		this.nombreMenu = nombreMenu;
		this.descripcionMenu = descripcionMenu;
		this.ordenMenu = ordenMenu;
		this.menus = menus;
		this.accions = accions;
	}

	// Property accessors

	public Long getPkCodigoMenu() {
		return this.pkCodigoMenu;
	}

	public void setPkCodigoMenu(Long pkCodigoMenu) {
		this.pkCodigoMenu = pkCodigoMenu;
	}

	public Privilegio getPrivilegio() {
		return this.privilegio;
	}

	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Nivelmenu getNivelmenu() {
		return this.nivelmenu;
	}

	public void setNivelmenu(Nivelmenu nivelmenu) {
		this.nivelmenu = nivelmenu;
	}

	public String getNombreMenu() {
		return this.nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getDescripcionMenu() {
		return this.descripcionMenu;
	}

	public void setDescripcionMenu(String descripcionMenu) {
		this.descripcionMenu = descripcionMenu;
	}

	public Short getOrdenMenu() {
		return this.ordenMenu;
	}

	public void setOrdenMenu(Short ordenMenu) {
		this.ordenMenu = ordenMenu;
	}

	/**
	 * @return the estadoMenu
	 */
	public Boolean getEstadoMenu() {
		return estadoMenu;
	}

	/**
	 * @param estadoMenu the estadoMenu to set
	 */
	public void setEstadoMenu(Boolean estadoMenu) {
		this.estadoMenu = estadoMenu;
	}

	public Set getMenus() {
		return this.menus;
	}

	public void setMenus(Set menus) {
		this.menus = menus;
	}

	public Set getAccions() {
		return this.accions;
	}

	public void setAccions(Set accions) {
		this.accions = accions;
	}

}