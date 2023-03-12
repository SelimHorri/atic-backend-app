
ALTER TABLE reservations
  ADD CONSTRAINT fk24_assign FOREIGN KEY (saloon_id) REFERENCES saloons (id);



