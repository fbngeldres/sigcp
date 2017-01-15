package pe.com.pacasmayo.sgcp.bean;

import java.util.Date;
import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: TablaKardexBean.java
 * Modificado: May 27, 2010 11:05:43 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface TablaKardexBean extends EntidadBean {

	public abstract Long getPkCodigoTablakardex();

	public abstract void setPkCodigoTablakardex(Long pkCodigoTablakardex);

	public abstract ProduccionDiariaBean getProducciondiaria();

	public abstract void setProducciondiaria(ProduccionDiariaBean producciondiaria);

	public abstract AlmacenBean getAlmacen();

	public abstract void setAlmacen(AlmacenBean almacen);

	public abstract UbicacionBean getUbicacionByFkCodigoUbicacionDestino();

	public abstract void setUbicacionByFkCodigoUbicacionDestino(UbicacionBean ubicacionByFkCodigoUbicacionDestino);

	public abstract UbicacionBean getUbicacionByFkCodigoUbicacionOrigen();

	public abstract void setUbicacionByFkCodigoUbicacionOrigen(UbicacionBean ubicacionByFkCodigoUbicacionOrigen);

	public abstract MedioAlmacenamientoBean getMedioalmacenamiento();

	public abstract void setMedioalmacenamiento(MedioAlmacenamientoBean medioalmacenamiento);

	public abstract Date getFechaTablakardex();

	public abstract void setFechaTablakardex(Date fechaTablakardex);

	public abstract String getObservacionTablakardex();

	public abstract void setObservacionTablakardex(String observacionTablakardex);

	public abstract Double getIngresoTablakardex();

	public abstract void setIngresoTablakardex(Double ingresoTablakardex);

	public abstract Double getConsumoTablakardex();

	public abstract void setConsumoTablakardex(Double consumoTablakardex);

	public abstract Double getSaldoInicialTablakardex();

	public abstract void setSaldoInicialTablakardex(Double saldoInicialTablakardex);

	public abstract Double getStockFisicoTablakardex();

	public abstract void setStockFisicoTablakardex(Double stockFisicoTablakardex);

	public abstract Double getStockFinalTablakardex();

	public abstract void setStockFinalTablakardex(Double stockFinalTablakardex);

	public abstract Double getVariacionTablakardex();

	public abstract void setVariacionTablakardex(Double variacionTablakardex);

	public abstract Double getIngresoHumedadTablakardex();

	public abstract void setIngresoHumedadTablakardex(Double ingresoHumedadTablakardex);

	public abstract Double getConsumoHumedadTablakardex();

	public abstract void setConsumoHumedadTablakardex(Double consumoHumedadTablakardex);

	public abstract List<ValorPromVariableCalidadBean> getValorpromvariablecalidads();

	public abstract void setValorpromvariablecalidads(List<ValorPromVariableCalidadBean> valorpromvariablecalidads);

	public abstract List<FactorKardexBean> getFactorkardexes();

	public abstract void setFactorkardexes(List<FactorKardexBean> factorkardexes);

	public abstract List<ConsumoComponenteBean> getConsumocomponentes();

	public abstract void setConsumocomponentes(List<ConsumoComponenteBean> consumocomponentes);

}