package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoMedioAlmacenamientoLogic.java
 * Modificado: Mar 11, 2010 3:58:21 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.bean.TipoMedioAlmacenamientoBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.UtilBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.TipoMedioAlmacenamientoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomedioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoMedioAlmacenamientoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TipoMedioAlmacenamientoDTO;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class TipoMedioAlmacenamientoLogic implements ConstantesFiltros, ConstantesMensajePresentacion,
		ConstantesMensajeAplicacion, TipoMedioAlmacenamientoLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;

	public TipoMedioAlmacenamientoLogic() {

		TipoMedioAlmacenamientoLogic.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.
	 * TipoMedioAlmacenamientoLogicFacade
	 * #obtenerTipoMedioAlmacenamiento(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.
	 * TipoMedioAlmacenamientoLogicFacade
	 * #obtenerTipoMedioAlmacenamiento(java.lang.Long)
	 */
	public TipoMedioAlmacenamientoBean obtenerTipoMedioAlmacenamiento(Long codigotipoMedAlm) throws LogicaException {

		String mensajeError = "";

		TipoMedioAlmacenamientoBean tipoMedioAlmacenamientoBean = null;

		try {
			tipoMedioAlmacenamientoBean = beanFactory.transformarTipoMedioAlmacenamiento(TipoMedioAlmacenamientoQuerier
					.getById(codigotipoMedAlm));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}

		return tipoMedioAlmacenamientoBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.
	 * TipoMedioAlmacenamientoLogicFacade#obtenerTipoMedioAlmacenamientos()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.
	 * TipoMedioAlmacenamientoLogicFacade#obtenerTipoMedioAlmacenamientos()
	 */
	public List<TipoMedioAlmacenamientoBean> obtenerTipoMedioAlmacenamientos() throws LogicaException {

		String mensajeError = "";

		try {
			return beanFactory.transformarListaTipoMedioAlmacenamiento(TipoMedioAlmacenamientoQuerier.getAll());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Tipocomponente.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}

	}

	public List<Tipomedioalmacenamiento> obtenerTiposMedioAlmacenamientoDataObject() throws LogicaException {

		String mensajeError = "";

		try {
			return TipoMedioAlmacenamientoQuerier.getAllPorNombre();
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Tipocomponente.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.
	 * TipoMedioAlmacenamientoLogicFacade#obtenerAtributos()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.
	 * TipoMedioAlmacenamientoLogicFacade#obtenerAtributos()
	 */
	public List<UtilBean> obtenerAtributos() {

		List<UtilBean> filtros = new ArrayList<UtilBean>();

		UtilBean filtro = new UtilBeanImpl();

		filtro.setCodigo(1);
		filtro.setValor("Código");
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(2);
		filtro.setValor("Nombre");
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(3);
		filtro.setValor("Tipo Medio de Almancenamiento");
		filtros.add(filtro);

		return filtros;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.
	 * TipoMedioAlmacenamientoLogicFacade
	 * #obtenerTipoMedioAlmacenamientoPorNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.
	 * TipoMedioAlmacenamientoLogicFacade
	 * #obtenerTipoMedioAlmacenamientoPorNombre(java.lang.String)
	 */
	public List<TipoMedioAlmacenamientoBean> obtenerTipoMedioAlmacenamientoPorNombre(String nombre) throws LogicaException {

		List<TipoMedioAlmacenamientoBean> listaTipoMedioAlmacenamientoBean = null;

		String mensajeError = "";

		try {

			listaTipoMedioAlmacenamientoBean = beanFactory.transformarListaTipoMedioAlmacenamiento(TipoMedioAlmacenamientoQuerier
					.findByNombre(nombre));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Tipocomponente.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}

		return listaTipoMedioAlmacenamientoBean;
	}

	public List<TipoMedioAlmacenamientoDTO> obtenerTiposMedioAlmacenamientoDTO() throws LogicaException {

		return null;
	}

	public Tipomedioalmacenamiento obtenerTipoMedioAlmacenamientoDataObject(Long codigoTipoMedio) throws LogicaException {

		String mensajeError = "";

		Tipomedioalmacenamiento tipoMedio = null;

		try {
			tipoMedio = TipoMedioAlmacenamientoQuerier.getById(codigoTipoMedio);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}

		return tipoMedio;
	}
}