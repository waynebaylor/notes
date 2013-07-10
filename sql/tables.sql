SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `note`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `tag`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE IF NOT EXISTS `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `context` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `authority` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note_id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `note_id` (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `enabled` char(1) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


ALTER TABLE `note`
  ADD CONSTRAINT `note_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `role`
  ADD CONSTRAINT `role_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `tag`
  ADD CONSTRAINT `tag_note_fk` FOREIGN KEY (`note_id`) REFERENCES `note` (`id`);
  
SET FOREIGN_KEY_CHECKS=1;

