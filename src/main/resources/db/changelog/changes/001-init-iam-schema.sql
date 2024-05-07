CREATE TABLE IF NOT EXISTS permissions (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL UNIQUE,
    `resource` varchar(255) NOT NULL,
    `comment` varchar(255) DEFAULT NULL,
    `action` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);