package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PeriodoContableBean.java
 * Modificado: May 25, 2010 10:42:30 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
import java.util.List;

public interface PeriodoContableBean extends EntidadBean {

	public abstract Long getPkCodigoPeridocontable();

	public abstract void setPkCodigoPeridocontable(Long pkCodigoPeridocontable);

	public abstract Integer getAnoPeriodocontable();

	public abstract void setAnoPeriodocontable(Integer anoPeriodocontable);

	public abstract Short getMesPeriodocontable();

	public abstract void setMesPeriodocontable(Short mesPeriodocontable);

	public abstract Boolean getCerradoPeridocontable();

	public abstract void setCerradoPeridocontable(Boolean cerradoPeridocontable);


	public abstract List<DocumentoMaterialBean> getDocumentomaterials();

	public abstract void setDocumentomaterials(List<DocumentoMaterialBean> documentomaterials);

	public abstract List<ParteDiarioBean> getPartediarios();

	public abstract void setPartediarios(List<ParteDiarioBean> partediarios);

	public abstract List<AjusteProduccionBean> getAjusteproduccions();

	public abstract void setAjusteproduccions(List<AjusteProduccionBean> ajusteproduccions);

}