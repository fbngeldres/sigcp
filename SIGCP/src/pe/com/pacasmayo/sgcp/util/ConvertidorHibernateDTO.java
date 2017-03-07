package pe.com.pacasmayo.sgcp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Alturasilo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacionproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Division;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadocubicacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medicion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Sociedad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomedioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.CubicacionProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.LineaNegocioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedioAlmacenamientoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProcesoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaMedicionDiaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TablaCubicacionDTO;

/**
 * Clase que convierte los objetos de hibernate en DTO
 * 
 * @author Hector
 */
public class ConvertidorHibernateDTO {

	/**
	 * Metodo que convierte una Produccion a ProduccionDTO
	 * 
	 * @param produccion
	 * @return
	 */
	public static ProduccionDTO convertirProduccionAProduccionDTO(Produccion produccion) {

		ProduccionDTO produccionDTO = new ProduccionDTO();
		produccionDTO.setPkProduccion(produccion.getPkProduccion());
		produccionDTO.setProceso(convertirProcesoAProcesoDTO(produccion.getProceso()));
		produccionDTO.setProducto(convertirProductoAProductoDTO(produccion.getProducto()));
		produccionDTO.setMedios(convertirSetMedios(produccion.getMedioalmacenamientos()));
		return produccionDTO;
	}

	/**
	 * Metodo que convierte el Set de Medio a Set de MedioDTO
	 * 
	 * @param medioalmacenamientos
	 * @return
	 */
	public static List<MedioAlmacenamientoDTO> convertirSetMedios(Set<Medioalmacenamiento> medioalmacenamientos) {

		List<MedioAlmacenamientoDTO> mediosDTO = new ArrayList<MedioAlmacenamientoDTO>();
		for (Iterator<Medioalmacenamiento> iterator = medioalmacenamientos.iterator(); iterator.hasNext();) {
			Medioalmacenamiento medio = iterator.next();
			mediosDTO.add(convertirMedioAMedioDTO(medio));
		}
		return mediosDTO;
	}

	/**
	 * Metodo que convierte un Medio a un MedioDTO
	 * 
	 * @param medio
	 */
	public static MedioAlmacenamientoDTO convertirMedioAMedioDTO(Medioalmacenamiento medio) {
		MedioAlmacenamientoDTO medioDTO = new MedioAlmacenamientoDTO();

		medioDTO.setPkCodigoMedioalmacenamiento(medio.getPkCodigoMedioalmacenamiento());
		medioDTO.setNombreMedioalmacenamiento(medio.getNombreMedioalmacenamiento());
		medioDTO.setNumeroMedioalmacenamiento(medio.getNumeroMedioalmacenamiento());
		return medioDTO;
	}

	/**
	 * Metodo que convierte una Ordenproduccion a OrdenProduccionDTO
	 * 
	 * @param medio
	 */
	public static OrdenProduccionDTO convertirOrdenproduccionAOrdenProduccionDTO(Ordenproduccion orden) {

		OrdenProduccionDTO ordenDTO = new OrdenProduccionDTO();
		ordenDTO.setPkCodigoOrdenproduccion(orden.getPkCodigoOrdenproduccion());
		ordenDTO.setFechaAprobacionOrdenproduccio(orden.getFechaAprobacionOrdenproduccio());
		ordenDTO.setFechaRegistroOrdenproduccion(orden.getFechaRegistroOrdenproduccion());
		ordenDTO.setMesOrdenproduccion(orden.getMesOrdenproduccion());
		ordenDTO.setNumeroDocumentoOrdenproduccio(orden.getNumeroDocumentoOrdenproduccio());
		ordenDTO.setNumeroOrdenOrdenproduccion(orden.getNumeroOrdenOrdenproduccion());

		ordenDTO.setProduccion(convertirProduccionAProduccionDTO(orden.getProduccion()));
		ordenDTO.setProduccionEjecutadaOrdenprodu(orden.getProduccionEjecutadaOrdenprodu());
		ordenDTO.setProduccionEstimadaOrdenproduc(orden.getProduccionEstimadaOrdenproduc());

		return ordenDTO;
	}

