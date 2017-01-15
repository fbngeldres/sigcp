package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.AjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.DetalleAjusteProduccionMesBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaGrupoAjusteBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillagrupoajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.AjusteProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.ConsumosFactoresPuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.OrdenMolCementoDto;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroPuestoTrabajoProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroTablaConsumoComponentesDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroTablaConsumosPuestoTrabajoDTO;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AjusteProduccionMesLogicFacade.java
 * Modificado: Aug 3, 2010 1:49:34 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface AjusteProduccionMesLogicFacade {

	/**
	 * Método para consultar los ajustes de la producción del mes en la linea de
	 * negocio
	 * 
	 * @param lineaCodigo
	 * @param anioContable
	 * @param mesContable
	 * @param estadoAjusteProduccionCodigo
	 * @return
	 * @throws LogicaException
	 */
	public List<DetalleAjusteProduccionMesBean> obtenerAjusteProduccionMesPorLineaNegocioYEstado(Long lineaCodigo,
			Integer anioContable, Short mesContable, Long estadoAjusteProduccionCodigo) throws LogicaException;

	/**
	 * Método para listar los grupos de ajuste por linea de negocio
	 * 
	 * @param codigoLineaNegocio
	 * @return
	 */
	public List<PlantillaGrupoAjusteBean> obtenerGruposProducto(Long codigoLineaNegocio) throws LogicaException;

	public List<Plantillagrupoajuste> obtenerGruposProductoDataObjects(Long codigoLineaNegocio) throws LogicaException;

	/**
	 * Método para listar los producctos del grupo de ajuste por linea de
	 * negocio
	 * 
	 * @param codigoGrupoAjuste
	 * @return
	 */
	public List<ProductoBean> obtenerProductoGrupos(Long codigoGrupoAjuste) throws LogicaException;

	/**
	 * Méodo para listar los producctos por linea de negocio
	 * 
	 * @param codigoGrupoAjuste
	 * @return
	 */
	public List<ProductoBean> obtenerProductosLineaNegocio(Long codigoLineaNegocio) throws LogicaException;

	/**
	 * Método para listar los productos por línea de negocio y clase de producto
	 * (Combustible)
	 * 
	 * @param codigoLineaNegocio
	 * @param claseProducto
	 * @return
	 */
	List<ProductoBean> obtenerProductosLineaNegocioYClaseProducto(Long codigoLineaNegocio, Long claseProducto)
			throws LogicaException;

	/**
	 * Método para obtener el ajuste el valor de ajute (fisico - libros)
	 * 
	 * @param codigoLinea
	 * @param codigoProduccion
	 * @param mesContable
	 * @param anioContable
	 * @param estadoCubicacionProducto
	 * @param tipoAlmacenamiento
	 * @param fechaMesInicial
	 * @param fechaMesFinal
	 * @param tipoMedioAlmacenamiento
	 * @return
	 */
	public double obtenerAjuste(Long codigoLinea, Long codigoProducto, Short mesContable, Integer anioContable,
			String estadoCubicacionProducto, Date fechaMesFinal) throws LogicaException;

	/**
	 * Método para obtener los valores de los conceptos en los libros para final
	 * de mes
	 * 
	 * @param codigoProduccion
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param fechaMesInicial
	 * @param fechaMesFinal
	 * @param codigoTipoMedioSilo
	 * @return
	 */
	public double[] obtenerConceptosLibro(Long codigoProducto, Long codigoLinea, Short mesContable, Integer anioContable,
			Date fechaMesInicial, Date fechaMesFinal, Long codigoTipoMedioSilo) throws LogicaException;
	
	/**
	 * Método para obtener los valores de los conceptos en los libros para final
	 * de mes (Solo metodos donde manejen sesion)
	 * 
	 * @param codigoProduccion
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param fechaMesInicial
	 * @param fechaMesFinal
	 * @param codigoTipoMedioSilo
	 * @return
	 */
	public double[] obtenerConceptosLibroDAO(Long codigoProducto, Long codigoLinea, Short mesContable, Integer anioContable,
			Date fechaMesInicial, Date fechaMesFinal, Long codigoTipoMedioSilo) throws LogicaException;

	/**
	 * Método para calcular la produccion del conjunto de puestos de trabajo que
	 * participan en la generación de un producto, para un periodo o su anterior
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return Map<produccionPuestoTrabajo, {puestoTrabajo, produccionTm,
	 *         horasProducto,minRendimiento, maxRendimiento}> El mapa contiene:
	 *         id del puesto de trabajo.
	 */
	public List<RegistroPuestoTrabajoProduccionDTO> obtenerProduccionPuestoTrabajoMes(Long codigoProducto, Long codigoLinea,
			Short mesContable, Integer anioContable) throws LogicaException;

	/**
	 * Metodo para cargar la tabla de movimientos y la tabla de consumo de
	 * componentes
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param fechaMesInicial
	 * @param fechaMesFinal
	 * @param otrosProductosAjuste
	 * @return List<RegistroTablaConsumoComponentesDTO>
	 * @throws LogicaException
	 */
	public List<RegistroTablaConsumoComponentesDTO> obtenerConceptosComponentesProducto(Long codigoProducto, Long codigoLinea,
			Short mesContable, Integer anioContable, Date fechaMesInicial, Date fechaMesFinal, Set<String> otrosProductosAjuste)
			throws LogicaException;

	/**
	 * Método para obtener los Consumos de los componentes por puesto de trabajo
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param fechaMesInicial
	 * @param fechaMesFinal
	 * @return
	 */
	public Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> obtenerConsumoComponentesPuestoTrabajo(Long codigoProducto,
			Long codigoLinea, Short mesContable, Integer anioContable, Date fechaMesInicial, Date fechaMesFinal)
			throws LogicaException;

	/**
	 * Método para insertar todos los ajustes de un producto, si el objeto
	 * ajuste producto existe lo elimina y se crea en limpio
	 * 
	 * @param ajuste
	 * @param codigoLineaNegocio
	 * @param anio
	 * @param mes
	 * @param codigoUsuarioAprueba
	 * @param codigoUsuarioAjusta
	 * @param codigoPlantillaGrupoAjuste
	 * @param codigoOrdenProduccion
	 * @param saldoInicialLibroBalance
	 * @param produccionLibroBalance
	 * @param saldoFinalLibroBalance
	 * @param consumoLibroBalance
	 * @param consumoAjusteBalance
	 * @param produccionAjusteBalance
	 * @param detallesProduccionPuestoTrabajo
	 * @param detallesConsumoComponenteAjuste
	 * @param detallesMovimientoAjuste
	 * @throws LogicaException
	 */
	public void insertarAjusteProducto(Double ajuste, Long codigoLineaNegocio, Integer anio, Short mes,
			Long codigoUsuarioAprueba, Long codigoUsuarioAjusta, Long codigoPlantillaGrupoAjuste, Long codigoProducto,
			double saldoInicialLibroBalance, double produccionLibroBalance, double saldoFinalLibroBalance,
			double consumoLibroBalance, double consumoAjusteBalance, double produccionAjusteBalance,
			List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste, String observaciones) throws LogicaException;

	/**
	 * Método para aprobar el ajuste de una producción
	 * 
	 * @param codigoAjusteProduccion
	 * @param usuarioBean
	 * @throws LogicaException
	 */
	public Object[] aprobarAjusteProduccion(Long codigoAjusteProduccion, UsuarioBean usuarioBean) throws LogicaException;

	/**
	 * Verifica si un producto tienes guardado ajustes para un mes y linea de
	 * negocio
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return Boolean
	 */
	public AjusteProductoDTO verificarSiExisteAjusteBd(Long codigoProducto, Long codigoLinea, String mesContable,
			Integer anioContable) throws LogicaException;

	public Double[] obtenerStocksMensualesComponente(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException;
	

	public Double[] obtenerStocksMensualesComponenteDAO(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException;


	/**
	 * Obtiene el stock fisico de un producto, verifica si el producto se
	 * almacena e silos, hace la consulta sobre las mediciones, si es cubicado
	 * de las cubicaciones
	 * 
	 * @param componente
	 * @param codigoLinea
	 * @param numeroMes
	 * @param anio
	 * @param fechaMesInicial
	 * @param fechaMesFinal
	 * @return double
	 * @throws LogicaException
	 */
	public double obtenerStockFisico(Producto componente, Long codigoLinea, Short numeroMes, Integer anio, Date fechaMesInicial,
			Date fechaMesFinal, Boolean estadoInicial) throws LogicaException;
	
	
	/**
	 * Obtiene el stock fisico de un producto, verifica si el producto se
	 * almacena e silos, hace la consulta sobre las mediciones, si es cubicado
	 * de las cubicaciones (Solo usar con metodos que ya tengan implentadio el Sesion)
	 * 
	 * @param componente
	 * @param codigoLinea
	 * @param numeroMes
	 * @param anio
	 * @param fechaMesInicial
	 * @param fechaMesFinal
	 * @return double
	 * @throws LogicaException
	 */
	public double obtenerStockFisicoDAO(Producto componente, Long codigoLinea, Short numeroMes, Integer anio, Date fechaMesInicial,
			Date fechaMesFinal, Boolean estadoInicial) throws LogicaException;

	/**
	 * Calcula los factores de crudo y carbo por puesto de trabajo, esto es
	 * necesario para el producto clinker los factores se calculan de la
	 * siguiente manera (hornos horizontales): crudo/clinker es el consumo de
	 * crudo en el horno divido entre la produccion de clinker del horno, de la
	 * misma forma se calcula el factor carbon/clinker para los hornos
	 * verticales se calcula factor antracita que es el consumo de carbon en el
	 * horno divido entre la produccion de clinker de ese horno.
	 * 
	 * @param codigoProducto Long
	 * @param codigoLinea Long
	 * @param anioContable Integer
	 * @param numeroMes Short
	 * @param codigosPuestosTrab Set<Long>
	 * @return Map<Long, Double[]>
	 */
	public Map<Long, ConsumosFactoresPuestoTrabajoDTO> obtenerFactoresCrudoCarbonPorPuesto(Long codigoProducto,
			Long codigoLineanegocio, Integer anoPeriodocontable, Short mesPeriodocontable, Set<Long> puestos)
			throws LogicaException;

	/**
	 * Método para obtener las horas total ajustadas por puesto de trabajo
	 * producción
	 * 
	 * @param detallesProduccionPuestoTrabajo
	 * @param codigoLineaNegocio
	 * @param mesContable
	 * @param anioContable
	 * @return
	 * @throws LogicaException
	 */
	public Map<String, Double> obtenerDesviacionHoras(Long codigoProducto,
			List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo, Long codigoLineaNegocio, Short mesContable,
			Integer anioContable) throws LogicaException;

	public abstract List<PlantillaGrupoAjusteBean> obtenerPlantillaGrupoAjuste(Long codigoLin) throws LogicaException;

	public abstract List<AjusteProduccionBean> obtenerAjustePorduccionPorPerdiodoLineaNegocio(Long lineaNegocio, Short mes,
			Integer anno) throws LogicaException;

	public abstract Double[] obtenerStocksMensualComponente(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException;

	public abstract Double[] obtenerStocksMensualComponenteDAO(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException;

	public abstract Double[] obtenerStocksAnualComponente(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException;

	public abstract Double[] obtenerStocksAnualComponenteDAO(Producto componente, Long codigoLinea, Short mes, Integer anio,
			boolean esClinkerI) throws LogicaException;

	public abstract ReporteAjusteProduccionBean obtenerReporteAjusteProduccion(Integer valorAnio, Short valorMes,
			Long valorLineaNegocio) throws LogicaException;

	public List<RegistroTablaConsumosPuestoTrabajoDTO> obtenerConsumoComponenteProducto(Long codigoProducto, Long codigoLinea,
			Short numeroMes, Integer anioContable) throws LogicaException;

	public void insertarAjusteProductoCombustible(Double mermaIngresada, Double ajuste, Long codigoLineaNegocio, Integer anio,
			Short numeroMes, Long codigoUsuarioAjusta, Long codigoUsuarioAprueba, Long codigoPlantillaGrupoAjuste,
			Long codigoProducto, double saldoInicialLibroBalance, double produccionLibroBalance, double saldoFinalLibroBalance,
			double consumoLibroBalance, double consumoAjusteBalance, double produccionAjusteBalance,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste, String fechaFactura, String cantidadFactura,
			String cantidadRestante, String observaciones) throws LogicaException;

	public String[] obtenerConsumoComponenteProductoFecha(Long codigoProducto, Long codigoLineaNegocio, String fecha)
			throws LogicaException;

	public String[] obtenerDatosComprobante(Long codigoProducto, Long codigoLinea, Short mes, Integer anio)
			throws LogicaException;

	/**
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return
	 */
	public Boolean verificarParteDiarioCerrado(Long codigoLinea, String mesContable, Integer anioContable) throws LogicaException;

	/**
	 * Obtener Ajuste produccion por codigo
	 * 
	 * @param codigo
	 * @return
	 * @throws LogicaException
	 */
	public AjusteProduccionBean obtenerAjustePorCodigo(Long codigo) throws LogicaException;

	/**
	 * Elimina un Ajuste Parte tecnico por el codigo seleccionado
	 * 
	 * @param codigo
	 * @return
	 * @throws LogicaException
	 */
	public Boolean eliminarAjustePorCodigo(Long codigo, UsuarioBean usuarioBean) throws LogicaException;

	public void validarExistenciaAjusteInicial(Short numeroMes, Integer anio, Long codigoLineaNegocio, UsuarioBean usuario)
			throws LogicaException;

	public void ordenarListaCompCementos(final boolean esMoliendaCemento, List<? extends OrdenMolCementoDto> lista,
			final boolean esMoliendaCrudo) throws LogicaException;

	public void asignarOrden(boolean esMoliendaCemento, OrdenMolCementoDto dto, Producto productoComponente,
			boolean esMoliendaCrudo) throws LogicaException;

	public double obtenerFactorHumPonderadoConsumo(Producto producto, Date fechaCubicacion) throws LogicaException;
	
	public double obtenerFactorHumPonderadoConsumoDAO(Producto producto, Date fechaCubicacion) throws LogicaException;

	public double obtenerCantidadMensualKardex(Producto producto, Date fecha) throws LogicaException;
	public double obtenerCantidadMensualKardexDAO(Producto producto, Date fecha) throws LogicaException;

	public double obtenerFactorHumPonderado(Producto producto, Date fechaCubicacion) throws LogicaException;
	
	public double obtenerFactorHumPonderadoDAO(Producto producto, Date fechaCubicacion) throws LogicaException;

	public Double obtenerFactorHumedadVariableCalidad(Producto producto) throws LogicaException;
	
	public Double obtenerFactorHumedadVariableCalidadDAO(Producto producto) throws LogicaException;

	public Map<Long, ConsumosFactoresPuestoTrabajoDTO> obtenerFactoresCrudoCarbonPorPuestoDAO(Long codigoProducto,
			Long codigoLineanegocio, Integer anoPeriodocontable, Short mesPeriodocontable, Set<Long> puestos) throws LogicaException;

}
