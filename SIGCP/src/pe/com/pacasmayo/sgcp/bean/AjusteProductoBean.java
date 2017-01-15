package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: AjusteProductoBean.java
 * Modificado: May 26, 2010 3:39:00 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface AjusteProductoBean extends EntidadBean {

	public abstract Long getPkCodigoAjusteproducto();

	public abstract void setPkCodigoAjusteproducto(Long pkCodigoAjusteproducto);

	public abstract UsuarioBean getUsuarioByFkCodigoUsuarioAprueba();

	public abstract void setUsuarioByFkCodigoUsuarioAprueba(UsuarioBean usuarioByFkCodigoUsuarioAprueba);

	public abstract EstadoAjusteProductoBean getEstadoajusteproducto();

	public abstract void setEstadoajusteproducto(EstadoAjusteProductoBean estadoajusteproducto);

	public abstract ProductoBean getProducto();

	public abstract void setProducto(ProductoBean producto);

	public abstract PlantillaGrupoAjusteBean getPlantillagrupoajuste();

	public abstract void setPlantillagrupoajuste(PlantillaGrupoAjusteBean plantillagrupoajuste);

	public abstract UsuarioBean getUsuarioByFkCodigoUsuarioAjusta();

	public abstract void setUsuarioByFkCodigoUsuarioAjusta(UsuarioBean usuarioByFkCodigoUsuarioAjusta);

	public abstract List<PuestoTrabajoProduccionBean> getPuestotrabajoproduccions();

	public abstract void setPuestotrabajoproduccions(List<PuestoTrabajoProduccionBean> puestotrabajoproduccions);

	public abstract List<BalanceProductoBean> getBalanceproductos();

	public abstract void setBalanceproductos(List<BalanceProductoBean> balanceproductos);

	public abstract AjusteProduccionBean getAjusteproduccion();

	public abstract void setAjusteproduccion(AjusteProduccionBean ajusteproduccion);

}