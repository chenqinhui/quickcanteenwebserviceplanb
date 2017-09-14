ALTER TABLE `company_info`
ADD COLUMN `busy_degree`  int(11) NULL DEFAULT -1 AFTER `end_time`,
ADD COLUMN `rating`  double NULL DEFAULT 0 AFTER `busy_degree`;