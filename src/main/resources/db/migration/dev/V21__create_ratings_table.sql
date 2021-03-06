
CREATE TABLE ratings (
	employee_id INT NOT NULL,
	customer_id INT NOT NULL,
	rate_date TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT,
	rate VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	created_at TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT,
	updated_at TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT,
	PRIMARY KEY (employee_id, customer_id, rate_date)
);



