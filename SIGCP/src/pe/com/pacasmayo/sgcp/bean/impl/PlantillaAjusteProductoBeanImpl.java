package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.PlantillaAjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaGrupoAjusteBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PlantillaAjusteProductoBeanImpl.java
 * Modificado: May 27, 2010 10:19:32 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class PlantillaAjusteProductoBeanImpl extends EntidadBeanImpl implements PlantillaAjusteProductoBean {

	private Long pkCodigoPlantillaajusteproduc;
	private PlantillaGrupoAjusteBean plantillagrupoajuste;
	private ProduccionBean produccion;
	private Short ordenPlantillaajusteproducto;

	public Long getPkCodigoPlantillaajusteproduc() {
		return pkCodigoPlantillaajusteproduc;
	}

	public void setPkCodigoPlantillaajusteproduc(Long pkCodigoPlantillaajusteproduc) {
		this.pkCodigoPlantillaajusteproduc = pkCodigoPlantillaajusteproduc;
	}

	public PlantillaGrupoAjusteBean getPlantillagrupoajuste() {
		return plantillagrupoajuste;
	}

	public void setPlantillagrupoajuste(PlantillaGrupoAjusteBean plantillagrupoajuste) {
		this.plantillagrupoajuste = plantillagrupoajuste;
	}

	public ProduccionBean getProduccion() {
		return produccion;
	}

	public void setProduccion(ProduccionBean produccion) {
		this.produccion = produccion;
	}

	public Short getOrdenPlantillaajusteproducto() {
		return ordenPlantillaajusteproducto;
	}

	public void setOrdenPlantillaajusteproducto(Short ordenPlantillaajusteproducto) {
		this.ordenPlantillaajusteproducto = ordenPlantillaajusteproducto;
	}

}
