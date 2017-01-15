package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoajusteproducto;

public class EstadoAjusteProductoQuerier extends Querier {

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * M�todo para obtener una lista de Estadoajusteproducto
	 * 
	 * @return
	 */
	public static List<Estadoajusteproducto> getAll() {

		return Querier.getAll(Estadoajusteproducto.class);
	}

	/**
	 * M�todo para obtener una lista de Estadoajusteproducto, ordenados por un
	 * atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Estadoajusteproducto> getAllOrderBy(String order) {

		return Querier.getAll(Estadoajusteproducto.class, order);
	}

	/**
	 * M�todo para obtener el estado por Nombre
	 * 
	 * @param name
	 * @return
	 */
	public static Estadoajusteproducto findByName(String name) {

		List<Estadoajusteproducto> estados = Querier.findByProperty(Estadoajusteproducto.class, "nombreEstadoajusteproducto",
				name);

		if (estados != null && estados.size() > 0)
			return estados.get(0);
		else
			return null;
	}

	/**
	 * M�todo para obtener un Estadoajusteproducto de la BD por c�digo.
	 * 
	 * @param codigo
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Estadoajusteproducto getById(Long codigo) throws ElementoNoEncontradoException {

		return (Estadoajusteproducto) Querier.getById(Estadoajusteproducto.class, codigo);

	}
}
