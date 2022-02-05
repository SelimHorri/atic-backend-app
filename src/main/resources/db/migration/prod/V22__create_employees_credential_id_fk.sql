
ALTER TABLE employees
  ADD CONSTRAINT fk6_assign FOREIGN KEY (credential_id) REFERENCES credentials (id);



