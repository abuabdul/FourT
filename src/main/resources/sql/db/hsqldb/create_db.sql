/*
 * Copyright 2013-2016 abuabdul.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
CREATE TABLE RESOURCE ( 
  ID   INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  NAME VARCHAR(50) NOT NULL,
  TASK_DATE  DATE NOT NULL,
  CREATED_DATE  DATE NOT NULL,
  CREATED_BY VARCHAR(50) NOT NULL,
  UPDATED_DATE  DATE NOT NULL,
  UPDATED_BY VARCHAR(50) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE TASK_DETAIL( 
  ID   INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  TASK_DESC VARCHAR(200) NOT NULL,
  TASK_DURATION  INTEGER NOT NULL,
  TASK_STATUS  VARCHAR(20) NOT NULL,
  RESOURCE_ID INTEGER NOT NULL CONSTRAINT RESOURCE_TASK_DETAIL_FK FOREIGN KEY REFERENCES RESOURCE(ID),
  PRIMARY KEY (ID)
);

CREATE TABLE REF_DETAIL( 
  ID   INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  REF_TYPE VARCHAR(50) NOT NULL,
  REF_VALUE  VARCHAR(100) NOT NULL,
  ACTIVE  VARCHAR(1) NOT NULL,
  PRIMARY KEY (ID)
);

INSERT INTO REF_DETAIL VALUES(DEFAULT, 'TITAN_RESOURCE_NAME','ABUBACKER','Y');
INSERT INTO REF_DETAIL VALUES(DEFAULT, 'TITAN_RESOURCE_NAME','SIDDIK','Y');
INSERT INTO REF_DETAIL VALUES(DEFAULT, 'TITAN_RESOURCE_NAME','MUNAWARA','Y');
INSERT INTO REF_DETAIL VALUES(DEFAULT, 'TITAN_RESOURCE_NAME','THAIUFA','Y');

CREATE USER READONLY_USER PASSWORD '';
CREATE ROLE readonly;
GRANT readonly TO READONLY_USER;
GRANT SELECT ON TABLE RESOURCE TO readonly;
GRANT SELECT ON TABLE TASK_DETAIL TO readonly;
GRANT SELECT ON TABLE REF_DETAIL TO readonly;