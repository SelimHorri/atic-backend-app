package tn.cita.app.container;

import java.util.Optional;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractTestSharedMySQLContainer {
	
	private static final MySQLContainer<?> MYSQL_CONTAINER;
	
	static {
		MYSQL_CONTAINER = getInstance();
		MYSQL_CONTAINER.start();
	}
	
	private synchronized static final MySQLContainer<?> getInstance() {
		
		if (!Optional.ofNullable(MYSQL_CONTAINER).isPresent())
			return new MySQLContainer<>("mysql:5.7");
		
		return MYSQL_CONTAINER;
	}
	
	
	
}







