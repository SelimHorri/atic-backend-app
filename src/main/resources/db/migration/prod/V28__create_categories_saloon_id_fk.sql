
ALTER TABLE categories
  ADD CONSTRAINT fk12_assign FOREIGN KEY (saloon_id) REFERENCES saloons (id);



