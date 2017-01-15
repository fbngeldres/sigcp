package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Nivelmenu;

public class NivelMenuQuerier extends Querier {

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * M�todo para obtener una lista de Nivelmenu
	 * 
	 * @return
	 */
	public static List<Nivelmenu> getAll() throws AplicacionException {

		return Querier.getAll(Nivelmenu.class);
	}

	/**
	 * M�todo para obtener un Nivelmenu de la BD por c�digo.
	 * 
	 * @param codigo
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Nivelmenu getById(Long codigo) throws ElementoNoEncontradoException {

		return (Nivelmenu) Querier.getById(Nivelmenu.class, codigo);

	}
}