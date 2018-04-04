package br.jus.tre_pa.seven.core.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditConfig {
	@Bean
	public AuditorAware<String> auditorAware() {
		return this.createAuditorAwareImpl();
	}

	protected AuditorAware<String> createAuditorAwareImpl() {
		return new AuditorAware<String>() {
			@Override
			public String getCurrentAuditor() {
				return "Naresh";
			}
		};
	}
}
