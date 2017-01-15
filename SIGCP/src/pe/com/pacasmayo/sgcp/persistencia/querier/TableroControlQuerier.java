package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablerocontrol;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class TableroControlQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String CODIGO_UNIDAD = "unidad.pkCodigoUnidad";
	private static final String CODIGO_PUESTOTRABAJO = "unidad.pkCodigoUnidad";

	/**
	 * Metodo para obtener la lista de objectos Tablero control
	 * 
	 * @return
	 */
	public static List<Tablerocontrol> getAll() throws AplicacionException {

		return Querier.getAll(Tablerocontrol.class);
	}

	/**
	 * Método para obtener los TableroControl de la BD por unidad.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Tablerocontrol> findByCodigoUnidad(Long codigo) throws AplicacionException {

		try {
			return (List<Tablerocontrol>) Querier.findByProperty(Tablerocontrol.class, CODIGO_UNIDAD, codigo);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static Tablerocontrol getById(Long codigo) throws ElementoNoEncontradoException {

		return (Tablerocontrol) Querier.getById(Tablerocontrol.class, codigo);
	}

	/**
	 * Método para obtener los TableroControl de la BD por unidad.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Tablerocontrol> findByPuestoTrabajoAndUnidad(Long codigoPuestoTrabajo, Long codigoUnidad)
			throws AplicacionException {

		List<Tablerocontrol> tableros = new ArrayList<Tablerocontrol>();

		String consulta = "SELECT tc FROM Tablerocontrol as tc, Tableropuestotrabajo as tpt WHERE "
				+ "tc.pkCodigoTablerocontrol = tpt.tablerocontrol.pkCodigoTablerocontrol AND "
				+ "tpt.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo AND "
				+ "tc.unidad.pkCodigoUnidad = :codigoUnidad";

		try {

			Query query = Querier.query(consulta);
			query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			query.setLong("codigoUnidad", codigoUnidad);

			tableros = query.list();

		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}

		return tableros;
	}
}
