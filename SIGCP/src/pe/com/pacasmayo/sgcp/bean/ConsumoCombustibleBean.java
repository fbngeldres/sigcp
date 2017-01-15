package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ConsumoCombustibleBean.java
 * Modificado: Oct 4, 2011 3:03:06 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConsumoCombustibleBean {

	public abstract Long getCodigoProductoCombustible();

	public abstract void setCodigoProductoCombustible(Long codigoProductoCombustible);

	public abstract String getNombreProductoCombustible();

	public abstract void setNombreProductoCombustible(String nombreProductoCombustible);

	public abstract Long getCodigoPuestoTrabajo();

	public abstract void setCodigoPuestoTrabajo(Long codigoPuestoTrabajo);

	public abstract String getNombrePuestoTrabajo();

	public abstract void setNombrePuestoTrabajo(String nombrePuestoTrabajo);

	public abstract Double getConsumoCalentamientoMes();

	public abstract void setConsumoCalentamientoMes(Double consumoCalentamientoMes);

	public abstract Double getConsumoCalentamientoAcumulado();

	public abstract void setConsumoCalentamientoAcumulado(Double consumoCalentamientoAcumulado);

	public abstract Double getConsumoProduccionMes();

	public abstract void setConsumoProduccionMes(Double consumoProduccionMes);

	public abstract Double getConsumoProduccionAcumulado();

	public abstract void setConsumoProduccionAcumulado(Double consumoProduccionAcumulado);

	public abstract Double getConsumoTotalMes();

	public abstract void setConsumoTotalMes(Double consumoTotalMes);

	public abstract Double getConsumoTotalAcumulado();

	public abstract void setConsumoTotalAcumulado(Double consumoTotalAcumulado);

	public abstract String getUnidadMedida();

	public abstract void setUnidadMedida(String unidadMedida);

}
