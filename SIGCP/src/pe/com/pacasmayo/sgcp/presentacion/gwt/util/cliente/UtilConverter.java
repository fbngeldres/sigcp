package pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DivisionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoAjusteProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoNotificacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoParteDiarioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoProduccionSemanalDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoRegistroMedicionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadocubicacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.GrupoAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.LineaNegocioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedioAlmacenamientoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PlantillaProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProcesoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.SociedadDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TableroControlDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TipoMedioAlmacenamientoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TipoProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.UnidadDTO;

import com.smartgwt.client.widgets.grid.ListGridField;

/*
 * SGCP (Sistema de Gestiï¿½n y Control de la Producciï¿½n) 
 * Archivo: Validaciones.java
 * Modificado: Apr 9, 2010 5:01:57 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
/**
 * Clase utilitaria para conversiones.
 */
public class UtilConverter {

	// solo para uso en registro de cubicacion
	private static Map<Long, String> mapaTipoMedioaALm = new HashMap<Long, String>();
	private static Map<Long, String> mapaMedioaALm = new HashMap<Long, String>();

	/**
	 * Metodo que convierte una lista de estadoNotificacionDTO a LinkedHashMap
	 * que contiene el codigo y nombre
	 * 
	 * @param estadoNotificacionDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaEstadoNotificacionDTO(List<EstadoNotificacionDTO> estadoNotificacionDTO) {
		LinkedHashMap<String, String> mapaEstadosNotificacion = new LinkedHashMap<String, String>();
		for (int i = 0; i < estadoNotificacionDTO.size(); i++) {
			mapaEstadosNotificacion.put(estadoNotificacionDTO.get(i).getPkCodigoEstadonotificacion().toString(),
					estadoNotificacionDTO.get(i).getNombreEstadonotificacion());
		}
		return mapaEstadosNotificacion;
	}

	/**
	 * Metodo que convierte una lista de estadoRegistroMedicionDTO a
	 * LinkedHashMap que contiene el codigo y nombre
	 * 
	 * @param estadoRegistroMedicionDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaEstadoRegistroMedicionDTO(
			List<EstadoRegistroMedicionDTO> estadoRegistroMedicionDTO) {
		LinkedHashMap<String, String> mapaEstadosRegistroMedicion = new LinkedHashMap<String, String>();
		for (int i = 0; i < estadoRegistroMedicionDTO.size(); i++) {
			mapaEstadosRegistroMedicion.put(estadoRegistroMedicionDTO.get(i).getPkCodigoEstadoregistromedicio().toString(),
					estadoRegistroMedicionDTO.get(i).getNombreEstadoregistromedicion());
		}
		return mapaEstadosRegistroMedicion;
	}

	/**
	 * Metodo que convierte una lista de mediosDTO a LinkedHashMap que contiene
	 * el codigo y nombre
	 * 
	 * @param mediosDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaTipoMediosAlmacenamientoDTO(List<TipoMedioAlmacenamientoDTO> mediosDTO) {
		mapaTipoMedioaALm = new HashMap<Long, String>();
		LinkedHashMap<String, String> mapaMedios = new LinkedHashMap<String, String>();
		for (int i = 0; i < mediosDTO.size(); i++) {
			mapaMedios.put(mediosDTO.get(i).getPkCodigoTipomedioalmacenamien().toString(), mediosDTO.get(i)
					.getNombreTipomedioalmacenamiento());
			mapaTipoMedioaALm.put(mediosDTO.get(i).getPkCodigoTipomedioalmacenamien(), mediosDTO.get(i)
					.getNombreTipomedioalmacenamiento());
		}
		return mapaMedios;
	}

	public static LinkedHashMap<String, String> obtenerMapaTipoMediosAlmacenamientoConClaveCompuestaDTO(
			List<TipoMedioAlmacenamientoDTO> mediosDTO) {
		LinkedHashMap<String, String> mapaMedios = new LinkedHashMap<String, String>();
		for (int i = 0; i < mediosDTO.size(); i++) {
			String codigo = mediosDTO.get(i).getPkCodigoTipomedioalmacenamien().toString();
			String nombre = mediosDTO.get(i).getNombreTipomedioalmacenamiento();
			mapaMedios.put(codigo + "," + nombre, nombre);
		}
		return mapaMedios;
	}

	/**
	 * Metodo que convierte una lista de divisionDTO a LinkedHashMap que
	 * contiene el codigo y el nombre
	 * 
	 * @param divisionesDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaDivisionDTO(List<DivisionDTO> divisionesDTO) {
		LinkedHashMap<String, String> mapaDivisiones = new LinkedHashMap<String, String>();

		for (int i = 0; i < divisionesDTO.size(); i++) {
			mapaDivisiones.put(divisionesDTO.get(i).getPkCodigoDivision().toString(), divisionesDTO.get(i).getNombreDivision());
		}
		return mapaDivisiones;
	}

	/**
	 * Metodo que convierte una lista de sociedadDTO a LinkedHashMap que
	 * contiene el codigo y el nombre
	 * 
	 * @param sociedadesDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaSociedadDTO(List<SociedadDTO> sociedadesDTO) {

		LinkedHashMap<String, String> mapaSociedades = new LinkedHashMap<String, String>();

		for (int i = 0; i < sociedadesDTO.size(); i++) {
			mapaSociedades.put(sociedadesDTO.get(i).getPkCodigoSociedad().toString(), sociedadesDTO.get(i).getNombreSociedad());
		}
		return mapaSociedades;
	}

	/**
	 * Metodo que convierte una lista de unidadDTO a LinkedHashMap que contiene
	 * el codigo y el nombre
	 * 
	 * @param unidadesDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaUnidadDTO(List<UnidadDTO> unidadesDTO) {

		LinkedHashMap<String, String> mapaUnidades = new LinkedHashMap<String, String>();

		for (int i = 0; i < unidadesDTO.size(); i++) {
			mapaUnidades.put(unidadesDTO.get(i).getPkCodigoUnidad().toString(), unidadesDTO.get(i).getNombreUnidad());
		}
		return mapaUnidades;
	}

	/**
	 * Metodo que convierte una lista de lineaNegocioDTO a LinkedHashMap que
	 * contiene el codigo y el nombre
	 * 
	 * @param lineasNegocioDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaLineaNegocioDTO(List<LineaNegocioDTO> lineasNegocioDTO) {

		LinkedHashMap<String, String> mapaLineasNegocio = new LinkedHashMap<String, String>();

		for (int i = 0; i < lineasNegocioDTO.size(); i++) {
			mapaLineasNegocio.put(lineasNegocioDTO.get(i).getPkCodigoLineanegocio().toString(), lineasNegocioDTO.get(i)
					.getNombreLineanegocio());
		}
		return mapaLineasNegocio;
	}

	/**
	 * Metodo que convierte una lista de procesoDTO a LinkedHashMap que contiene
	 * el codigo y el nombre
	 * 
	 * @param procesosDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaProcesoDTO(List<ProcesoDTO> procesosDTO) {

		LinkedHashMap<String, String> mapaProceso = new LinkedHashMap<String, String>();

		for (int i = 0; i < procesosDTO.size(); i++) {
			mapaProceso.put(procesosDTO.get(i).getPkCodigoProceso().toString(), procesosDTO.get(i).getNombreProceso());
		}
		return mapaProceso;
	}

	/**
	 * Metodo que convierte una lista de procesoDTO a LinkedHashMap que contiene
	 * el codigo y el nombre
	 * 
	 * @param procesosDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaGrupoAjusteDTO(List<GrupoAjusteDTO> grupoAjusteDTO) {

		LinkedHashMap<String, String> mapaGrupoAjuste = new LinkedHashMap<String, String>();

		for (int i = 0; i < grupoAjusteDTO.size(); i++) {
			mapaGrupoAjuste.put(grupoAjusteDTO.get(i).getCodigo().toString(), grupoAjusteDTO.get(i).getNombre() + " - "
					+ grupoAjusteDTO.get(i).getOrdenPlantilla());
		}
		return mapaGrupoAjuste;
	}

	/**
	 * Metodo que convierte una lista de productoDTO a LinkedHashMap que
	 * contiene el codigo y el nombre
	 * 
	 * @param productoDTOs
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerValueMapProductos(List<ProductoDTO> productoDTOs) {

		LinkedHashMap<String, String> mapaProductos = new LinkedHashMap<String, String>();

		for (ProductoDTO productoDTO : productoDTOs) {

			mapaProductos.put(productoDTO.getPkCodigoProducto().toString(), productoDTO.getNombreProducto());
		}

		return mapaProductos;
	}

	/**
	 * Metodo que convierte una lista de ordenDTO a LinkedHashMap que contiene
	 * el codigo y el nombre
	 * 
	 * @param productoDTOs
	 * @return
	 */
	public static String[] obtenerArregloStringOrdenDTO(List<OrdenProduccionDTO> ordenDTOs) {

		String[] arregloOrdenes = new String[ordenDTOs.size()];

		for (int i = 0; i < ordenDTOs.size(); i++) {
			OrdenProduccionDTO ordenProduccionDTO = ordenDTOs.get(i);
			arregloOrdenes[i] = ordenProduccionDTO.getNumeroOrdenOrdenproduccion().toString();
		}

		return arregloOrdenes;
	}

