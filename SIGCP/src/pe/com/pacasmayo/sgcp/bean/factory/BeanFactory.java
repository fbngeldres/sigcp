package pe.com.pacasmayo.sgcp.bean.factory;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: BeanFactory.java
 * Modificado: Mar 17, 2010 10:13:34 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.*;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.*;

public interface BeanFactory {

	/**
	 * @param actividades
	 * @return
	 */
	public abstract List<ActividadBean> transformarListaActividad(
			List<Actividad> actividades);

	public abstract List<ActividadBean> transformarListaActividadBasico(
			List<Actividad> actividades);

	/**
	 * @param actividad
	 * @return
	 */
	public abstract ActividadBean transformarActividad(Actividad actividad);

	/**
	 * @param actividad
	 * @return
	 */
	public abstract ActividadBean transformarActividadBasico(Actividad actividad);

	/**
	 * @param almacen
	 * @return
	 */
	public abstract AlmacenBean transformarAlmacen(Almacen almacen);

	/**
	 * @param almacenes
	 * @return
	 */
	public abstract List<AlmacenBean> transformarListaAlmacen(
			List<Almacen> almacenes);

	/**
	 * @param clasificacionTipoMovimiento
	 * @return
	 */
	public abstract ClasificacionTipoMovimientoBean transformarClasificacionTipoMovimiento(
			Clasificaciontipomovimiento clasificacionTipoMovimiento);

	/**
	 * @param listaClasificacionTipoMovimiento
	 * @return
	 */
	public abstract List<ClasificacionTipoMovimientoBean> transformarListaClasificacionTipoMovimiento(
			List<Clasificaciontipomovimiento> listaClasificacionTipoMovimiento);

	/**
	 * @param componente
	 * @return
	 */
	public abstract ComponenteBean transformarComponente(Componente componente);

	/**
	 * Método para tranformar un componente sin tomar en cuenta el factor de
	 * dosificacion
	 * 
	 * @param componente
	 *            Componente a transformar
	 * @return Componente Bean
	 */
	public abstract ComponenteBean transformarComponenteSinFactorDosificacion(
			Componente componente);

	/**
	 * @param componenteLista
	 * @return
	 */
	public abstract List<ComponenteBean> transformarListaComponente(
			List<Componente> componenteLista);

	/**
	 * @param division
	 * @return
	 */
	public abstract DivisionBean transformarDivision(Division division);




	/**
	 * @param divisionBeans
	 * @return
	 */
	public abstract List<DivisionBean> transformarListaDivision(
			List<Division> divisionBeans);

	/**
	 * @param listaEstadoActividad
	 * @return
	 */
	public abstract List<EstadoActividadBean> transformarListaEstadoActividad(
			List<Estadoactividad> listaEstadoActividad);

	public abstract List<EstadoPrivilegioBean> transformarListaEstadoPrivilegio(
			List<Estadoprivilegio> listaEstadoPrivilegio);

	/**
	 * @param estadoActividad
	 * @return
	 */
	public abstract EstadoActividadBean transformarEstadoActividad(
			Estadoactividad estadoActividad);

	/**
	 * @param estadoNotificacion
	 * @return
	 */
	public abstract EstadoNotificacionBean transformarEstadoNotificacion(
			Estadonotificacion estadoNotificacion);

	/**
	 * @param listaEstadoNotificacion
	 * @return
	 */
	public abstract List<EstadoNotificacionBean> transformarListaEstadoNotificacion(
			List<Estadonotificacion> listaEstadoNotificacion);

	/**
	 * @param estadoHohaRuta
	 * @return
	 */
	public abstract EstadoHojaRutaBean transformarEstadoHojaRuta(
			Estadohojaruta estadoHohaRuta);

	/**
	 * @param listaEstadosHojaRuta
	 * @return
	 */
	public abstract List<EstadoHojaRutaBean> transformarListaEstadoHojaRuta(
			List<Estadohojaruta> listaEstadosHojaRuta);

	/**
	 * @param estadoProductoBeans
	 * @return
	 */
	public abstract List<EstadoProductoBean> transformarListaEstadoProducto(
			List<Estadoproducto> estadoProductoBeans);

	/**
	 * @param estadoProducto
	 * @return
	 */
	public abstract EstadoProductoBean transformarEstadoProducto(
			Estadoproducto estadoProducto);

	/**
	 * @param estadoPuestoTrabajos
	 * @return
	 */
	public abstract List<EstadoPuestoTrabajoBean> transformarListaEstadoPuestoTrabajo(
			List<Estadopuestotrabajo> estadoPuestoTrabajos);

	/**
	 * @param estadoPuestoTrabajo
	 * @return
	 */
	public abstract EstadoPuestoTrabajoBean transformarEstadoPuestoTrabajo(
			Estadopuestotrabajo estadoPuestoTrabajo);

	/**
	 * @param estadoUsuario
	 * @return
	 */
	public abstract EstadoUsuarioBean transformarEstadoUsuario(
			Estadousuario estadoUsuario);

	/**
	 * @param estadoPrivilegio
	 * @return
	 */
	public abstract EstadoPrivilegioBean transformarEstadoPrivilegio(
			Estadoprivilegio estadoPrivilegio);

	/**
	 * @param grupousuario
	 * @param agregarGrupoPrivilegios
	 * @return
	 */
	public abstract GrupoUsuarioBean transformarGrupoUsuario(
			Grupousuario grupousuario, boolean agregarGrupoPrivilegios);

	/**
	 * @param usuariogrupousuario
	 * @return
	 */
	public abstract UsuarioGrupoUsuarioBean transformarUsuarioGrupoUsuario(
			Usuariogrupousuario usuariogrupousuario);

	/**
	 * @param accion
	 * @return
	 */
	public abstract AccionBean transformarAccion(Accion accion);

	/**
	 * @param opcion
	 * @return
	 */
	public abstract OpcionBean transformarOpcion(Opcion opcion);

	public abstract List<OpcionBean> transformarListaOpcionParaCombo(
			List<Opcion> opcionList);

	public abstract OpcionAccionBean transformarOpcionAccion(
			Opcionaccion opcionaccion);

	public abstract List<OpcionAccionBean> transformarListaOpcionAccionParaCombo(
			List<Opcionaccion> opcionaccionList);

