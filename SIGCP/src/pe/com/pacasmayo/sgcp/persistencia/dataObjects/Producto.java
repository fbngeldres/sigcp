package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Producto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Producto implements java.io.Serializable {

	// Fields

	private Long pkCodigoProducto;
	private Unidadmedida unidadmedida;
	private Tipocategoriaproducto tipocategoriaproducto;
	private Tipoproducto tipoproducto;
	private Estadoproducto estadoproducto;
	private String nombreProducto;
	private String descripcionProducto;
	private String siglasProducto;
	private Double stockMinimoProducto;
	private Double stockMaximoProducto;
	private String codigoSapProducto;
	private String codigoSapBolsaProducto;
	private String codigoSapBigBagProducto;
	private Long codigoSccProducto;
	private Double costoProducto;
	private Boolean estadoFisicoSolidoProducto;
	private String codigoSapEspecial1Producto;
	private String codigoSapEspecial2Producto;
	private String codigoSapEspecial3Producto;
	private String codigoSapEspecial4Producto;
	private String grupoProducto;
	private Tipoconsumo tipoconsumo;
	
	
	private Set plantillaproductos = new HashSet(0);

	
	private Set ingresoproductoprocesos = new HashSet(0);
	
	private Set movimientos = new HashSet(0);
	private Set componentesForFkCodigoProductoComponente = new HashSet(0);
	private Set componentesForFkCodigoProducto = new HashSet(0);
	private Set productovariablevariacions = new HashSet(0);
	private Set movimientoajustes = new HashSet(0);
	
	private Set produccions = new HashSet(0);
	private Set producciondiarias = new HashSet(0);

	// Constructors

	/** default constructor */
	public Producto() {
	}

	/** minimal constructor */
	public Producto(Tipoproducto tipoproducto, Estadoproducto estadoproducto, String nombreProducto, String siglasProducto,
			Double stockMinimoProducto, Double stockMaximoProducto) {
		this.tipoproducto = tipoproducto;
		this.estadoproducto = estadoproducto;
		this.nombreProducto = nombreProducto;
		this.siglasProducto = siglasProducto;
		this.stockMinimoProducto = stockMinimoProducto;
		this.stockMaximoProducto = stockMaximoProducto;
	}

	/** full constructor */
	public Producto(Long pkCodigoProducto, Unidadmedida unidadmedida, Tipocategoriaproducto tipocategoriaproducto,
			Tipoproducto tipoproducto, Estadoproducto estadoproducto, String nombreProducto, String descripcionProducto,
			String siglasProducto, Double stockMinimoProducto, Double stockMaximoProducto, String codigoSapProducto,
			String codigoSapBolsaProducto, String codigoSapBigBagProducto, Long codigoSccProducto, Double costoProducto,
			Boolean estadoFisicoSolidoProducto, String codigoSapEspecial1Producto, String codigoSapEspecial2Producto,
			String codigoSapEspecial3Producto, String codigoSapEspecial4Producto, String grupoProducto, Tipoconsumo tipoconsumo,
			 Set plantillaproductos,
			 
			 Set ingresoproductoprocesos,  Set movimientos,
			Set componentesForFkCodigoProductoComponente, Set componentesForFkCodigoProducto, Set productovariablevariacions,
			Set movimientoajustes, Set produccions, Set producciondiarias) {

		this.pkCodigoProducto = pkCodigoProducto;
		this.unidadmedida = unidadmedida;
		this.tipocategoriaproducto = tipocategoriaproducto;
		this.tipoproducto = tipoproducto;
		this.estadoproducto = estadoproducto;
		this.nombreProducto = nombreProducto;
		this.descripcionProducto = descripcionProducto;
		this.siglasProducto = siglasProducto;
		this.stockMinimoProducto = stockMinimoProducto;
		this.stockMaximoProducto = stockMaximoProducto;
		this.codigoSapProducto = codigoSapProducto;
		this.codigoSapBolsaProducto = codigoSapBolsaProducto;
		this.codigoSapBigBagProducto = codigoSapBigBagProducto;
		this.codigoSccProducto = codigoSccProducto;
		this.costoProducto = costoProducto;
		this.estadoFisicoSolidoProducto = estadoFisicoSolidoProducto;
		this.codigoSapEspecial1Producto = codigoSapEspecial1Producto;
		this.codigoSapEspecial2Producto = codigoSapEspecial2Producto;
		this.codigoSapEspecial3Producto = codigoSapEspecial3Producto;
		this.codigoSapEspecial4Producto = codigoSapEspecial4Producto;
		this.grupoProducto = grupoProducto;
		this.tipoconsumo = tipoconsumo;
		
		this.plantillaproductos = plantillaproductos;
	
		
		this.ingresoproductoprocesos = ingresoproductoprocesos;
		
		this.movimientos = movimientos;
		this.componentesForFkCodigoProductoComponente = componentesForFkCodigoProductoComponente;
		this.componentesForFkCodigoProducto = componentesForFkCodigoProducto;
		this.productovariablevariacions = productovariablevariacions;
		this.movimientoajustes = movimientoajustes;

		this.produccions = produccions;
		this.producciondiarias = producciondiarias;
	}

	// Property accessors

	public Long getPkCodigoProducto() {
		return this.pkCodigoProducto;
	}

	public void setPkCodigoProducto(Long pkCodigoProducto) {
		this.pkCodigoProducto = pkCodigoProducto;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public Tipocategoriaproducto getTipocategoriaproducto() {
		return this.tipocategoriaproducto;
	}

	public void setTipocategoriaproducto(Tipocategoriaproducto tipocategoriaproducto) {
		this.tipocategoriaproducto = tipocategoriaproducto;
	}

	public Tipoproducto getTipoproducto() {
		return this.tipoproducto;
	}

	public void setTipoproducto(Tipoproducto tipoproducto) {
		this.tipoproducto = tipoproducto;
	}

	public Estadoproducto getEstadoproducto() {
		return this.estadoproducto;
	}

	public void setEstadoproducto(Estadoproducto estadoproducto) {
		this.estadoproducto = estadoproducto;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcionProducto() {
		return this.descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getSiglasProducto() {
		return this.siglasProducto;
	}

	public void setSiglasProducto(String siglasProducto) {
		this.siglasProducto = siglasProducto;
	}

	public Double getStockMinimoProducto() {
		return this.stockMinimoProducto;
	}

	public void setStockMinimoProducto(Double stockMinimoProducto) {
		this.stockMinimoProducto = stockMinimoProducto;
	}

	public Double getStockMaximoProducto() {
		return this.stockMaximoProducto;
	}

	public void setStockMaximoProducto(Double stockMaximoProducto) {
		this.stockMaximoProducto = stockMaximoProducto;
	}

	public String getCodigoSapProducto() {
		return this.codigoSapProducto;
	}

	public void setCodigoSapProducto(String codigoSapProducto) {
		this.codigoSapProducto = codigoSapProducto;
	}

	public Long getCodigoSccProducto() {
		return this.codigoSccProducto;
	}

	public void setCodigoSccProducto(Long codigoSccProducto) {
		this.codigoSccProducto = codigoSccProducto;
	}

	public Double getCostoProducto() {
		return this.costoProducto;
	}

	public void setCostoProducto(Double costoProducto) {
		this.costoProducto = costoProducto;
	}

	public String getCodigoSapBolsaProducto() {
		return this.codigoSapBolsaProducto;
	}

	public void setCodigoSapBolsaProducto(String codigoSapBolsaProducto) {
		this.codigoSapBolsaProducto = codigoSapBolsaProducto;
	}

	public String getCodigoSapBigBagProducto() {
		return this.codigoSapBigBagProducto;
	}

	public void setCodigoSapBigBagProducto(String codigoSapBigbagProducto) {
		this.codigoSapBigBagProducto = codigoSapBigbagProducto;
	}

	public Boolean getEstadoFisicoSolidoProducto() {
		return this.estadoFisicoSolidoProducto;
	}

	public void setEstadoFisicoSolidoProducto(Boolean estadoFisicoSolidoProducto) {
		this.estadoFisicoSolidoProducto = estadoFisicoSolidoProducto;
	}

	public String getCodigoSapEspecial1Producto() {
		return this.codigoSapEspecial1Producto;
	}

	public void setCodigoSapEspecial1Producto(String codigoSapEspecial1Producto) {
		this.codigoSapEspecial1Producto = codigoSapEspecial1Producto;
	}

	public String getCodigoSapEspecial2Producto() {
		return this.codigoSapEspecial2Producto;
	}

	public void setCodigoSapEspecial2Producto(String codigoSapEspecial2Producto) {
		this.codigoSapEspecial2Producto = codigoSapEspecial2Producto;
	}

	public String getCodigoSapEspecial3Producto() {
		return this.codigoSapEspecial3Producto;
	}

	public void setCodigoSapEspecial3Producto(String codigoSapEspecial3Producto) {
		this.codigoSapEspecial3Producto = codigoSapEspecial3Producto;
	}

	public String getCodigoSapEspecial4Producto() {
		return this.codigoSapEspecial4Producto;
	}

	public void setCodigoSapEspecial4Producto(String codigoSapEspecial4Producto) {
		this.codigoSapEspecial4Producto = codigoSapEspecial4Producto;
	}


	public Set getPlantillaproductos() {
		return this.plantillaproductos;
	}

	public void setPlantillaproductos(Set plantillaproductos) {
		this.plantillaproductos = plantillaproductos;
	}

	

	public Set getIngresoproductoprocesos() {
		return this.ingresoproductoprocesos;
	}

	public void setIngresoproductoprocesos(Set ingresoproductoprocesos) {
		this.ingresoproductoprocesos = ingresoproductoprocesos;
	}

	
	public Set getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(Set movimientos) {
		this.movimientos = movimientos;
	}

	public Set getComponentesForFkCodigoProductoComponente() {
		return this.componentesForFkCodigoProductoComponente;
	}

	public void setComponentesForFkCodigoProductoComponente(Set componentesForFkCodigoProductoComponente) {
		this.componentesForFkCodigoProductoComponente = componentesForFkCodigoProductoComponente;
	}

	public Set getComponentesForFkCodigoProducto() {
		return this.componentesForFkCodigoProducto;
	}

	public void setComponentesForFkCodigoProducto(Set componentesForFkCodigoProducto) {
		this.componentesForFkCodigoProducto = componentesForFkCodigoProducto;
	}

	public Set getProductovariablevariacions() {
		return this.productovariablevariacions;
	}

	public void setProductovariablevariacions(Set productovariablevariacions) {
		this.productovariablevariacions = productovariablevariacions;
	}

	public Set getMovimientoajustes() {
		return this.movimientoajustes;
	}

	public void setMovimientoajustes(Set movimientoajustes) {
		this.movimientoajustes = movimientoajustes;
	}

	

	public Set getProduccions() {
		return this.produccions;
	}

	public void setProduccions(Set produccions) {
		this.produccions = produccions;
	}

	public Set getProducciondiarias() {
		return this.producciondiarias;
	}

	public void setProducciondiarias(Set producciondiarias) {
		this.producciondiarias = producciondiarias;
	}

	public String getGrupoProducto() {
		return grupoProducto;
	}

	public void setGrupoProducto(String grupoProducto) {
		this.grupoProducto = grupoProducto;
	}

	public Tipoconsumo getTipoconsumo() {
		return tipoconsumo;
	}

	public void setTipoconsumo(Tipoconsumo tipoconsumo) {
		this.tipoconsumo = tipoconsumo;
	}

}