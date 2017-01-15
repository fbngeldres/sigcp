package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Clasificaciontipomovimiento;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class ClasificacionTipoMovimientoQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Clasificaion de tipo de
	 * movimiento
	 * 
	 * @return
	 */
	public static List<Clasificaciontipomovimiento> getAll() throws AplicacionException {

		return Querier.getAll(Clasificaciontipomovimiento.class);
	}

	/**
	 * Método para obtener una Clasificaciontipomovimiento de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Clasificaciontipomovimiento getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Clasificaciontipomovimiento.class, codigo);
	}
}
