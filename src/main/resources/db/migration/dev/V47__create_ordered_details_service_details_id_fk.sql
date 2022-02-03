
ALTER TABLE ordered_details
  ADD CONSTRAINT fk15_assign FOREIGN KEY (service_details_id) REFERENCES service_details (id);



