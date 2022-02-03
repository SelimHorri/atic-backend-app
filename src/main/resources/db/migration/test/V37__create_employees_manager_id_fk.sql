
ALTER TABLE employees
  ADD CONSTRAINT fk5_assign FOREIGN KEY (manager_id) REFERENCES employees (id);



