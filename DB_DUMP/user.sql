--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-06-20 14:12:13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4806 (class 0 OID 17033)
-- Dependencies: 216
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" VALUES (1, 'admin1', 'password1', 'ADMIN');
INSERT INTO public."user" VALUES (2, 'admin2', 'password2', 'ADMIN');
INSERT INTO public."user" VALUES (3, 'personnel1', 'password3', 'PERSONNEL');
INSERT INTO public."user" VALUES (4, 'personnel2', 'password4', 'PERSONNEL');
INSERT INTO public."user" VALUES (5, 'personnel3', 'password5', 'PERSONNEL');
INSERT INTO public."user" VALUES (6, 'personnel4', 'password6', 'PERSONNEL');
INSERT INTO public."user" VALUES (7, 'personnel5', 'password7', 'PERSONNEL');
INSERT INTO public."user" VALUES (8, 'personnel6', 'password8', 'PERSONNEL');
INSERT INTO public."user" VALUES (9, 'personnel7', 'password9', 'PERSONNEL');
INSERT INTO public."user" VALUES (10, 'personnel8', 'password10', 'PERSONNEL');
INSERT INTO public."user" VALUES (11, 'admintest', '1234', 'ADMIN');


--
-- TOC entry 4813 (class 0 OID 0)
-- Dependencies: 215
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 1, false);


-- Completed on 2024-06-20 14:12:13

--
-- PostgreSQL database dump complete
--

