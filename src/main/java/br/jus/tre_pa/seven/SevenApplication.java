package br.jus.tre_pa.seven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.jus.tre_pa.seven.config.property.ApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class SevenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SevenApplication.class, args);
	}
}