	/**
	 * @param nivelCargo
	 * @return
	 */
	public abstract NivelCargoBean transformarNivelCargo(Nivelcargo nivelCargo);

	public abstract List<NivelCargoBean> transformarListaNivelCargo(
			List<Nivelcargo> listaNivelCargo);

	/**
	 * @param privilegio
	 * @return
	 */
	public abstract PrivilegioBean transformarPrivilegio(Privilegio privilegio);

	public abstract List<PrivilegioBean> transformarListaPrivilegios(
			List<Privilegio> listaPrivilegio);

	public abstract NivelMenuBean transformarNivelMenu(Nivelmenu nivelmenu);

	public abstract List<NivelMenuBean> transformarListaNivelMenu(
			List<Nivelmenu> nivelesMenu);

	/**
	 * @param grupoUsuarioPrivilegioList
	 * @return
	 */
	public abstract List<GrupoUsuarioPrivilegioBean> transformarListaGrupoUsuarioPrivilegio(
			Set<Grupousuarioprivilegio> grupoUsuarioPrivilegioList);

	/**
	 * @param grupoUsuarioPrivilegioList
	 * @return
	 */
	public abstract List<GrupoUsuarioPrivilegioBean> transformarListaGrupoUsuarioPrivilegio(
			List<Grupousuarioprivilegio> grupoUsuarioPrivilegioList);

	/**
	 * @param menu
	 * @param nivel
	 * @return
	 */
	public abstract MenuBean transformarMenu(Menu menu, Short nivel);

	public abstract List<MenuBean> transformarListaMenu(List<Menu> menuList,
			Short nivel);

	public abstract MenuBean transformarMenuMantenimiento(Menu menu);

	/**
	 * @param unidadCargo
	 * @return
	 */
	public abstract UnidadCargoBean transformarUnidadCargo(
			Unidadcargo unidadCargo);

	/**
	 * @param unidadCargoList
	 * @return
	 */
	public abstract List<UnidadBean> transformarListaUnidadCargo(
			Set<Unidadcargo> unidadCargoList);

	/**
	 * @param divisionCargo
	 * @return
	 */
	public abstract DivisionCargoBean transformarDivisionCargo(
			Divisioncargo divisionCargo);

	/**
	 * @param divisionCargoList
	 * @return
	 */
	public abstract List<DivisionBean> transformarListaDivisionCargo(
			Set<Divisioncargo> divisionCargoList);

	/**
	 * @param sociedadCargo
	 * @return
	 */
	public abstract SociedadCargoBean transformarSociedadCargo(
			Sociedadcargo sociedadCargo);

	/**
	 * @param sociedadCargoList
	 * @return
	 */
	public abstract List<SociedadBean> transformarListaSociedadCargo(
			Set<Sociedadcargo> sociedadCargoList);

	/**
	 * @param cargo
	 * @return
	 */
	public abstract CargoBean transformarCargo(Cargo cargo);

	/**
	 * @param grupousuarioprivilegio
	 * @return
	 */
	public abstract GrupoUsuarioPrivilegioBean transformarGrupoUsuarioPrivilegio(
			Grupousuarioprivilegio grupousuarioprivilegio);

	/**
	 * @param factorDosificacion
	 * @return
	 */
	public abstract FactorDosificacionBean transformarFactordosificacion(
			Factordosificacion factorDosificacion);

	/**
	 * @param listaFactorDosificacion
	 * @return
	 */
	public abstract List<FactorDosificacionBean> transformarListaFactordosificacionParaConsulta(
			List<Factordosificacion> listaFactorDosificacion);

	/**
	 * @param factorDosificacionBeans
	 * @return
	 */
	public abstract List<FactorDosificacionBean> transformarListaFactordosificacion(
			List<Factordosificacion> factorDosificacionBeans);

	/**
	 * @param factorDosificacionBeans
	 * @return
	 */
	public abstract List<FactorDosificacionBean> transformarListaFactordosificacion(
			Set<Factordosificacion> factorDosificacionBeans);

	/**
	 * @param factorDosificacionRegistroMensual
	 * @return
	 */
	public abstract FactorDosificacionRegistroMensualBean transformarFactordosificacionregistromensu(
			Factordosificacionregistromensu factorDosificacionRegistroMensual);

	/**
	 * @param factorDosificacionRegistroMensualBeans
	 * @return
	 */
	public abstract List<FactorDosificacionRegistroMensualBean> transformarListaFactordosificacionregistromensu(
			List<Factordosificacionregistromensu> factorDosificacionRegistroMensualBeans);

	/**
	 * @param factorDosificacionRegistroMensualBeans
	 * @return
	 */
	public abstract FactorDosificacionRegistroMensualBean[] transformarListaFactorDosificacionRegistroMensual(
			Set<Factordosificacionregistromensu> factorDosificacionRegistroMensualBeans);

	/**
	 * @param hojaRuta
	 * @return
	 */
	public abstract HojaRutaBean transformarHojaRuta(Hojaruta hojaRuta);

	public abstract HojaRutaBean transformarHojaRutaParaConsultaOrdenProduccion(
			Hojaruta hojaRuta);

	/**
	 * @param listaHojaRuta
	 * @return
	 */
	public abstract List<HojaRutaBean> transformarListaHojaRuta(
			List<Hojaruta> listaHojaRuta);

	public abstract List<HojaRutaBean> transformarListaHojaRutaParaCombo(
			List<Hojaruta> listaHojaRuta);

	/**
	 * @param listaHojaruta
	 * @return
	 */
	public abstract List<HojaRutaBean> transformarListaHojaRutaConFactoresDosificacion(
			List<Hojaruta> listaHojaruta);

	/**
	 * @param hojaRutaComponente
	 * @return
	 */
	public abstract HojaRutaComponenteBean transformarHojaRutaComponente(
			Hojarutacomponente hojaRutaComponente);

	/**
	 * @param listaHojaRutaComponente
	 * @return
	 */
	public abstract List<HojaRutaComponenteBean> transformarListaHojaRutaComponente(
			List<Hojarutacomponente> listaHojaRutaComponente);

	/**
	 * @param listaHojaRutaComponente
	 * @return
	 */
	public abstract List<HojaRutaComponenteBean> transformarListaHojaRutaComponente(
			Set<Hojarutacomponente> listaHojaRutaComponente);

