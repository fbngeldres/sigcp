package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Objetocostos entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Objetocostos implements java.io.Serializable {

	// Fields

	private Long pkCodigoObjetocosto;
	private Estadoobjetocostos estadoobjetocostos;
	private Tipoobjetocostos tipoobjetocostos;
	private String descripcionObjetocosto;
	private String abreviaturaObjetocosto;
	private String codigoSapObjetocosto;
	private Date fechaInicioObjetocosto;
	private Date fechaFinObjetocosto;
	private Long valorestadisticoObjetocosto;
	
	private Boolean flagparticionadObjetocosto;

	// Constructors

	/** default constructor */
	public Objetocostos() {
	}

	/** minimal constructor */
	public Objetocostos(Estadoobjetocostos estadoobjetocostos, Tipoobjetocostos tipoobjetocostos, String descripcionObjetocosto,
			String abreviaturaObjetocosto, String codigoSapObjetocosto) {
		this.estadoobjetocostos = estadoobjetocostos;
		this.tipoobjetocostos = tipoobjetocostos;
		this.descripcionObjetocosto = descripcionObjetocosto;
		this.abreviaturaObjetocosto = abreviaturaObjetocosto;
		this.codigoSapObjetocosto = codigoSapObjetocosto;
	}

	/** full constructor */
	public Objetocostos(Estadoobjetocostos estadoobjetocostos, Tipoobjetocostos tipoobjetocostos, 
			String descripcionObjetocosto, String abreviaturaObjetocosto, String codigoSapObjetocosto,
			Date fechaInicioObjetocosto, Date fechaFinObjetocosto, Long valorestadisticoObjetocosto) {
		this.estadoobjetocostos = estadoobjetocostos;
		this.tipoobjetocostos = tipoobjetocostos;
		this.descripcionObjetocosto = descripcionObjetocosto;
		this.abreviaturaObjetocosto = abreviaturaObjetocosto;
		this.codigoSapObjetocosto = codigoSapObjetocosto;
		this.fechaInicioObjetocosto = fechaInicioObjetocosto;
		this.fechaFinObjetocosto = fechaFinObjetocosto;
		this.valorestadisticoObjetocosto = valorestadisticoObjetocosto;
	
	}

	// Property accessors

	public Long getPkCodigoObjetocosto() {
		return this.pkCodigoObjetocosto;
	}

	public void setPkCodigoObjetocosto(Long pkCodigoObjetocosto) {
		this.pkCodigoObjetocosto = pkCodigoObjetocosto;
	}

	public Estadoobjetocostos getEstadoobjetocostos() {
		return this.estadoobjetocostos;
	}

	public void setEstadoobjetocostos(Estadoobjetocostos estadoobjetocostos) {
		this.estadoobjetocostos = estadoobjetocostos;
	}

	public Tipoobjetocostos getTipoobjetocostos() {
		return this.tipoobjetocostos;
	}

	public void setTipoobjetocostos(Tipoobjetocostos tipoobjetocostos) {
		this.tipoobjetocostos = tipoobjetocostos;
	}



	public String getDescripcionObjetocosto() {
		return this.descripcionObjetocosto;
	}

	public void setDescripcionObjetocosto(String descripcionObjetocosto) {
		this.descripcionObjetocosto = descripcionObjetocosto;
	}

	public String getAbreviaturaObjetocosto() {
		return this.abreviaturaObjetocosto;
	}

	public void setAbreviaturaObjetocosto(String abreviaturaObjetocosto) {
		this.abreviaturaObjetocosto = abreviaturaObjetocosto;
	}

	public String getCodigoSapObjetocosto() {
		return this.codigoSapObjetocosto;
	}

	public void setCodigoSapObjetocosto(String codigoSapObjetocosto) {
		this.codigoSapObjetocosto = codigoSapObjetocosto;
	}

	public Date getFechaInicioObjetocosto() {
		return this.fechaInicioObjetocosto;
	}

	public void setFechaInicioObjetocosto(Date fechaInicioObjetocosto) {
		this.fechaInicioObjetocosto = fechaInicioObjetocosto;
	}

	public Date getFechaFinObjetocosto() {
		return this.fechaFinObjetocosto;
	}

	public void setFechaFinObjetocosto(Date fechaFinObjetocosto) {
		this.fechaFinObjetocosto = fechaFinObjetocosto;
	}

	public Long getValorestadisticoObjetocosto() {
		return this.valorestadisticoObjetocosto;
	}

	public void setValorestadisticoObjetocosto(Long valorestadisticoObjetocosto) {
		this.valorestadisticoObjetocosto = valorestadisticoObjetocosto;
	}

	

	public Boolean getFlagparticionadObjetocosto() {

		return this.flagparticionadObjetocosto;

	}

	public void setFlagparticionadObjetocosto(Boolean flagparticionadObjetocosto) {

		this.flagparticionadObjetocosto = flagparticionadObjetocosto;

	}

}