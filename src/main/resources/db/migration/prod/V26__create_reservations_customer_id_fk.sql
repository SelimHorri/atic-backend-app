
ALTER TABLE reservations
  ADD CONSTRAINT fk10_assign FOREIGN KEY (customer_id) REFERENCES customers (id);