	/**
	 * @param lineaNegocio
	 * @return
	 */
	public abstract LineaNegocioBean transformarLineaNegocio(
			Lineanegocio lineaNegocio);

	/**
	 * @param listaLineaNegocio
	 * @return
	 */
	public abstract List<LineaNegocioBean> transformarListaLineaNegocio(
			List<Lineanegocio> listaLineaNegocio);

	/**
	 * @param lineaNegocio
	 * @return
	 */
	public abstract LineaNegocioBean transformarLineaNegocioBasico(
			Lineanegocio lineaNegocio);

	/**
	 * @param listaLineaNegocio
	 * @return
	 */
	public abstract List<LineaNegocioBean> transformarListaLineaNegocioBasico(
			List<Lineanegocio> listaLineaNegocio);

	/**
	 * @param operacion
	 * @return
	 */
	public abstract OperacionBean transformarOperacion(Operacion operacion);

	public abstract OperacionBean transformarOperacionParaPlanAnual(
			Operacion operacion);

	/**
	 * @param listaOperacion
	 * @return
	 */
	public abstract List<OperacionBean> transformarListaOperacion(
			List<Operacion> listaOperacion);

	public abstract List<OperacionBean> transformarListaOperacionParaPlanAnual(
			List<Operacion> listaOperacion);

	public abstract OperacionComponenteBean transformarOperacionComponente(
			Operacioncomponente operacionComponente);

	/**
	 * @param listaOperacionComponente
	 * @return
	 */
	public abstract List<OperacionComponenteBean> transformarListaOperacionComponente(
			Set<Operacioncomponente> listaOperacionComponente);

	/**
	 * @param operacionRecurso
	 * @return
	 */
	public abstract OperacionRecursoBean transformarOperacionRecurso(
			Operacionrecurso operacionRecurso);

	/**
	 * @param listaOperacionComponente
	 * @return
	 */
	public abstract List<OperacionRecursoBean> transformarListaOperacionRecurso(
			Set<Operacionrecurso> listaOperacionComponente);

	/**
	 * @param proceso
	 * @return
	 */
	public abstract ProcesoBean transformarProceso(Proceso proceso);

	/**
	 * @param procesos
	 * @return
	 */
	public abstract List<ProcesoBean> transformarListaProceso(
			List<Proceso> procesos);

	/**
	 * @param procesos
	 * @return
	 */
	public abstract List<ProcesoBean> transformarListaProcesoBasico(
			List<Proceso> procesos);

	/**
	 * @param produccion
	 * @return
	 */
	public abstract ProduccionBean transformarProduccion(Produccion produccion);

	/**
	 * @param produccionsBeans
	 * @return
	 */
	public abstract List<ProduccionBean> transformarListaProduccion(
			List<Produccion> produccionsBeans);

	/**
	 * @param producto
	 * @return
	 */
	public abstract ProductoBean transformarProducto(Producto producto);

	/**
	 * @param producto
	 * @return
	 */
	public abstract ProductoBean transformarProductoBasico(Producto producto);

	/**
	 * @param productosBeans
	 * @return
	 */
	public abstract List<ProductoBean> transformarListaProductos(
			List<Producto> productosBeans);

	/**
	 * @param produccionesBeans
	 * @return
	 */
	public abstract Set<ProduccionBean> transformarListaProducciones(
			Set<Produccion> produccionesBeans);

	/**
	 * @param puestosTrabajo
	 * @return
	 */
	public abstract List<PuestoTrabajoBean> transformarListaPuestoTrabajo(
			List<Puestotrabajo> puestosTrabajo);

	/**
	 * @param puestoTrabajo
	 * @return
	 */
	public abstract PuestoTrabajoBean transformarPuestoTrabajo(
			Puestotrabajo puestoTrabajo);

	/**
	 * @param recurso
	 * @return
	 */
	public abstract RecursoBean transformarRecurso(Recurso recurso);

	/**
	 * @param recursoLista
	 * @return
	 */
	public abstract List<RecursoBean> transformarListaRecurso(
			List<Recurso> recursoLista);

	/**
	 * @param listaRecursosRM
	 * @return
	 */
	public RecursoRegistroMensualBean[] transformarListaRecursosRM(
			Set<Recursoregistromensual> listaRecursosRM);

	/**
	 * @param sociedad
	 * @return
	 */
	public abstract SociedadBean transformarSociedad(Sociedad sociedad);

	/**
	 * @param sociedadBeans
	 * @return
	 */
	public abstract List<SociedadBean> transformarListaSociedad(
			List<Sociedad> sociedadBeans);

	/**
	 * @param tipoComponente
	 * @return
	 */
	public abstract TipoComponenteBean transformarTipoComponente(
			Tipocomponente tipoComponente);

	/**
	 * @param listaTipoComponentes
	 * @return
	 */
	public abstract List<TipoComponenteBean> transformarListaTipoComponentes(
			List<Tipocomponente> listaTipoComponentes);

	/**
	 * @param tipoMedioAlmacenamiento
	 * @return
	 */
	public abstract TipoMedioAlmacenamientoBean transformarTipoMedioAlmacenamiento(
			Tipomedioalmacenamiento tipoMedioAlmacenamiento);

	public abstract List<TipoMedioAlmacenamientoBean> transformarListaTipoMedioAlmacenamiento(
			List<Tipomedioalmacenamiento> tipoMedioAlmacenamientoBeans);

	/**
	 * @param tipoMovimiento
	 * @return
	 */
	public abstract TipoMovimientoBean transformarTipoMovimiento(
			Tipomovimiento tipoMovimiento);

	/**
	 * @param listaTipoMovimientoBeans
	 * @return
	 */
	public abstract List<TipoMovimientoBean> transformarListaTipoMovimiento(
			List<Tipomovimiento> listaTipoMovimientoBeans);

	/**
	 * @param tipoProductoBeans
	 * @return
	 */
	public abstract List<TipoProductoBean> transformarListaTipoProducto(
			List<Tipoproducto> tipoProductoBeans);

	/**
	 * @param tipoProducto
	 * @return
	 */
	public abstract TipoProductoBean transformarTipoProducto(
			Tipoproducto tipoProducto);

	/**
	 * @param tipoPuestoTrabajo
	 * @return
	 */
	public abstract TipoPuestoTrabajoBean transformarTipoPuestoTrabajo(
			Tipopuestotrabajo tipoPuestoTrabajo);

