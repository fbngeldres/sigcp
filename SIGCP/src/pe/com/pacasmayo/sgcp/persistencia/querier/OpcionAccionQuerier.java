package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Opcionaccion;

public class OpcionAccionQuerier extends Querier {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	public static List<Opcionaccion> getAll() throws AplicacionException {

		return Querier.getAll(Opcionaccion.class);
	}

	public static Opcionaccion getById(Long codigo) throws ElementoNoEncontradoException, EntornoEjecucionException {

		return (Opcionaccion) Querier.getById(Opcionaccion.class, codigo);
	}

	public static Opcionaccion obtenerOpcionAccionPorOpcionYAccion(Long codigoOpcion, Long codigoAccion)
			throws AplicacionException {

		Query query = query("from Opcionaccion oa where oa.opcion.pkCodigoOpcion = ? and oa.accion.pkCodigoAccion = ?");
		query.setLong(0, codigoOpcion);
		query.setLong(1, codigoAccion);

		return (Opcionaccion) query.uniqueResult();
	}

	public static List<Opcionaccion> obtenerOpcionAccionPorMenu(Long codigo) throws AplicacionException {

		Query query = query("from Opcionaccion oa where oa.accion.menu.pkCodigoMenu = ? ");
		query.setLong(0, codigo);

		return (List<Opcionaccion>) query.list();
	}

	public static void deleteAllByCodigoMenu(Long codigo) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Query query = query("delete Opcionaccion oa where oa.accion.menu.pkCodigoMenu = ? ");
		query.setLong(0, codigo);
		query.executeUpdate();
	}

	public static void deleteAllById(Long codigo) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Query query = query("delete Opcionaccion oa where oa.pkCodigoOpcionaccion = ? ");
		query.setLong(0, codigo);
		query.executeUpdate();
	}
}
