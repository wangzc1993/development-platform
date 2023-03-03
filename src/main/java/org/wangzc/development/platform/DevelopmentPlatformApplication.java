package org.wangzc.development.platform;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DevelopmentPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevelopmentPlatformApplication.class, args);
	}

	@RequestMapping("/")
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("index.html");
	}
}