	/**
	 * @param puestosTrabajo
	 * @return
	 */
	public abstract List<TipoPuestoTrabajoBean> transformarListaTipoPuestoTrabajo(
			List<Tipopuestotrabajo> puestosTrabajo);

	/**
	 * @param puestosTrabajo
	 * @return
	 */
	public abstract List<UbicacionBean> transformarListaUbicaciones(
			List<Ubicacion> puestosTrabajo);

	/**
	 * @param ubicacion
	 * @return
	 */
	public abstract UbicacionBean transformarUbicacion(Ubicacion ubicacion);

	/**
	 * @param unidad
	 * @return
	 */
	public abstract UnidadBean transformarUnidad(Unidad unidad);

	/**
	 * @param unidad
	 * @return
	 */
	public abstract UnidadBean transformarUnidadBasico(Unidad unidad);

	/**
	 * @param unidadBeans
	 * @return
	 */
	public abstract List<UnidadBean> transformarListaUnidad(
			List<Unidad> unidadBeans);

	/**
	 * @param unidadBeans
	 * @return
	 */
	public abstract List<UnidadBean> transformarListaUnidadBasico(
			List<Unidad> unidadBeans);

	/**
	 * @param unidadMedidaBeans
	 * @return
	 */
	public abstract List<UnidadMedidaBean> transformarListaUnidadMedida(
			List<Unidadmedida> unidadMedidaBeans);

	/**
	 * @param unidadMedida
	 * @return
	 */
	public abstract UnidadMedidaBean transformarUnidadMedida(
			Unidadmedida unidadMedida);

	/**
	 * @param persona
	 * @return
	 */
	public abstract PersonaBean transformarPersona(Persona persona);

	/**
	 * @param personas
	 * @return
	 */
	public abstract List<PersonaBean> transformarListaPersona(
			List<Persona> personas);

	/**
	 * @param usuarios
	 * @return
	 */
	public abstract List<UsuarioBean> transformarListaUsuario(
			List<Usuario> usuarios);

	/**
	 * @param usuario
	 * @return
	 */
	public abstract UsuarioBean transformarUsuario(Usuario usuario);

	/**
	 * Toma los datos de un objeto de persistencia Ordenproduccion y crea el
	 * objeto bean de la orden de produccion
	 * 
	 * @param ordenproduccion
	 *            objeto de persistencia
	 * @return OrdenProduccionBeanImpl
	 */
	public abstract OrdenProduccionBean transformarOrdenProduccion(
			Ordenproduccion ordenproduccion);

	/**
	 * @param ordenproduccion
	 * @return
	 */
	public abstract OrdenProduccionBean transformarOrdenProduccionBasico(
			Ordenproduccion ordenproduccion);

	/**
	 * Toma los datos de un objeto bean de orden de produccion y crea el objeto
	 * de persistencia Ordenproduccion
	 * 
	 * @param ordenproduccionBean
	 *            OrdenProduccionBeanImpl
	 * @return Ordenproduccion objeto de persistencia
	 */
	public abstract Ordenproduccion transformarOrdenProduccionBean(
			OrdenProduccionBean ordenProduccionBean);

	/**
	 * Toma una lista de objetos de persistencia Ordenproduccion y retorna un
	 * ArrayList de objetos q implenentan la interfaz OrdenProduccionBean
	 * 
	 * @param listOrdenProduccion
	 * @return
	 */
	public abstract List<OrdenProduccionBean> transformarListaOrdenProduccion(
			List<Ordenproduccion> listOrdenProduccion);

	/**
	 * @param listOrdenProduccion
	 * @return
	 */
	public abstract List<OrdenProduccionBean> transformarListaOrdenProduccionBasico(
			List<Ordenproduccion> listOrdenProduccion);

	/**
	 * Método para transformar el objeto Plananual en objeto de negocio
	 * PlanAnualBean
	 * 
	 * @param plananual
	 * @return Data Object tranformado
	 */
	public abstract PlanAnualBean transformarPlanAnual(Plananual plananual);

	/**
	 * Método para transformar el objeto Plananual en objeto de negocio
	 * PlanAnualBean, asi como las tablas asociadas al plan anual: - Plan de
	 * Comercializacion
	 * 
	 * @param plananual
	 * @return Data Object tranformado
	 */

	public abstract List<PlanComercializacionBean> transformarListaPlanComercializacion(
			Set<Plancomercializacion> plancomercializacions);

	/**
	 * @param planAnualBeans
	 * @return
	 */
	public abstract List<PlanAnualBean> transformarListaPlanAnual(
			List<Plananual> planAnualBeans);

	/**
	 * @param estadoPlanList
	 * @return
	 */
	public abstract List<EstadoPlanBean> transformarListaEstadoPlanAnual(
			List<Estadoplananual> estadoPlanList);

	/**
	 * @param estadoplananual
	 * @return
	 */
	public abstract EstadoPlanBean transformarEstadoPlan(
			Estadoplananual estadoplananual);

	/**
	 * @param plananual
	 * @return
	 */
	public abstract PlanAnualBean transformarPlanAnualCompleto(
			Plananual plananual);

	/**
	 * @param concepto
	 * @return
	 */
	public abstract ConceptoBean transformarConcepto(Concepto concepto);

	/**
	 * @param concepto
	 * @return
	 */
	public abstract List<ConceptoBean> transformarListaConcepto(
			List<Concepto> concepto);

	/**
	 * @param medioalmacenamiento
	 * @return
	 */
	public abstract MedioAlmacenamientoBean transformarMedioAlmacenamiento(
			Medioalmacenamiento medioalmacenamiento);

	/**
	 * @param mediosAlmacenamiento
	 * @return
	 */
	public abstract List<MedioAlmacenamientoBean> transformarListaMedioAlmacenamiento(
			List<Medioalmacenamiento> mediosAlmacenamiento);

	/**
	 * @param dosificacionRegistroMensual
	 * @return
	 */
	public abstract DosificacionRegistroMensualBean transformarDosificacionRegistroMensual(
			Dosificacionregistromensual dosificacionRegistroMensual);

