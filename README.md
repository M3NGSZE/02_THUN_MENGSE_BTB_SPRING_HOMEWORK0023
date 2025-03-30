# Database Schema

## **Create Tables**
```sql

-- create database
create database homework003;

-- Create venues table
create table venues(
    venue_id serial primary key,
    venue_name varchar(150) not null ,
    location varchar(200) not null
);

-- Create events table
create table events(
    event_id serial primary key,
    event_name varchar(150) not null ,
    event_date timestamp not null ,
    venue_id int,
    foreign key (venue_id) references venues(venue_id) on delete cascade
);

-- Create attendee table
create table attendee(
    attendee_id serial primary key,
    attendee_name varchar(150) not null ,
    email varchar(150) unique not null
);

-- Create event_attendee table
create table event_attendee(
    id serial primary key,
    event_id int,
    foreign key (event_id) references events(event_id) on delete cascade,
    attendee_id int,
    foreign key (attendee_id) references attendee(attendee_id) on delete cascade
);
