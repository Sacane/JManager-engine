DROP TABLE IF EXISTS histo;

--TODO : Update the methods to insert into the historical
CREATE TABLE histo(
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      id_income INTEGER REFERENCES income(id_income),
      actualSold FLOAT,
      date date,
      label VARCHAR(100)
);


