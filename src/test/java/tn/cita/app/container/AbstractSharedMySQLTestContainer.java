package tn.cita.app.container;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractSharedMySQLTestContainer {
	
	private static final MySQLContainer<?> MYSQL_CONTAINER;
	
	static {
		MYSQL_CONTAINER = getInstance();
		MYSQL_CONTAINER.start();
	}
	
	private synchronized static final MySQLContainer<?> getInstance() {
		return (MYSQL_CONTAINER == null) ? new MySQLContainer<>("mysql:5.7") : MYSQL_CONTAINER;
	}
	
}






