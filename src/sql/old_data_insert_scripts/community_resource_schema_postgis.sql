
-- ================================
-- COMMUNITY RESOURCE DATABASE SCHEMA (POSTGIS VERSION)
-- ================================
-- Ensure PostGIS Extension is Installed
CREATE EXTENSION IF NOT EXISTS postgis;

-- Create Schema
CREATE SCHEMA IF NOT EXISTS swegrg25;

-- Set search path to include new schema and PostGIS
SET search_path TO swegrg25, public, postgis;

--Create Sequence statemnets
CREATE SEQUENCE IF NOT EXISTS swegrg25.org_id_seq;
CREATE SEQUENCE IF NOT EXISTS swegrg25.vo_id_seq;
CREATE SEQUENCE IF NOT EXISTS swegrg25.cs_id_seq;
CREATE SEQUENCE IF NOT EXISTS swegrg25.fb_id_seq;
CREATE SEQUENCE IF NOT EXISTS swegrg25.shelters_cc_id_seq;
CREATE SEQUENCE IF NOT EXISTS swegrg25.med_id_seq;
CREATE SEQUENCE IF NOT EXISTS swegrg25.community_centers_cc_id_seq;
CREATE SEQUENCE IF NOT EXISTS swegrg25.ev_id_seq;
CREATE SEQUENCE IF NOT EXISTS swegrg25.user_id_seq;

-- CREATION OF MAIN TABLES
/*
-- ORGANIZER TABLE
CREATE TABLE IF NOT EXISTS "ORGANIZER" (
    OUSER_ID   SERIAL PRIMARY KEY,
    OUSER_Name VARCHAR,
    email      VARCHAR UNIQUE,
    phone      VARCHAR,
    USER_TYPE  VARCHAR CHECK (USER_TYPE IN ('Volunteer', 'Organizer'))
);
ALTER TABLE "ORGANIZER" OWNER TO postgres;
*/

-- ORGANIZATION TABLE
create table "Organization"
(
    org_id       integer default nextval('swegrg25.org_id_seq'::regclass) not null
        primary key,
    org_name     varchar,
    address      varchar,
    contact_info varchar
);
alter table "Organization"
    owner to postgres;

-- VOLUNTEER OPPORTUNITIES TABLE
create table "Volunteer_Opportunities"
(
    vo_id               integer default nextval('swegrg25.vo_id_seq'::regclass) not null
        primary key,
    vo_name             varchar,
    org_id              integer
        constraint volunteer_opportunities_table_org_fk
            references "Organization",
    vo_description      text,
    event_date          timestamp,
    ongoing             boolean,
    requirements        json,
    coordinate_location text,
    address             varchar,
    contact_info        varchar,
    location_notes      text,
    permanent           varchar DEFAULT 'volunteer'
        constraint volunteer_opprtunities_permanent_check
            check (permanent in ('event', 'volunteer', 'place'))

);
alter table "Volunteer_Opportunities"
    owner to postgres;

-- CLOTHING STORES TABLE
create table "Clothing_Stores"
(
    cs_id               integer default nextval('swegrg25.cs_id_seq'::regclass) not null
        constraint clothing_stores_table_pk
            primary key,
    name                varchar,
    org_id              integer
        constraint clothing_stores__organization__org_id_fk
            references "Organization",
    price_range         varchar
        constraint "Clothing_Stores_price_range_check"
            check ((price_range)::text = ANY
                   ((ARRAY ['Free'::character varying, 'Low-Cost'::character varying, 'Mixed'::character varying])::text[])),
    hours_of_operation  json,
    address             varchar,
    contact_info        varchar,
    coordinate_location text,
    location_notes      text,
    permanent           varchar DEFAULT 'place'
        constraint clothing_stores_permanent_check
            check (permanent in ('event', 'volunteer', 'place'))
);

alter table "Clothing_Stores"
    owner to postgres;

-- FOOD BANKS TABLE
create table "FoodBanks"
(
    fb_id               integer default nextval('swegrg25.fb_id_seq'::regclass) not null
        constraint foodbanks_table_pk
            primary key,
    name                varchar,
    org_id              integer
        constraint foodbanks__organization__org_id_fk
            references "Organization",
    accommodation_limit integer,
    hours_of_operation  json,
    coordinate_location text,
    address             varchar,
    contact_info        varchar,
    location_notes      json,
    permanent           varchar DEFAULT 'place'
        constraint foodbanks_permanent_check
            check (permanent in ('event', 'volunteer', 'place'))
);
alter table "FoodBanks"
    owner to postgres;

