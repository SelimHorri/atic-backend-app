
CREATE TABLE customers (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	identifier VARCHAR(255) NOT NULL DEFAULT (UUID()),
	ssn VARCHAR(255),
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	is_male BOOLEAN,
	email VARCHAR(255) DEFAULT 'cita.team.mail@gmail.com',
	phone VARCHAR(255) DEFAULT '+21622125144',
	birthdate DATE,
	facebook_url VARCHAR(255) DEFAULT 'https://www.facebook.com/SelimHorri/',
	instagram_url VARCHAR(255) DEFAULT 'https://www.instagram.com/selimhorri/',
	linkedin_url VARCHAR(255) DEFAULT 'https://www.linkedin.com/in/selim-horri-98b363158/',
	user_image_id INT,
	credential_id INT,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP()
);



