package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface AjusteProductoLogicFacade {
	/**
	 * Metodo que obtiene los ajustes Productos ingresando algunos parametros.
	 * 
	 * @param lineaCodigo
	 * @param anioContable
	 * @param mesContable
	 * @param estadoAjusteProduccionCodigo
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<AjusteProductoBean> obtenerAjusteProducto(Long lineaCodigo, Integer anioContable, Short mesContable,
			Long estadoAjusteProduccionCodigo) throws LogicaException;

	/**
	 * Obtiene Ajuste Producto mediante su codigo
	 * 
	 * @param codigoAjuste
	 * @return
	 */
	public abstract AjusteProductoBean obtenerPorCodigo(Long codigoAjuste) throws LogicaException;

	/**
	 * Metodo que se encarga de Eliminar un AjusteProducto
	 * 
	 * @param codigoAjuste
	 * @param usuarioBean
	 */
	public abstract void eliminarAjusteProducto(Long codigoAjuste, UsuarioBean usuarioBean) throws LogicaException;
}
