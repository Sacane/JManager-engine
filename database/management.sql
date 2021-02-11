--income manage the income and outcome that the user has done. 

CREATE TABLE income(
    id_income INTEGER PRIMARY KEY,
    date_inc date NOT NULL,
    label VARCHAR(100) NOT NULL,
    is_income boolean,
    value_inc float NOT NULL,
    description text
);

--Promises is a final income or outcome the user have to pay or will receive.

CREATE TABLE promises(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    value float NOT NULL,
    is_debt boolean NOT NULL,
    name_owner VARCHAR(100) NOT NULL,
    description text
);

--Project are a intention to pay or receive money during a determinated period.

CREATE TABLE project(
    id_project INTEGER PRIMARY KEY AUTOINCREMENT,
    name_project VARCHAR(100) NOT NULL,
    day int,
    is_income boolean NOT NULL,
    value float NOT NULL,
    duration int 
);

