# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  comment_id                bigint not null,
  author                    varchar(255),
  posted_at                 timestamp,
  CONTENT                   varchar(1024),
  post_post_id              bigint,
  constraint pk_comment primary key (comment_id))
;

create table post (
  post_id                   bigint not null,
  title                     varchar(255),
  posted_at                 timestamp,
  CONTENT                   varchar(1024),
  author                    varchar(255),
  constraint pk_post primary key (post_id))
;

create table user (
  email                     varchar(255) not null,
  password                  varchar(255),
  fullname                  varchar(255),
  is_admin                  boolean,
  constraint pk_user primary key (email))
;

create table visits (
  id                        bigint not null,
  current_visit_count       bigint,
  constraint pk_visits primary key (id))
;

create sequence comment_seq;

create sequence post_seq;

create sequence user_seq;

create sequence visits_seq;

alter table comment add constraint fk_comment_post_1 foreign key (post_post_id) references post (post_id) on delete restrict on update restrict;
create index ix_comment_post_1 on comment (post_post_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists comment;

drop table if exists post;

drop table if exists user;

drop table if exists visits;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists comment_seq;

drop sequence if exists post_seq;

drop sequence if exists user_seq;

drop sequence if exists visits_seq;

