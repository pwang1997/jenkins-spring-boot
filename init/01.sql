# create databases
CREATE DATABASE IF NOT EXISTS `testdb`;

# create root user and grant rights
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';