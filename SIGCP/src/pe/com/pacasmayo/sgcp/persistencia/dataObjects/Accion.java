package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Accion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Accion implements java.io.Serializable {

	// Fields

	private Long pkCodigoAccion;
	private Menu menu;
	private String nombreAccion;
	private String presentacionAccion;
	private String urlAccion;
	private Set opcionaccions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Accion() {
	}

	/** minimal constructor */
	public Accion(String nombreAccion, String presentacionAccion, String urlAccion) {
		this.nombreAccion = nombreAccion;
		this.presentacionAccion = presentacionAccion;
		this.urlAccion = urlAccion;
	}

	/** full constructor */
	public Accion(Menu menu, String nombreAccion, String presentacionAccion, String urlAccion, Set opcionaccions) {
		this.menu = menu;
		this.nombreAccion = nombreAccion;
		this.presentacionAccion = presentacionAccion;
		this.urlAccion = urlAccion;
		this.opcionaccions = opcionaccions;
	}

	// Property accessors

	public Long getPkCodigoAccion() {
		return this.pkCodigoAccion;
	}

	public void setPkCodigoAccion(Long pkCodigoAccion) {
		this.pkCodigoAccion = pkCodigoAccion;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getNombreAccion() {
		return this.nombreAccion;
	}

	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public String getPresentacionAccion() {
		return this.presentacionAccion;
	}

	public void setPresentacionAccion(String presentacionAccion) {
		this.presentacionAccion = presentacionAccion;
	}

	public String getUrlAccion() {
		return this.urlAccion;
	}

	public void setUrlAccion(String urlAccion) {
		this.urlAccion = urlAccion;
	}

	public Set getOpcionaccions() {
		return this.opcionaccions;
	}

	public void setOpcionaccions(Set opcionaccions) {
		this.opcionaccions = opcionaccions;
	}

}