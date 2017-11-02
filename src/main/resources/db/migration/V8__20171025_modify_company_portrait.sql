ALTER TABLE `company_info`
ADD COLUMN `company_portrait`  varchar(255) NULL DEFAULT '' AFTER `rating`;

UPDATE `company_info` SET `company_portrait`= '' ;