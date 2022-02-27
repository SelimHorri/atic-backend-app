
ALTER TABLE employees
  ADD CONSTRAINT fk4_assign FOREIGN KEY (user_image_id) REFERENCES user_images (id);



