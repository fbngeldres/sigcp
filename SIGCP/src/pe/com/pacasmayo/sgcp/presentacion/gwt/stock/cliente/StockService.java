package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente;

import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaMedicionDiaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaStockDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("servicioStock")
public interface StockService extends RemoteService {

	public List<RegistroTablaStockDTO> obtenerListaRegistroStock(Long codigoProceso, Long codigoEstadoRegistroMedicion,
			Long codigoTipoMedio, Integer anno, String mes) throws ServicioGWTGlobalException;

	public List<RegistroTablaMedicionDiaDTO> obtenerListaDetallesSilos(Long codigoProceso, Long codigoTipoMedio, String fecha,
			Long codigoEstado, Long lineaNegocio) throws ServicioGWTGlobalException;

	public List<RegistroTablaMedicionDiaDTO> obtenerListaRegistroDetallesStock(Long codigoRegistroMedicion)
			throws ServicioGWTGlobalException;

	public String cambiarEstadoRegistroMedicion(Long codigoRegistroMedicion, Long codigoEstado) throws ServicioGWTGlobalException;

	public String registrarMedicion(Long codigoProceso, Long codigoEstado, Long codigoTipoMedio,
			List<RegistroTablaMedicionDiaDTO> listaRegistros, String fecha) throws ServicioGWTGlobalException;

	public Double obtenerCantidadAlmacenadaFormula(Long codigoSilo, List<Double> alturas) throws ServicioGWTGlobalException;
}