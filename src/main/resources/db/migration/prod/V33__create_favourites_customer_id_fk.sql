
ALTER TABLE favourites
  ADD CONSTRAINT fk17_assign FOREIGN KEY (customer_id) REFERENCES customers (id);