	/**
	 * Metodo que convierte un Proceso a ProcesoDTO
	 * 
	 * @param proceso
	 * @return
	 */
	public static ProcesoDTO convertirProcesoAProcesoDTO(Proceso proceso) {

		ProcesoDTO procesoDTO = new ProcesoDTO();
		procesoDTO.setPkCodigoProceso(proceso.getPkCodigoProceso());
		procesoDTO.setCodigoSccProceso(proceso.getCodigoSccProceso());
		procesoDTO.setNombreProceso(proceso.getNombreProceso());
		procesoDTO.setDescripcionProceso(proceso.getDescripcionProceso());
		procesoDTO.setOrdenEjecucionProceso(proceso.getOrdenEjecucionProceso());
		procesoDTO.setSiglasProceso(proceso.getSiglasProceso());
		procesoDTO.setLineaNegocio(convertirLineaNegocioALineaNegocioDTO(proceso.getLineanegocio()));
		return procesoDTO;
	}

	/**
	 * Metodo que convierte una LineaNegocio a LineaNegocioDTO
	 * 
	 * @param proceso
	 * @return
	 */
	public static LineaNegocioDTO convertirLineaNegocioALineaNegocioDTO(Lineanegocio lineaNegocio) {

		LineaNegocioDTO lineaNegocioDTO = new LineaNegocioDTO();
		lineaNegocioDTO.setPkCodigoLineanegocio(lineaNegocio.getPkCodigoLineanegocio());
		lineaNegocioDTO.setNombreLineanegocio(lineaNegocio.getNombreLineanegocio());

		return lineaNegocioDTO;
	}

	/**
	 * Metodo que convierte un Producto a ProductoDTO
	 * 
	 * @param producto
	 * @return
	 */
	public static ProductoDTO convertirProductoAProductoDTO(Producto producto) {

		ProductoDTO productoDTO = new ProductoDTO();
		productoDTO.setPkCodigoProducto(producto.getPkCodigoProducto());
		productoDTO.setFkCodigoTipoProducto(producto.getTipoproducto().getPkCodigoTipoproducto());
		productoDTO.setNombreProducto(producto.getNombreProducto());
		productoDTO.setDescripcionProducto(producto.getDescripcionProducto());
		productoDTO.setGrupoProducto(producto.getGrupoProducto());
		return productoDTO;
	}

	/**
	 * Metodo que convierte una CubicacionProducto a CubicacionProductoDTO
	 * 
	 * @param cubicacionproducto
	 * @return
	 */
	public static CubicacionProductoDTO convertirCubicacionProductoACubicacionProductoDTO(Cubicacionproducto cubicacionProducto) {

		CubicacionProductoDTO cubicacionProductoDTO = new CubicacionProductoDTO();
		cubicacionProductoDTO.setPkCodigoCubicacionproducto(cubicacionProducto.getPkCodigoCubicacionproducto());
		cubicacionProductoDTO.setFechaCubicacionproducto(cubicacionProducto.getFechaCubicacionproducto());
		cubicacionProductoDTO.setStockFisicoCubicacionproducto(cubicacionProducto.getStockFisicoCubicacionproducto());
		cubicacionProductoDTO.setMesCubicacionproducto(cubicacionProducto.getMesCubicacionproducto());
		cubicacionProductoDTO.setAnoCubicacionproducto(cubicacionProducto.getAnoCubicacionproducto());
		cubicacionProductoDTO.setToneladasCubicacionproducto(cubicacionProducto.getToneladasCubicacionproducto());

		Lineanegocio ln = cubicacionProducto.getLineanegocio();
		Unidad un = ln.getUnidad();
		Sociedad soc = un.getSociedad();
		Division div = soc.getDivision();
		Producto prod = cubicacionProducto.getProduccion().getProducto();
		Proceso proc = cubicacionProducto.getProduccion().getProceso();
		Estadocubicacion estado = cubicacionProducto.getEstadocubicacion();

		cubicacionProductoDTO.setCodigoDivision(div.getPkCodigoDivision());
		cubicacionProductoDTO.setCodigoSociedad(soc.getPkCodigoSociedad());
		cubicacionProductoDTO.setCodigoUnidad(un.getPkCodigoUnidad());
		cubicacionProductoDTO.setCodigoLineanegocio(ln.getPkCodigoLineanegocio());
		cubicacionProductoDTO.setCodigoProceso(proc.getPkCodigoProceso());
		cubicacionProductoDTO.setCodigoProducto(prod.getPkCodigoProducto());
		cubicacionProductoDTO.setCodigoEstadocubicacion(estado.getPkCodigoEstadocubicacion());

		cubicacionProductoDTO.setNombreDivision(div.getNombreDivision());
		cubicacionProductoDTO.setNombreSociedad(soc.getNombreSociedad());
		cubicacionProductoDTO.setNombreUnidad(un.getNombreUnidad());
		cubicacionProductoDTO.setNombreLineaNegocio(ln.getNombreLineanegocio());
		cubicacionProductoDTO.setNombreProceso(proc.getNombreProceso());
		cubicacionProductoDTO.setNombreProducto(prod.getNombreProducto());
		cubicacionProductoDTO.setNombreEstado(estado.getNombreEstadocubicacion());

		cubicacionProductoDTO.setProduccion(convertirProduccionAProduccionDTO(cubicacionProducto.getProduccion()));

		cubicacionProductoDTO.setCubicaciones(convertirCubicacionesACubicacionesDTO(cubicacionProducto.getCubicacions()));

		return cubicacionProductoDTO;
	}

