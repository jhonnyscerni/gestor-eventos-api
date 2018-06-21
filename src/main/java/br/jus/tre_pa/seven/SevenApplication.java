package br.jus.tre_pa.seven;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.jus.tre_pa.seven.config.property.ApiProperty;
import br.jus.tre_pa.seven.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class SevenApplication implements CommandLineRunner{
	
	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(SevenApplication.class, args);
	}
	
	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
