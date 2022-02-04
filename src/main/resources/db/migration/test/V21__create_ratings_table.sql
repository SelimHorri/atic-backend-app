
CREATE TABLE ratings (
	employee_id INT NOT NULL,
	customer_id INT NOT NULL,
	rate_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	rate DECIMAL(2, 1) NOT NULL,
	rate_description VARCHAR(255),
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	PRIMARY KEY (employee_id, customer_id, rate_date)
);


