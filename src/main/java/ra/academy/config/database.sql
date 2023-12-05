create
database social_media;
use
social_media;
create table User
(
    user_id       bigint PRIMARY KEY auto_increment,
    full_name     varchar(100),
    sex           boolean,
    email         varchar(100),
    avatar_url    text,
    status        bit,
    date_of_birth date,
    created_at    datetime,
    updated_at    datetime,
    phone_number  varchar(11),
    role          bit,
    password      text
);

create table Post
(
    post_id     bigint PRIMARY KEY auto_increment,
    user_id     bigint,
    foreign key (user_id) references User (user_id),
    topic       varchar(100),
    content     text,
    post_time   datetime,
    tag_contact bigint,
    privacy     enum ('ONLYME','PUBLIC','FRIEND'),
    status      bit
);

create table Post_tag_user
(
    post_id bigint,
    foreign key (post_id) references Post (post_id),
    user_id bigint,
    foreign key (user_id) references User (user_id)
);

create table Post_topic
(
    post_id bigint,
    foreign key (post_id) references Post (user_id),
    topic   ENUM('PET', 'TRAVEL', 'MOVIE', 'GAME', 'MUSIC','FASHION')
);

CREATE TABLE Like_Post
(
    like_id  bigint PRIMARY KEY auto_increment,
    post_id  bigint,
    foreign key (post_id) references Post (post_id),
    user_id  bigint,
    foreign key (user_id) REFERENCES User (user_id),
    liked_at datetime
);

CREATE TABLE Comment
(
    comment_id       bigint PRIMARY KEY auto_increment,
    post_id          bigint,
    foreign key (post_id) references Post (post_id),
    user_id          bigint,
    foreign key (user_id) references User (user_id),
    content          varchar(255),
    comment_time     datetime,
    comment_reply_id bigint
);

CREATE TABLE Like_Comment
(
    like_id    bigint PRIMARY KEY auto_increment,
    comment_id bigint,
    foreign key (comment_id) references Comment (comment_id),
    user_id    bigint,
    foreign key (user_id) references User (user_id),
    liked_at   datetime
);

-- CREATE TABLE Share_Post (
--     share_id bigint PRIMARY KEY auto_increment,
--     post_id bigint,
--     foreign key (post_id) references Post(post_id),
--     user_id bigint,
--      foreign key (user_id) references User(user_id),
--     share_time datetime
-- );

create table file_url
(
    post_id  bigint,
    foreign key (post_id) references post (post_id),
    post_url text
);

-- CREATE TABLE Share_Comment (
--     share_id bigint PRIMARY KEY,
--     comment_id bigint,
--     foreign key (comment_id) references Comment(comment_id),
--     user_id bigint REFERENCES User(user_id),
--     share_time datetime
-- );

CREATE TABLE User_relationship
(
    user_id1 bigint,
    foreign key (user_id) references User (user_id),
    user_id2 bigint,
    foreign key (follower_id) references User (user_id),
    status   bit

);

CREATE TABLE Comment_like
(
    comment_id bigint,
    foreign key (comment_id) references Comment (comment_id),
    id_user    bigint REFERENCES User (user_id),
    like_time  datetime
);



-- insert into User(full_name ,email  ,status ,date_of_birth ,created_at ,phone_number ,roll )
-- values('trần đức châu','a@gmail.com',1,'1999-01-01',now(),'1234567891' ,1)

-- insert into Post (   post_id bigint PRIMARY KEY auto_increment,
--     user_id bigint,
--      (user_id) references User(user_id),
--      varchar(100),
--     text,
--      datetime,
--      bigint,
--      bit  ,
--      bit )

-- DELIMITER //
-- CREATE PROCEDURE test()
-- BEGIN
--
-- END; //
-- DELIMITER

// -------------------------

delimiter //
# saveUser
create procedure SaveUser(
    u_full_name varchar (100),
    u_email varchar (100),
    u_avatar_url text,
    u_date_of_birth date,
    u_phone_number varchar (11),
    u_password text
)
begin
insert into User (full_name, email, avatar_url, date_of_birth,
                  phone_number, password)
values ( u_full_name, u_email, u_avatar_url, u_date_of_birth
       , u_phone_number, u_password);
end
//

delimiter ;


delimiter
//
create procedure findAllUser()
begin
select *
from user;
end
//
delimiter ;

    delimiter
//
create procedure findAllUser()
begin
select *
from user;
end
//
delimiter ;


delimiter //

create procedure findById(u_id bigint)
begin
    select * from User where user_id = u_id;
end //
    delimiter;

create
    definer = root@localhost procedure EditUser(IN u_full_name varchar(100), IN u_email varchar(100),
                                                IN u_avatar_url text, IN u_date_of_birth date,
                                                IN u_phone_number varchar(11))
begin
    update User
    set full_name     = u_full_name,
        email         = u_email,
        avatar_url = COALESCE(u_avatar_url, avatar_url),
        date_of_birth = u_date_of_birth,
        phone_number  = u_phone_number,
        updated_at    = now()

    where email = u_email;
end;

create
    definer = root@localhost procedure findUserByName(IN u_limit int, IN u_offset int, IN name varchar(255))
begin
    SELECT * FROM user
    WHERE user.full_name LIKE concat( '%',name,'%')
    LIMIT u_limit
        OFFSET u_offset;
end;

create
    definer = root@localhost procedure banOrUnban(IN u_user_id mediumtext)
begin
    declare current_status bit;

    select status into current_status from user where user_id = u_user_id;

    if current_status = 1 THEN
        update user set status = 0 where user_id = u_user_id;
    else
        update user set status = 1 where user_id = u_user_id;
    end if;
end;

use social_media;
delimiter //
create procedure addReel(
    userId bigint,
    reelImg text,
    out idReel bigint
)
begin
    insert into reel (user_id , reel_url) values (userId, reelImg );
end //
delimiter ;