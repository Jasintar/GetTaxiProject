-- Database: taxiapp

-- DROP DATABASE taxiapp;

CREATE DATABASE taxiapp
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;

-- Schema: public

-- DROP SCHEMA public;

CREATE SCHEMA public
  AUTHORIZATION postgres;

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;
COMMENT ON SCHEMA public
  IS 'standard public schema';

-- Table: "user"

-- DROP TABLE "user";

CREATE TABLE "user"
(
  id_user bigserial NOT NULL,
  username character varying(30),
  password character varying(50) NOT NULL,
  first_name character varying(30),
  user_type character varying(1) NOT NULL,
  phone character varying(30),
  CONSTRAINT id_user PRIMARY KEY (id_user)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "user"
  OWNER TO postgres;

-- Table: car

-- DROP TABLE car;

CREATE TABLE car
(
  id_car bigserial NOT NULL,
  brend character varying(30),
  "number" character varying(30),
  id_user bigint NOT NULL,
  CONSTRAINT id_car PRIMARY KEY (id_car),
  CONSTRAINT id_user FOREIGN KEY (id_user)
      REFERENCES "user" (id_user) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE car
  OWNER TO postgres;

-- Table: "order"

-- DROP TABLE "order";

CREATE TABLE "order"
(
  id_order bigserial NOT NULL,
  start character varying(30) NOT NULL,
  finish character varying(30) NOT NULL,
  id_client bigint NOT NULL,
  id_driver bigint,
  status character varying(30) DEFAULT 'new'::character varying,
  CONSTRAINT id_order PRIMARY KEY (id_order),
  CONSTRAINT id_client FOREIGN KEY (id_client)
      REFERENCES "user" (id_user) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_driver FOREIGN KEY (id_driver)
      REFERENCES "user" (id_user) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE SET DEFAULT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "order"
  OWNER TO postgres;

