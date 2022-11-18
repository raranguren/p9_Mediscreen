/* Create database */
DROP DATABASE IF EXISTS mediscreen;
CREATE DATABASE mediscreen;
USE mediscreen;

/* Create the user that the API will use (set in application.properties) */
DROP USER IF EXISTS apiuser;
CREATE USER apiuser IDENTIFIED WITH caching_sha2_password BY 'dbpassword';
GRANT SELECT, INSERT, UPDATE, DELETE ON mediscreen.* TO apiuser;

/* Tables */
CREATE TABLE patient
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    family  VARCHAR(20)           NOT NULL,
    given   VARCHAR(20)           NOT NULL,
    dob     DATE                  NOT NULL,
    sex     CHAR,
    address VARCHAR(80),
    phone   VARCHAR(12),
    PRIMARY KEY (id)
);
