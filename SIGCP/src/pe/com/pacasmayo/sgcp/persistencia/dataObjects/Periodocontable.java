package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Periodocontable entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Periodocontable implements java.io.Serializable {

	// Fields

	private Long pkCodigoPeridocontable;
	private Integer anoPeriodocontable;
	private Short mesPeriodocontable;
	private Boolean cerradoPeridocontable;

	private Set documentomaterials = new HashSet(0);
	private Set partediarios = new HashSet(0);
	private Set ajusteproduccions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Periodocontable() {
	}

	/** minimal constructor */
	public Periodocontable(Integer anoPeriodocontable, Short mesPeriodocontable, Boolean cerradoPeridocontable) {
		this.anoPeriodocontable = anoPeriodocontable;
		this.mesPeriodocontable = mesPeriodocontable;
		this.cerradoPeridocontable = cerradoPeridocontable;
	}

	/** full constructor */
	public Periodocontable(Integer anoPeriodocontable, Short mesPeriodocontable, Boolean cerradoPeridocontable,
			Set documentomaterials, Set partediarios, Set ajusteproduccions) {
		this.anoPeriodocontable = anoPeriodocontable;
		this.mesPeriodocontable = mesPeriodocontable;
		this.cerradoPeridocontable = cerradoPeridocontable;
		
		this.documentomaterials = documentomaterials;
		this.partediarios = partediarios;
		this.ajusteproduccions = ajusteproduccions;
	}

	// Property accessors

	public Long getPkCodigoPeridocontable() {
		return this.pkCodigoPeridocontable;
	}

	public void setPkCodigoPeridocontable(Long pkCodigoPeridocontable) {
		this.pkCodigoPeridocontable = pkCodigoPeridocontable;
	}

	public Integer getAnoPeriodocontable() {
		return this.anoPeriodocontable;
	}

	public void setAnoPeriodocontable(Integer anoPeriodocontable) {
		this.anoPeriodocontable = anoPeriodocontable;
	}

	public Short getMesPeriodocontable() {
		return this.mesPeriodocontable;
	}

	public void setMesPeriodocontable(Short mesPeriodocontable) {
		this.mesPeriodocontable = mesPeriodocontable;
	}

	public Boolean getCerradoPeridocontable() {
		return this.cerradoPeridocontable;
	}

	public void setCerradoPeridocontable(Boolean cerradoPeridocontable) {
		this.cerradoPeridocontable = cerradoPeridocontable;
	}



	public Set getDocumentomaterials() {
		return this.documentomaterials;
	}

	public void setDocumentomaterials(Set documentomaterials) {
		this.documentomaterials = documentomaterials;
	}

	public Set getPartediarios() {
		return this.partediarios;
	}

	public void setPartediarios(Set partediarios) {
		this.partediarios = partediarios;
	}

	public Set getAjusteproduccions() {
		return this.ajusteproduccions;
	}

	public void setAjusteproduccions(Set ajusteproduccions) {
		this.ajusteproduccions = ajusteproduccions;
	}

}