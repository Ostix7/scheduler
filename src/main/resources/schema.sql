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