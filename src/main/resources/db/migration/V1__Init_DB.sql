create table attendee (
    attendee_id uuid not null,
    email varchar(255) not null ,
    image bytea,
    name varchar(255) not null,
    skills varchar(255),
    surname varchar(255) not null,
    user_id varchar(255) not null,
    primary key (attendee_id)
);

create table chat (
    chat_id uuid not null,
    name varchar(255) not null,
    primary key (chat_id)
);

create table chat_members (
    attendee_id uuid not null,
    chat_id uuid not null
);

create table message (
    msg_id uuid not null,
    content varchar(255) not null,
    msg_date timestamp not null,
    sender int4 not null,
    chat_id uuid not null,
    primary key (msg_id)
);

alter table attendee
    add constraint UK_dbrvvjk0jxpin779y2rt5v5cq unique (email);

alter table attendee
    add constraint UK_js1888t3ixiu4tpy08ibcekna unique (user_id);

alter table chat_members
    add constraint FKcjnigrgwbin0pdph6mdphhp6i
    foreign key (chat_id) references chat;

alter table chat_members
    add constraint FKrx0iqvnb3tsct6v5w3527pxyl
    foreign key (attendee_id) references attendee;

alter table message
    add constraint FKmejd0ykokrbuekwwgd5a5xt8a
    foreign key (chat_id) references chat;