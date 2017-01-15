package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.AjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.BalanceProductoBean;
import pe.com.pacasmayo.sgcp.bean.EstadoAjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaGrupoAjusteBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoProduccionBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: AjusteProductoBeanImpl.java
 * Modificado: May 27, 2010 8:44:27 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class AjusteProductoBeanImpl extends EntidadBeanImpl implements AjusteProductoBean {

	private Long pkCodigoAjusteproducto;
	private UsuarioBean usuarioByFkCodigoUsuarioAprueba;
	private EstadoAjusteProductoBean estadoajusteproducto;
	private ProductoBean producto;
	private AjusteProduccionBean ajusteproduccion;
	private PlantillaGrupoAjusteBean plantillagrupoajuste;
	private UsuarioBean usuarioByFkCodigoUsuarioAjusta;
	private List<PuestoTrabajoProduccionBean> puestotrabajoproduccions = new ArrayList<PuestoTrabajoProduccionBean>();
	private List<BalanceProductoBean> balanceproductos = new ArrayList<BalanceProductoBean>();

	public Long getPkCodigoAjusteproducto() {
		return pkCodigoAjusteproducto;
	}

	public void setPkCodigoAjusteproducto(Long pkCodigoAjusteproducto) {
		this.pkCodigoAjusteproducto = pkCodigoAjusteproducto;
	}

	public UsuarioBean getUsuarioByFkCodigoUsuarioAprueba() {
		return usuarioByFkCodigoUsuarioAprueba;
	}

	public void setUsuarioByFkCodigoUsuarioAprueba(UsuarioBean usuarioByFkCodigoUsuarioAprueba) {
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
	}

	public EstadoAjusteProductoBean getEstadoajusteproducto() {
		return estadoajusteproducto;
	}

	public void setEstadoajusteproducto(EstadoAjusteProductoBean estadoajusteproducto) {
		this.estadoajusteproducto = estadoajusteproducto;
	}

	public UsuarioBean getUsuarioByFkCodigoUsuarioAjusta() {
		return usuarioByFkCodigoUsuarioAjusta;
	}

	public void setUsuarioByFkCodigoUsuarioAjusta(UsuarioBean usuarioByFkCodigoUsuarioAjusta) {
		this.usuarioByFkCodigoUsuarioAjusta = usuarioByFkCodigoUsuarioAjusta;
	}

	public List<PuestoTrabajoProduccionBean> getPuestotrabajoproduccions() {
		return puestotrabajoproduccions;
	}

	public void setPuestotrabajoproduccions(List<PuestoTrabajoProduccionBean> puestotrabajoproduccions) {
		this.puestotrabajoproduccions = puestotrabajoproduccions;
	}

	public List<BalanceProductoBean> getBalanceproductos() {
		return balanceproductos;
	}

	public void setBalanceproductos(List<BalanceProductoBean> balanceproductos) {
		this.balanceproductos = balanceproductos;
	}

	/**
	 * @return the producto
	 */
	public ProductoBean getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	/**
	 * @return the ajusteproduccion
	 */
	public AjusteProduccionBean getAjusteproduccion() {
		return ajusteproduccion;
	}

	/**
	 * @param ajusteproduccion the ajusteproduccion to set
	 */
	public void setAjusteproduccion(AjusteProduccionBean ajusteproduccion) {
		this.ajusteproduccion = ajusteproduccion;
	}

	/**
	 * @return the plantillagrupoajuste
	 */
	public PlantillaGrupoAjusteBean getPlantillagrupoajuste() {
		return plantillagrupoajuste;
	}

	/**
	 * @param plantillagrupoajuste the plantillagrupoajuste to set
	 */
	public void setPlantillagrupoajuste(PlantillaGrupoAjusteBean plantillagrupoajuste) {
		this.plantillagrupoajuste = plantillagrupoajuste;
	}

}
