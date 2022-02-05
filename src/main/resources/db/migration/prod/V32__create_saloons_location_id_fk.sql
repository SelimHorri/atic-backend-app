
ALTER TABLE saloons
  ADD CONSTRAINT fk16_assign FOREIGN KEY (location_id) REFERENCES locations (id);