	/**
	 * Metodo que convierte una lista de ordenDTO a arreglo de String
	 * 
	 * @param productoDTOs
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaOrdenDTO(List<OrdenProduccionDTO> ordenDTOs) {

		LinkedHashMap<String, String> mapaOrdenes = new LinkedHashMap<String, String>();

		for (OrdenProduccionDTO ordenDTO : ordenDTOs) {
			mapaOrdenes.put(ordenDTO.getPkCodigoOrdenproduccion().toString(), ordenDTO.getNombreProducto() + "  "
					+ ordenDTO.getProduccion().getProceso().getSiglasProceso());
		}

		return mapaOrdenes;
	}

	/**
	 * Metodo que convierte una lista de estadoDTO a LinkedHashMap que contiene
	 * el codigo y el nmbre
	 */
	public static LinkedHashMap<String, String> obtenerMapaEstadoDTO(List<EstadoProduccionSemanalDTO> estadosDTO) {

		LinkedHashMap<String, String> mapaEstado = new LinkedHashMap<String, String>();

		for (int i = 0; i < estadosDTO.size(); i++) {
			mapaEstado.put(estadosDTO.get(i).getPkCodigoEstadoProduccionSemanal().toString(), estadosDTO.get(i)
					.getNombreEstadoProduccionSemanal());
		}
		return mapaEstado;
	}

