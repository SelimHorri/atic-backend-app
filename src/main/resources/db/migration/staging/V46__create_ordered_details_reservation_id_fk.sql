
ALTER TABLE ordered_details
  ADD CONSTRAINT fk14_assign FOREIGN KEY (reservation_id) REFERENCES reservations (id);



