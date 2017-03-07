package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registroreporteecs;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class RegistroReporteECSQuerier extends Querier implements ConstantesMensajeAplicacion {

	private static final String NOMBRE_REGISTRO_REPORTE_ECS = "nombreRegistroreporteecs";

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar un reporte ECS en la BD.
	 * 
	 * @param reporteECS
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Registroreporteecs reporteECS) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(reporteECS);
	}

	/**
	 * Método para modificar un reporte ECS en la BD.
	 * 
	 * @param reporteECS
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Registroreporteecs reporteECS) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(reporteECS);
	}

	/**
	 * Método para eliminar un reporte ECS en la BD.
	 * 
	 * @param reporteECS
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Registroreporteecs reporteECS) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(reporteECS);
	}

	/**
	 * Método para obtener una Registroreporteecs de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Registroreporteecs getById(Long codigo) throws ElementoNoEncontradoException {

		return (Registroreporteecs) Querier.getById(Registroreporteecs.class, codigo);
	}

	/**
	 * Método para obtener los reportes registrados de la BD por nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static List<Registroreporteecs> findByNombre(String nombre) throws ElementoNoEncontradoException {

		return Querier.getByLikeStringPropertie(Registroreporteecs.class, NOMBRE_REGISTRO_REPORTE_ECS, nombre);

	}

	public static Registroreporteecs obtenerRegistroReporteDO(Date fecha, String nombre) throws AplicacionException {
		String queryStr = "SELECT rre FROM Registroreporteecs as rre" + "WHERE rre.fechaRegistroreporteecs = :fecha"
				+ "AND upper(rre.nombreRegistroreporteecs) like :nombreReporte";

		Query query = Querier.query(queryStr);
		query.setDate("fecha", fecha);
		query.setString("nombreReporte", nombre);
		try {
			return (Registroreporteecs) query.uniqueResult();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}
}
