ALTER TABLE `company_time_slot`
MODIFY COLUMN `people_number`  int(11) NOT NULL DEFAULT 0 AFTER `time_slot_id`,
MODIFY COLUMN `max_people_number`  int(11) NOT NULL DEFAULT 300 AFTER `people_number`,
MODIFY COLUMN `busy_state`  int(11) NOT NULL DEFAULT -1 AFTER `max_people_number`;
