package br.jus.tre_pa.seven.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seven")
public class ApiProperty {

	private String originPermitida = "http://localhost:8000";

	private final Mail mail = new Mail();

	public Mail getMail() {
		return mail;
	}

	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}

	public static class Mail {

		private String host;

		private Integer port;

		private String username;

		private String password;

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}