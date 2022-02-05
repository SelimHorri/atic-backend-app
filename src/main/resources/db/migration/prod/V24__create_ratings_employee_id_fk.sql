
ALTER TABLE ratings
  ADD CONSTRAINT fk8_assign FOREIGN KEY (employee_id) REFERENCES employees (id);



