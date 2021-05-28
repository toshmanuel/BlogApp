drop DATABASE if exists blogdb;
CREATE DATABASE blogdb;


-- drop user if exists 'bloguer' @'localhost';
create user if not exists 'bloguser'@'localhost' identified by 'ToshManuel@123';
grant all privileges on blogdb.* to 'bloguser'@'localhost';
flush privileges;