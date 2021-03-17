DROP SCHEMA IF EXISTS `room_service`;

CREATE SCHEMA IF NOT EXISTS `room_service`;

USE `room_service` ;



CREATE TABLE `room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bulb` bit(1) DEFAULT 0,
  `name` varchar(255) NOT NULL UNIQUE,
  `country` varchar(255) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `room` (`id`, `name`, `country`) VALUES ('1', 'Комната Романа', 'Belarus');
INSERT INTO `room` (`id`, `name`, `country`) VALUES ('2', 'Комната Романа2', 'Belarus');
INSERT INTO `room` (`id`, `name`, `country`) VALUES ('3', 'Комната Томаса', 'Poland');
INSERT INTO `room` (`id`, `name`, `country`) VALUES ('4', 'Комната Владислава', 'Russia');

