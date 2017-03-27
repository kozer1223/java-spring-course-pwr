create database weather;
use weather;

create user 'admin'@'localhost' identified by 'haslo';

grant all on weather.* to 'admin'@'localhost';
