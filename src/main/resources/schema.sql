CREATE TABLE IF NOT EXISTS TASKS(ID LONG PRIMARY KEY, TASK_NAME VARCHAR(255),TASK_STATUS CHAR(1) ,TASK_DESC VARCHAR(255), TASK_UPDATE_DATE DATE, USER_ID LONG);

CREATE TABLE IF NOT EXISTS USER(ID LONG PRIMARY KEY, USERNAME VARCHAR(50),PASSWORD VARCHAR(255) ,NAME VARCHAR(50));



