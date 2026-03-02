ALTER TABLE UserEntity
DROP
COLUMN id;

ALTER TABLE UserEntity
    ADD CONSTRAINT pk_userentity PRIMARY KEY (email);