	/**
	 * @param dosificacionRegistroMensuals
	 * @return
	 */
	public abstract DosificacionRegistroMensualBean[] transformarListaDosificacionRegistroMensual(
			Collection<Dosificacionregistromensual> dosificacionRegistroMensuals);

	/**
	 * @param listaTemporalPlan
	 * @return
	 */
	public abstract List<PlanNecesidadBean> transformarListaPlanNecesidad(
			List<Plannecesidad> listaTemporalPlan);

	/**
	 * @param plannecesidad
	 * @return
	 */
	public abstract PlanNecesidadBean transformarPlanNecesidad(
			Plannecesidad plannecesidad);

	/**
	 * @param listaConceptoRegistroMensual
	 * @return
	 */
	public abstract ConceptoRegistroMensualBean[] transformarListaConceptoRegistroMensual(
			Collection<Conceptoregistromensual> listaConceptoRegistroMensual);

	/**
	 * @param conceptoRegistroMensual
	 * @return
	 */
	public abstract ConceptoRegistroMensualBean transformarConceptoRegistroMensual(
			Conceptoregistromensual conceptoRegistroMensual);

	/**
	 * @param conceptoMensual
	 * @return
	 */
	public abstract ConceptoMensualBean transformarConceptoMensual(
			Conceptomensual conceptoMensual);

	/**
	 * @param tasarealproduccionregmen
	 * @return
	 */
	public abstract TasaRealProduccionRegistroMensualBean[] transformarTasaRealProduccionRegMen(
			Set<Tasarealprodregistromensual> tasarealproduccionregmen);

	/**
	 * @param tasarealprod
	 * @return
	 */
	public abstract TasaRealProduccionBean transformarTasaRealProduccion(
			Tasarealproduccion tasarealprod);

	/**
	 * @param tasarealprodregmen
	 * @return
	 */
	public abstract TasaRealProduccionRegistroMensualBean transformarTasaRealProduccionRegistroMensual(
			Tasarealprodregistromensual tasarealprodregmen);

	/**
	 * @param listaTasaRealProduccion
	 * @return
	 */
	public abstract List<TasaRealProduccionBean> transformarListaTasaRealProduccion(
			List<Tasarealproduccion> listaTasaRealProduccion);

	/**
	 * @param capacidadoperativaregistromensus
	 * @return
	 */
	public abstract CapacidadOperativaRegistroMensualBean[] transformarListaCapacidadOperativaRM(
			Set<Capacidadoperativaregistromensu> capacidadoperativaregistromensus);

	/**
	 * @param listaCapOperRegMen
	 * @return
	 */
	public abstract List<CapacidadOperativaRegistroMensualBean> transformarListaCapacidadOperativaRegistroMensual(
			Set<Capacidadoperativaregistromensu> listaCapOperRegMen);

	/**
	 * @param movimientos
	 * @return
	 */
	public abstract List<MovimientoBean> transformarListaMovimiento(
			List<Movimiento> movimientos);

	/**
	 * @param capacidadOperativa
	 * @return
	 */
	public abstract CapacidadOperativaBean transformarCapacidadOperativa(
			Capacidadoperativa capacidadOperativa);

	/**
	 * @param tipoCapacidadOperativa
	 * @return
	 */
	public abstract TipoCapacidadOperativaBean transformarTipoCapacidadOperativa(
			Tipocapacidadoperativa tipoCapacidadOperativa);

	/**
	 * @param movimiento
	 * @return
	 */
	public abstract MovimientoBean transformarMovimiento(Movimiento movimiento);

	/**
	 * @param estadoMovimientos
	 * @return
	 */
	public abstract List<EstadoMovimientoBean> transformarListaEstadoMovimiento(
			List<Estadomovimiento> estadoMovimientos);

	/**
	 * @param estadomovimiento
	 * @return
	 */
	public abstract EstadoMovimientoBean transformarEstadoMovimiento(
			Estadomovimiento estadomovimiento);

	/**
	 * @param documentomateriales
	 * @return
	 */
	public abstract List<DocumentoMaterialBean> transformarListaDocumentoMaterial(
			List<Documentomaterial> documentomateriales);

	/**
	 * @param documentomaterial
	 * @return
	 */
	public abstract DocumentoMaterialBean transformarDocumentoMaterial(
			Documentomaterial documentomaterial);

	/**
	 * @param tipoDocuentomateriales
	 * @return
	 */
	public abstract List<TipoDocumentoMaterialBean> transformarListaTipoDocumentoMaterial(
			List<Tipodocumentomaterial> tipoDocuentomateriales);

	/**
	 * @param tipodocumentomaterial
	 * @return
	 */
	public abstract TipoDocumentoMaterialBean transformarTipoDocumentoMaterial(
			Tipodocumentomaterial tipodocumentomaterial);

	/**
	 * @param periodosContables
	 * @return
	 */
	public abstract List<PeriodoContableBean> transformarListaPeriodoContable(
			List<Periodocontable> periodosContables);

	/**
	 * @param periodoContable
	 * @return
	 */
	public abstract PeriodoContableBean transformarPeriodoContable(
			Periodocontable periodoContable);

	/**
	 * @param movimientoAjustes
	 * @return
	 */
	public abstract List<MovimientoAjusteBean> transformarListaMovimientoAjuste(
			List<Movimientoajuste> movimientoAjustes);

	/**
	 * @param movimientoajuste
	 * @return
	 */
	public abstract MovimientoAjusteBean transformarMovimientoAjuste(
			Movimientoajuste movimientoajuste);

	/**
	 * @param consumoComponentePuestoTrabajos
	 * @return
	 */
	public abstract List<ConsumoComponentePuestoTrabajoBean> transformarListaConsumoComponentePuestoTrabajo(
			List<Consumocomponentepuestotrabajo> consumoComponentePuestoTrabajos);

	/**
	 * @param consumoComponentePuestoTrabajo
	 * @return
	 */
	public abstract ConsumoComponentePuestoTrabajoBean transformarConsumoComponentePuestoTrabajo(
			Consumocomponentepuestotrabajo consumoComponentePuestoTrabajo);

	/**
	 * @param listaDataObjects
	 * @return
	 */
	public abstract List<ConsumoComponenteAjusteBean> transformarListaConsumoComponenteAjuste(
			List<Consumocomponenteajuste> listaDataObjects);

