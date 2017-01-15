package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Partediario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccionpuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProduccionPuestoTrabajoQuerier.java
 * Modificado: Mar 24, 2011 3:18:53 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ProduccionPuestoTrabajoQuerier extends Querier {

	private static final String PARTE_DIARIO = "partediario.pkCodigoPartediario";
	private static final String PUESTO_TRABAJO = "puestotrabajo.pkCodigoPuestotrabajo";

	private static Logger log = Logger.getLogger(ProduccionPuestoTrabajoQuerier.class);

	public static Produccionpuestotrabajo obtenerProduccionPuestoTrabajo(Partediario partediario, Puestotrabajo puestotrabajo)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		String consulta = "FROM {0} AS pc where pc.{1} = :codigoParteDiario AND pc.{2} = :codigoOrdenProduccion";
		consulta = MessageFormat.format(consulta, new Object[] { Produccionpuestotrabajo.class.getName(), PARTE_DIARIO,
				PUESTO_TRABAJO });

		try {
			Query query = Querier.query(consulta);
			query.setLong("codigoParteDiario", partediario.getPkCodigoPartediario());
			query.setLong("codigoOrdenProduccion", puestotrabajo.getPkCodigoPuestotrabajo());

			Produccionpuestotrabajo produccionpuestotrabajo = (Produccionpuestotrabajo) query.uniqueResult();
			return produccionpuestotrabajo;

		} catch (UnresolvableObjectException uOException) {
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new EntornoEjecucionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Produccionpuestotrabajo> getByPartediario(Long codigoParteDiario) throws ElementoNoEncontradoException,
			EntornoEjecucionException {
		String consulta = "FROM Produccionpuestotrabajo AS ppt where ppt.partediario.pkCodigoPartediario = :codigoParteDiario ";

		try {
			Query query = Querier.query(consulta);
			query.setLong("codigoParteDiario", codigoParteDiario);

			return query.list();

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	public static void eliminarFechaLineaNegocio(Long lineaNegocio, Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer mes = cal.get(Calendar.MONTH) + 1;
		Integer anio = cal.get(Calendar.YEAR);

		StringBuilder querystr = new StringBuilder("DELETE FROM Produccionpuestotrabajo ppt ");
		querystr.append(" WHERE ppt.partediario IN (SELECT pt FROM Partediario pt WHERE pt.periodocontable.anoPeriodocontable=:anio");
		querystr.append(" AND  pt.periodocontable.mesPeriodocontable=:mes");
		querystr.append(" AND  pt.lineanegocio.pkCodigoLineanegocio=:lineaNegocio)");

		Query query = Querier.query(querystr.toString());
		query.setShort("mes", mes.shortValue());
		query.setInteger("anio", anio);
		query.setLong("lineaNegocio", lineaNegocio);
		query.executeUpdate();

	}

}
