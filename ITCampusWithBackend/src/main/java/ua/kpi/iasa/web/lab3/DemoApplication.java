package ua.kpi.iasa.web.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//		context.register(SecurityConfiguration.class);
//		context.scan("ua.kpi.iasa.web.lab3");
//		context.refresh();
//		
//		UserController controller = context.getBean(UserController.class);
//		controller.getPageDataForUser("tordek",null);
	}

}
