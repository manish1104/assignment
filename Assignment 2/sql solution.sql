-------------Problem Statement 1------------
SELECT
    emp.emp_id,
    emp.emp_name,
    emp.age,
    dept.dept_name
FROM
    employee emp
    INNER JOIN department dept ON emp.dept_id = dept.dept_id
WHERE
    emp.age > 35
ORDER BY
    emp.age;
    
------------Problem Statement 2-------------

SELECT
    emp_id,
    emp_name,
    age,
    dept_name
FROM
    (
        SELECT
            emp_id,
            emp_name,
            age,
            dept_name,
            ROW_NUMBER() OVER(
                ORDER BY
                    age DESC
            ) AS age_order
        FROM
            employee emp
            INNER JOIN department dept ON emp.dept_id = dept.dept_id
    ) dt
WHERE
    dt.age_order = 2;
    
-------------Problem Statement 3--------------

SELECT
    emp.emp_id,
    emp.emp_name,
    emp.age,
    dept.dept_name
FROM
    (
        SELECT
            emp_id,
            emp_name,
            age,
            dept_id
        FROM
            employee
        ORDER BY
            age DESC
    ) emp
    INNER JOIN department dept ON emp.dept_id = dept.dept_id
WHERE
    ROWNUM <= 5
ORDER BY
    age DESC;