
CREATE TABLE saloons (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	code VARCHAR(255) NOT NULL,
	name VARCHAR(255) NOT NULL,
	is_primary BOOLEAN NOT NULL DEFAULT false,
	opening_date DATE,
	full_adr VARCHAR(255),
	iframe_google_map CLOB,
	email VARCHAR(255) DEFAULT 'cita.team.mail@gmail.com',
	location_id INT NOT NULL,
	created_at TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT,
	updated_at TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT
);



