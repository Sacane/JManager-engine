DROP TABLE IF EXISTS histo;

CREATE TABLE histo(
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      actualSold FLOAT NOT NULL,
      date date,
      label VARCHAR(100) NOT NULL
);


