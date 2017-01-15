package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanCapacidadBean.java
 * Modificado: Dec 22, 2009 12:55:52 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface PlanCapacidadBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the puestoTrabajo
	 */
	public abstract PuestoTrabajoBean getPuestoTrabajo();

	/**
	 * @param puestoTrabajo the puestoTrabajo to set
	 */
	public abstract void setPuestoTrabajo(PuestoTrabajoBean puestoTrabajo);

	/**
	 * @return the registroMesAnual
	 */
	public abstract RegistroMesAnualBean getRegistroMesAnual();

	/**
	 * @param registroMesAnual the registroMesAnual to set
	 */
	public abstract void setRegistroMesAnual(RegistroMesAnualBean registroMesAnual);

	/**
	 * @return the unidadMedida
	 */
	public UnidadMedidaBean getUnidadMedida();

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(UnidadMedidaBean unidadMedida);

	/**
	 * @return the plancapacidadregistromensual
	 */
	public PlanCapacidadRegistroMensualBean[] getPlancapacidadregistromensual();

	/**
	 * @param plancapacidadregistromensual the plancapacidadregistromensual to
	 *            set
	 */
	public void setPlancapacidadregistromensual(PlanCapacidadRegistroMensualBean[] plancapacidadregistromensual);

	/**
	 * @return the versionPlancapacidad
	 */
	public String getVersionPlancapacidad();

	/**
	 * @param versionPlancapacidad the versionPlancapacidad to set
	 */
	public void setVersionPlancapacidad(String versionPlancapacidad);

	/**
	 * @return the annoPlancapacidad
	 */
	public Integer getAnnoPlancapacidad();

	/**
	 * @param annoPlancapacidad the annoPlancapacidad to set
	 */
	public void setAnnoPlancapacidad(Integer annoPlancapacidad);

	/**
	 * @return the estadoPlanCapacidad
	 */
	public EstadoPlanCapacidadBean getEstadoPlanCapacidad();

	/**
	 * @param estadoPlanCapacidad the estadoPlanCapacidad to set
	 */
	public void setEstadoPlanCapacidad(EstadoPlanCapacidadBean estadoPlanCapacidad);

	/**
	 * @return the lineaNegocioBean
	 */
	public LineaNegocioBean getLineaNegocio();

	/**
	 * @param lineaNegocioBean the lineaNegocioBean to set
	 */
	public void setLineaNegocio(LineaNegocioBean lineaNegocioBean);

}