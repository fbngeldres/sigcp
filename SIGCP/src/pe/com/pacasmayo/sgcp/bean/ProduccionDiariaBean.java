package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProduccionDiariaBean.java
 * Modificado: May 26, 2010 4:43:48 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ProduccionDiariaBean extends EntidadBean {

	public abstract Long getPkCodigoProducciondiaria();

	public abstract void setPkCodigoProducciondiaria(Long pkCodigoProducciondiaria);

	public abstract ParteDiarioBean getPartediario();

	public abstract void setPartediario(ParteDiarioBean partediario);

	public abstract OrdenProduccionBean getOrdenproduccion();

	public abstract void setOrdenproduccion(OrdenProduccionBean ordenproduccion);

	public abstract Double getSaldoInicialProducciondiaria();

	public abstract void setSaldoInicialProducciondiaria(Double saldoInicialProducciondiaria);

	public abstract Double getIngresoProduccionProducciondi();

	public abstract void setIngresoProduccionProducciondi(Double ingresoProduccionProducciondi);

	public abstract Double getConsumoProducciondiaria();

	public abstract void setConsumoProducciondiaria(Double consumoProducciondiaria);

	public abstract Double getSaldoFinalProducciondiaria();

	public abstract void setSaldoFinalProducciondiaria(Double saldoFinalProducciondiaria);

	public abstract List<TablaKardexBean> getTablakardexes();

	public abstract void setTablakardexes(List<TablaKardexBean> tablakardexes);

}