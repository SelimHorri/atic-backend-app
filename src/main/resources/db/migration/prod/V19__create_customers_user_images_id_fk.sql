
ALTER TABLE customers
  ADD CONSTRAINT fk3_assign FOREIGN KEY (user_images_id) REFERENCES user_images (id);



