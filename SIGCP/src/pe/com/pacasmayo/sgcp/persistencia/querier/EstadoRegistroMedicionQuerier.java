package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoregistromedicion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class EstadoRegistroMedicionQuerier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Estadoregistromedicion
	 * 
	 * @return
	 */
	public static List<Estadoregistromedicion> getAll() throws AplicacionException {

		return Querier.getAll(Estadoregistromedicion.class);
	}

	/**
	 * Método para obtener la lista de objetos Estadoregistromedicion, ordenados
	 * por un campo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Estadoregistromedicion> getAllOrderBy(String order) {

		return Querier.getAll(Estadoregistromedicion.class, order);
	}

	/**
	 * Método para obtener una Estadoregistromedicion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Estadoregistromedicion getById(Long codigo) throws ElementoNoEncontradoException {

		return (Estadoregistromedicion) Querier.getById(Estadoregistromedicion.class, codigo);
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Estadoregistromedicion en la BD.
	 * 
	 * @param Estadoregistromedicion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Estadoregistromedicion Estadoregistromedicion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(Estadoregistromedicion);
	}

	/**
	 * Metodo para modificar una Estadoregistromedicion de la BD.
	 * 
	 * @param Estadoregistromedicion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Estadoregistromedicion Estadoregistromedicion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(Estadoregistromedicion);
	}

	/**
	 * Metodo para eliminar una Estadoregistromedicion de la BD.
	 * 
	 * @param Estadoregistromedicion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Estadoregistromedicion Estadoregistromedicion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(Estadoregistromedicion);
	}

}
