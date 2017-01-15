package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.bean.ConsumoCombustibleBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoComponenteProductoBean;
import pe.com.pacasmayo.sgcp.bean.DetalleParteTecnicoConsumoComponentesBean;
import pe.com.pacasmayo.sgcp.bean.DetalleParteTecnicoPuestoTrabajoComponenteBean;

import pe.com.pacasmayo.sgcp.bean.impl.ReporteConsumoVentasCal_List;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;

public interface GenerarParteTecnicoLogicFacade {

	/**
	 * Método para cargar u obtener los balances de los componentes
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @param estadoParteTec
	 * @return
	 */
	public List<DetalleParteTecnicoConsumoComponentesBean> obtenerTotalBalanceComponentes(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoProducto, Long codigoLineaNegocio, Short mes, Integer anio,
			Long estadoParteTec) throws LogicaException;

	/**
	 * Método para obtener los consumos por puestos de trabajo de un producto
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @throws LogicaException
	 */
	public List<DetalleParteTecnicoPuestoTrabajoComponenteBean> obtenerPuestosTrabajoPorProducto(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoProducto, Long codigoLineaNegocio, Short mes, Integer anio,
			Long estado) throws LogicaException;

	/**
	 * Método para cargar u obtener los balances de los productos de la linea de
	 * negocio
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @param estadoParteTec
	 * @return
	 * @throws LogicaException
	 */
	public List<DetalleParteTecnicoConsumoComponentesBean> obtenerTotalBalanceProductosLineaNegocio(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio, Short mes, Integer anio, Long estadoParteTec)
			throws LogicaException;

	/**
	 * Método para obtener los consumos por puestos de trabajo de un producto
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @throws LogicaException
	 */
	public List<DetalleParteTecnicoPuestoTrabajoComponenteBean> obtenerPuestosTrabajoPorLineaNegocio(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio, Short mes, Integer anio, Long estado)
			throws LogicaException;

	/**
	 * Obtiene el consumo de componentes del grupo cemento por los valores
	 * indicados
	 * 
	 * @param codigoLineaNegocio puede ser null
	 * @param codigoUnidad puede ser null
	 * @param codigoSociedad
	 * @param codigoDivision
	 * @param anio
	 * @param mes
	 * @param estadoParteTec2
	 * @return
	 * @throws LogicaException
	 */
	public Map<String, List<ConsumoComponenteProductoBean>> obtenerConsumoConponenteGrupoCemento(Long codigoLineaNegocio,
			Long codigoUnidad, Long codigoSociedad, Long codigoDivision, Integer anio, Short mes, Long estadoParteTec2)
			throws LogicaException;

	/**
	 * Obtiene el consumo de componentes del grupo crudo por los valores
	 * indicados
	 * 
	 * @param codigoLineaNegocio puede ser null
	 * @param codigoUnidad puede ser null
	 * @param codigoSociedad
	 * @param codigoDivision
	 * @param anio
	 * @param mes
	 * @param estadoParteTec2
	 * @return
	 * @throws LogicaException
	 */
	public Map<String, List<ConsumoComponenteProductoBean>> obtenerConsumoConponenteGrupoCrudo(Long codigoLineaNegocio,
			Long codigoUnidad, Long codigoSociedad, Long codigoDivision, Integer anio, Short mes, Long estadoParteTec2)
			throws LogicaException;

	/**
	 * Obtiene el consumo de componentes del grupo clinker por los valores
	 * indicados
	 * 
	 * @param codigoLineaNegocio puede ser null
	 * @param codigoUnidad puede ser null
	 * @param codigoSociedad
	 * @param codigoDivision
	 * @param anio
	 * @param mes
	 * @param estadoParteTec2
	 * @return
	 * @throws LogicaException
	 */
	public Map<String, List<ConsumoComponenteProductoBean>> obtenerConsumoConponenteGrupoClinker(Long codigoLineaNegocio,
			Long codigoUnidad, Long codigoSociedad, Long codigoDivision, Integer anio, Short mes, Long estadoParteTec2)
			throws LogicaException;

	/**
	 * Obtiene los productos combustible de la división
	 * 
	 * @param codigoDivision
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public List<String> obtenerCombustibles(Long codigoDivision, Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio)
			throws LogicaException;

	/**
	 * @param codigoDivision
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @param estadoCombustible
	 * @return
	 * @throws LogicaException
	 */
	Map<String, List<ConsumoCombustibleBean>> obtenerProductosCombustibles(Long codigoDivision, Long codigoSociedad,
			Long codigoUnidad, Long codigoLineaNegocio, Short mes, Integer anio, boolean estadoFisicoSolido)
			throws LogicaException;

	public List<ReporteConsumoVentasCal_List> obtenerConsumosVentasProductos(Long codigoDivision, Long codigoSociedad,
			Long codigoLineaNegocio, Long codigoUnidad, Integer anio, Short mes) throws LogicaException;

	public List<DetalleParteTecnicoPuestoTrabajoComponenteBean> obtenerPuestoTrabajoConsumo(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio, Short mes, Integer anio) throws LogicaException;

	// FIXME quitar objeto de capa persistencia
	public DetalleParteTecnicoConsumoComponentesBean obtenerDetalleParteTecnicoConsumoparaPD(Producto producto,
			Long codigoLineaNegocio, Short mes, Integer anio) throws LogicaException;
	
	// FIXME quitar objeto de capa persistencia
	public DetalleParteTecnicoConsumoComponentesBean obtenerDetalleParteTecnicoConsumoparaPDDAO(Producto producto,
			Long codigoLineaNegocio, Short mes, Integer anio) throws LogicaException;

	
	
}
