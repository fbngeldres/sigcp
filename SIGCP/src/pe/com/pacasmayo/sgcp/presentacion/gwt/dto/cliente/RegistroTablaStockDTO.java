package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa una fila en la tabla de stock. Cada registro contiene:
 * Date: fecha List<ColumnaTablaStockDTO>: lista de ColumnaTablaStockDTO del dia
 */
public class RegistroTablaStockDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigoRegistro;
	private String fecha;
	private String nombreEstado;
	private Long codigoEstado;
	private List<StockDTO> columnas = new ArrayList<StockDTO>();

	public List<StockDTO> getColumnas() {
		return columnas;
	}

	public void setColumnas(List<StockDTO> columnas) {
		this.columnas = columnas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public Long getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Long codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public Long getCodigoRegistro() {
		return codigoRegistro;
	}

	public void setCodigoRegistro(Long codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}
}