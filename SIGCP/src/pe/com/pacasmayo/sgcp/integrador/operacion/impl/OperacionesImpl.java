package pe.com.pacasmayo.sgcp.integrador.operacion.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import pe.com.pacasmayo.sgcp.excepciones.IntegracionException;
import pe.com.pacasmayo.sgcp.integrador.excepciones.IntegradorExcepcion;
import pe.com.pacasmayo.sgcp.integrador.operacion.OperacionesFacade;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OperacionesImpl.java
 * Modificado: Mar 11, 2011 2:56:19 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class OperacionesImpl implements OperacionesFacade {

	private static final String CONEXION_SCC_PROPERTIES = "conexionScc.properties";
	private static final String PATRON_FECHA = "yyyy-MM-dd";

	// claves de archivo de propiedades para la conexion scc
	private static final String JDBC_PASSWORD_KEY = "jdbc.password";
	private static final String JDBC_URL_KEY = "jdbc.url";
	private static final String JDBC_USER_KEY = "jdbc.user";
	private static final String JDBC_DRIVER_KEY = "jdbc.driver";

	// claves de archivo de propiedades para i18n
	private static final String ERROR_DRIVER = "integrador.conexion.scc.error.driver";
	private static final String ERROR_CONEXION = "integrador.conexion.scc.error.conexion";
	private static final String ERROR_PROPIEDADES = "integrador.conexion.scc.error.propiedades";
	private static final String ERROR_CERRAR_CONEXION = "integrador.conexion.scc.error.cerrar.conexion";

	private static String DRIVER;
	private static String DB_URL;
	private static String USER;
	private static String PASSWORD;

	private String mensajeError = null;

	private Logger logger = Logger.getLogger(OperacionesImpl.class);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.integrador.operacion.OperacionesFacade#
	 * obtenerFactorHumedadSac(java.util.Date, java.util.Date, java.lang.Long,
	 * java.lang.Long)
	 */
	public Double obtenerVariableCalidadMpSac(Date fecha, Long codigoProcesoScc, Long codigoProductoScc, String variableCalDesc)
			throws IntegradorExcepcion {
		Connection con = null;
		try {
			con = getConexionScc();

			SimpleDateFormat formato = new SimpleDateFormat(PATRON_FECHA);

			String patronInicio = "07:00:00";
			String patronFin = "07:00:00";

			String patronReal = "{0} {1}";

			String fechaInicial = MessageFormat.format(patronReal, new Object[] { formato.format(fecha), patronInicio });

			Calendar cal = Calendar.getInstance();
			cal.setTime(fecha);
			cal.add(Calendar.DAY_OF_MONTH, 1);

			String fechaFinal = MessageFormat.format(patronReal, new Object[] { formato.format(cal.getTime()), patronFin });

			StringBuilder query = obtenerQueryVarCalidad(codigoProcesoScc, codigoProductoScc, variableCalDesc, fechaInicial,
					fechaFinal);

			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(query.toString());

			logger.debug("Consulta ejecutada con exito");

			if (rs.next()) {
				
				Double valor = NumberUtil.reducirAdosDecimales(rs.getDouble("valorPromedio"));
				
				System.out.println("OBTUVO VARIABLES SAC --> " +  fechaInicial + " _ " + fechaFinal + " _ " + codigoProcesoScc + " _ " +codigoProductoScc + " _ " +  variableCalDesc+ " = " +valor);
				return valor;
			}

			return 0d;
		} catch (ClassNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_DRIVER);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (SQLException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONEXION);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (IOException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_PROPIEDADES);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CERRAR_CONEXION);
				logger.error(mensajeError, e);
				throw new IntegracionException(mensajeError);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.integrador.operacion.OperacionesFacade#
	 * obtenerPromedioVarCalidadEntreFechas(java.util.Date, java.util.Date,
	 * java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public Double obtenerPromedioVarCalidadMes(Date fechaFinal, Long codigoProcesoScc, Long codigoProductoScc,
			String variableCalDesc) throws IntegradorExcepcion {
		Connection con = null;
		try {
			con = getConexionScc();

			SimpleDateFormat formato = new SimpleDateFormat(PATRON_FECHA);

			String patronInicio = "07:00:00";
			String patronFin = "07:00:00";

			String patronReal = "{0} {1}";

			Calendar calendarFechafinal = Calendar.getInstance();
			calendarFechafinal.setTime(fechaFinal);
			calendarFechafinal.add(Calendar.DAY_OF_MONTH, 1);

			Calendar calendarFechaInicial = Calendar.getInstance();
			calendarFechaInicial.setTime(fechaFinal);
			calendarFechaInicial.set(Calendar.DAY_OF_MONTH, 1);

			String fechaInicialStr = MessageFormat.format(patronReal,
					new Object[] { formato.format(calendarFechaInicial.getTime()), patronInicio });

			String fechaFinalStr = MessageFormat.format(patronReal, new Object[] { formato.format(calendarFechafinal.getTime()),
					patronFin });

			StringBuilder query = obtenerQueryVarCalidad(codigoProcesoScc, codigoProductoScc, variableCalDesc, fechaInicialStr,
					fechaFinalStr);

			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(query.toString());

			logger.debug("Consulta ejecutada con exito");

			if (rs.next()) {
				Double valor = NumberUtil.reducirAdosDecimales(rs.getDouble("valorPromedio"));
				System.out.println("OBTUVO VARIABLES SAC --> " +  fechaInicialStr + " _ " + fechaFinalStr + " _ " + codigoProcesoScc + " _ " +codigoProductoScc + " _ " +  variableCalDesc + " = " +valor);
				
				return valor;
			}

			return 0d;
		} catch (ClassNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_DRIVER);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (SQLException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONEXION);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (IOException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_PROPIEDADES);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CERRAR_CONEXION);
				logger.error(mensajeError, e);
				throw new IntegracionException(mensajeError);
			}
		}
	}

	/**
	 * @param codigoProcesoScc
	 * @param codigoProductoScc
	 * @param variableCalDesc
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	private StringBuilder obtenerQueryVarCalidad(Long codigoProcesoScc, Long codigoProductoScc, String variableCalDesc,
			String fechaInicial, String fechaFinal) {
		// el query contiene comilla(") escapadas ya que si no postgres no
		// reconoce ni tablas ni esquemas
		StringBuilder query = new StringBuilder("SELECT ");

		query.append("AVG(ENSA.\"SCMOE_VLR_PRO\") AS valorPromedio ");

		query.append("FROM ");

		query.append("\"CPSAA\".\"GESAC_MOV_ENSA\" ENSA, ");
		query.append("\"CPSAA\".\"GESAC_MOV_MUES\" MUES, ");
		query.append("\"CPSAA\".\"GESAC_MAE_TIPO_ENSA\" TIPO_ENSA, ");
		query.append("\"CPSAA\".\"GESAC_MAE_GRUP_TIPO_ENSA\" GRUPO_TIPO_ENSA, ");
		query.append("\"CPSAA\".\"GESAC_MAE_MATR_TRAT\" MATR_TRAT ");

		query.append("WHERE ");

		query.append("MUES.\"SCMOM_FCH_HOR_MUE\" >= TIMESTAMP \'" + fechaInicial + "' AND ");
		query.append("MUES.\"SCMOM_FCH_HOR_MUE\" < TIMESTAMP \'" + fechaFinal + "' AND ");

		query.append("ENSA.\"SCMTE_IDE_TIPO_ENSA_K\" = TIPO_ENSA.\"SCMTE_IDE_TIPO_ENSA_K\" AND ");
		query.append("TIPO_ENSA.\"SCMGT_IDE_GRUP_TIPO_ENSA_K\" = GRUPO_TIPO_ENSA.\"SCMGT_IDE_GRUP_TIPO_ENSA_K\" AND ");
		query.append("GRUPO_TIPO_ENSA.\"SCMMT_IDE_MATR_TRAT_K\" = MATR_TRAT.\"SCMMT_IDE_MATR_TRAT_K\" AND ");
		query.append("MATR_TRAT.\"SCMMT_IDE_MATR_TRAT_K\" = MUES.\"SCMMT_IDE_MATR_TRAT_K\"  AND ");
		query.append("MUES.\"SCMOM_IDE_MUES_K\" = ENSA.\"SCMOM_IDE_MUES_K\" AND ");
		query.append("TIPO_ENSA.\"SCMTE_NOM_TIPO_ENSA\" LIKE \'" + variableCalDesc + "\' AND ");
		query.append("MATR_TRAT.\"SCMPC_IDE_PROC_K\" = " + codigoProcesoScc.toString() + " AND ");
		query.append("MATR_TRAT.\"SCMPR_IDE_PROD_K\" = " + codigoProductoScc.toString() + " AND ");
		query.append("ENSA.\"SCMOE_COC_EST_ENSA\" != \'REE\'");
		return query;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.integrador.operacion.OperacionesFacade#
	 * obtenerFactorHumedadSacPorPuestoTrabajo(java.util.Date, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public double obtenerVariableCalidadSacPorPuestoTrabajo(Date fecha, Long codigoProcesoScc, Long codigoProductoScc,
			Long codigoPuestotrabajo, String variableCalDesc) throws IntegracionException {
		Connection con = null;
		try {
			con = getConexionScc();

			SimpleDateFormat formato = new SimpleDateFormat(PATRON_FECHA);

			String patronInicio = "07:00:00";
			String patronFin = "07:00:00";

			String patronReal = "{0} {1}";

			String fechaInicial = MessageFormat.format(patronReal, new Object[] { formato.format(fecha), patronInicio });

			Calendar cal = Calendar.getInstance();
			cal.setTime(fecha);
			cal.add(Calendar.DAY_OF_MONTH, 1);

			String fechaFinal = MessageFormat.format(patronReal, new Object[] { formato.format(cal.getTime()), patronFin });

			// el query contiene comilla(") escapadas ya que si no postgres no
			// reconoce ni tablas ni esquemas
			StringBuilder query = new StringBuilder("SELECT ");

			query.append("AVG(ENSA.\"SCMOE_VLR_PRO\") AS valorPromedio ");

			query.append("FROM ");

			query.append("\"CPSAA\".\"GESAC_MOV_ENSA\" ENSA, ");
			query.append("\"CPSAA\".\"GESAC_MOV_MUES\" MUES, ");
			query.append("\"CPSAA\".\"GESAC_MAE_TIPO_ENSA\" TIPO_ENSA, ");
			query.append("\"CPSAA\".\"GESAC_MAE_GRUP_TIPO_ENSA\" GRUPO_TIPO_ENSA, ");
			query.append("\"CPSAA\".\"GESAC_MAE_MATR_TRAT\" MATR_TRAT ");

			query.append("WHERE ");

			query.append("MUES.\"SCMOM_FCH_HOR_MUE\" >= TIMESTAMP \'" + fechaInicial + "' AND ");
			query.append("MUES.\"SCMOM_FCH_HOR_MUE\" < TIMESTAMP \'" + fechaFinal + "' AND ");

			query.append("ENSA.\"SCMTE_IDE_TIPO_ENSA_K\" = TIPO_ENSA.\"SCMTE_IDE_TIPO_ENSA_K\" AND ");
			query.append("TIPO_ENSA.\"SCMGT_IDE_GRUP_TIPO_ENSA_K\" = GRUPO_TIPO_ENSA.\"SCMGT_IDE_GRUP_TIPO_ENSA_K\" AND ");
			query.append("GRUPO_TIPO_ENSA.\"SCMMT_IDE_MATR_TRAT_K\" = MATR_TRAT.\"SCMMT_IDE_MATR_TRAT_K\" AND ");
			query.append("MATR_TRAT.\"SCMMT_IDE_MATR_TRAT_K\" = MUES.\"SCMMT_IDE_MATR_TRAT_K\"  AND ");
			query.append("MUES.\"SCMOM_IDE_MUES_K\" = ENSA.\"SCMOM_IDE_MUES_K\" AND ");
			query.append("TIPO_ENSA.\"SCMTE_NOM_TIPO_ENSA\" LIKE \'" + variableCalDesc + "\' AND ");
			query.append("MATR_TRAT.\"SCMPC_IDE_PROC_K\" = " + codigoProcesoScc.toString() + " AND ");
			query.append("MATR_TRAT.\"SCMPR_IDE_PROD_K\" = " + codigoProductoScc.toString() + " AND ");
			query.append("MUES.\"SCMEQ_IDE_EQUI_K\" = " + codigoPuestotrabajo.toString());
			query.append(" AND ENSA.\"SCMOE_COC_EST_ENSA\" != \'REE\'");
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(query.toString());

			logger.debug("Consulta ejecutada con exito");

			if (rs.next()) {
				Double valor = NumberUtil.reducirAdosDecimales(rs.getDouble("valorPromedio"));
				System.out.println("OBTUVO VARIABLES SAC --> " +  fechaInicial + " _ " + fechaFinal + " _ " + codigoProcesoScc + " _ " +codigoProductoScc + " _ " +  variableCalDesc + " = " +valor);
				
				
				return valor;
			}

			return 0d;
		} catch (ClassNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_DRIVER);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (SQLException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONEXION);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (IOException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_PROPIEDADES);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CERRAR_CONEXION);
				logger.error(mensajeError, e);
				throw new IntegracionException(mensajeError);
			}
		}
	}

	/**
	 * Devuelve una conexion con la BD SCC
	 * 
	 * @return Connection
	 * @throws IOException si no se puede cargar el archivo de propiedas de la
	 *             BD
	 * @throws ClassNotFoundException si no se encuentra el driver
	 * @throws SQLException si ocurre un error intentando conectar o hay algun
	 *             de error de sintaxis en SQL
	 */
	private Connection getConexionScc() throws IOException, ClassNotFoundException, SQLException {
		Connection connection = null;
		inicializarPropiedadesConexionScc();

		Class.forName(DRIVER);
		connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		return connection;
	}

	/**
	 * Inicializa los atributos de la clase, DRIVER, USER, DB_URL y PASSWORD con
	 * los valores obtenidos del archivo de propiedades de la conexion a scc
	 * 
	 * @throws IOException si ocurre un error cargando el archivo de propiedades
	 */
	private void inicializarPropiedadesConexionScc() throws IOException {
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream(CONEXION_SCC_PROPERTIES));
		DRIVER = props.getProperty(JDBC_DRIVER_KEY);
		USER = props.getProperty(JDBC_USER_KEY);
		DB_URL = props.getProperty(JDBC_URL_KEY);
		PASSWORD = props.getProperty(JDBC_PASSWORD_KEY);
	}

	public List getCantidadEnsayos(String GrupoEnsayo, Integer anio, Short mes, Long miarea, Long idProducto, Long idProceso,
			Long idPuestoTrabajo) throws IntegradorExcepcion {
		Connection con = null;
		try {

			// ///*************John Agregue
			Date fechaInicio = null;

			List Listaux = new ArrayList();

			// ///***********

			con = getConexionScc();

			SimpleDateFormat formato = new SimpleDateFormat(PATRON_FECHA);

			// /"CPSAA"."GESAC_MOV_ENSA"

			// el query contiene comilla(") escapadas ya que si no postgres no
			// reconoce ni tablas ni esquemas //

			StringBuilder query = new StringBuilder("SELECT ");
			// query.append("ENSA.\"SCMOE_FCH_HRA_REG\" AS fechaRegistro ");
			// SELECT count(ENSA."SCMOE_IDE_ENSA_K") AS ID_MOV_TIPO_ENSAYO
			query.append(" COUNT(ENSA.\"SCMOE_IDE_ENSA_K\") AS ID_MOV_TIPO_ENSAYO ");

			query.append(" FROM ");
			query.append("\"CPSAA\".\"GESAC_MOV_ENSA\" ENSA ");

			query.append(" INNER JOIN  \"CPSAA\".\"GESAC_MAE_TIPO_ENSA\"  TIPO_ENSA  ");

			query.append("  ON  (ENSA.\"SCMTE_IDE_TIPO_ENSA_K\"=TIPO_ENSA.\"SCMTE_IDE_TIPO_ENSA_K\")  ");

			query.append("  INNER JOIN  \"CPSAA\".\"GESAC_MAE_GRUP_TIPO_ENSA\" GRUPO_TIPO_ENSA   ");// Grupo
			// de
			// tipo
			// de
			// ensayo
			// //"SCMGT_COC_CAT_TIPO_ENSA"

			query.append("  ON (TIPO_ENSA.\"SCMGT_IDE_GRUP_TIPO_ENSA_K\" = GRUPO_TIPO_ENSA.\"SCMGT_IDE_GRUP_TIPO_ENSA_K\" )   ");

			query.append(" 	INNER JOIN  \"CPSAA\".\"GESAC_MAE_MATR_TRAT\" MATR_TRAT  ");

			query.append(" 	ON (GRUPO_TIPO_ENSA.\"SCMMT_IDE_MATR_TRAT_K\" = MATR_TRAT.\"SCMMT_IDE_MATR_TRAT_K\" )   ");

			// query.append(" INNER JOIN \"CPSAA\".\"GESAC_MOV_MUES\" MUES ");

			// query.append(" ON (MATR_TRAT.\"SCMMT_IDE_MATR_TRAT_K\" =
			// MUES.\"SCMMT_IDE_MATR_TRAT_K\") ");

			query.append(" ,  \"CPSAA\".\"GESAC_MAE_PROC\"  PROCESO  ");

			query.append(" ,  \"CPSAA\".\"GESAC_MAE_EQUI\"  EQUIPO  ");

			query.append(" ,  \"CPSAA\".\"GESAC_DET_RESP\"  DET_RESP  ");// ////

			query.append(" ,  \"CPSAA\".\"GESAC_DET_ALCA\"  DET_ALCA  ");

			query.append(" 	  WHERE    ");

			query.append("    GRUPO_TIPO_ENSA.\"SCMGT_COC_CAT_TIPO_ENSA\" = '" + GrupoEnsayo.trim() + "'    AND  ");

			// query.append("ENSA.\"SCMOE_FCH_HRA_REG\" <= '03/31/2006'
			// ::TIMESTAMP ");

			// query.append(" AND ENSA.\"SCMOE_FCH_HRA_REG\" >= '03/01/2006'
			// ::TIMESTAMP ");

			query.append("   DATE_PART('month',ENSA.\"SCMOE_FCH_HRA_REG\" ) = " + mes);

			query.append(" AND  DATE_PART('year',ENSA.\"SCMOE_FCH_HRA_REG\" ) = " + anio);

			if (idProceso != null && !idProceso.toString().trim().equals("")) {
				query.append(" AND    MATR_TRAT.\"SCMPC_IDE_PROC_K\" = " + idProceso.toString());

			}

			if (idProducto != null && !idProducto.toString().trim().equals("")) {

				query.append("  AND   MATR_TRAT.\"SCMPR_IDE_PROD_K\" = " + idProducto.toString());

			}

			// String grupoTipoEnsayo=""; // esta y la otra linea debes usarlas

			// query.append(" AND GRUPO_TIPO_ENSA.\"SCMGT_COC_CAT_TIPO_ENSA\" =
			// " + grupoTipoEnsayo.trim() );

			query.append(" AND  MATR_TRAT.\"SCMPC_IDE_PROC_K\"=PROCESO.\"SCMPC_IDE_PROC_K\"   ");

			query.append(" AND  PROCESO.\"SCMPC_IDE_PROC_K\"=EQUIPO.\"SCMPC_IDE_PROC_K\"   ");

			query.append(" AND  EQUIPO.\"SCMPC_IDE_PROC_K\"=DET_RESP.\"SCMPC_IDE_PROC_K\"   ");

			query.append(" AND  DET_RESP.\"SCDRE_IDE_RESP_K\"=DET_ALCA.\"SCDRE_IDE_RESP_K\"  ");

			if (idPuestoTrabajo != null && !idPuestoTrabajo.toString().trim().equals("")) {

				query.append(" AND  DET_ALCA.\"SCDEA_COC_PUE_TRA\" = '" + idPuestoTrabajo + "'");

			}

			// ///////////////////////////////////////////////////////////////
			// "SCMOE_VLR_PRO"---->parece que este te da la cantidad de muestras
			// que piden

			logger.debug("consulta: " + query.toString());

			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(query.toString());

			logger.debug("Consulta ejecutada con exito");

			while (rs.next()) {

				Listaux.add(rs.getArray("ID_MOV_TIPO_ENSAYO"));
				// throw new IntegracionException("No se encontro factor de
				// humedad para los datos especificados");
			}

			// return rs.getDouble("valorPromedio");

			return Listaux;

		} catch (ClassNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_DRIVER);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (SQLException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONEXION);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (IOException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_PROPIEDADES);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CERRAR_CONEXION);
				logger.error(mensajeError, e);
				throw new IntegracionException(mensajeError);
			}
		}

	}

	/**
	 * Jordan Torres (non-Javadoc)
	 * 
	 * @throws ParseException
	 * @see pe.com.pacasmayo.sgcp.integrador.operacion.OperacionesFacade#obtenerPromedioVarCalidadRango(java.util.Date,
	 *      java.util.Date, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      java.lang.String)
	 * @return List<Object[]>
	 */
	public List<Object[]> obtenerPromedioVarCalidadRangoFechasReporte(Date fechaInicial, Date fechaFinal, Long codigoProcesoScc,
			Long codigoPuestoTrabajoScc, Long codigoProductoScc, String variableCalDesc) throws IntegradorExcepcion {
		Connection con = null;
		try {
			con = getConexionScc();
			SimpleDateFormat formato = new SimpleDateFormat(PATRON_FECHA);

			String patronInicio = "07:00:00";
			String patronFin = "07:00:00";

			String patronReal = "{0} {1}";

			String fechaInicio = MessageFormat.format(patronReal, new Object[] { formato.format(fechaInicial), patronInicio });
			String fechaFin = MessageFormat.format(patronReal, new Object[] { formato.format(fechaFinal), patronFin });

			StringBuilder query = obtenerVarCalidadPuestoTrabProcesoProducto(codigoProcesoScc, codigoPuestoTrabajoScc,
					codigoProductoScc, variableCalDesc, fechaInicio, fechaFin);

			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(query.toString());

			logger.debug("Consulta ejecutada con exito");

			List<Object[]> datos = new ArrayList<Object[]>();
			Object[] valores;
			while (rs.next()) {
				valores = new Object[6];
				valores[0] = rs.getInt("codigoProducto");
				valores[1] = rs.getString("nombreProducto");
				valores[2] = rs.getString("fechaMuestraTimestamp");
				valores[3] = rs.getString("nombreTipo");
				valores[4] = rs.getDouble("valor");
				valores[5] = rs.getInt("idEquipo");

				datos.add(valores);
			}

			return datos;
		} catch (ClassNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_DRIVER);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (SQLException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONEXION);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (IOException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_PROPIEDADES);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CERRAR_CONEXION);
				logger.error(mensajeError, e);
				throw new IntegracionException(mensajeError);
			}
		}
	}

	/**
	 * Jordan Torres "Reporte de Datos de Calidad"
	 * 
	 * @param codigoProcesoScc
	 * @param codigoPuestoTrabajoScc
	 * @param codigoProductoScc
	 * @param variableCalDesc
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return StringBuilder
	 */
	private StringBuilder obtenerVarCalidadPuestoTrabProcesoProducto(Long codigoProcesoScc, Long codigoPuestoTrabajoScc,
			Long codigoProductoScc, String variableCalDesc, String fechaInicial, String fechaFinal) {
		// el query contiene comilla(") escapadas ya que si no postgres no
		// reconoce ni tablas ni esquemas
		StringBuilder query = new StringBuilder("SELECT ");

		query.append("MAE_MATR_TRAT.\"SCMPR_IDE_PROD_K\" AS codigoProducto, ");
		query.append("MAE_PROD.\"SCMPR_NOM_PROD\" AS nombreProducto, ");
		query.append("to_date(to_char(MUES_ENSA.\"SCMOM_FCH_HOR_MUE\" - interval "
				+ "\'07:00:00\', \'DD-MM-YYYY\'), \'dd-mm-yyyy\') as fechaMuestraTimestamp, ");
		query.append("AVG(MUES_ENSA.\"SCMOE_VLR_PRO\") AS valor, ");
		query.append("MAE_TIPO_ENSA.\"SCMTE_NOM_TIPO_ENSA\" AS nombreTipo, ");
		query.append("MUES_ENSA.\"SCMEQ_IDE_EQUI_K\" AS idEquipo ");

		query.append("FROM ");

		query.append("(SELECT ");
		query.append("\"CPSAA\".\"GESAC_MOV_MUES\".\"SCMEQ_IDE_EQUI_K\", ");
		query.append("\"CPSAA\".\"GESAC_MOV_ENSA\".\"SCMTE_IDE_TIPO_ENSA_K\", ");
		query.append("\"CPSAA\".\"GESAC_MOV_MUES\".\"SCMMT_IDE_MATR_TRAT_K\", ");
		query.append("\"CPSAA\".\"GESAC_MOV_ENSA\".\"SCMOE_VLR_PRO\", ");
		query.append("\"CPSAA\".\"GESAC_MOV_MUES\".\"SCMOM_FCH_HOR_MUE\" ");
		query.append("FROM ");
		query.append("\"CPSAA\".\"GESAC_MOV_ENSA\" JOIN \"CPSAA\".\"GESAC_MOV_MUES\" ");
		query.append("ON \"CPSAA\".\"GESAC_MOV_ENSA\".\"SCMOM_IDE_MUES_K\" = \"CPSAA\".\"GESAC_MOV_MUES\".\"SCMOM_IDE_MUES_K\" ");
		query.append("WHERE 1 = 1 ");
		query.append("AND \"CPSAA\".\"GESAC_MOV_MUES\".\"SCMOM_FCH_HOR_MUE\" >= '" + fechaInicial + "' ");
		query.append("AND \"CPSAA\".\"GESAC_MOV_MUES\".\"SCMOM_FCH_HOR_MUE\" < '" + fechaFinal + "' ");
		query.append("AND \"CPSAA\".\"GESAC_MOV_ENSA\".\"SCMOE_COC_EST_ENSA\" = \'NOR\' ");
		query.append("AND \"CPSAA\".\"GESAC_MOV_MUES\".\"SCMEQ_IDE_EQUI_K\" = " + codigoPuestoTrabajoScc.toString() + ") ");

		query.append("MUES_ENSA, ");
		query.append(" \"CPSAA\".\"GESAC_MAE_MATR_TRAT\" MAE_MATR_TRAT, ");
		query.append(" \"CPSAA\".\"GESAC_MAE_TIPO_ENSA\" MAE_TIPO_ENSA, ");
		query.append(" \"CPSAA\".\"GESAC_MAE_PROD\" MAE_PROD ");

		query.append("WHERE ");

		query.append("MAE_MATR_TRAT.\"SCMMT_COC_EST\" = \'ACT\' ");
		query.append("AND MAE_TIPO_ENSA.\"SCMTE_COC_EST\" != \'ELI\' ");
		query.append("AND MAE_PROD.\"SCMPR_COC_EST\"  = \'ACT\' ");
		query.append("AND MAE_MATR_TRAT.\"SCMMT_IDE_MATR_TRAT_K\" = MUES_ENSA.\"SCMMT_IDE_MATR_TRAT_K\" ");
		query.append("AND MAE_TIPO_ENSA.\"SCMTE_IDE_TIPO_ENSA_K\" = MUES_ENSA.\"SCMTE_IDE_TIPO_ENSA_K\" ");
		query.append("AND MAE_PROD.\"SCMPR_IDE_PROD_K\" = MAE_MATR_TRAT.\"SCMPR_IDE_PROD_K\" ");
		query.append("AND MAE_MATR_TRAT.\"SCMPC_IDE_PROC_K\" = " + codigoProcesoScc.toString() + " ");
		query.append("AND MAE_MATR_TRAT.\"SCMPR_IDE_PROD_K\" = " + codigoProductoScc.toString() + " ");
		query.append("AND MAE_TIPO_ENSA.\"SCMTE_NOM_TIPO_ENSA\" LIKE \'%" + variableCalDesc.toString() + "%\' ");

		query.append("GROUP BY ");
		query.append("MAE_MATR_TRAT.\"SCMPR_IDE_PROD_K\", ");
		query.append("MAE_PROD.\"SCMPR_NOM_PROD\", ");
		query.append("fechaMuestraTimestamp, ");
		query.append("nombreTipo, idEquipo ");
		query.append("order by (fechaMuestraTimestamp)");

		return query;
	}

	/**
	 * Jordan Torres (non-Javadoc)
	 * 
	 * @throws ParseException
	 * @see pe.com.pacasmayo.sgcp.integrador.operacion.OperacionesFacade#obtenerVarCalidadReportePorProductovarcalidad(java.util.Date,
	 *      java.util.Date, java.lang.Long, java.lang.Long, java.lang.String)
	 * @return List<Object[]>
	 */
	public List<Object[]> obtenerVarCalidadReportePorProductovarcalidad(Date fechaInicial, Date fechaFinal,
			Long codigoProcesoScc, Long codigoMatrixScc, String variableCalDesc) throws IntegradorExcepcion {
		Connection con = null;
		try {
			con = getConexionScc();

			SimpleDateFormat formato = new SimpleDateFormat(PATRON_FECHA);

			String patronInicio = "07:00:00";
			String patronFin = "07:00:00";

			String patronReal = "{0} {1}";

			String fechaInicio = MessageFormat.format(patronReal, new Object[] { formato.format(fechaInicial), patronInicio });
			String fechaFin = MessageFormat.format(patronReal, new Object[] { formato.format(fechaFinal), patronFin });

			StringBuilder query = obtenerQueryReporteProductoVarCalidad(codigoProcesoScc, codigoMatrixScc, variableCalDesc,
					fechaInicio, fechaFin);

			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(query.toString());

			logger.debug("Consulta ejecutada con exito");

			List<Object[]> datos = new ArrayList<Object[]>();
			Object[] valores;
			while (rs.next()) {
				valores = new Object[2];
				valores[0] = rs.getDouble("valor");
				valores[1] = rs.getString("fechaMuestraTimestamp");
				datos.add(valores);
			}

			return datos;
		} catch (ClassNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_DRIVER);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (SQLException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONEXION);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} catch (IOException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_PROPIEDADES);
			logger.error(mensajeError, e);
			throw new IntegracionException(mensajeError);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CERRAR_CONEXION);
				logger.error(mensajeError, e);
				throw new IntegracionException(mensajeError);
			}
		}
	}

	/**
	 * Jordan Torres "Reporte de Datos de Calidad"
	 * 
	 * @param codigoProcesoScc
	 * @param codigoMatrixScc
	 * @param variableCalDesc
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	private StringBuilder obtenerQueryReporteProductoVarCalidad(Long codigoProcesoScc, Long codigoMatrixScc,
			String variableCalDesc, String fechaInicial, String fechaFinal) {
		// el query contiene comilla(") escapadas ya que si no postgres no
		// reconoce ni tablas ni esquemas
		StringBuilder query = new StringBuilder("SELECT ");

		query.append("AVG(ENSA.\"SCMOE_VLR_PRO\") AS valor, ");
		query.append("to_date(to_char(MUES.\"SCMOM_FCH_HOR_MUE\" - interval "
				+ "\'07:00:00\', \'DD-MM-YYYY\'), \'dd-mm-yyyy\') AS fechaMuestraTimestamp ");

		query.append("FROM ");

		query.append("\"CPSAA\".\"GESAC_MOV_ENSA\" ENSA, ");
		query.append("\"CPSAA\".\"GESAC_MOV_MUES\" MUES, ");
		query.append("\"CPSAA\".\"GESAC_MAE_TIPO_ENSA\" TIPO_ENSA, ");
		query.append("\"CPSAA\".\"GESAC_MAE_GRUP_TIPO_ENSA\" GRUPO_TIPO_ENSA, ");
		query.append("\"CPSAA\".\"GESAC_MAE_MATR_TRAT\" MATR_TRAT ");

		query.append("WHERE ");

		query.append("MUES.\"SCMOM_FCH_HOR_MUE\" >= TIMESTAMP \'" + fechaInicial + "' AND ");
		query.append("MUES.\"SCMOM_FCH_HOR_MUE\" < TIMESTAMP \'" + fechaFinal + "' AND ");

		query.append("ENSA.\"SCMTE_IDE_TIPO_ENSA_K\" = TIPO_ENSA.\"SCMTE_IDE_TIPO_ENSA_K\" AND ");
		query.append("TIPO_ENSA.\"SCMGT_IDE_GRUP_TIPO_ENSA_K\" = GRUPO_TIPO_ENSA.\"SCMGT_IDE_GRUP_TIPO_ENSA_K\" AND ");
		query.append("GRUPO_TIPO_ENSA.\"SCMMT_IDE_MATR_TRAT_K\" = MATR_TRAT.\"SCMMT_IDE_MATR_TRAT_K\" AND ");
		query.append("MATR_TRAT.\"SCMMT_IDE_MATR_TRAT_K\" = MUES.\"SCMMT_IDE_MATR_TRAT_K\"  AND ");
		query.append("MUES.\"SCMOM_IDE_MUES_K\" = ENSA.\"SCMOM_IDE_MUES_K\" AND ");
		query.append("TIPO_ENSA.\"SCMTE_NOM_TIPO_ENSA\" LIKE \'" + variableCalDesc + "\' AND ");
		query.append("MATR_TRAT.\"SCMPC_IDE_PROC_K\" = " + codigoProcesoScc.toString() + " AND ");
		query.append("MATR_TRAT.\"SCMPR_IDE_PROD_K\" = " + codigoMatrixScc.toString() + " AND ");
		query.append("ENSA.\"SCMOE_COC_EST_ENSA\" != \'REE\'");

		query.append("GROUP BY fechaMuestraTimestamp ");
		query.append("ORDER BY (fechaMuestraTimestamp)");
		return query;
	}

}
