package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.PeriodoContableBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PeriodoContableLogicFacade.java
 * Modificado: Jun 1, 2010 4:03:37 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface PeriodoContableLogicFacade {

	/**
	 * @param mes
	 * @param ano
	 * @return
	 * @throws LogicaException
	 */
	public abstract PeriodoContableBean getPeriodoContablePorFecha(short mes, int ano) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Integer> obtenerAnosPorPeriodoAbierto() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<String> obtenerMesesPorPeriodoAbierto() throws LogicaException;

	/**
	 * @throws LogicaException
	 */
	public void registrarNuevoPeriodoContable(int year, Short month) throws LogicaException;

	public abstract List<Integer> obtenerAnosPorPeriodo() throws LogicaException;
}
