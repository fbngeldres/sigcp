package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DetalleAjusteProduccionMesBean.java
 * Modificado: Jul 29, 2010 4:11:10 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface DetalleAjusteProduccionMesBean {

	public abstract void DetalleAjusteProduccionMes();

	public abstract Long getCodigoAjuste();

	public abstract void setCodigoAjuste(Long codigoAjuste);

	public abstract String getSociedad();

	public abstract void setSociedad(String sociedad);

	public abstract String getUnidad();

	public abstract void setUnidad(String unidad);

	public abstract String getLineaNegocio();

	public abstract void setLineaNegocio(String lineaNegocio);

	public abstract String getEstado();

	public abstract void setEstado(String estado);

	public abstract String getMesAnio();

	public abstract void setMesAnio(String mesAnio);

	public abstract String getUsuarioAprueba();

	public abstract void setUsuarioAprueba(String usuarioAprueba);

	public abstract String getUsuarioAjusta();

	public abstract void setUsuarioAjusta(String usuarioAjusta);

	public abstract String getFechaAprueba();

	public abstract void setFechaAprueba(String fechaAprueba);

	public abstract String getDivision();

	public abstract void setDivision(String division);

	public String getMensajeConsumo();

	public String getMensajeCombustible();

	/**
	 * @return the envioconsumo
	 */
	public Boolean getEnvioconsumo();

	/**
	 * @param envioconsumo the envioconsumo to set
	 */
	public void setEnvioconsumo(Boolean envioconsumo);

	/**
	 * @return the enviocobustible
	 */
	public Boolean getEnviocombustible();

	/**
	 * @param enviocobustible the enviocobustible to set
	 */
	public void setEnviocombustible(Boolean enviocobustible);

}
