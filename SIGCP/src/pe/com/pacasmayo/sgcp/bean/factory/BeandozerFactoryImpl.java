package pe.com.pacasmayo.sgcp.bean.factory;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class BeandozerFactoryImpl implements BeandozerFactory {

	private static BeandozerFactory singletton;
	public static List<String> listaMapperDozer;
	public static Mapper mapper;

	public static BeandozerFactory getInstance() {

		if (singletton == null) {
			singletton = new BeandozerFactoryImpl();
		}

		listaMapperDozer = new ArrayList<String>();

		

		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/clasificaciontipomovimiento.xml");
		listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/division.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/documentomaterialenvasemensual.xml");
		// listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/factorconversion.xml");
		listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/lineanegocio.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/medioalmacenamiento.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/movimientoenvasemensual.xml");
	//	listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/periodocontable.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/persona.xml");
		listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/proceso.xml");
		// listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/produccionmensual.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/producto.xml");
		listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/puestotrabajo.xml");
		listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/sociedad.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/tipodocumentomaterial.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/tipomovimiento.xml");

		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/turno.xml");
		listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/unidad.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/unidadmedida.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/usuario.xml");

		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/componente.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/consumocomponenteenvasado.xml");

		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/factorconversion.xml");

		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/factorproduccionenvasado.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/factorpuestotrabajoenvasado.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/estadofactorproduccionenvasado.xml");
		
		listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/notificaciondiaria.xml");
		listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/estadonotificacion.xml");
		//listaMapperDozer.add("pe/com/pacasmayo/sgcp/bean/factory/beandozer/estadoarea.xml");
		
		
		mapper = new DozerBeanMapper(listaMapperDozer);

		return singletton;
	}

	public Object transformarBean(Object o, Class<?> clase) {
		Object d = null;
		try {
			if (o != null) {
				d = mapper.map(o, clase);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	@Override
	public List<?> tranformarLista(List<?> lista, Class<?> claseDestino) {
		List resultado = new ArrayList<>();

		if (lista != null) {
			for (Object o : lista) {
				Object r = transformarBean(o, claseDestino);
				resultado.add(r);
			}
		}
		return resultado;
	}

}
