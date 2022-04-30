
ALTER TABLE tasks
  ADD CONSTRAINT fk22_assign FOREIGN KEY (worker_id) REFERENCES employees (id);



