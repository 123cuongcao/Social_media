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

use social_media;
create table user_relation
(
    id          bigint primary key auto_increment,
    sender_id   bigint,
    foreign key (sender_id) references user (user_id),
    receiver_id bigint,
    foreign key (receiver_id) references user (user_id),
    status enum('PENDING', 'ACCEPTED', 'REJECTED', 'BLOCK', 'FRIEND', 'CANCELED')

);

create
    definer = root@localhost procedure get_not_friend(IN in_currentUserId bigint)
BEGIN
    SELECT u.*
    FROM user u
    WHERE u.user_id not IN (SELECT DISTINCT CASE
                                                WHEN status = 'BLOCK' AND sender_id = in_currentUserId THEN receiver_id
                                                WHEN status = 'BLOCK' AND receiver_id = in_currentUserId THEN sender_id
                                                WHEN status = 'PENDING' AND sender_id = in_currentUserId
                                                    THEN receiver_id
                                                WHEN status = 'PENDING' AND receiver_id = in_currentUserId
                                                    THEN sender_id
                                                WHEN status = 'FRIEND' AND sender_id = in_currentUserId THEN receiver_id
                                                WHEN status = 'FRIEND' AND receiver_id = in_currentUserId THEN sender_id
                                                ELSE NULL
                                                END
                            FROM user_relation
                            WHERE (status IN ('BLOCK', 'PENDING', 'FRIEND') AND
                                   (sender_id = in_currentUserId OR receiver_id = in_currentUserId)))
      AND u.user_id != in_currentUserId;
END;
create
    definer = root@localhost procedure getRequestFriendFromUser(IN currentId mediumtext)
begin
    SELECT u.*
    FROM user u
    WHERE u.user_id IN (
        SELECT DISTINCT sender_id
        FROM user_relation
        WHERE receiver_id = currentId AND status = 'PENDING'
    );
end;
create
    definer = root@localhost procedure getRequestFriendFromUser(IN currentId mediumtext)
begin
    SELECT u.*
    FROM user u
    WHERE u.user_id IN (
        SELECT DISTINCT sender_id
        FROM user_relation
        WHERE receiver_id = currentId AND status = 'PENDING'
    );
end;
create
    definer = root@localhost procedure GetUserRelations(IN in_idSender bigint, IN in_idReceiver bigint)
BEGIN
    SELECT *
    FROM user_relation
    WHERE (sender_id = in_idSender AND receiver_id = in_idReceiver)
       OR (sender_id = in_idReceiver AND receiver_id = in_idSender);
END;
create
    definer = root@localhost procedure create_user_relation(IN in_sender_id bigint, IN in_receiver_id bigint,
                                                            IN in_status enum ('PENDING', 'ACCEPTED', 'REJECTED', 'CANCELED', 'BLOCK', 'FRIEND'))
begin
    insert into  user_relation (sender_id, receiver_id, status)
    values (in_sender_id, in_receiver_id, in_status);
end;

create
    definer = root@localhost procedure GetSentPendingFriendRequests(IN in_currentUserId bigint)
BEGIN
    SELECT u.*
    FROM user u
    WHERE u.user_id IN (
        SELECT DISTINCT receiver_id
        FROM user_relation
        WHERE sender_id = in_currentUserId AND status = 'PENDING'
    );
END;
create
    definer = root@localhost procedure change_user_relation(IN in_sender_id bigint, IN in_receiver_id bigint,
                                                            IN in_status enum ('PENDING', 'ACCEPTED', 'REJECTED', 'BLOCK', 'FRIEND', 'CANCELED'))
begin
    UPDATE user_relation
    SET status = in_status
    WHERE sender_id = in_sender_id AND receiver_id = in_receiver_id;

end;
create
    definer = root@localhost procedure getAllFriend(IN in_currentUserId bigint)
begin
    select u.* from user u where u.user_id in (
        select distinct sender_id from user_relation where sender_id = in_currentUserId
    )  and u.user_id != in_currentUserId;
end;

create
    definer = root@localhost procedure GetAllFriends(IN in_currentUserId bigint)
BEGIN
    SELECT u.*
    FROM user u
             JOIN user_relation ur ON (u.user_id = ur.sender_id OR u.user_id = ur.receiver_id)
    WHERE ur.status = 'FRIEND' AND (ur.sender_id = in_currentUserId OR ur.receiver_id = in_currentUserId) AND u.user_id != in_currentUserId;
END;
drop procedure get_not_friend;

create
    definer = root@localhost procedure get_not_friend(IN in_currentUserId bigint)
BEGIN
    SELECT u.*
    FROM user u
    WHERE u.user_id not IN (
        SELECT DISTINCT CASE
                            WHEN status = 'BLOCK' AND sender_id = in_currentUserId THEN receiver_id
                            WHEN status = 'BLOCK' AND receiver_id = in_currentUserId THEN sender_id
                            WHEN status = 'PENDING' AND sender_id = in_currentUserId THEN receiver_id
                            WHEN status = 'PENDING' AND receiver_id = in_currentUserId THEN sender_id
                            WHEN status = 'FRIEND' AND sender_id = in_currentUserId THEN receiver_id
                            WHEN status = 'FRIEND' AND receiver_id = in_currentUserId THEN sender_id
                            WHEN status = 'CANCELED' AND sender_id = in_currentUserId THEN receiver_id
                            WHEN status = 'CANCELED' AND receiver_id = in_currentUserId THEN sender_id
                            ELSE NULL
                            END
        FROM user_relation
        WHERE (status IN ('BLOCK', 'PENDING', 'FRIEND') AND (sender_id = in_currentUserId OR receiver_id = in_currentUserId))
    ) AND u.user_id != in_currentUserId;
END;
create
    definer = root@localhost procedure deleteUserRelation(IN idSender bigint, IN idReceiver bigint)
begin
    delete from user_relation where sender_id = idSender and receiver_id = idReceiver;
end;