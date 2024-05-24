CREATE TABLE IF NOT EXISTS `user_group_permission_assn`
(
    `user_group_id` bigint NOT NULL,
    `permission_id` bigint NOT NULL,
    KEY `FK_PERMISSION_ID` (`permission_id`),
    KEY `FK_USER_GROUP_ID` (`user_group_id`),
    CONSTRAINT `FK_PERMISSION_ID` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`) ON DELETE CASCADE ,
    CONSTRAINT `FK_USER_GROUP_ID` FOREIGN KEY (`user_group_id`) REFERENCES `user_groups` (`id`) ON DELETE CASCADE
)