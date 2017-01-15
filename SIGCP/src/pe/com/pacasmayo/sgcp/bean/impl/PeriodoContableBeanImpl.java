package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.DocumentoMaterialBean;
import pe.com.pacasmayo.sgcp.bean.ParteDiarioBean;
import pe.com.pacasmayo.sgcp.bean.PeriodoContableBean;


/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PeriodoContableBeanImpl.java
 * Modificado: May 25, 2010 10:52:42 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class PeriodoContableBeanImpl extends EntidadBeanImpl implements PeriodoContableBean {

	private Long pkCodigoPeridocontable;
	private Integer anoPeriodocontable;
	private Short mesPeriodocontable;
	// private Byte cerradoPeridocontable;
	private Boolean cerradoPeridocontable;
	
	private List<DocumentoMaterialBean> documentomaterials = new ArrayList<DocumentoMaterialBean>();
	private List<ParteDiarioBean> partediarios = new ArrayList<ParteDiarioBean>();
	private List<AjusteProduccionBean> ajusteproduccions = new ArrayList<AjusteProduccionBean>();

	public List<AjusteProduccionBean> getAjusteproduccions() {
		return ajusteproduccions;
	}

	public Integer getAnoPeriodocontable() {
		return anoPeriodocontable;
	}

	public Boolean getCerradoPeridocontable() {
		return cerradoPeridocontable;
	}

	public List<DocumentoMaterialBean> getDocumentomaterials() {
		return documentomaterials;
	}

	public Short getMesPeriodocontable() {
		return mesPeriodocontable;
	}

	public List<ParteDiarioBean> getPartediarios() {
		return partediarios;
	}

	public Long getPkCodigoPeridocontable() {
		return pkCodigoPeridocontable;
	}

	

	public void setAjusteproduccions(List<AjusteProduccionBean> ajusteproduccions) {
		this.ajusteproduccions = ajusteproduccions;
	}

	public void setAnoPeriodocontable(Integer anoPeriodocontable) {
		this.anoPeriodocontable = anoPeriodocontable;
	}

	public void setCerradoPeridocontable(Boolean cerradoPeridocontable) {
		this.cerradoPeridocontable = cerradoPeridocontable;
	}

	public void setDocumentomaterials(List<DocumentoMaterialBean> documentomaterials) {
		this.documentomaterials = documentomaterials;
	}

	public void setMesPeriodocontable(Short mesPeriodocontable) {
		this.mesPeriodocontable = mesPeriodocontable;
	}

	public void setPartediarios(List<ParteDiarioBean> partediarios) {
		this.partediarios = partediarios;
	}

	public void setPkCodigoPeridocontable(Long pkCodigoPeridocontable) {
		this.pkCodigoPeridocontable = pkCodigoPeridocontable;
	}

	

}
