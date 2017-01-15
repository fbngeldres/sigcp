package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tablerocontrol entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tablerocontrol implements java.io.Serializable {

	// Fields

	private Long pkCodigoTablerocontrol;
	private Unidad unidad;
	private String nombreTablerocontrol;
	private String descripcionTablerocontrol;


	// Constructors

	/** default constructor */
	public Tablerocontrol() {
	}

	/** minimal constructor */
	public Tablerocontrol(Unidad unidad, String nombreTablerocontrol) {
		this.unidad = unidad;
		this.nombreTablerocontrol = nombreTablerocontrol;
	}

	/** full constructor */
	public Tablerocontrol(Unidad unidad, String nombreTablerocontrol, String descripcionTablerocontrol) {
		this.unidad = unidad;
		this.nombreTablerocontrol = nombreTablerocontrol;
		this.descripcionTablerocontrol = descripcionTablerocontrol;
		
	}

	// Property accessors

	public Long getPkCodigoTablerocontrol() {
		return this.pkCodigoTablerocontrol;
	}

	public void setPkCodigoTablerocontrol(Long pkCodigoTablerocontrol) {
		this.pkCodigoTablerocontrol = pkCodigoTablerocontrol;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public String getNombreTablerocontrol() {
		return this.nombreTablerocontrol;
	}

	public void setNombreTablerocontrol(String nombreTablerocontrol) {
		this.nombreTablerocontrol = nombreTablerocontrol;
	}

	public String getDescripcionTablerocontrol() {
		return this.descripcionTablerocontrol;
	}

	public void setDescripcionTablerocontrol(String descripcionTablerocontrol) {
		this.descripcionTablerocontrol = descripcionTablerocontrol;
	}

	

}