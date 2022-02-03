
ALTER TABLE verification_tokens
  ADD CONSTRAINT fk1_assign FOREIGN KEY (credential_id) REFERENCES credentials (id);



