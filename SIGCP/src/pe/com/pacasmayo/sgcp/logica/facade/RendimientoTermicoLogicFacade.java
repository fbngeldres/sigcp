package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.RendimientoTermicoBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface RendimientoTermicoLogicFacade {

	public abstract List<RendimientoTermicoBean> obtenerRendimientosTermicos() throws LogicaException;

	public abstract RendimientoTermicoBean obtenerRendimientoTermico(Long pkCodigoRendimientoTermico) throws LogicaException;

	public abstract void insertarRendimientoTermico(RendimientoTermicoBean rendimientoTermicoBean) throws LogicaException;

	public abstract void modificarRendimientoTermico(RendimientoTermicoBean rendimientoTermicoBean) throws LogicaException;

	public abstract void eliminarRendimientoTermico(RendimientoTermicoBean rendimientoTermicoBean) throws LogicaException;

	public abstract List<Long> obtenerCodigosProductosRendimientosTermicos() throws LogicaException;

	public abstract List<Long> obtenerCodigosPuestoTrabajoRendimientosTermicos() throws LogicaException;
}