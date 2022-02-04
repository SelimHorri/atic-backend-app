
ALTER TABLE categories
  ADD CONSTRAINT fk11_assign FOREIGN KEY (parent_category_id) REFERENCES categories (id);