	/**
	 * @param dataObject
	 * @return
	 */
	public abstract ConsumoComponenteAjusteBean transformarConsumoComponenteAjuste(
			Consumocomponenteajuste dataObject);

	/**
	 * @param listaDataObjects
	 * @return
	 */
	public abstract List<PuestoTrabajoProduccionBean> transformarListaPuestoTrabajoProduccion(
			List<Puestotrabajoproduccion> listaDataObjects);

	/**
	 * @param dataObject
	 * @return
	 */
	public abstract PuestoTrabajoProduccionBean transformarPuestoTrabajoProduccion(
			Puestotrabajoproduccion dataObject);

	/**
	 * @param ajusteProductos
	 * @return
	 */
	public abstract List<AjusteProductoBean> transformarListaAjusteProducto(
			List<Ajusteproducto> ajusteProductos);

	/**
	 * @param ajusteProducto
	 * @return
	 */
	public abstract AjusteProductoBean transformarAjusteProducto(
			Ajusteproducto ajusteProducto);

	/**
	 * @param estadoAjusteProductos
	 * @return
	 */
	public abstract List<EstadoAjusteProductoBean> transformarListaEstadoAjusteProducto(
			List<Estadoajusteproducto> estadoAjusteProductos);

	/**
	 * @param estadoAjusteProducto
	 * @return
	 */
	public abstract EstadoAjusteProductoBean transformarEstadoAjusteProducto(
			Estadoajusteproducto estadoAjusteProducto);

	/**
	 * @param balanceProductos
	 * @return
	 */
	public abstract List<BalanceProductoBean> transformarListaBalanceProducto(
			List<Balanceproducto> balanceProductos);

	/**
	 * @param balanceProducto
	 * @return
	 */
	public abstract BalanceProductoBean transformarBalanceProducto(
			Balanceproducto balanceProducto);

	/**
	 * @param tipoBalances
	 * @return
	 */
	public abstract List<TipoBalanceBean> transformarListaTipoBalance(
			List<Tipobalance> tipoBalances);

	/**
	 * @param tipoBalance
	 * @return
	 */
	public abstract TipoBalanceBean transformarTipoBalance(
			Tipobalance tipoBalance);

	/**
	 * @param plantillaGrupoAjustes
	 * @return
	 */
	public abstract List<PlantillaGrupoAjusteBean> transformarListaPlantillaGrupoAjuste(
			List<Plantillagrupoajuste> plantillaGrupoAjustes);

	/**
	 * @param plantillaGrupoAjuste
	 * @return
	 */
	public abstract PlantillaGrupoAjusteBean transformarPlantillaGrupoAjuste(
			Plantillagrupoajuste plantillaGrupoAjuste);

	/**
	 * @param plantillaAjusteProductos
	 * @return
	 */
	public abstract List<PlantillaAjusteProductoBean> transformarListaPlantillaAjusteProducto(
			List<Plantillaajusteproducto> plantillaAjusteProductos);

	/**
	 * @param plantillaAjusteProducto
	 * @return
	 */
	public abstract PlantillaAjusteProductoBean transformarPlantillaAjusteProducto(
			Plantillaajusteproducto plantillaAjusteProducto);

	/**
	 * @param parteDiarios
	 * @return
	 */
	public abstract List<ParteDiarioBean> transformarListaParteDiario(
			List<Partediario> parteDiarios);

	/**
	 * @param parteDiario
	 * @return
	 */
	public abstract ParteDiarioBean transformarParteDiario(
			Partediario parteDiario);

	/**
	 * @param produccionPuestoTrabajos
	 * @return
	 */
	public abstract List<ProduccionPuestoTrabajoBean> transformarListaProduccionPuestoTrabajos(
			ArrayList<Produccionpuestotrabajo> produccionPuestoTrabajos);

	/**
	 * @param produccionPuestoTrabajo
	 * @return
	 */
	public abstract ProduccionPuestoTrabajoBean transformarProduccionPuestoTrabajo(
			Produccionpuestotrabajo produccionPuestoTrabajo);

	/**
	 * @param tablasOperaciones
	 * @return
	 */
	public abstract List<TablaOperacionBean> transformarListaTablaOperaciones(
			ArrayList<Tablaoperacion> tablasOperaciones);

	/**
	 * @param tablaOperacion
	 * @return
	 */
	public abstract TablaOperacionBean transformarTablaOperaciones(
			Tablaoperacion tablaOperacion);

	/**
	 * @param productosGenerados
	 * @return
	 */
	public abstract List<ProductoGeneradoBean> transformarListaProductosGenerados(
			ArrayList<Productogenerado> productosGenerados);

	/**
	 * @param productoGenerado
	 * @return
	 */
	public abstract ProductoGeneradoBean transformarProductoGenerado(
			Productogenerado productoGenerado);

	/**
	 * @param consumoPuestoTrabajos
	 * @return
	 */
	public abstract List<ConsumoPuestoTrabajoBean> transformarListaConsumoPuestoTrabajo(
			ArrayList<Consumopuestotrabajo> consumoPuestoTrabajos);

	/**
	 * @param consumoPuestoTrabajo
	 * @return
	 */
	public abstract ConsumoPuestoTrabajoBean transformarConsumoPuestoTrabajo(
			Consumopuestotrabajo consumoPuestoTrabajo);

	/**
	 * @param produccionesDiarias
	 * @return
	 */
	public abstract List<ProduccionDiariaBean> transformarListaProduccionDiaria(
			ArrayList<Producciondiaria> produccionesDiarias);

	/**
	 * @param produccionDiaria
	 * @return
	 */
	public abstract ProduccionDiariaBean transformarProduccionDiaria(
			Producciondiaria produccionDiaria);

	/**
	 * @param produccionDiaria
	 * @return
	 */
	public abstract ProduccionDiariaBean transformarProduccionDiariaBasico(
			Producciondiaria produccionDiaria);

	/**
	 * @param tablaKardexes
	 * @return
	 */
	public abstract List<TablaKardexBean> transformarListaTablaKardex(
			List<Tablakardex> tablaKardexes);

	/**
	 * @param tablaKardex
	 * @return
	 */
	public abstract TablaKardexBean transformarTablaKardex(
			Tablakardex tablaKardex);

	/**
	 * @param valoresPromVariablesCalidades
	 * @return
	 */
	public abstract List<ValorPromVariableCalidadBean> transformarListaValorPromedioVarCalidad(
			ArrayList<Valorpromvariablecalidad> valoresPromVariablesCalidades);

