package tn.cita.app.container;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractTestSharedMySQLContainer {
	
	protected static final MySQLContainer<?> MYSQL_CONTAINER;
	
	static {
		MYSQL_CONTAINER = new MySQLContainer<>("mysql:5.7.33");
		MYSQL_CONTAINER.start();
	}
	
	
	
}






