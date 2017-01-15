package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n)
 * Archivo: PuestoTrabajoProduccionBean.java
 * Modificado: May 26, 2010 3:26:09 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface PuestoTrabajoProduccionBean extends EntidadBean {

	public abstract Long getPkCodigoPuestotrabajoproducci();

	public abstract void setPkCodigoPuestotrabajoproducci(Long pkCodigoPuestotrabajoproducci);

	public abstract AjusteProductoBean getAjusteproducto();

	public abstract void setAjusteproducto(AjusteProductoBean ajusteproducto);

	public abstract PuestoTrabajoBean getPuestotrabajo();

	public abstract void setPuestotrabajo(PuestoTrabajoBean puestotrabajo);

	public abstract Double getTmPuestotrabajoproduccion();

	public abstract void setTmPuestotrabajoproduccion(Double tmPuestotrabajoproduccion);

	public abstract Double getHrPuestotrabajoproduccion();

	public abstract void setHrPuestotrabajoproduccion(Double hrPuestotrabajoproduccion);

	public abstract Double getTmphPuestotrabajoproduccion();

	public abstract void setTmphPuestotrabajoproduccion(Double tmphPuestotrabajoproduccion);

	public abstract Double getHrAjustePuestotrabajoproducci();

	public abstract void setHrAjustePuestotrabajoproducci(Double hrAjustePuestotrabajoproducci);

	public abstract Double getTmAjustePuestotrabajoproducci();

	public abstract void setTmAjustePuestotrabajoproducci(Double tmAjustePuestotrabajoproducci);

	public abstract Double getTmRealPuestotrabajoproduccion();

	public abstract void setTmRealPuestotrabajoproduccion(Double tmRealPuestotrabajoproduccion);

	public abstract Double getHrRealPuestotrabajoproduccion();

	public abstract void setHrRealPuestotrabajoproduccion(Double hrRealPuestotrabajoproduccion);

	public abstract Double getTmphRealPuestotrabajoproducci();

	public abstract void setTmphRealPuestotrabajoproducci(Double tmphRealPuestotrabajoproducci);

	public abstract Double getKcalPuestotrabajoproduccion();

	public abstract void setKcalPuestotrabajoproduccion(Double kcalPuestotrabajoproduccion);

	public abstract Double getKcalRealPuestotrabajoproducci();

	public abstract void setKcalRealPuestotrabajoproducci(Double kcalRealPuestotrabajoproducci);

	public abstract Double getCarProdPuestotrabajoproduccion();

	public abstract void setCarProdPuestotrabajoproduccion(Double carProdPuestotrabajoproduccion);

	public abstract Double getCarCalentPuestotrabajoproduccion();

	public abstract void setCarCalentPuestotrabajoproduccion(Double carCalentPuestotrabajoproduccion);

	public abstract Double getCarProdRealPuestotrabajoproduccion();

	public abstract void setCarProdRealPuestotrabajoproduccion(Double carProdRealPuestotrabajoproduccion);

	public abstract Double getCarCalentRealPuestotrabajoproduccion();

	public abstract void setCarCalentRealPuestotrabajoproduccion(Double carCalentRealPuestotrabajoproduccion);

	public abstract Double getBunkProdPuestotrabajoproduccion();

	public abstract void setBunkProdPuestotrabajoproduccion(Double bunkProdPuestotrabajoproduccion);

	public abstract Double getBunkCalentPuestotrabajoproduccion();

	public abstract void setBunkCalentPuestotrabajoproduccion(Double bunkCalentPuestotrabajoproduccion);

	public abstract Double getBunkProdRealPuestotrabajoproduccion();

	public abstract void setBunkProdRealPuestotrabajoproduccion(Double bunkProdRealPuestotrabajoproduccion);

	public abstract Double getBunkCalentRealPuestotrabajoproduccion();

	public abstract void setBunkCalentRealPuestotrabajoproduccion(Double bunkCalentRealPuestotrabajoproduccion);

	public abstract List<ConsumoComponentePuestoTrabajoBean> getConsumocomponentepuestotrabajos();

	public abstract void setConsumocomponentepuestotrabajos(
			List<ConsumoComponentePuestoTrabajoBean> consumocomponentepuestotrabajos);

}