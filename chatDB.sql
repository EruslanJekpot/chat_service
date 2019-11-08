create table attendee (
    attendee_id serial PRIMARY KEY ,
    user_id varchar(32) NOT NULL UNIQUE ,
    email varchar(128)  NOT NULL UNIQUE ,
    surname varchar(64) NOT NULL ,
    name varchar(32) NOT NULL ,
    skills text
);
create table chat (
    chat_id serial PRIMARY KEY ,
    name varchar(64) NOT NULL
);
create table message (
    msg_id serial PRIMARY KEY ,
    chat_id integer REFERENCES chat NOT NULL ,
    sender integer REFERENCES attendee NOT NULL ,
    content text NOT NULL ,
    msg_date timestamp NOT NULL
);
create table chat_members (
    chat_id integer REFERENCES chat NOT NULL ,
    attendee_id integer REFERENCES attendee NOT NULL,
    PRIMARY KEY(chat_id, attendee_id)
);