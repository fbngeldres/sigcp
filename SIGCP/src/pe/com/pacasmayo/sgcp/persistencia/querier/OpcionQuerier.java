package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Opcion;

public class OpcionQuerier extends Querier {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	public static List<Opcion> getAll() throws AplicacionException {

		return Querier.getAll(Opcion.class);
	}

	public static Opcion getById(Long codigo) throws ElementoNoEncontradoException, EntornoEjecucionException {

		return (Opcion) Querier.getById(Opcion.class, codigo);
	}
}