-- SHELTERS TABLE
create table "Shelters"
(
    sh_id               integer default nextval('swegrg25.shelters_cc_id_seq'::regclass) not null
        constraint shelters_table_pk
            primary key,
    name                varchar,
    org_id              integer
        constraint shelters__organization__org_id_fk
            references "Organization",
    space_available     integer,
    maximum_capacity    integer,
    hours_of_operation  json,
    address             varchar,
    contact_info        varchar,
    coordinate_location text,
    location_notes      json,
   permanent           varchar DEFAULT 'place'
        constraint shelters_permanent_check
            check (permanent in ('event', 'volunteer', 'place'))
);
alter table "Shelters"
    owner to postgres;

-- MEDICAL CENTERS TABLE
create table "Med_Centers"
(
    med_id              integer default nextval('swegrg25.med_id_seq'::regclass) not null
        constraint med_centers_table_pk
            primary key,
    name                varchar,
    org_id              integer
        constraint med_centers__organization__org_id_fk
            references "Organization",
    average_wait_time   integer,
    address             varchar,
    contact_info        varchar,
    services_provided   json,
    coordinate_location text,
    location_notes      json,
   permanent           varchar DEFAULT 'place'
        constraint med_centers_permanent_check
            check (permanent in ('event', 'volunteer', 'place'))
);
alter table "Med_Centers"
    owner to postgres;

-- COMMUNITY CENTERS TABLE
create table "Community_Centers"
(
    cc_id               integer default nextval('swegrg25.community_centers_cc_id_seq'::regclass) not null
        constraint community_centers_table_pk
            primary key,
    name                varchar,
    org_id              integer
        constraint community_centers__organization__org_id_fk
            references "Organization",
    hours_of_operation  json,
    coordinate_location text,
    address             varchar,
    contact_info        varchar,
    offered_services    json,
    location_notes      text,
   permanent           varchar DEFAULT 'place'
        constraint community_centers_permanent_check
            check (permanent in ('event', 'volunteer', 'place'))
);
alter table "Community_Centers"
    owner to postgres;

-- COMMUNITY EVENTS TABLE
create table "ENDUSER"
(
    user_id      integer default nextval('swegrg25.user_id_seq'::regclass) not null
        constraint enduser_pkey_pk
            primary key,
    first_name   varchar,
    last_name    varchar,
    username     varchar,
    email_linked varchar,
    password     varchar,
    address      varchar,
    city         varchar,
    state        varchar,
    zip          varchar(5)
);
alter table "ENDUSER"
    owner to postgres;

create table "Community_Events"
(
    ev_id               integer default nextval('swegrg25.ev_id_seq'::regclass) not null
        constraint community_event_table_pk
            primary key,
    name                varchar,
    description         text,
    event_date          timestamp,
    user_id             integer
        constraint community_event_table_enduser_fk
            references "ENDUSER",
    address             varchar,
    contact_info        varchar,
    category            text,
    attendee_count      integer,
    coordinate_location text,
    location_notes      text,
    permanent           varchar DEFAULT 'event'
        constraint community_events_permanent_check
            check (permanent in ('event', 'volunteer', 'place'))
);
alter table "Community_Events"
    owner to postgres;

create table "USER_TYPES"
(
    user_type        varchar not null
        constraint user_types_table_pk
            primary key,
    type_abbrev      varchar,
    type_description text
);

create table "LOG_User_Login"
(
    user_id         integer default nextval('swegrg25.user_id_seq'::regclass) not null
        constraint log_user_login_enduser_fk
            references "ENDUSER",
    timelogged      timestamp,
    account_name    varchar(255)                                              not null,
    hashed_password varchar(255)                                              not null,
    security_token  varchar(255),
    user_type       text
        constraint "LOG_User_Login_user_type_check"
            check (user_type = ANY (ARRAY ['EndUser'::text, 'Volunteer'::text, 'Organizer'::text, 'Admin'::text]))
);
alter table "LOG_User_Login"
    owner to postgres;

create table "LOG_User_Info_Queue"
(
    user_id      integer default nextval('swegrg25.user_id_seq'::regclass) not null
        constraint log_user_info_queue_enduser_fk
            references "ENDUSER",
    timelogged   timestamp,
    first_name   varchar,
    last_name    varchar,
    username     varchar,
    email_linked varchar,
    password     varchar,
    address      varchar,
    city         varchar,
    state        varchar,
    zip          varchar(5)
);
alter table "LOG_User_Info_Queue"
    owner to postgres;



create table "Volunteers"
(
    vo_id   integer default nextval('swegrg25.vo_id_seq'::regclass) not null
        constraint volunteers_pk
            primary key
        constraint volunteers_volunteer_opportunities_vo_id_fk
            references "Volunteer_Opportunities",
    user_id integer                                                 not null
        constraint volunteers_enduser_user_id_fk
            references "ENDUSER",
     permanent varchar DEFAULT 'volunteer',
    CONSTRAINT volunteers_permanent_check CHECK (permanent IN ('event', 'volunteer', 'place'))
);
alter table "Volunteers"
    owner to postgres;


comment on table "Volunteers" is 'This would map what volunteers are connected to what events. Junction Table: many-to-many';

