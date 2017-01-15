package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ParteDiarioBean.java
 * Modificado: May 26, 2010 4:40:40 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ParteDiarioBean extends EntidadBean {

	public abstract Long getPkCodigoPartediario();

	public abstract void setPkCodigoPartediario(Long pkCodigoPartediario);

	public abstract EstadoParteDiarioBean getEstadopartediario();

	public abstract void setEstadopartediario(EstadoParteDiarioBean estadopartediario);

	public abstract PeriodoContableBean getPeriodocontable();

	public abstract void setPeriodocontable(PeriodoContableBean periodocontable);

	public abstract LineaNegocioBean getLineanegocio();

	public abstract void setLineanegocio(LineaNegocioBean lineanegocio);

	public abstract List<ProduccionDiariaBean> getProducciondiarias();

	public abstract void setProducciondiarias(List<ProduccionDiariaBean> producciondiarias);

	public abstract List<ProduccionPuestoTrabajoBean> getProduccionpuestotrabajos();

	public abstract void setProduccionpuestotrabajos(List<ProduccionPuestoTrabajoBean> produccionpuestotrabajos);

	public abstract UsuarioBean getUsuarioByFkCodigoUsuarioRegistra();

	public abstract void setUsuarioByFkCodigoUsuarioRegistra(UsuarioBean usuario);

	public abstract UsuarioBean getUsuarioByFkCodigoUsuarioCierra();

	public abstract void setUsuarioByFkCodigoUsuarioCierra(UsuarioBean usuario);

}