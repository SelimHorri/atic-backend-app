
CREATE TABLE employees (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	identifier VARCHAR(255) NOT NULL DEFAULT RANDOM_UUID(),
	ssn VARCHAR(255),
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	is_male BOOLEAN,
	email VARCHAR(255) DEFAULT 'cita.team.mail@gmail.com',
	phone VARCHAR(255) DEFAULT '+21622125144',
	birthdate DATE,
	hiredate DATE,
	user_image_id INT,
	manager_id INT,
	credential_id INT,
	saloon_id INT,
	created_at TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT,
	updated_at TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT
);



