CREATE TABLE IF NOT EXISTS `user_groups` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `comment` varchar(255) DEFAULT NULL,
    `description` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);