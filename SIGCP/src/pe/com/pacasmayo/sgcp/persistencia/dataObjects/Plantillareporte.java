package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Plantillareporte entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Plantillareporte implements java.io.Serializable {

	// Fields

	private Long pkCodigoPlantillareporte;
	
	private Puestotrabajo puestotrabajo;
	private Proceso proceso;
	private String nombrePlantillareporte;
	private Date fechaPlantillareporte;
	private Short columnaPlantillareporte;
	private Set columnareportes = new HashSet(0);
	private Set plantillaproductos = new HashSet(0);
	public Long getPkCodigoPlantillareporte() {
		return pkCodigoPlantillareporte;
	}
	public void setPkCodigoPlantillareporte(Long pkCodigoPlantillareporte) {
		this.pkCodigoPlantillareporte = pkCodigoPlantillareporte;
	}
	public Puestotrabajo getPuestotrabajo() {
		return puestotrabajo;
	}
	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}
	public Proceso getProceso() {
		return proceso;
	}
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
	public String getNombrePlantillareporte() {
		return nombrePlantillareporte;
	}
	public void setNombrePlantillareporte(String nombrePlantillareporte) {
		this.nombrePlantillareporte = nombrePlantillareporte;
	}
	public Date getFechaPlantillareporte() {
		return fechaPlantillareporte;
	}
	public void setFechaPlantillareporte(Date fechaPlantillareporte) {
		this.fechaPlantillareporte = fechaPlantillareporte;
	}
	public Short getColumnaPlantillareporte() {
		return columnaPlantillareporte;
	}
	public void setColumnaPlantillareporte(Short columnaPlantillareporte) {
		this.columnaPlantillareporte = columnaPlantillareporte;
	}
	public Set getColumnareportes() {
		return columnareportes;
	}
	public void setColumnareportes(Set columnareportes) {
		this.columnareportes = columnareportes;
	}
	public Set getPlantillaproductos() {
		return plantillaproductos;
	}
	public void setPlantillaproductos(Set plantillaproductos) {
		this.plantillaproductos = plantillaproductos;
	}

	
}