	/**
	 * @param valorPromVariableCalidad
	 * @return
	 */
	public abstract ValorPromVariableCalidadBean transformarValorPromedioVarCalidad(
			Valorpromvariablecalidad valorPromVariableCalidad);

	/**
	 * @param productosVariableCalidad
	 * @return
	 */
	public abstract List<ProductoVariableCalidadBean> transformarListaProductoVariableCalidad(
			List<Productovariablecalidad> productosVariableCalidad);

	/**
	 * @param productoVariableCalidad
	 * @return
	 */
	public abstract ProductoVariableCalidadBean transformarProductoVariableCalidad(
			Productovariablecalidad productoVariableCalidad);

	/**
	 * @param variablesCalidad
	 * @return
	 */
	public abstract List<VariableCalidadBean> transformarListaVariableCalidad(
			List<Variablecalidad> variablesCalidad);

	/**
	 * @param variableCalidad
	 * @return
	 */
	public abstract VariableCalidadBean transformarVariableCalidad(
			Variablecalidad variableCalidad);

	/**
	 * @param factorKardexes
	 * @return
	 */
	public abstract List<FactorKardexBean> transformarListaFactorKardex(
			ArrayList<Factorvariacionpuestotrabajo> factorKardexes);

	/**
	 * @param factorKardex
	 * @return
	 */
	public abstract FactorKardexBean transformarFactorKardex(
			Factorvariacionpuestotrabajo factorKardex);

	/**
	 * @param consumoComponentes
	 * @return
	 */
	public abstract List<ConsumoComponenteBean> transformarListaConsumoComponente(
			List<Consumocomponente> consumoComponentes);

	/**
	 * @param consumoComponente
	 * @return
	 */
	public abstract ConsumoComponenteBean transformarConsumoComponente(
			Consumocomponente consumoComponente);

	/**
	 * @param estadopartediario
	 * @return
	 */
	public abstract List<EstadoParteDiarioBean> transformarListaEstadoParteDiario(
			List<Estadopartediario> estadopartediario);

	/**
	 * @param estadoParteDiario
	 * @return
	 */
	public abstract EstadoParteDiarioBean transformarEstadoParteDiario(
			Estadopartediario estadoParteDiario);

	/**
	 * @param ajusteProducciones
	 * @return
	 */
	public abstract List<AjusteProduccionBean> transformarListaAjusteProduccion(
			List<Ajusteproduccion> ajusteProducciones);

	/**
	 * @param ajusteProduccion
	 * @return
	 */
	public abstract AjusteProduccionBean transformarAjusteProduccion(
			Ajusteproduccion ajusteProduccion);

	/**
	 * @param estadosAjusteProduccion
	 * @return
	 */
	public abstract List<EstadoAjusteProduccionBean> transformarListaEstadoAjusteProduccion(
			List<Estadoajusteproduccion> estadosAjusteProduccion);

	/**
	 * @param estadoAjusteProduccion
	 * @return
	 */
	public abstract EstadoAjusteProduccionBean transformarEstadoAjusteProduccion(
			Estadoajusteproduccion estadoAjusteProduccion);

	
	/**
	 * @param columnaReporte
	 * @return
	 */
	public abstract ColumnaReporteBean transformarColumnaPlantillaReporte(
			Columnareporte columnaReporte);

	/**
	 * @param columnasReporte
	 * @return
	 */
	public abstract List<ColumnaReporteBean> transformarListaColumnaPlantillaReporte(
			Set<Columnareporte> columnasReporte);

	/**
	 * @param estadoOrdenProduccion
	 * @return
	 */
	public abstract EstadoOrdenProduccionBean transformarEstadoOrdenProduccionBean(
			Estadoordenproduccion estadoOrdenProduccion);

	public abstract List<EstadoOrdenProduccionBean> transformarListaEstadoOrdenProduccionBean(
			List<Estadoordenproduccion> listaEstadoordenProduccion);


	/**
	 * @param plantillasProducto
	 * @return
	 */
	public abstract List<PlantillaProductoBean> transformarListaPlantillaProducto(
			List<Plantillaproducto> plantillasProducto);

	/**
	 * @param estado
	 * @return
	 */
	public abstract EstadoColumnaReporteBean transformarEstadoColumnaReporte(
			Estadocolumnareporte estado);

	/**
	 * @param estados
	 * @return
	 */
	public abstract List<EstadoColumnaReporteBean> transformarListaEstadoColumnaReporte(
			List<Estadocolumnareporte> estados);

	/**
	 * @param notificaciondiaria
	 * @return
	 */
	public abstract NotificacionDiariaBean transformarNotificacionDiaria(
			Notificaciondiaria notificaciondiaria);

	/**
	 * @param notificaciondiaria
	 * @return
	 */
	public abstract NotificacionDiariaBean transformarNotificacionDiariaSimple(
			Notificaciondiaria notificaciondiaria);

	/**
	 * @param notificacionesDiaria
	 * @return
	 */
	public abstract List<NotificacionDiariaBean> transformarListaNotificacionDiaria(
			List<Notificaciondiaria> notificacionesDiaria);

	/**
	 * @param notificacionesDiaria
	 * @return
	 */
	public abstract List<NotificacionDiariaBean> transformarListaNotificacionDiariaSimple(
			List<Notificaciondiaria> notificacionesDiaria);

	/**
	 * @param tablerocontrol
	 * @return
	 */
	public abstract TableroControlBean transformarTableroControl(
			Tablerocontrol tablerocontrol);

	/**
	 * @param notificacionoperacion
	 * @return
	 */
	public abstract NotificacionOperacionBean transformarNotificacionOperacion(
			Notificacionoperacion notificacionoperacion);

	/**
	 * @param notificacionesOperacion
	 * @return
	 */
	public abstract Set<NotificacionOperacionBean> transformarListaNotificacionOperacion(
			Set<Notificacionoperacion> notificacionesOperacion);

	public abstract RegistroReporteEcsBean TransformarRegistroReporteEcs(
			Registroreporteecs registroreporteecs);

	/**
	 * @param notificacionproduccion
	 * @return
	 */
	public abstract NotificacionProduccionBean transformarNotificacionProduccion(
			Notificacionproduccion notificacionproduccion);

