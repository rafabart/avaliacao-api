
CREATE TABLE public.category (
	id bigserial NOT NULL,
	description varchar(100) NOT NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);


CREATE TABLE public.course (
	id bigserial NOT NULL,
	description varchar(100) NOT NULL,
	end_date timestamp NOT NULL,
	number_of_students int4 NOT NULL,
	start_date timestamp NOT NULL,
	category_id int8 NULL,
	CONSTRAINT course_pkey PRIMARY KEY (id),
	CONSTRAINT fkkyes7515s3ypoovxrput029bh FOREIGN KEY (category_id) REFERENCES category(id)
);



CREATE TABLE public.user_system (
	id bigserial NOT NULL,
	name varchar(100) NOT NULL,
	login varchar(30) unique NOT NULL,
	password varchar NOT NULL,
	CONSTRAINT user_system_pkey PRIMARY KEY (id)
);