	/**
	 * Metodo que convierte unas Cubicaciones a una lista de TablaCubicacionDTO
	 * 
	 * @param cubicacion
	 * @return
	 */
	public static List<TablaCubicacionDTO> convertirCubicacionesACubicacionesDTO(Set<Cubicacion> cubicaciones) {

		ArrayList<TablaCubicacionDTO> cubicacionesDTO = new ArrayList<TablaCubicacionDTO>();
		TablaCubicacionDTO cubicacionDTO;

		for (Cubicacion cubicacion : cubicaciones) {
			cubicacionDTO = convertirCubicacionACubicacionDTO(cubicacion);
			cubicacionesDTO.add(cubicacionDTO);
		}

		Collections.sort(cubicacionesDTO, new Comparator<TablaCubicacionDTO>() {
			public int compare(TablaCubicacionDTO o1, TablaCubicacionDTO o2) {
				return o1.getMedioAlmacenamiento().compareToIgnoreCase(o2.getMedioAlmacenamiento());
			}
		});

		return cubicacionesDTO;
	}

	/**
	 * Metodo que convierte unas Cubicacion a TablaCubicacionDTO
	 * 
	 * @param cubicacion
	 * @return
	 */
	private static TablaCubicacionDTO convertirCubicacionACubicacionDTO(Cubicacion cubicacion) {
		Medioalmacenamiento medioAlmacenamiento = cubicacion.getMedioalmacenamiento();
		Tipomedioalmacenamiento tipoMedioAlmacenamiento = medioAlmacenamiento.getTipomedioalmacenamiento();

		TablaCubicacionDTO cubicacionDTO = new TablaCubicacionDTO();
		cubicacionDTO.setVolumen(cubicacion.getVolumenM3Cubicacion());

		if (cubicacion.getDensidadMedioalmacenamiento() != null) {
			cubicacionDTO.setDensidadMedioAlmc(cubicacion.getDensidadMedioalmacenamiento());
		} else {
			cubicacionDTO.setDensidadMedioAlmc(medioAlmacenamiento.getDensidadMedioalmacenamiento());
		}

		cubicacionDTO.setAreaCresta(cubicacion.getAreaCrestaM2Cubicacion());
		cubicacionDTO.setAreaPie(cubicacion.getAreaPieM2Cubicacion());
		cubicacionDTO.setDiferenciaNivel(cubicacion.getDiferenciaNivelMtsCubicacion());
		cubicacionDTO.setObservacionCubicacion(cubicacion.getObservacionCubicacion());
		cubicacionDTO.setAreaOcupada(cubicacion.getAreaOcupadaM2Cubicacion());
		cubicacionDTO.setRelacionCubicacion(cubicacion.getRelacionCubicacion());
		cubicacionDTO.setAlturaLadoCarboni(cubicacion.getAlturaLadoCarbonMtsCubicaci());
		cubicacionDTO.setAlturaCentral(cubicacion.getAlturaCentralMtsCubicacion());
		cubicacionDTO.setAlturaLadoClinker(cubicacion.getAlturaLadoClinkerMtsCubicac());
		cubicacionDTO.setAlturaLibrePromedio(cubicacion.getAlturaLibrePromedioMtsCubic());
		cubicacionDTO.setUnidad(cubicacion.getUnidadKgCubicacion());
		cubicacionDTO.setMedioAlmacenamiento(medioAlmacenamiento.getNombreMedioalmacenamiento());
		cubicacionDTO.setTipoMedioAlmacenamiento(tipoMedioAlmacenamiento.getNombreTipomedioalmacenamiento());
		cubicacionDTO.setCodigoMedioalmacenamiento(medioAlmacenamiento.getPkCodigoMedioalmacenamiento().intValue());

		return cubicacionDTO;
	}

