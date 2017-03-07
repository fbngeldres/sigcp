package pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TestingServiceAsync {
	public void grabarArchivo(String json, String nombre, AsyncCallback<Void> callback);

	public void assertFileEqualsExpected(String json, String nombreArchivo, AsyncCallback<Void> callback) throws Exception;
}
