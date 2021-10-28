package com.airtnt.frontend;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("../amentity_images", registry);
		exposeDirectory("../category_images", registry);
		exposeDirectory("../images", registry);
		exposeDirectory("../room_images", registry);
		exposeDirectory("../room_types", registry);
		exposeDirectory("../user-photos", registry);
	}

	private void exposeDirectory(String pathPattern, ResourceHandlerRegistry registry) {
		Path path = Paths.get(pathPattern);
		String absolutePath = path.toFile().getAbsolutePath();

		String logicalPath = pathPattern.replace("..", "") + "/**";
		registry.addResourceHandler(logicalPath).addResourceLocations("file:/" + absolutePath + "/");
	}
}
