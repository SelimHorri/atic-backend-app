
ALTER TABLE ratings
  ADD CONSTRAINT fk9_assign FOREIGN KEY (customer_id) REFERENCES customers (id);



