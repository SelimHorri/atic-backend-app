
ALTER TABLE ratings
  ADD CONSTRAINT fk8_assign FOREIGN KEY (worker_id) REFERENCES employees (id);



