
ALTER TABLE saloon_tags
  ADD CONSTRAINT fk20_assign FOREIGN KEY (saloon_id) REFERENCES saloons (id);



