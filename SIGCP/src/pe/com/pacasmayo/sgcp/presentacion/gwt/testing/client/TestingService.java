package pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../servicioTesting")
public interface TestingService extends RemoteService {
	public void grabarArchivo(String json, String nombre);

	public void assertFileEqualsExpected(String json, String nombre) throws Exception;
}
