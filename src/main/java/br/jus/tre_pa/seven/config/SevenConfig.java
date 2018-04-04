package br.jus.tre_pa.seven.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe com as propriedades do seven.
 *
 */
@Component
@ConfigurationProperties(prefix = "seven")
@Getter
@Setter
public class SevenConfig {

  private String buildTimestamp;

	private String projectVersion;

}
