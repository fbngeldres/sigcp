package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Componente entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Componente implements java.io.Serializable {

	// Fields

	private Long pkCodigoComponente;
	private Producto productoByFkCodigoProductoComponente;
	private Producto productoByFkCodigoProducto;
	private Set componentes = new HashSet(0);
	private Set configuracionreceptors = new HashSet(0);
	private Set consumocomponentemanuals = new HashSet(0);
	private Set factordosificacions = new HashSet(0);
	private Set hojarutacomponentes = new HashSet(0);
	private Set columnaplantillaproductos = new HashSet(0);
	private Set componentenotificacions = new HashSet(0);
	private Set consumocomponenteajustes = new HashSet(0);
	private Set consumocomponentes = new HashSet(0);
	private Set consumopuestotrabajos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Componente() {
	}

	/** minimal constructor */
	public Componente(Producto productoByFkCodigoProductoComponente, Producto productoByFkCodigoProducto) {
		this.productoByFkCodigoProductoComponente = productoByFkCodigoProductoComponente;
		this.productoByFkCodigoProducto = productoByFkCodigoProducto;
	}

	/** full constructor */
	public Componente(Producto productoByFkCodigoProductoComponente, Producto productoByFkCodigoProducto,
			Set consumocomponentemanuals, Set factordosificacions, Set hojarutacomponentes, Set columnaplantillaproductos,
			Set componentenotificacions, Set consumocomponenteajustes, Set consumocomponentes, Set consumopuestotrabajos,
			Set configuracionreceptors) {
		this.productoByFkCodigoProductoComponente = productoByFkCodigoProductoComponente;
		this.productoByFkCodigoProducto = productoByFkCodigoProducto;
		this.consumocomponentemanuals = consumocomponentemanuals;
		this.factordosificacions = factordosificacions;
		this.hojarutacomponentes = hojarutacomponentes;
		this.columnaplantillaproductos = columnaplantillaproductos;
		this.componentenotificacions = componentenotificacions;
		this.consumocomponenteajustes = consumocomponenteajustes;
		this.consumocomponentes = consumocomponentes;
		this.consumopuestotrabajos = consumopuestotrabajos;
		this.configuracionreceptors = configuracionreceptors;
	}

	// Property accessors

	public Long getPkCodigoComponente() {
		return this.pkCodigoComponente;
	}

	public void setPkCodigoComponente(Long pkCodigoComponente) {
		this.pkCodigoComponente = pkCodigoComponente;
	}

	public Producto getProductoByFkCodigoProductoComponente() {
		return this.productoByFkCodigoProductoComponente;
	}

	public void setProductoByFkCodigoProductoComponente(Producto productoByFkCodigoProductoComponente) {
		this.productoByFkCodigoProductoComponente = productoByFkCodigoProductoComponente;
	}

	public Producto getProductoByFkCodigoProducto() {
		return this.productoByFkCodigoProducto;
	}

	public void setProductoByFkCodigoProducto(Producto productoByFkCodigoProducto) {
		this.productoByFkCodigoProducto = productoByFkCodigoProducto;
	}

	public Set getConsumocomponentemanuals() {
		return this.consumocomponentemanuals;
	}

	public void setConsumocomponentemanuals(Set consumocomponentemanuals) {
		this.consumocomponentemanuals = consumocomponentemanuals;
	}

	public Set getFactordosificacions() {
		return this.factordosificacions;
	}

	public void setFactordosificacions(Set factordosificacions) {
		this.factordosificacions = factordosificacions;
	}

	public Set getHojarutacomponentes() {
		return this.hojarutacomponentes;
	}

	public void setHojarutacomponentes(Set hojarutacomponentes) {
		this.hojarutacomponentes = hojarutacomponentes;
	}

	public Set getColumnaplantillaproductos() {
		return this.columnaplantillaproductos;
	}

	public void setColumnaplantillaproductos(Set columnaplantillaproductos) {
		this.columnaplantillaproductos = columnaplantillaproductos;
	}

	public Set getComponentenotificacions() {
		return this.componentenotificacions;
	}

	public void setComponentenotificacions(Set componentenotificacions) {
		this.componentenotificacions = componentenotificacions;
	}

	public Set getConsumocomponenteajustes() {
		return this.consumocomponenteajustes;
	}

	public void setConsumocomponenteajustes(Set consumocomponenteajustes) {
		this.consumocomponenteajustes = consumocomponenteajustes;
	}

	public Set getConsumocomponentes() {
		return this.consumocomponentes;
	}

	public void setConsumocomponentes(Set consumocomponentes) {
		this.consumocomponentes = consumocomponentes;
	}

	public Set getConsumopuestotrabajos() {
		return this.consumopuestotrabajos;
	}

	public void setConsumopuestotrabajos(Set consumopuestotrabajos) {
		this.consumopuestotrabajos = consumopuestotrabajos;
	}

	public Set getComponentes() {
		return componentes;
	}

	public void setComponentes(Set componentes) {
		this.componentes = componentes;
	}

	public Set getConfiguracionreceptors() {
		return configuracionreceptors;
	}

	public void setConfiguracionreceptors(Set configuracionreceptors) {
		this.configuracionreceptors = configuracionreceptors;
	}

}