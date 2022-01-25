package tn.cita.app;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractTestSharedContainer {
	
	protected static final MySQLContainer<?> MYSQL_CONTAINER;
	
	static {
		MYSQL_CONTAINER = new MySQLContainer<>("mysql:5.7.33");
		MYSQL_CONTAINER.start();
	}
	
	
	
}







