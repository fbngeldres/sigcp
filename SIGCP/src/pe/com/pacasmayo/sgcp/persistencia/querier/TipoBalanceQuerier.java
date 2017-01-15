package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipobalance;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;

public class TipoBalanceQuerier extends Querier {

	/**
	 * Método para obtener la lista de objectos ajuste de produccion
	 * 
	 * @return
	 */
	public static List<Tipobalance> getAll() {

		return Querier.getAll(Tipobalance.class);
	}

	/**
	 * Método para obtener un ajuste de produccion de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Tipobalance getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Tipobalance.class, codigo);
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar un Tipobalance en la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Tipobalance tipobalance) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(tipobalance);
	}

	/**
	 * Metodo para modificar un Tipobalance de la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Tipobalance tipobalance) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(tipobalance);
	}

	/**
	 * Método para eliminar un Tipobalance de la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Tipobalance tipobalance) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(tipobalance);
	}

}
