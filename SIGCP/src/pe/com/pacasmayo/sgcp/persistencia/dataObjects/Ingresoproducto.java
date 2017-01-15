package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ingresoproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ingresoproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoIngresoproducto;
	private Lineanegocio lineanegocio;
	private Date fechaIngresoproducto;
	private Integer anoIngresoproducto;
	private Short mesIngresoproducto;
	private Set ingresoproductoprocesos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Ingresoproducto() {
	}

	/** minimal constructor */
	public Ingresoproducto(Lineanegocio lineanegocio, Date fechaIngresoproducto, Integer anoIngresoproducto,
			Short mesIngresoproducto) {
		this.lineanegocio = lineanegocio;
		this.fechaIngresoproducto = fechaIngresoproducto;
		this.anoIngresoproducto = anoIngresoproducto;
		this.mesIngresoproducto = mesIngresoproducto;
	}

	/** full constructor */
	public Ingresoproducto(Lineanegocio lineanegocio, Date fechaIngresoproducto, Integer anoIngresoproducto,
			Short mesIngresoproducto, Set ingresoproductoprocesos) {
		this.lineanegocio = lineanegocio;
		this.fechaIngresoproducto = fechaIngresoproducto;
		this.anoIngresoproducto = anoIngresoproducto;
		this.mesIngresoproducto = mesIngresoproducto;
		this.ingresoproductoprocesos = ingresoproductoprocesos;
	}

	// Property accessors

	public Long getPkCodigoIngresoproducto() {
		return this.pkCodigoIngresoproducto;
	}

	public void setPkCodigoIngresoproducto(Long pkCodigoIngresoproducto) {
		this.pkCodigoIngresoproducto = pkCodigoIngresoproducto;
	}

	public Lineanegocio getLineanegocio() {
		return this.lineanegocio;
	}

	public void setLineanegocio(Lineanegocio lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public Date getFechaIngresoproducto() {
		return this.fechaIngresoproducto;
	}

	public void setFechaIngresoproducto(Date fechaIngresoproducto) {
		this.fechaIngresoproducto = fechaIngresoproducto;
	}

	public Integer getAnoIngresoproducto() {
		return this.anoIngresoproducto;
	}

	public void setAnoIngresoproducto(Integer anoIngresoproducto) {
		this.anoIngresoproducto = anoIngresoproducto;
	}

	public Short getMesIngresoproducto() {
		return this.mesIngresoproducto;
	}

	public void setMesIngresoproducto(Short mesIngresoproducto) {
		this.mesIngresoproducto = mesIngresoproducto;
	}

	public Set getIngresoproductoprocesos() {
		return this.ingresoproductoprocesos;
	}

	public void setIngresoproductoprocesos(Set ingresoproductoprocesos) {
		this.ingresoproductoprocesos = ingresoproductoprocesos;
	}

}