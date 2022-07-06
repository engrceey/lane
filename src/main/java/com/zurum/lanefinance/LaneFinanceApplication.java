package com.zurum.lanefinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@SpringBootApplication
public class LaneFinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaneFinanceApplication.class, args);
	}

}

@RestController
class TestController {
	@GetMapping("/ping")
	public String ping() {
		return "pong " + Instant.now();
	}
}
