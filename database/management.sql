

CREATE TABLE trans(
    id_trans INTEGER PRIMARY KEY AUTOINCREMENT,
    is_in boolean NOT NULL,
    label VARCHAR(100) NOT NULL,
    value float NOT NULL,
    description text
);

--income manage the income and outcome that the user has done.

CREATE TABLE income(
    id_income INTEGER PRIMARY KEY AUTOINCREMENT,
    transition INTEGER REFERENCES trans(id_trans),
    date date NOT NULL
);

--Promises is a final income or outcome the user have to pay or will receive in the future.

CREATE TABLE promise(
    id_promise INTEGER PRIMARY KEY REFERENCES trans(id_trans),
    name_owner VARCHAR(100) NOT NULL
);

--Project are a intention to pay or receive money during a determinate period.

CREATE TABLE project(
    id_project INTEGER PRIMARY KEY REFERENCES trans(id_trans),
    day int,
    duration int 
);

--Account are the location where are store the total amount of the user

CREATE TABLE account(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name_account VARCHAR(100) NOT NULL,
    amount float NOT NULL
);

--income[1, z]
--transaction[1, true, "baba", 200, "oui"]
--SELECT * FROM income INNER JOIN transaction ON income.id_income = transaction.id_trans WHERE transaction.label = {label}