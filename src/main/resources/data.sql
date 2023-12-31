-- Insertar 5 ciudades de ejemplo
INSERT INTO desi2023.City (city_name, state_or_province, country)
VALUES
  ('Nueva York', 'Nueva York', 'Estados Unidos'),
  ('Londres', 'Inglaterra', 'Reino Unido'),
  ('París', 'Isla de Francia', 'Francia'),
  ('Sídney', 'Nueva Gales del Sur', 'Australia'),
  ('Tokio', 'Tokio', 'Japón'),
  ('Barcelona', 'Cataluña', 'España'),
  ('Hamburgo', 'Hamburgo', 'Alemania'),
  ('Milán', 'Lombardía', 'Italia'),
  ('Shanghái', 'Shanghái', 'China'),
  ('Guadalajara', 'Jalisco', 'México'),
  ('Madrid', 'Comunidad de Madrid', 'España'),
  ('Berlín', 'Berlín', 'Alemania'),
  ('Roma', 'Lacio', 'Italia'),
  ('Pekín', 'Pekín', 'China'),
  ('México DF', 'Ciudad de México', 'México');


-- Insertar 5 aeropuertos de ejemplo en las ciudades
INSERT INTO desi2023.Airport (id_airport, location_id_city)
VALUES ('JFK', 1), ('LHR', 2), ('CDG', 3), ('SYD', 4), ('HND', 5),('BCN', 6), ('HAM', 7), ('MXP', 8), ('SHA', 9), ('GDL', 10),
 ('MAD', 11), ('TXL', 12), ('FCO', 13), ('PEK', 14), ('MEX', 15);


-- Insertar 5 modelos de aeronaves de ejemplo
INSERT INTO desi2023.Aircraft (capacity, number_of_rows, seats_per_row)
VALUES (300, 30, 10),
       (150, 20, 6),
       (50, 10, 5),
       (20, 5, 4),
       (500, 40, 12);

INSERT INTO desi2023.Customer (dni, firts_name, last_name, address, email, birth_date, passport_number)
VALUES
  (123456789, 'John', 'Doe', '123 Main St', 'john.doe@example.com', '1990-01-01', 'AB123456'),
  (987654321, 'Jane', 'Smith', '456 Oak St', 'jane.smith@example.com', '1985-05-15', 'CD789012'),
  (456789012, 'Bob', 'Johnson', '789 Elm St', 'bob.johnson@example.com', '1982-09-30', 'EF345678'),
  (345678901, 'Alice', 'Williams', '234 Pine St', 'alice.williams@example.com', '1995-07-12', 'GH901234'),
  (789012345, 'David', 'Brown', '567 Cedar St', 'david.brown@example.com', '1978-03-25', 'IJ567890');

-- Insertar 5 vuelos
INSERT INTO desi2023.Flight (flight_number, flight_type, ticket_price, departure_date_time, flight_status, aircraft_id_aircraft, origin_city_id_city, dest_city_id_city)
VALUES
  ('FL001', 'national', 200.0, '2023-12-01 08:00:00', 'normal', 1, 1, 2),
  ('FL002', 'international', 400.0, '2023-12-02 12:30:00', 'normal', 1, 1, 3),
  ('FL003', 'national', 250.0, '2023-12-03 15:45:00', 'normal', 2, 2, 4),
  ('FL004', 'national', 180.0, '2023-12-04 10:15:00', 'normal', 2, 3, 5),
  ('FL005', 'international', 350.0, '2023-12-05 14:00:00', 'normal', 3, 4, 1);

  INSERT INTO desi2023.Ticket (number_row, seat_number, amount_to_pay, issue_date_time, customer_dni, flight_flight_number)
VALUES
  (5, 10, 150.0, '2023-12-01 10:30:00', 123456789, 'FL001'),
  (1, 5, 250.0, '2023-12-02 14:45:00', 987654321, 'FL002'),
  (2, 2, 200.0, '2023-12-03 18:00:00', 456789012, 'FL003'),
  (1, 1, 180.0, '2023-12-04 22:15:00', 345678901, 'FL004'),
  (5, 4, 300.0, '2023-12-05 08:30:00', 789012345, 'FL005');

-- Insertar datos de impuestos y tasas
-- INSERT INTO desi2023.TaxInfo (iva, tasa_nacional, tasa_internacional, dolar)
-- VALUES (21, 1100, 1.4, 400);