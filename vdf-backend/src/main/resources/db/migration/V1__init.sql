create table users (
    id bigint not null auto_increment,
    email varchar(255) not null,
    email_verified bit not null,
    image_url varchar(255),
    name varchar(255) not null,
    password varchar(255),
    provider varchar(255) not null,
    provider_id varchar(255),
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table vdf_event (
    id bigint not null auto_increment,
    age_categories varchar(255),
    date_end date,
    date_start date,
    description varchar(1000),
    location_coordinates varchar(255),
    location_name varchar(255),
    name varchar(255),
    organizer varchar(255),
    prizes varchar(255),
    registration_link varchar(255),
    registration_tax varchar(255),
    schedule varchar(255),
    technical_guide_link varchar(255),
    tracks varchar(255),
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table vdf_event_tag (
    id bigint not null auto_increment,
    category VARCHAR(20) default 'Miscellaneous',
    name varchar(20),
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table vdf_event_tags (
    vdf_event_id bigint not null,
    tags_id bigint not null,
    primary key (vdf_event_id, tags_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table vdf_organizer (
    id bigint not null auto_increment,
    logo_link varchar(255),
    name varchar(255),
    website_link varchar(255),
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table users add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table vdf_event_tags add constraint UK_a06v1q0xomjh3t9rj1s40qrop unique (tags_id);
alter table vdf_event_tags add constraint FK8gianffswbpm2fe9hs1mkq8s3 foreign key (tags_id) references vdf_event_tag (id);
alter table vdf_event_tags add constraint FKm4uqht2qhqopjitdlf6qx7tdm foreign key (vdf_event_id) references vdf_event (id);

insert into vdf_event_tag (category, name)
values
    ('MTB', 'XCM'),
    ('MTB', 'XCO'),
    ('MTB', 'Enduro'),
    ('MTB', 'Downhill'),
    ('MTB', 'Trial'),
    ('Sosea', 'Fond'),
    ('Sosea', 'Criterium'),
    ('Sosea', 'Contratimp'),
    ('Sosea', 'Randonneur'),
    ('Sosea', 'Fond'),
    ('Ciclocross', 'Ciclocross'),
    ('Miscellaneous', 'Virtual'),
    ('Miscellaneous', 'Copii'),
    ('Miscellaneous', 'Cupa Nationala'),
    ('Miscellaneous', 'Campionat National'),
    ('Miscellaneous', 'Stage Race');


