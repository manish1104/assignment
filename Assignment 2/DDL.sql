CREATE TABLE department (
    dept_id            CHAR(4) NOT NULL,
    dept_name          VARCHAR(36) NOT NULL,
    dept_description   VARCHAR(100) NOT NULL,
    PRIMARY KEY ( dept_id )
);

CREATE TABLE employee (
    emp_id     CHAR(4) NOT NULL PRIMARY KEY,
    emp_name   VARCHAR(50) NOT NULL,
    age        CHAR(4),
    dept_id    CHAR(4) NOT NULL,
    FOREIGN KEY ( dept_id )
        REFERENCES department ( dept_id )
);

INSERT INTO department VALUES (
    10,
    'ACCOUNTING',
    'ACCOUNTING DEPARTMENT AT NEW YORK'
);

INSERT INTO department VALUES (
    20,
    'RESEARCH',
    'RESEARCH DEPARTMENT AT DALLAS'
);

INSERT INTO department VALUES (
    30,
    'SALES',
    'SALES DEPARTMENT AT CHICAGO'
);

INSERT INTO department VALUES (
    40,
    'OPERATIONS',
    'OPERATIONS DEPARTMENT AT BOSTON'
);

INSERT INTO employee VALUES (
    7369,
    'SMITH',
    25,
    20
);

INSERT INTO employee VALUES (
    7499,
    'ALLEN',
    30,
    30
);

INSERT INTO employee VALUES (
    7521,
    'WARD',
    35,
    30
);

INSERT INTO employee VALUES (
    7566,
    'JONES',
    40,
    20
);

INSERT INTO employee VALUES (
    7698,
    'BLAKE',
    45,
    30
);

INSERT INTO employee VALUES (
    7782,
    'CLARK',
    50,
    10
);

INSERT INTO employee VALUES (
    7788,
    'SCOTT',
    55,
    40
);

INSERT INTO employee VALUES (
    7839,
    'KING',
    60,
    10
);

INSERT INTO employee VALUES (
    7844,
    'TURNER',
    65,
    30
);

INSERT INTO employee VALUES (
    7876,
    'ADAMS',
    70,
    20
);

INSERT INTO employee VALUES (
    7900,
    'JAMES',
    35,
    40
);

INSERT INTO employee VALUES (
    7934,
    'MILLER',
    40,
    10
);

INSERT INTO employee VALUES (
    7902,
    'FORD',
    45,
    20
);

INSERT INTO employee VALUES (
    7654,
    'MARTIN',
    50,
    40
);