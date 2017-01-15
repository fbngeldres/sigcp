package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Grupousuario entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Grupousuario implements java.io.Serializable {

	// Fields

	private Long pkCodigoGrupousuario;
	private String nombreGrupousuario;
	private String descripcionGrupousuario;
	private Set usuariogrupousuarios = new HashSet(0);
	private Set grupousuarioprivilegios = new HashSet(0);

	// Constructors

	/** default constructor */
	public Grupousuario() {
	}

	/** minimal constructor */
	public Grupousuario(String nombreGrupousuario) {
		this.nombreGrupousuario = nombreGrupousuario;
	}

	/** full constructor */
	public Grupousuario(String nombreGrupousuario, String descripcionGrupousuario, Set usuariogrupousuarios,
			Set grupousuarioprivilegios) {
		this.nombreGrupousuario = nombreGrupousuario;
		this.descripcionGrupousuario = descripcionGrupousuario;
		this.usuariogrupousuarios = usuariogrupousuarios;
		this.grupousuarioprivilegios = grupousuarioprivilegios;
	}

	// Property accessors

	public Long getPkCodigoGrupousuario() {
		return this.pkCodigoGrupousuario;
	}

	public void setPkCodigoGrupousuario(Long pkCodigoGrupousuario) {
		this.pkCodigoGrupousuario = pkCodigoGrupousuario;
	}

	public String getNombreGrupousuario() {
		return this.nombreGrupousuario;
	}

	public void setNombreGrupousuario(String nombreGrupousuario) {
		this.nombreGrupousuario = nombreGrupousuario;
	}

	public String getDescripcionGrupousuario() {
		return this.descripcionGrupousuario;
	}

	public void setDescripcionGrupousuario(String descripcionGrupousuario) {
		this.descripcionGrupousuario = descripcionGrupousuario;
	}

	public Set getUsuariogrupousuarios() {
		return this.usuariogrupousuarios;
	}

	public void setUsuariogrupousuarios(Set usuariogrupousuarios) {
		this.usuariogrupousuarios = usuariogrupousuarios;
	}

	public Set getGrupousuarioprivilegios() {
		return this.grupousuarioprivilegios;
	}

	public void setGrupousuarioprivilegios(Set grupousuarioprivilegios) {
		this.grupousuarioprivilegios = grupousuarioprivilegios;
	}

}