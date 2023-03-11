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
    CONSTRAINT principal_for_role_fk FOREIGN KEY (principal_id) REFERENCES principal(id),
    CONSTRAINT role_for_principal_fk FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE faculty (
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE
);

CREATE TABLE cathedra (
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE,
    faculty_id bigint NOT NULL,
    CONSTRAINT faculty_of_cathedra_fk FOREIGN KEY(faculty_id) REFERENCES faculty(id)
);

CREATE TABLE speciality (
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE,
    faculty_id bigint NOT NULL,
    CONSTRAINT faculty_of_speciality_fk FOREIGN KEY(faculty_id) REFERENCES faculty(id)
);

CREATE TABLE student (
    id bigserial PRIMARY KEY,
    principal_id bigint NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    study_year integer NOT NULL,
    speciality_id bigint NOT NULL,
    CONSTRAINT principal_of_student_fk FOREIGN KEY(principal_id) REFERENCES principal(id),
    CONSTRAINT speciality_of_student_fk FOREIGN KEY(speciality_id) REFERENCES speciality(id)
);

CREATE TABLE teacher (
    id bigserial PRIMARY KEY,
    principal_id bigint NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    rank varchar(255) NOT NULL,
    CONSTRAINT principal_of_teacher_fk FOREIGN KEY(principal_id) REFERENCES principal(id)
);

CREATE TABLE methodologist (
    id bigserial PRIMARY KEY,
    principal_id bigint NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    CONSTRAINT principal_of_methodologist_fk FOREIGN KEY(principal_id) REFERENCES principal(id)
);

CREATE TABLE subject (
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE,
    author_id bigint NOT NULL,
    CONSTRAINT author_of_subject_fk FOREIGN KEY (author_id) REFERENCES teacher(id)
);

CREATE TABLE university_group (
    id bigserial PRIMARY KEY,
    number smallint NOT NULL CHECK (number > 1 AND number < 10),
    teacher_id bigint NOT NULL,
    subject_id bigint NOT NULL,
    CONSTRAINT subject_of_group_fk FOREIGN KEY (subject_id) REFERENCES subject(id),
    CONSTRAINT teacher_of_group_fk FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);

CREATE TABLE group_student (
    group_id bigint NOT NULL,
    student_id bigint NOT NULL,
    CONSTRAINT group_student_pk PRIMARY KEY (group_id, student_id),
    CONSTRAINT group_for_student_fk FOREIGN KEY (group_id) REFERENCES university_group(id),
    CONSTRAINT student_for_group_fk FOREIGN KEY (student_id) REFERENCES student(id)
);

CREATE TABLE schedule (
    id bigserial PRIMARY KEY,
    group_id bigint NOT NULL,
    week integer NOT NULL,
    week_day varchar(255) NOT NULL,
    start_time varchar(255) NOT NULL,
    end_time varchar(255) NOT NULL,
    CONSTRAINT group_of_schedule_fk FOREIGN KEY (group_id) REFERENCES university_group(id)
);