
ALTER TABLE customers
  ADD CONSTRAINT fk3_assign FOREIGN KEY (user_image_id) REFERENCES user_images (id);



