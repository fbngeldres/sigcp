package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproduccion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;

public class AjusteProduccionQuerier extends Querier {

	/**
	 * Método para obtener la lista de objectos ajuste de produccion
	 * 
	 * @return
	 */
	public static List<Ajusteproduccion> getAll() throws AplicacionException {

		return Querier.getAll(Ajusteproduccion.class);
	}

	/**
	 * Método para obtener un ajuste de produccion de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Ajusteproduccion getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Ajusteproduccion.class, codigo);
	}

	/**
	 * Método para obtener la produccion por linea de negocio, mes y año
	 * contable abierto
	 * 
	 * @param mes
	 * @param ano
	 * @param codigoLineaNegocio
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Ajusteproduccion getByLineaNegocioMesYAno(short mes, int ano, Long codigoLineaNegocio)
			throws ElementoNoEncontradoException {
		String consulta = "from {0} as pc where pc.{1} = :mes and pc.{2} = :ano and pc.{3} = :lineaNegocio ";
		consulta = MessageFormat.format(consulta, new Object[] { "Ajusteproduccion", "periodocontable.mesPeriodocontable",
				"periodocontable.anoPeriodocontable", "lineanegocio.pkCodigoLineanegocio" });

		try {
			Query query = Querier.query(consulta);
			query.setShort("mes", mes);
			query.setInteger("ano", ano);
			query.setLong("lineaNegocio", codigoLineaNegocio);

			Ajusteproduccion ajusteproduccion = (Ajusteproduccion) query.uniqueResult();
			return ajusteproduccion;

		} catch (UnresolvableObjectException uOException) {
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new EntornoEjecucionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar un ajuste de produccion en la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Ajusteproduccion ajusteProduccion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(ajusteProduccion);
	}

	/**
	 * Metodo para modificar un ajuste de producción de la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Ajusteproduccion ajusteProduccion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(ajusteProduccion);
	}

	/**
	 * Método para eliminar un ajuste de produccion de la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Ajusteproduccion ajusteProduccion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(ajusteProduccion);
	}

	/**
	 * Agregado por Fabian Metodo que obtiene el Ajuste produccion Validando si
	 * la linea de negocio es nula Método para obtener la produccion por linea
	 * de negocio, mes y año contable abierto
	 * 
	 * @param mes
	 * @param ano
	 * @param codigoLineaNegocio
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static List<Ajusteproduccion> getListaByLineaNegocioMesYAno(short mes, int ano, Long codigoLineaNegocio)
			throws ElementoNoEncontradoException {
		StringBuilder consulta = new StringBuilder("FROM  Ajusteproduccion AS pc ");
		consulta.append(" WHERE ");
		consulta.append(" pc.periodocontable.mesPeriodocontable = :mes ");
		consulta.append(" AND pc.periodocontable.anoPeriodocontable = :ano ");
		if (codigoLineaNegocio != null) {
			consulta.append(" AND pc.lineanegocio.pkCodigoLineanegocio = :lineaNegocio ");
		}

		try {
			Query query = Querier.query(consulta.toString());
			query.setShort("mes", mes);
			query.setInteger("ano", ano);
			if (codigoLineaNegocio != null) {
				query.setLong("lineaNegocio", codigoLineaNegocio);
			}

			return query.list();

		} catch (UnresolvableObjectException uOException) {
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new EntornoEjecucionException(ERROR_HIBERNATE, hException.getCause());
		}
	}
}
