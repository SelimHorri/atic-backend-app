
ALTER TABLE customers
  ADD CONSTRAINT fk2_assign FOREIGN KEY (credential_id) REFERENCES credentials (id);



