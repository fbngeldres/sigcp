package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocategoriaproducto;

public class TipoCategoriaProductoQuerier extends Querier {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Tipocategoriaproducto
	 * 
	 * @return
	 */
	public static List getAll() throws AplicacionException {

		return Querier.getAll(Tipocategoriaproducto.class);
	}

	/**
	 * Método para obtener un Tipocategoriaproducto de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Tipocategoriaproducto getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Tipocategoriaproducto.class, codigo);
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

}
