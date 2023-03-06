CREATE TABLE principal (
    id bigserial PRIMARY KEY,
    email varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL
);

CREATE TABLE role (
    id bigserial PRIMARY KEY,
    title varchar(255) NOT NULL UNIQUE
);

CREATE TABLE principal_role (
    principal_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT principal_role_pk PRIMARY KEY (principal_id, role_id),
    CONSTRAINT principal_id_fk FOREIGN KEY (principal_id) REFERENCES principal(id),
    CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE subject (
    id bigint PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE,
    author bigserial NOT NULL,
    CONSTRAINT author_fk FOREIGN KEY (author) REFERENCES -- TODO: Sasha needs to add
);

CREATE TABLE group (
    id bigint PRIMARY KEY,
    number smallint NOT NULL
                   check (number > 1 and number < 10),
    lecturer bigserial NOT NULL,
    subject bigint NOT NULL,
    CONSTRAINT subject_fk FOREIGN KEY (subject) REFERENCES subject(id)
    CONSTRAINT lecturer_fk FOREIGN KEY (lecturer) REFERENCES --TODO: Sasha needs to add
);

CREATE TABLE group_students (
    group_id bigint NOT NULL,
    student_id bigserial NOT NULL,
    CONSTRAINT group_student_pk PRIMARY KEY (group_id, student_id),
    CONSTRAINT group_id_fk FOREIGN KEY (group_id) REFERENCES group(id),
    CONSTRAINT student_id_fk FOREIGN KEY (student_id) REFERENCES --TODO: Sasha needs to add
);

--TODO: users & schedules tables