	/**
	 * @param notificacionesProduccion
	 * @return
	 */
	public abstract Set<NotificacionProduccionBean> transformarListaNotificacionProduccion(
			Set<Notificacionproduccion> notificacionesProduccion);

	/**
	 * @param hora
	 * @return
	 */
	public abstract HoraBean transformarHora(Hora hora);

	/**
	 * @param horaBeans
	 * @return
	 * @throws AplicacionException
	 */
	public abstract List<HoraBean> transformarListaHora(List<Hora> horaBeans)
			throws AplicacionException;

	/**
	 * @param tablerosControl
	 * @return
	 */
	public abstract List<TableroControlBean> transformarListaTableroControl(
			List<Tablerocontrol> tablerosControl);

	/**
	 * @param notificacionDiariaBean
	 * @return
	 */
	abstract Notificaciondiaria transformarNotificacionDiariaBean(
			NotificacionDiariaBean notificacionDiariaBean);

	/**
	 * @param usuarioBean
	 * @return
	 */
	public abstract Usuario transformarUsuarioBean(UsuarioBean usuarioBean);

	/**
	 * @param personaBean
	 * @return
	 */
	public abstract Persona transformarPersonaBean(PersonaBean personaBean);

	/**
	 * @param tableroControlBean
	 * @return
	 */
	public abstract Tablerocontrol transformarTableroControlBean(
			TableroControlBean tableroControlBean);

	/**
	 * @param lineaNegocioBean
	 * @return
	 */
	public abstract Lineanegocio transformarLineaNegocioBean(
			LineaNegocioBean lineaNegocioBean);

	/**
	 * @param unidadBean
	 * @return
	 */
	public abstract Unidad transformarUnidadBean(UnidadBean unidadBean);

	/**
	 * @param componenteNotificacionHO
	 * @return
	 */
	public ComponenteNotificacionBean transformarComponenteNotificacion(
			Componentenotificacion componenteNotificacionHO);

	/**
	 * @param componentesNotificacionHO
	 * @return
	 */
	public List<ComponenteNotificacionBean> tranformarListaComponenteNotificacion(
			List<Componentenotificacion> componentesNotificacionHO);

	/**
	 * @param listaHojaruta
	 * @return
	 */
	public abstract List<HojaRutaBean> transformarListaHojaRutaParaConsulta(
			List<Hojaruta> listaHojaruta);

	/**
	 * @param lineaNegocios
	 * @return
	 */
	public abstract List<LineaNegocioBean> transformarListaLineaNegocioParaCombos(
			List<Lineanegocio> lineaNegocios);

	/**
	 * @param unidades
	 * @return
	 */
	public abstract List<UnidadBean> transformarListaUnidadParaCombos(
			List<Unidad> unidades);

	/**
	 * @param procesos
	 * @return
	 */
	public abstract List<ProcesoBean> transformarListaProcesoParaCombos(
			List<Proceso> procesos);

	/**
	 * @param productos
	 * @return
	 */
	public abstract List<ProductoBean> transformarListaProductoParaCombos(
			List<Producto> productos);

	/**
	 * @param tipoCategoriaProducto
	 * @return
	 */
	public abstract TipoCategoriaProductoBean transformarTipoCategoriaProducto(
			Tipocategoriaproducto tipoCategoriaProducto);

	/**
	 * @param listaTipoCategoriaProducto
	 * @return
	 */
	public abstract List<TipoCategoriaProductoBean> transformarListaTipoCategoriaProducto(
			List<Tipocategoriaproducto> listaTipoCategoriaProducto);

	public List<OperacionBean> transformarListaOperacionModificarHojaRuta(
			List<Operacion> listaOperacion);

	public OperacionBean transformarOperacionModificarHojaRuta(
			Operacion operacion);

	/**
	 * @param plananual
	 * @return PlanAnualBean
	 */
	public abstract PlanAnualBean transformarPlanAnualParaConsulta(
			Plananual plananual);

	/**
	 * @param estados
	 * @return
	 */
	public List<EstadoUsuarioBean> transformarListaEstados(
			List<Estadousuario> estados);

	/**
	 * @param grupos
	 * @return
	 */
	public List<GrupoUsuarioBean> transformarListaGruposUsuario(
			List<Grupousuario> grupos);

	/**
	 * @param cargos
	 * @return
	 */
	public List<CargoBean> transformarListaCargos(List<Cargo> cargos);

	/**
	 * @param usuarioGrupoLista
	 * @return
	 */
	public List<UsuarioGrupoUsuarioBean> transformarListaUsuarioGruposUsuarios(
			List<Usuariogrupousuario> usuarioGrupoLista);

	
	/**
	 * @param estadoBean
	 * @return
	 */
	public Estadousuario transformarEstadoUsuarioDTO(
			EstadoUsuarioBean estadoBean);

	/**
	 * @param grupoBean
	 * @return
	 */
	public Grupousuario transformarGrupoUsuarioDTO(GrupoUsuarioBean grupoBean);

	/**
	 * @param grupoBean
	 * @param usuario
	 * @return
	 */
	public Usuariogrupousuario transformarUsuarioGrupoUsuarioDTO(
			UsuarioGrupoUsuarioBean grupoBean, Usuario usuario);

	public abstract List<RendimientoTermicoBean> transformarListaRendimientoTermico(
			List<RendimientoTermico> listRendimientoTermico)
			throws LogicaException;

	public abstract RendimientoTermicoBean transformarRendimientoTermico(
			RendimientoTermico rendimientoTermico);

	public abstract ParametroSistemaBean transformarParametroSistema(
			ParametroSistema parametro);

	public abstract OrdenReporteBean transformarOrdenReporte(
			Ordenreporte ordenreporte);

	public abstract List<OrdenReporteBean> transformarListaOrdenResumen(
			List<Ordenreporte> listaOrdenesReporte);

	public abstract List<ParametroSistemaBean> transformarParametrosSistema(
			List<ParametroSistema> parametros);

	public abstract CapacidadBolsaProductoBean transformarCapacidadBolsaProduccion(
			Capacidadbolsaproducto capacidadbolsaproducto);

	public abstract List<TipoConsumoBean> transformarListaTipoConsumo(
			List<Tipoconsumo> listaTipoConsumo);

}
