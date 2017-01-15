package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Plantillaproducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Plantillaproducto implements java.io.Serializable {

	// Fields

	private Long pkCodigoPlantillaproducto;
	private Producto producto;
	private Plantillareporte plantillareporte;
	private Integer versionPlantillaproducto;
	private Date fechaPlantillaproducto;
	private Set columnaplantillaproductos = new HashSet(0);
	private Set notificacionproduccions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Plantillaproducto() {
	}

	/** minimal constructor */
	public Plantillaproducto(Producto producto, Plantillareporte plantillareporte) {
		this.producto = producto;
		this.plantillareporte = plantillareporte;
	}

	/** full constructor */
	public Plantillaproducto(Producto producto, Plantillareporte plantillareporte, Set columnaplantillaproductos) {
		this.producto = producto;
		this.plantillareporte = plantillareporte;
		this.columnaplantillaproductos = columnaplantillaproductos;
	}

	// Property accessors

	public Long getPkCodigoPlantillaproducto() {
		return this.pkCodigoPlantillaproducto;
	}

	public void setPkCodigoPlantillaproducto(Long pkCodigoPlantillaproducto) {
		this.pkCodigoPlantillaproducto = pkCodigoPlantillaproducto;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Plantillareporte getPlantillareporte() {
		return this.plantillareporte;
	}

	public void setPlantillareporte(Plantillareporte plantillareporte) {
		this.plantillareporte = plantillareporte;
	}

	public Set getColumnaplantillaproductos() {
		return this.columnaplantillaproductos;
	}

	public void setColumnaplantillaproductos(Set columnaplantillaproductos) {
		this.columnaplantillaproductos = columnaplantillaproductos;
	}

	public Set getNotificacionproduccions() {
		return notificacionproduccions;
	}

	public void setNotificacionproduccions(Set notificacionproduccions) {
		this.notificacionproduccions = notificacionproduccions;
	}

	public Integer getVersionPlantillaproducto() {
		return versionPlantillaproducto;
	}

	public void setVersionPlantillaproducto(Integer versionPlantillaproducto) {
		this.versionPlantillaproducto = versionPlantillaproducto;
	}

	public Date getFechaPlantillaproducto() {
		return fechaPlantillaproducto;
	}

	public void setFechaPlantillaproducto(Date fechaPlantillaproducto) {
		this.fechaPlantillaproducto = fechaPlantillaproducto;
	}

}