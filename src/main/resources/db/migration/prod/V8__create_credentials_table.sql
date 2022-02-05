
CREATE TABLE credentials (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	role VARCHAR(255) NOT NULL,
	is_enabled BOOLEAN DEFAULT false,
	is_account_non_expired BOOLEAN DEFAULT true,
	is_account_non_locked BOOLEAN DEFAULT true,
	is_credentials_non_expired BOOLEAN DEFAULT true,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()
);