	/**
	 * Metodo que convierte una lista de estadoAjusteProduccionDTO a
	 * LinkedHashMap que contiene el codigo y el nombre
	 */
	public static LinkedHashMap<String, String> obtenerMapaEstadoAjusteProduccionDTO(List<EstadoAjusteProduccionDTO> estadosDTO) {

		LinkedHashMap<String, String> mapaEstado = new LinkedHashMap<String, String>();

		for (int i = 0; i < estadosDTO.size(); i++) {
			mapaEstado.put(estadosDTO.get(i).getPkCodigoEstadoajusteproduccio().toString(), estadosDTO.get(i)
					.getNombreEstadoajusteproduccion());
		}
		return mapaEstado;
	}

	/**
	 * Método para convertir la lista de meses en un mapa de meses
	 * 
	 * @param mesesDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaMesesDTO(List<String> mesesDTO) {

		LinkedHashMap<String, String> mapaMeses = new LinkedHashMap<String, String>();

		for (int i = 0; i < mesesDTO.size(); i++) {

			mapaMeses.put(mesesDTO.get(i), mesesDTO.get(i));
		}
		return mapaMeses;
	}

	/**
	 * Método para convertir la lista de meses en un mapa de anios
	 * 
	 * @param aniosDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaAniosDTO(List<Integer> aniosDTO) {

		LinkedHashMap<String, String> mapaAnios = new LinkedHashMap<String, String>();

		for (int i = 0; i < aniosDTO.size(); i++) {

			mapaAnios.put(aniosDTO.get(i).toString(), aniosDTO.get(i).toString());
		}
		return mapaAnios;
	}

	/**
	 * Metodo que convierte una lista de estadoDTO a LinkedHashMap que contiene
	 * el codigo y el nombre
	 */
	public static LinkedHashMap<String, String> obtenerMapaEstadoParteDiarioDTO(List<EstadoParteDiarioDTO> estadosDTO) {

		LinkedHashMap<String, String> mapaEstado = new LinkedHashMap<String, String>();

		for (EstadoParteDiarioDTO estadoParteDiarioDTO : estadosDTO) {
			mapaEstado.put(estadoParteDiarioDTO.getPkCodigoEstadoParteDiario().toString(),
					estadoParteDiarioDTO.getNombreEstadoParteDiario());
		}

		return mapaEstado;
	}

