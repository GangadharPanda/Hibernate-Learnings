
use hb_one_2_one;



create table instructor_details(
id INT auto_increment primary key,
youtube_channel_url varchar(100),
linkedin_profile_url varchar(50)
)
;


create table instructor(
id INT auto_increment primary key,
first_name varchar(50),
last_name varchar(50),
email varchar(100),
instructor_details_id int ,
FOREIGN KEY (instructor_details_id) REFERENCES instructor_details(id)
);

