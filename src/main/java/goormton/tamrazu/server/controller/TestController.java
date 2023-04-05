package goormton.tamrazu.server.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/health")
	public String healthCheck() {
		return "server connect";
	}

	@GetMapping("/auth/test")
	public String testPrincipal(Principal principal) {
		return principal.getName();
	}
}
