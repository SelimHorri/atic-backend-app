
CREATE TABLE customers (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	email VARCHAR(255) DEFAULT '@gmail.com',
	phone VARCHAR(255) DEFAULT '+21622125144',
	birthdate DATE,
	user_images_id INT,
	credential_id INT,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP()
);



