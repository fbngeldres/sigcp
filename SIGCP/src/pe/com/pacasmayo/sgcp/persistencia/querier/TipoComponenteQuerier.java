package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocomponente;

public class TipoComponenteQuerier extends Querier {

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener todas las Operaciones
	 * 
	 * @return
	 */
	public static List<Tipocomponente> getAll() throws AplicacionException {

		return Querier.getAll(Tipocomponente.class);
	}

	/**
	 * Método para obtener un tipo de componente de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Tipocomponente getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Tipocomponente.class, codigo);
	}
}
