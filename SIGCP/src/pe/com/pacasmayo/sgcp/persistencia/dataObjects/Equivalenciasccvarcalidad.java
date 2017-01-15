package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Equivalenciasccvarcalidad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Equivalenciasccvarcalidad implements java.io.Serializable {

	// Fields

	private Long pkCodigoEquiscccalidadscc;
	private Long codigoPuestotrabajoscc;
	private Long codigoProcesoscc;
	private Long codigoProductoscc;
	private Puestotrabajo puestotrabajo;
	private Proceso proceso;
	private Componente componente;
	private String descripVarCalidad;
	private String descripSgcpVarCalidad;
	private Boolean afectaConsumoEquivalenciasccvarcalidad;
	private Set factorconsumopuestotrabajos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Equivalenciasccvarcalidad() {
	}

	/** minimal constructor */
	public Equivalenciasccvarcalidad(Long fkCodigoPuestotrabajoscc, Long fkCodigoProcesoscc, Long fkCodigoProductoscc,
			Puestotrabajo fkPuestotrabajo, Proceso fkProceso, Componente componente,
			Boolean afectaConsumoEquivalenciasccvarcalidad) {
		this.codigoPuestotrabajoscc = fkCodigoPuestotrabajoscc;
		this.codigoProcesoscc = fkCodigoProcesoscc;
		this.codigoProductoscc = fkCodigoProductoscc;
		this.puestotrabajo = fkPuestotrabajo;
		this.proceso = fkProceso;
		this.componente = componente;
		this.afectaConsumoEquivalenciasccvarcalidad = afectaConsumoEquivalenciasccvarcalidad;
	}

	/** full constructor */
	public Equivalenciasccvarcalidad(Long fkCodigoPuestotrabajoscc, Long fkCodigoProcesoscc, Long fkCodigoProductoscc,
			Puestotrabajo fkPuestotrabajo, Proceso fkProceso, Componente componente, String descripVarCalidad,
			Boolean afectaConsumoEquivalenciasccvarcalidad) {
		this.codigoPuestotrabajoscc = fkCodigoPuestotrabajoscc;
		this.codigoProcesoscc = fkCodigoProcesoscc;
		this.codigoProductoscc = fkCodigoProductoscc;
		this.puestotrabajo = fkPuestotrabajo;
		this.proceso = fkProceso;
		this.componente = componente;
		this.descripVarCalidad = descripVarCalidad;
		this.afectaConsumoEquivalenciasccvarcalidad = afectaConsumoEquivalenciasccvarcalidad;
	}

	// Property accessors

	public Long getPkCodigoEquiscccalidadscc() {
		return this.pkCodigoEquiscccalidadscc;
	}

	public void setPkCodigoEquiscccalidadscc(Long codigoEquiscccalidadscc) {
		this.pkCodigoEquiscccalidadscc = codigoEquiscccalidadscc;
	}

	public Long getCodigoPuestotrabajoscc() {
		return this.codigoPuestotrabajoscc;
	}

	public void setCodigoPuestotrabajoscc(Long codigoPuestotrabajoscc) {
		this.codigoPuestotrabajoscc = codigoPuestotrabajoscc;
	}

	public Long getCodigoProcesoscc() {
		return this.codigoProcesoscc;
	}

	public void setCodigoProcesoscc(Long codigoProcesoscc) {
		this.codigoProcesoscc = codigoProcesoscc;
	}

	public Long getCodigoProductoscc() {
		return this.codigoProductoscc;
	}

	public void setCodigoProductoscc(Long codigoProductoscc) {
		this.codigoProductoscc = codigoProductoscc;
	}

	public Puestotrabajo getPuestotrabajo() {
		return this.puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public String getDescripVarCalidad() {
		return this.descripVarCalidad;
	}

	public void setDescripVarCalidad(String descripVarCalidad) {
		this.descripVarCalidad = descripVarCalidad;
	}

	public Boolean getAfectaConsumoEquivalenciasccvarcalidad() {
		return this.afectaConsumoEquivalenciasccvarcalidad;
	}

	public void setAfectaConsumoEquivalenciasccvarcalidad(Boolean afectaConsumoEquivalenciasccvarcalidad) {
		this.afectaConsumoEquivalenciasccvarcalidad = afectaConsumoEquivalenciasccvarcalidad;
	}

	public Set getFactorconsumopuestotrabajos() {
		return factorconsumopuestotrabajos;
	}

	public void setFactorconsumopuestotrabajos(Set factorconsumopuestotrabajos) {
		this.factorconsumopuestotrabajos = factorconsumopuestotrabajos;
	}

	public String getDescripSgcpVarCalidad() {
		return descripSgcpVarCalidad;
	}

	public void setDescripSgcpVarCalidad(String descripSgcpVarCalidad) {
		this.descripSgcpVarCalidad = descripSgcpVarCalidad;
	}

}