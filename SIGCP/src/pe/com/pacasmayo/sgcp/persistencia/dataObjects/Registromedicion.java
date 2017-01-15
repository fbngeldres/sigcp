package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Registromedicion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Registromedicion implements java.io.Serializable {

	// Fields

	private Long pkCodigoRegistromedicion;
	
	private Proceso proceso;

	private Integer anoRegistromedicion;
	private Short mesRegistromedicion;
	private Date fechaRegistromedicion;
	private Set medicions = new HashSet(0);
	public Long getPkCodigoRegistromedicion() {
		return pkCodigoRegistromedicion;
	}
	public void setPkCodigoRegistromedicion(Long pkCodigoRegistromedicion) {
		this.pkCodigoRegistromedicion = pkCodigoRegistromedicion;
	}
	public Proceso getProceso() {
		return proceso;
	}
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
	public Integer getAnoRegistromedicion() {
		return anoRegistromedicion;
	}
	public void setAnoRegistromedicion(Integer anoRegistromedicion) {
		this.anoRegistromedicion = anoRegistromedicion;
	}
	public Short getMesRegistromedicion() {
		return mesRegistromedicion;
	}
	public void setMesRegistromedicion(Short mesRegistromedicion) {
		this.mesRegistromedicion = mesRegistromedicion;
	}
	public Date getFechaRegistromedicion() {
		return fechaRegistromedicion;
	}
	public void setFechaRegistromedicion(Date fechaRegistromedicion) {
		this.fechaRegistromedicion = fechaRegistromedicion;
	}
	public Set getMedicions() {
		return medicions;
	}
	public void setMedicions(Set medicions) {
		this.medicions = medicions;
	}

	

}