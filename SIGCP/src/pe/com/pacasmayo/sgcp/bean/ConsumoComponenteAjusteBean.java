package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ConsumoComponenteAjusteBean.java
 * Modificado: May 26, 2010 3:19:16 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ConsumoComponenteAjusteBean extends EntidadBean {

	public abstract Long getPkCodigoConsumocomponenteajus();

	public abstract void setPkCodigoConsumocomponenteajus(Long pkCodigoConsumocomponenteajus);

	public abstract ComponenteBean getComponente();

	public abstract void setComponente(ComponenteBean componente);

	public abstract Double getTmConsumocomponenteajuste();

	public abstract void setTmConsumocomponenteajuste(Double tmConsumocomponenteajuste);

	public abstract Double getPorcentajeConsumocomponenteaju();

	public abstract void setPorcentajeConsumocomponenteaju(Double porcentajeConsumocomponenteaju);

	public abstract Double getTmRealConsumocomponenteajuste();

	public abstract void setTmRealConsumocomponenteajuste(Double tmRealConsumocomponenteajuste);

	public abstract Double getPorcentajeRealConsumocomponen();

	public abstract void setPorcentajeRealConsumocomponen(Double porcentajeRealConsumocomponen);

	public abstract Double getDiferenciaConsumocomponenteaju();

	public abstract void setDiferenciaConsumocomponenteaju(Double diferenciaConsumocomponenteaju);

	public abstract List<ConsumoComponentePuestoTrabajoBean> getConsumocomponentepuestotrabajos();

	public abstract void setConsumocomponentepuestotrabajos(
			List<ConsumoComponentePuestoTrabajoBean> consumocomponentepuestotrabajos);

}