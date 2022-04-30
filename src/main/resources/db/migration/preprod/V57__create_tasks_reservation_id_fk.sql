
ALTER TABLE tasks
  ADD CONSTRAINT fk23_assign FOREIGN KEY (reservation_id) REFERENCES reservations (id);