	/**
	 * Metodo que convierte una lista de tipoProductoDTO a LinkedHashMap que
	 * contiene el codigo y el nombre
	 */
	public static LinkedHashMap<String, String> obtenerMapaTipoProductoDTO(List<TipoProductoDTO> tipoProductoDTOs) {

		LinkedHashMap<String, String> mapaTipoProducto = new LinkedHashMap<String, String>();

		for (TipoProductoDTO tipoProductoDTO : tipoProductoDTOs) {
			mapaTipoProducto.put(tipoProductoDTO.getPkCodigoTipoProducto().toString(), tipoProductoDTO.getNombreTipoProducto());
		}

		return mapaTipoProducto;
	}

	/**
	 * Metodo que convierte una lista de medioDTO a LinkedHashMap que contiene
	 * el codigo y el nombre
	 * 
	 * @param divisionesDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaMedioAlmacenamientoDTO(List<MedioAlmacenamientoDTO> mediosDTO) {
		LinkedHashMap<String, String> mapaSilos = new LinkedHashMap<String, String>();
		mapaMedioaALm = new HashMap<Long, String>();
		for (int i = 0; i < mediosDTO.size(); i++) {
			MedioAlmacenamientoDTO medioAlm = mediosDTO.get(i);
			mapaSilos.put(medioAlm.getPkCodigoMedioalmacenamiento().toString(), medioAlm.getNombreMedioalmacenamiento());
			mapaMedioaALm.put(medioAlm.getPkCodigoMedioalmacenamiento(), medioAlm.getNombreMedioalmacenamiento());
		}
		return mapaSilos;
	}

	/**
	 * Metodo que convierte una lista de medioDTO a LinkedHashMap que contiene
	 * el codigo y el nombre
	 * 
	 * @param divisionesDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaPlantillaProductoDTO(List<PlantillaProductoDTO> plantillasDTO) {
		LinkedHashMap<String, String> mapaSilos = new LinkedHashMap<String, String>();
		for (int i = 0; i < plantillasDTO.size(); i++) {
			PlantillaProductoDTO plantillaDTO = plantillasDTO.get(i);
			mapaSilos.put(plantillaDTO.getPkCodigoPlantillaProducto().toString(), plantillaDTO.getNombrePlantillaProducto());
		}
		return mapaSilos;
	}

	/**
	 * Metodo que convierte una lista de medioDTO a arreglo de String con los
	 * numeros de los medios
	 * 
	 * @param meidosDTO
	 * @return
	 */
	public static String[] obtenerArregloStringMedioAlmacenamientoDTO(List<MedioAlmacenamientoDTO> mediosDTO) {
		String[] arregloSilos = new String[mediosDTO.size()];

		for (int i = 0; i < mediosDTO.size(); i++) {
			MedioAlmacenamientoDTO medioDTO = mediosDTO.get(i);
			arregloSilos[i] = medioDTO.getNumeroMedioalmacenamiento().toString();
		}
		return arregloSilos;
	}

