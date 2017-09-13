-- ----------------------------
-- Records of time_slot
-- ----------------------------
INSERT INTO `time_slot` VALUES ('1', '00:00:00', '00:00:00');
INSERT INTO `time_slot` VALUES ('2', '11:00:00', '11:30:00');
INSERT INTO `time_slot` VALUES ('3', '11:30:00', '11:45:00');
INSERT INTO `time_slot` VALUES ('4', '11:45:00', '12:00:00');
INSERT INTO `time_slot` VALUES ('5', '12:00:00', '12:30:00');
INSERT INTO `time_slot` VALUES ('6', '12:30:00', '13:00:00');
INSERT INTO `time_slot` VALUES ('7', '16:30:00', '17:00:00');
INSERT INTO `time_slot` VALUES ('8', '17:00:00', '18:00:00');
INSERT INTO `time_slot` VALUES ('9', '18:00:00', '19:00:00');
-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '热菜', '1');
INSERT INTO `type` VALUES ('2', '冷菜', '1');
INSERT INTO `type` VALUES ('3', '套餐', '1');
INSERT INTO `type` VALUES ('4', '酒水', '1');
INSERT INTO `type` VALUES ('5', '主食', '1');
-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '10152510261', '123456', '1', 'yrj', '叶润杰', '17701689978', '2015-01-01', '计算机科学与软件工程', '华东师范大学', '0', '0');

INSERT INTO `company_info` VALUES ('1', '河西食堂', 'hexishitang', '12345', '2017-05-01 21:57:26', '2017-06-18 21:57:46');
-- ----------------------------
-- Records of dishes_type
-- ----------------------------
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('1', '1');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('2', '1');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('3', '1');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('4', '2');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('5', '2');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('6', '2');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('7', '2');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('8', '5');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('9', '3');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('10', '3');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('11', '4');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('12', '4');
INSERT INTO `dishes_type` (`dishes_id`, `type_id`) VALUES ('13', '3');
-- ----------------------------
-- Records of dishes
-- ----------------------------
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '5', '这是番茄炒蛋1', '0', '1', '6', '1', '0', '番茄炒蛋', '2017-08-17 22:10:50', '0',  'fileResources/1/fanqiechaodan.jpg');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '4', '这是鱼香肉丝', '0', '1', '10', '2', '0', '鱼香肉丝', '2017-02-21 21:40:05', '0',  'fileResources/2/yuxiangrousi.jpg');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '3', '这是炒青菜', '0', '1', '5', '3', '0', '炒青菜', '2017-02-21 21:40:05', '0',  'fileResources/3/chaoqingcai.jpg');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '5', '这是拌黄瓜', '0', '1', '5', '4', '0', '拌黄瓜', '2017-02-21 21:40:05', '0',  'fileResources/4/banhuanggua.jpg');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '4', '这是凉拌牛肉', '0', '1', '15', '5', '0', '凉拌牛肉', '2017-02-21 21:40:05', '0',  'fileResources/5/liangbanniurou.jpg');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '3', '这是麻婆豆腐', '0', '1', '5', '6', '0', '麻婆豆腐', '2017-02-21 21:40:05', '0',  'fileResources/6/mapodoufu.jpg');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '3', '这是京酱肉丝', '0', '1', '6', '7', '0', '京酱肉丝', '2017-02-21 21:40:05', '0',  'fileResources/7/jingjiangrousi.jpg');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '4', '这是炸酱面', '0', '1', '8.5', '8', '0', '炸酱面', '2017-08-22 10:46:53', '0',  'fileResources/8/zhajiangmian.jpg');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '1', '包含三个素菜，一份米饭1', '0', '1', '10.5', '9', '0', '全素套餐1', '2017-08-22 10:45:52', '0', 'fileResources/9/quansutaocan.png');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '1', '包含鱼香肉丝，时蔬与米饭', '0', '1', '15', '10', '0', '鱼香肉丝套餐', '2017-02-21 21:40:05', '0',  'fileResources/10/yuxiangrousitaocan.png');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '0', '这是农夫山泉', '0', '1', '2', '11', '0', '农夫山泉', '2017-02-21 21:40:05', '0',  'fileResources/11/nongfushanquan.jpg');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '0', '这是可口可乐', '0', '1', '4', '12', '0', '可口可乐', '2017-02-21 21:40:05', '0',  'fileResources/12/kekoukele.jpg');
insert into `dishes` ( `rating`, `dishes_introduce`, `collect_num`, `company_id`, `price`, `dishes_id`, `comment_num`, `dishes_name`, `publish_time`, `praise_num`,  `diagrammatic_sketch_address`) values ( '5', '包含糖醋排骨，时蔬与米饭', '0', '1', '15', '13', '0', '糖醋排骨套餐', '2017-02-21 21:40:05', '0',  'fileResources/13/tangcupaigu.png');