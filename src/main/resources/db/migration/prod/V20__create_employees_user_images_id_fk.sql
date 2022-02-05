
ALTER TABLE employees
  ADD CONSTRAINT fk4_assign FOREIGN KEY (user_images_id) REFERENCES user_images (id);



