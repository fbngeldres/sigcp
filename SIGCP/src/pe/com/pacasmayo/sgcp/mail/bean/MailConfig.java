package pe.com.pacasmayo.sgcp.mail.bean;

public class MailConfig {

	private String user;
	private String pswd;
	private String transportHost;
	private String mailTransportProtocol;
	private String transportProtocol;
	private String mailTransportHost;
	private String mailTransportAuth;
	private String authorization;
	private String contentType;
	private String mailTimeout;
	private String mailConnectionTimeout;
	private String timeout;
	private String connectionTimeout;

	public MailConfig() {
		contentType = "text/plain";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getTransportHost() {
		return transportHost;
	}

	public void setTransportHost(String transportHost) {
		this.transportHost = transportHost;
	}

	public String getMailTransportProtocol() {
		return mailTransportProtocol;
	}

	public void setMailTransportProtocol(String mailTransportProtocol) {
		this.mailTransportProtocol = mailTransportProtocol;
	}

	public String getTransportProtocol() {
		return transportProtocol;
	}

	public void setTransportProtocol(String transportProtocol) {
		this.transportProtocol = transportProtocol;
	}

	public String getMailTransportHost() {
		return mailTransportHost;
	}

	public void setMailTransportHost(String mailTransportHost) {
		this.mailTransportHost = mailTransportHost;
	}

	public String getMailTransportAuth() {
		return mailTransportAuth;
	}

	public void setMailTransportAuth(String mailTransportAuth) {
		this.mailTransportAuth = mailTransportAuth;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMailTimeout() {
		return mailTimeout;
	}

	public void setMailTimeout(String mailTimeout) {
		this.mailTimeout = mailTimeout;
	}

	public String getMailConnectionTimeout() {
		return mailConnectionTimeout;
	}

	public void setMailConnectionTimeout(String mailConnectionTimeout) {
		this.mailConnectionTimeout = mailConnectionTimeout;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

}
