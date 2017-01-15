package pe.com.pacasmayo.sgcp.persistencia.querier;

import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Operacioncomponente;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;

public class OperacionComponenteQuerier extends Querier {

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * M�todo para obtener un Operacioncomponente de la BD por el c�digo.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Operacioncomponente getById(Long codigo) throws ElementoNoEncontradoException {

		return (Operacioncomponente) Querier.getById(Operacioncomponente.class, codigo);
	}

	public static Operacioncomponente getByOperacionHojaRutaComponente(Long codigoOperacion, Long codigoHojaRutaComponente) {

		String consulta = "from Operacioncomponente opcomp " + "where opcomp.operacion.pkCodigoOperacion = ? "
				+ "and opcomp.hojarutacomponente.pkCodigoHojarutacomponente = ?  ";

		Query query = Querier.query(consulta);
		query.setLong(0, codigoOperacion);
		query.setLong(1, codigoHojaRutaComponente);

		return (Operacioncomponente) query.uniqueResult();
	}

	/**
	 * M�todo para Insertar una Operaci�n Componente en la BD.
	 * 
	 * @param operacionComponente
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Operacioncomponente operacionComponente) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(operacionComponente);
	}

	/**
	 * M�todo para eliminar una lista de Operaciones Componentes de la BD, por
	 * medio del c�digo de la hoja de ruta.
	 * 
	 * @param codigoHojaRuta
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 */
	public static void deleteByCodigoHojaRuta(Long codigoHojaRuta) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		String hqlDelete = "delete from Operacioncomponente oc where oc.operacion.pkCodigoOperacion in (Select op.pkCodigoOperacion from Operacion op where op.hojaruta.pkCodigoHojaruta = :codigo)";
		getSession().createQuery(hqlDelete).setLong("codigo", codigoHojaRuta).executeUpdate();
	}
}
