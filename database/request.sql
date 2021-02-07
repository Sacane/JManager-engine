--request to select all the input in a chosen month : 

SELECT name_pages, value_input 
FROM input
WHERE date_input = "xx-xx-xx";


--sum of the input's values in a chosen month : 

SELECT label, SUM(value_inc) AS sum_inputs
FROM income
WHERE is_income = true
AND date_input = "xxxx-xx-xx";

--sum of the output's values in a chosen month : 

SELECT label, SUM(value_inc) AS sum_outputs
FROM income
WHERE is_income = false
AND date_input = "xxxx-xx-xx";


--sum of the debt that someone own you : 

SELECT SUM(value) AS sum_to_own 
FROM promises
WHERE is_debt = true
AND name_owner = 'insert name';

--sum of the debt that you own someone :



