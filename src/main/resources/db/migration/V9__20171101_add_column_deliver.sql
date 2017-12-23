ALTER TABLE `user_info`
  ADD COLUMN `deliver` TINYINT(1) DEFAULT 0
  AFTER `points`;
ALTER TABLE `order`
  ADD COLUMN `deliver_man_id` INT NOT NULL DEFAULT 0
  AFTER `timeslot_id`,
  ADD COLUMN `deliver_price` DOUBLE NOT NULL DEFAULT 0.0
  AFTER `deliver_man_id`,
  ADD COLUMN `location_id` INT NOT NULL DEFAULT 0
  AFTER `deliver_price`;
UPDATE `order`
SET `deliver_man_id` = 0, `deliver_price` = 0.0, `location_id` = 0;
CREATE TABLE `location` (
  `location_id` INT    NOT NULL AUTO_INCREMENT,
  `user_id`     INT    NOT NULL,
  `address`     TEXT   NOT NULL,
  `longitude`   DOUBLE NOT NULL DEFAULT 0.0,
  `latitude`    DOUBLE NOT NULL DEFAULT 0.0,
  PRIMARY KEY (`location_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
