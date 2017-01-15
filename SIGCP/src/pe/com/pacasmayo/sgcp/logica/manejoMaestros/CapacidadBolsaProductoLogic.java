package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.bean.CapacidadBolsaProductoBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.CapacidadBolsaProductoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Capacidadbolsaproducto;
import pe.com.pacasmayo.sgcp.persistencia.querier.CapacidadBolsaProductoQuerier;

/*
 * Backlog 
 * Archivo: CapacidadBolsaProductoLogic.java
 * Modificado: Mar 18, 2014 5:46:53 PM
 * Autor: ginteldos
 *
 * Copyright (C) Gintelligence, 2012. All rights reserved.
 *
 * Developed by: Gintelligence. http://www.gintelligence.net
 */
public class CapacidadBolsaProductoLogic implements CapacidadBolsaProductoLogicFacade {
	private BeanFactory beanFactory;
	private Log logger = LogFactory.getLog(this.getClass());

	public CapacidadBolsaProductoLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	public CapacidadBolsaProductoBean obtenerPorCodigoSap(String codigoSap) throws LogicaException {

		CapacidadBolsaProductoBean bolsaProductoBean = null;
		Capacidadbolsaproducto capacidadbolsaproducto;
		try {
			capacidadbolsaproducto = CapacidadBolsaProductoQuerier.obtenerByCodigoSap(codigoSap);

			bolsaProductoBean = beanFactory.transformarCapacidadBolsaProduccion(capacidadbolsaproducto);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e);
			throw new LogicaException(e.getMensaje(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new LogicaException(e.getMessage(), e);
		}

		return bolsaProductoBean;
	}

}
