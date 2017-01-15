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
	 * M�todo para obtener la lista de objectos ajuste de produccion
	 * 
	 * @return
	 */
	public static List<Tipobalance> getAll() {

		return Querier.getAll(Tipobalance.class);
	}

	/**
	 * M�todo para obtener un ajuste de produccion de la BD por el c�digo.
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
	 * M�todo para Insertar un Tipobalance en la BD.
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
	 * M�todo para eliminar un Tipobalance de la BD.
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
