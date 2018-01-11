Assignment 1 
-------------
Write simple hello world application using spring boot:

> **Steps to deploy app**
>* Clone GIT repository locally using URL: https://github.com/manishsingh1104/assignment.git
>* Import Maven project in eclipse
>* Run AppMain.java as Java Application
>* Use below url to access the endpoint
>
>
>```
>Method: GET
>URL: [http://localhost:8090/api/greet?name={YOUR_NAME}](http://localhost:8090/api/greet?name=manish)
>```

Assignment 2
-----
> **Database Setup**
> * Run DDL included in the repository. Assignment 2 -> DDL.sql 

>**Note**
>* All solutions are available in Assignment 2 -> sql solution.sql

#### Problem Statement 1: Fetch all employees along with there dept name where employee age is greater than 35.

**Solution**
>```sql
>SELECT
>    emp.emp_id,
>    emp.emp_name,
>    emp.age,
>    dept.dept_name
>FROM
>    employee emp
>    INNER JOIN department dept ON emp.dept_id = dept.dept_id
>WHERE
>    emp.age > 35
>ORDER BY
>    emp.age;
>```

**Output**
>| EMP_ID | EMP_NAME | AGE | DEPT_NAME |
>| --- | --- | --- | --- | 
>| 7566 | JONES	| 40 | RESEARCH | 
>| 7934 | MILLER	| 40 | ACCOUNTING | 
>| 7698 | BLAKE	| 45 | SALES | 
>| 7902 | FORD	| 45 | RESEARCH | 
>| 7782 | CLARK	| 50 | ACCOUNTING | 
>| 7654 | MARTIN	| 50 | OPERATIONS | 
>| 7788 | SCOTT	| 55 | OPERATIONS | 
>| 7839 | KING	| 60 | ACCOUNTING | 
>| 7844 | TURNER	| 65 | SALES | 
>| 7876 | ADAMS	| 70 | RESEARCH |  

#### Problem Statement 2: Fetch details of second oldest employee.

**Solution**
>```sql
>SELECT
>    emp_id,
>    emp_name,
>    age,
>    dept_name
>FROM
>    (
>        SELECT
>            emp_id,
>            emp_name,
>            age,
>            dept_name,
>            ROW_NUMBER() OVER(
>                ORDER BY
>                    age DESC
>            ) AS age_order
>        FROM
>            employee emp
>            INNER JOIN department dept ON emp.dept_id = dept.dept_id
>    ) dt
>WHERE
>    dt.age_order = 2;
>    ```

**Output**
>| EMP_ID | EMP_NAME | AGE | DEPT_NAME |
>| --- | --- | --- | --- | 
>| 7844 | TURNER | 65 | SALES |

#### Problem Statement 3: Fetch details of 5 oldest employees along with their department names.

**Solution**
> ```sql
>SELECT
>    emp.emp_id,
>    emp.emp_name,
>    emp.age,
>    dept.dept_name
>FROM
>    (
>        SELECT
>            emp_id,
>            emp_name,
>            age,
>            dept_id
>        FROM
>            employee
>        ORDER BY
>            age DESC
>    ) emp
>    INNER JOIN department dept ON emp.dept_id = dept.dept_id
>WHERE
>    ROWNUM <= 5
>ORDER BY
>    age DESC;
>    ```

**Output**
>| EMP_ID | EMP_NAME | AGE | DEPT_NAME |
>| --- | --- | --- | --- | 
>| 7876 | ADAMS	 | 70 | RESEARCH   |
>| 7844 | TURNER | 65 | SALES      |
>| 7839 | KING	 | 60 | ACCOUNTING |
>| 7788 | SCOTT	 | 55 | OPERATIONS |
>| 7782 | CLARK	 | 50 | ACCOUNTING |

Assignment 3
----- 
#### Understand Spring Transaction Management: Done

Assignment 4
----- 
#### Understand any ORM like Hibernate: Done
Assignment 5
-----
Core Java Assignment
>* Use FileProcessor &rarr; resources &rarr; application.properties file to specify the root folder and other app specific properties.
>* Run Assignment 5 &rarr; FileProcessor &rarr; src &rarr; com.sapient.fileprocessor &rarr; FileProcessorMain.java as 'Java Application'.