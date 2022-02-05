
ALTER TABLE saloon_tags
  ADD CONSTRAINT fk21_assign FOREIGN KEY (tag_id) REFERENCES tags (id);



