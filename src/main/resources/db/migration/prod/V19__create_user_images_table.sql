
CREATE TABLE user_images (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	image_lob BLOB,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()
);



