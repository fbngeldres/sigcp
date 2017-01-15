package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ConsumoComponentePuestoTrabajoBean.java
 * Modificado: May 26, 2010 3:12:01 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ConsumoComponentePuestoTrabajoBean extends EntidadBean {

	public abstract Long getPkCodigoConsumocomponentepues();

	public abstract void setPkCodigoConsumocomponentepues(Long pkCodigoConsumocomponentepues);

	public abstract ConsumoComponenteAjusteBean getConsumocomponenteajuste();

	public abstract void setConsumocomponenteajuste(ConsumoComponenteAjusteBean consumocomponenteajuste);

	public abstract PuestoTrabajoProduccionBean getPuestotrabajoproduccion();

	public abstract void setPuestotrabajoproduccion(PuestoTrabajoProduccionBean puestotrabajoproduccion);

	public abstract Double getTmConsumocomponentepuestotraba();

	public abstract void setTmConsumocomponentepuestotraba(Double tmConsumocomponentepuestotraba);

	public abstract Double getPorcentajeConsumocomponentepue();

	public abstract void setPorcentajeConsumocomponentepue(Double porcentajeConsumocomponentepue);

	public abstract Double getTmRealConsumocomponentepuesto();

	public abstract void setTmRealConsumocomponentepuesto(Double tmRealConsumocomponentepuesto);

	public abstract Double getPorcentajeRealConsumocomponen();

	public abstract void setPorcentajeRealConsumocomponen(Double porcentajeRealConsumocomponen);

	public abstract Double getDiferenciaConsumocomponentepue();

	public abstract void setDiferenciaConsumocomponentepue(Double diferenciaConsumocomponentepue);

	public abstract Double getPoderCalorificoConsumocompone();

	public abstract void setPoderCalorificoConsumocompone(Double poderCalorificoConsumocompone);

	public abstract String getObservacionConsumocomponentepu();

	public abstract void setObservacionConsumocomponentepu(String observacionConsumocomponentepu);

	public abstract List<MovimientoAjusteBean> getMovimientoajustes();

	public abstract void setMovimientoajustes(List<MovimientoAjusteBean> movimientoajustes);

}