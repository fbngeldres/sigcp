package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;

public class GrupoAjusteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8535056347071454189L;

	private Short ordenPlantilla;
	private String nombre;
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Short getOrdenPlantilla() {
		return ordenPlantilla;
	}

	public void setOrdenPlantilla(Short ordenPlantilla) {
		this.ordenPlantilla = ordenPlantilla;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
