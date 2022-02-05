
ALTER TABLE service_details
  ADD CONSTRAINT fk13_assign FOREIGN KEY (category_id) REFERENCES categories (id);