	/**
	 * Metodo que convierte unas Cubicacion a TablaCubicacionDTO
	 * 
	 * @param cubicacion
	 * @return
	 */
	public static RegistroTablaMedicionDiaDTO convertirMedicionAMedicionDTO(Medicion medicion) {
		RegistroTablaMedicionDiaDTO medicionDTO = new RegistroTablaMedicionDiaDTO();

		medicionDTO.setCodigoProducto(medicion.getProduccion().getProducto().getPkCodigoProducto());
		medicionDTO.setCodigoProduccion(medicion.getProduccion().getPkProduccion());
		medicionDTO.setNombreProducto(medicion.getProduccion().getProducto().getNombreProducto());
		medicionDTO.setCodigoSilo(medicion.getMedioalmacenamiento().getPkCodigoMedioalmacenamiento());
		medicionDTO.setNombreSilo(medicion.getMedioalmacenamiento().getNombreMedioalmacenamiento());
		medicionDTO.setCapacidad(medicion.getMedioalmacenamiento().getCapacidadMaximaMedioalmacenam());
		medicionDTO.setNumeroAlturas(medicion.getMedioalmacenamiento().getNumeroAlturasMedioalmacenamie());
		medicionDTO.setAlturaSilo(medicion.getMedioalmacenamiento().getAlturaEspecificaMedioalmacena());
		medicionDTO.setFactorMetrosCubicos(medicion.getMedioalmacenamiento().getFactorMetrosCubicosMedioalma());
		medicionDTO.setStockSeguridad(medicion.getMedioalmacenamiento().getStockSeguridadMedioalmacenami());
		medicionDTO.setCantidad(medicion.getCantidadAlmacenadaMedicion());
		medicionDTO.setPorcentajeUso(medicion.getPorcentajeUsoMedicion());
		medicionDTO
				.setCodigoEstado(medicion.getRegistromedicion().getEstadoregistromedicion().getPkCodigoEstadoregistromedicio());
		ArrayList<Double> alturasSilos = new ArrayList<Double>();
		medicionDTO.setCodigo(medicion.getPkCodigoMedicion());
		List<Alturasilo> alturassiloOrdenado = new ArrayList<Alturasilo>(medicion.getAlturasilos());
		Collections.sort(alturassiloOrdenado, new Comparator<Alturasilo>() {

			public int compare(Alturasilo o1, Alturasilo o2) {
				return o1.getNumeroAlturaAlturasilo().compareTo(o2.getNumeroAlturaAlturasilo());
			}

		});
		for (Iterator<Alturasilo> iterator = alturassiloOrdenado.iterator(); iterator.hasNext();) {
			Alturasilo alturaSilo = (Alturasilo) iterator.next();
			alturasSilos.add(alturaSilo.getMedicionAlturaAlturasilo());
		}
		medicionDTO.setListaAlturas(alturasSilos);

		return medicionDTO;
	}
}
