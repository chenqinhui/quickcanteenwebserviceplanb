ALTER TABLE `user_comment`
ADD COLUMN `comment_time`  datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP AFTER `rating`;