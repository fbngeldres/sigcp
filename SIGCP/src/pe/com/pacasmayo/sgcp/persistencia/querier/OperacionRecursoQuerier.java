package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Operacionrecurso;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;

public class OperacionRecursoQuerier extends Querier {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String CODIGO_OPERACION = "operacion.pkCodigoOperacion";

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	public static List<Operacionrecurso> findByOperacion(Long codigo) throws AplicacionException {

		try {

			return Querier.findByProperty(Operacionrecurso.class, CODIGO_OPERACION, codigo);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para Insertar una OperacionRecurso en la BD.
	 * 
	 * @param operacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Operacionrecurso operacionRecurso) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(operacionRecurso);
	}

	/**
	 * Método para eliminar una lista de Operaciones Recursos de la BD, por
	 * medio del código de la hoja de ruta.
	 * 
	 * @param codigoHojaRuta
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 */
	public static void deleteByCodigoHojaRuta(Long codigoHojaRuta) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		String hqlDelete = "delete from Operacionrecurso where operacion.pkCodigoOperacion in (Select pkCodigoOperacion from Operacion where hojaruta.pkCodigoHojaruta = :codigo)";
		getSession().createQuery(hqlDelete).setLong("codigo", codigoHojaRuta).executeUpdate();
	}

	/**
	 * Método para obtener una Operacion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Operacionrecurso getById(Long codigo) throws ElementoNoEncontradoException {

		return (Operacionrecurso) Querier.getById(Operacionrecurso.class, codigo);
	}
}
