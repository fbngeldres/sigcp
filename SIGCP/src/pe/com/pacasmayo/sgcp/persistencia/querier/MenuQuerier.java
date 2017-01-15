package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Menu;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class MenuQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String NOMBRE_MENU = "nombreMenu";
	private static final String CODIGO_MENU_PADRE = "menu.pkCodigoMenu";

	/*
	 * private static final String CODIGO_SCC = "codigoSccLineanegocio"; private
	 * static final String CODIGO_UNIDAD = "unidad.pkCodigoUnidad";
	 */

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Menu
	 * 
	 * @return
	 */
	public static List<Menu> getAll() throws AplicacionException {

		return Querier.getAll(Menu.class);
	}

	/**
	 * Método para obtener un Menu de la BD por código.
	 * 
	 * @param codigo
	 * @return
	 */
	public static Menu getById(Long codigo) throws ElementoNoEncontradoException {

		return (Menu) Querier.getById(Menu.class, codigo);
	}

	/**
	 * Método para obtener los Línea de Negocio de la BD por nombre.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Menu> findByNombre(String value) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Menu.class, NOMBRE_MENU, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Menu> obtenerMenusPadres() throws AplicacionException {

		try {
			return Querier.findByProperty(Menu.class, CODIGO_MENU_PADRE, null);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar un Menu en la BD.
	 * 
	 * @param menu
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Menu menu) throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {

		Querier.save(menu);
	}

	/**
	 * Metodo para modificar un Menu de la BD.
	 * 
	 * @param menu
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Menu menu) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(menu);
	}

	/**
	 * Método para eliminar un Menu de la BD.
	 * 
	 * @param lineaNegocio
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Menu menu) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(menu);
	}

	public static void deleteById(Long codigo) {
		String borrado = "delete from Menu menu " + "where menu.pkCodigoMenu = ? ";

		Query query = Querier.query(borrado);
		query.setLong(0, codigo);

		query.executeUpdate();
	}
}