	/**
	 * Metodo que convierte una lista de puestoTrabajoDTO a LinkedHashMap que
	 * contiene el codigo y el nombre
	 * 
	 * @param divisionesDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaPuestoTrabajoDTO(List<PuestoTrabajoDTO> puestosDTO) {
		LinkedHashMap<String, String> mapaPuestos = new LinkedHashMap<String, String>();

		for (int i = 0; i < puestosDTO.size(); i++) {
			mapaPuestos.put(puestosDTO.get(i).getPkCodigoPuestotrabajo().toString(), puestosDTO.get(i).getNombrePuestotrabajo());
			// Window.alert("puestosDTO.get(i).getNombrePuestotrabajo()
			// "+puestosDTO.get(i).getNombrePuestotrabajo());
		}
		return mapaPuestos;
	}

	/**
	 * Metodo que convierte una lista de puestoTrabajoDTO a LinkedHashMap que
	 * contiene el codigo y el nombre
	 * 
	 * @param divisionesDTO
	 * @return
	 */
	public static LinkedHashMap<String, String> obtenerMapaTableroControlDTO(List<TableroControlDTO> tablerosControlDTO) {
		LinkedHashMap<String, String> mapaTableros = new LinkedHashMap<String, String>();

		for (TableroControlDTO tableroControlDTO : tablerosControlDTO) {
			mapaTableros.put(tableroControlDTO.getPkCodigoTableroControl().toString(), tableroControlDTO
					.getNombreTableroControl().toString());
		}
		return mapaTableros;
	}

	/**
	 * Metodo que converite una lista de ListGridField a arreglo de
	 * ListGridField
	 * 
	 * @param listaColumnas
	 * @return
	 */
	public static ListGridField[] obtenerArregloDeListaListGridField(List<ListGridField> listaColumnas) {

		ListGridField[] arreglolistGridField = new ListGridField[listaColumnas.size()];
		for (int i = 0; i < listaColumnas.size(); i++) {
			ListGridField campo = listaColumnas.get(i);
			arreglolistGridField[i] = campo;
		}
		return arreglolistGridField;
	}

	/**
	 * Metodo que convierte un enum a un arreglo de String
	 * 
	 * @param <T>
	 * @param values
	 * @return
	 */
	public static <T extends Enum<T>> String[] convertirStringEnumToStringArray(T[] values) {
		int i = 0;
		String[] arregloString = new String[values.length];
		for (T value : values) {
			arregloString[i++] = value.name();
		}
		return arregloString;
	}

	public static LinkedHashMap<String, String> obtenerMapaEstadoCubicacion(List<EstadocubicacionDTO> estadoCubicacionDTO) {
		LinkedHashMap<String, String> mapaEstadosCubicacion = new LinkedHashMap<String, String>();

		for (int i = 0; i < estadoCubicacionDTO.size(); i++) {
			mapaEstadosCubicacion.put(estadoCubicacionDTO.get(i).getPkCodigoEstadocubicacion().toString(), estadoCubicacionDTO
					.get(i).getNombreEstadocubicacion());
		}
		return mapaEstadosCubicacion;
	}

	public static Map<Long, String> getMapaTipoMedioaALm() {
		return mapaTipoMedioaALm;
	}

	public static void setMapaTipoMedioaALm(Map<Long, String> mapaMedioaALm) {
		UtilConverter.mapaTipoMedioaALm = mapaMedioaALm;
	}

	public static Map<Long, String> getMapaMedioaALm() {
		return mapaMedioaALm;
	}

	public static void setMapaMedioaALm(Map<Long, String> mapaMedioaALm) {
		UtilConverter.mapaMedioaALm = mapaMedioaALm;
	}

	public static Map<Long, ProductoDTO> obtenerMapProductos(List<ProductoDTO> productoDTOs) {
		HashMap<Long, ProductoDTO> mapaProductos = new HashMap<Long, ProductoDTO>();

		for (ProductoDTO productoDTO : productoDTOs) {
			mapaProductos.put(productoDTO.getPkCodigoProducto(), productoDTO);
		}

		return mapaProductos;
	}

}