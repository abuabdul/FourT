CREATE SCHEMA FOURT AUTHORIZATION DBA;

CREATE TABLE FOURT.RESOURCE ( 
  ID   INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  NAME VARCHAR(50) NOT NULL,
  TASK_DATE  DATE NOT NULL,
  CREATED_DATE  DATE NOT NULL,
  CREATED_BY VARCHAR(50) NOT NULL,
  UPDATED_DATE  DATE NOT NULL,
  UPDATED_BY VARCHAR(50) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE FOURT.TASK_DETAIL( 
  ID   INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  TASK_DESC VARCHAR(200) NOT NULL,
  TASK_DURATION  INTEGER NOT NULL,
  TASK_STATUS  VARCHAR(20) NOT NULL,
  RESOURCE_ID INTEGER NOT NULL CONSTRAINT RESOURCE_TASK_DETAIL_FK FOREIGN KEY REFERENCES FOURT.RESOURCE(ID),
  PRIMARY KEY (ID)
);

--SET SCHEMA FOURT;

--GRANT SELECT, UPDATE ON FOURT.RESOURCE TO PUBLIC;
--GRANT SELECT, UPDATE ON FOURT.TASK_DETAIL TO PUBLIC;