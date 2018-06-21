package br.jus.tre_pa.seven.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeycloakService {
	
	@Autowired
	private HttpServletRequest req;
//	
//	public KeycloakSecurityContext getKeycloakSecurityContext() {
//		return (KeycloakSecurityContext) req.getAttribute(KeycloakSecurityContext.class.getName());
//		}

}
