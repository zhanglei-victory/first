/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.106
Source Server Version : 50022
Source Host           : 192.168.1.106:3306
Source Database       : ycjc

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2016-04-20 20:49:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tbl_authority`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_authority`;
CREATE TABLE `tbl_authority` (
  `id` int(11) default NULL,
  `authid` varchar(11) default NULL,
  `priauthid` varchar(11) default NULL,
  `authname` varchar(50) default NULL,
  `sysflag` varchar(1) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_authority
-- ----------------------------
INSERT INTO tbl_authority VALUES ('1', '1', '999999999', '实时监控', '1');
INSERT INTO tbl_authority VALUES ('11', '11', '1', 'GIS模块', '1');
INSERT INTO tbl_authority VALUES ('12', '12', '1', '告警监控', '1');
INSERT INTO tbl_authority VALUES ('2', '2', '999999999', '统计分析', '1');
INSERT INTO tbl_authority VALUES ('21', '21', '2', 'PM2.5曲线', '1');
INSERT INTO tbl_authority VALUES ('22', '22', '2', 'PM10曲线', '1');
INSERT INTO tbl_authority VALUES ('3', '3', '999999999', '配置管理', '1');
INSERT INTO tbl_authority VALUES ('31', '31', '3', '扬尘点管理', '1');
INSERT INTO tbl_authority VALUES ('5', '5', '999999999', '网关配置', '1');
INSERT INTO tbl_authority VALUES ('51', '51', '5', '网关绑定', '1');
INSERT INTO tbl_authority VALUES ('4', '4', '999999999', '权限管理', '1');
INSERT INTO tbl_authority VALUES ('41', '41', '4', '用户管理', '1');
INSERT INTO tbl_authority VALUES ('42', '42', '4', '角色管理', '1');
INSERT INTO tbl_authority VALUES ('23', '23', '2', 'PM2.5报表', '1');
INSERT INTO tbl_authority VALUES ('24', '24', '2', 'PM10报表', '1');
INSERT INTO tbl_authority VALUES ('25', '25', '2', '历史数据', '1');

-- ----------------------------
-- Table structure for `tbl_farm`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_farm`;
CREATE TABLE `tbl_farm` (
  `farmid` int(11) NOT NULL auto_increment,
  `farmname` varchar(50) default NULL,
  `farmarea` varchar(10) default NULL,
  `farmlinkman` varchar(15) default NULL,
  `farmphone` varchar(15) default NULL,
  `farmaddress` varchar(100) default NULL,
  `temp` varchar(10) default NULL,
  `humidity` varchar(10) default NULL,
  `phvalue` varchar(10) default NULL,
  `light` varchar(10) default NULL,
  `lightstatus` varchar(1) default NULL,
  `fanstatus` varchar(1) default NULL,
  `remark` varchar(500) default NULL,
  `healthness` varchar(1) default NULL,
  `lng` varchar(20) default NULL,
  `lat` varchar(20) default NULL,
  `licenseid` varchar(20) default NULL,
  `insdatetime` varchar(19) default NULL,
  `upddatetime` varchar(19) default NULL,
  `MarkID` varchar(30) default NULL,
  `breednumber` varchar(20) default NULL,
  PRIMARY KEY  (`farmid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_farm
-- ----------------------------
INSERT INTO tbl_farm VALUES ('1', '河南高新养殖场', '1000', '张明华', '18854132002', '河南省郑州市高新区顺华路100号', '17', '34', '8', '15', '0', '0', '                                                本养殖场拥有众多丰富的饲养员，经验比较丰富，对待牲畜养殖比较专业。            \r\n            \r\n            \r\n            \r\n            ', '1', '113.264655', '35.920464', '11111111', '2015-12-26 08:25:36', '2015-12-30 15:24:52', '15234324', null);
INSERT INTO tbl_farm VALUES ('2', '大华养殖场', '25', '张梦龙', '18645565987', '河南省焦作市城府村大华养殖场', '22', '23', '5', '49', '0', '0', null, '0', '113.254306', '35.120464', '34234234', '2015-12-26 08:25:36', '2015-12-30 15:56:03', '5765754', '3');
INSERT INTO tbl_farm VALUES ('6', '秦岭养殖场', '100', '林志玲', '18645565955', '河南省焦作市秦岭镇秦岭养殖场', '30', '2', '2', '21', '1', '0', null, '1', '113.264655', '36.120464', '32423423', '2015-12-26 08:25:36', '2015-12-30 15:56:03', '342232435', null);
INSERT INTO tbl_farm VALUES ('9', '合作社养殖场', '100', '张玲玲', '18756432348', '河南省焦作市舜华镇舜华养殖场', '31', '34', '5', '321', '1', '0', null, '1', '113.235362', '35.331362', '18612565955', '2015-12-31 10:00:01', '2015-12-31 17:41:25', '1234556', null);
INSERT INTO tbl_farm VALUES ('10', '雨滴养殖场', '100', '张玲玲', '18756432348', '河南省焦作市舜华镇雨滴路32号', '31', '35', '6', '322', '1', '0', null, '1', '114.264655', '35.160464', '18435665955', '2015-12-31 10:23:01', '2015-12-30 15:56:03', '23456', null);
INSERT INTO tbl_farm VALUES ('11', '美莲养殖场', '100', '刘于华', '18756432348', '河南省焦作市美莲镇雨滴路12314号', '26', '35', '7', '232', '1', '0', null, '1', '113.464655', '35.420464', '13233456565', '2015-12-31 10:23:01', '2015-12-30 15:56:03', '1234567', null);
INSERT INTO tbl_farm VALUES ('12', '万达养殖场', '1000', '王建林', '1875643248', '河南省焦作市万达镇定好路12314号', '26', '36', '6', '324', '1', '0', null, '1', '113.564655', '35.720464', '965329034565', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '1234787', null);
INSERT INTO tbl_farm VALUES ('13', '罗切牙养殖场', '1000', '王建林', '1875643248', '河南省焦作市罗切牙镇定好路12314号', '26', '37', '5', '34', '1', '0', null, '1', '113.274655', '35.120464', '098454565', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '97563443', null);
INSERT INTO tbl_farm VALUES ('14', '董家沟养殖场', '1000', '王建美', '1875643248', '河南省焦作市董家沟镇定好路1714号', '25', '38', '6', '543', '1', '0', null, '1', '113.284655', '35.820464', '65523754565', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '436676657', null);
INSERT INTO tbl_farm VALUES ('15', '吴家沟养殖场', '1000', '吴建美', '1875643248', '河南省焦作市吴家沟镇定好路1714号', '24', '39', '8', '23', '1', '0', null, '1', '113.294655', '35.631362', '22333674565', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '23425', null);
INSERT INTO tbl_farm VALUES ('17', '王家沟养殖场', '1000', '王里美', '1875643248', '河南省焦作市王家沟定好路1714号', '22', '24', '7', '54', '1', '0', null, '1', '113.264655', '35.231362', '9954115566', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '324324', null);
INSERT INTO tbl_farm VALUES ('18', '张家沟养殖场', '1000', '申满', '1875643248', '河南省焦作市张家沟定好路3214号', '24', '45', '7', '67', '1', '0', null, '1', '113.264655', '35.631362', '995212146', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '4656456', null);
INSERT INTO tbl_farm VALUES ('19', '定家沟养殖场', '1000', '定满', '1875643248', '河南省焦作市定家沟定好路3214号', '25', '57', '6', '67', '1', '0', null, '1', '113.344655', '35.731362', '555212146', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '123645', null);
INSERT INTO tbl_farm VALUES ('20', '湖曲沟养殖场', '1000', '湖满', '1875643248', '河南省焦作市湖曲沟定好路3324号', '26', '56', '5', '78', '1', '0', null, '1', '113.444655', '35.931362', '555323446', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '546545324', null);
INSERT INTO tbl_farm VALUES ('21', '腾家沟养殖场', '1000', '六四', '1875643248', '河南省焦作市腾家定好路3324号', '29', '46', '4', '89', '1', '0', null, '1', '113.374655', '35.031362', '553456723446', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '2343435646', null);
INSERT INTO tbl_farm VALUES ('22', '刘家沟养殖场', '1000', '刘无能', '13435643248', '河南省焦作市刘家沟定好路3324号', '24', '36', '6', '79', '1', '0', null, '1', '113.394655', '35.361362', '90329446', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '566557567', null);
INSERT INTO tbl_farm VALUES ('23', '菜家沟养殖场', '1000', '菜鸟', '13432345248', '河南省焦作市菜家沟定好路3324号', '26', '34', '4', '56', '1', '0', null, '1', '113.54655', '35.391362', '902349446', '2015-12-31 10:34:01', '2015-12-30 15:56:03', '12566765', null);
INSERT INTO tbl_farm VALUES ('24', '兴发养殖场', '1000', '张明明', '16098762345', '河南省焦作市万家沟村22号', '25', '34', '3', '47', '1', '0', null, '1', '113.394655', '35.301362', '206303028', '2016-01-02 11:19:52', '2016-03-22 18:39:14', '370987', null);
INSERT INTO tbl_farm VALUES ('25', '聖舜养殖场', '90', '彰武路', '1676543937', '河南省焦作市兴业大道33号', '24', '37', '2', '35', '0', '0', null, '1', '113.844655', '35.931362', '209384756', '2016-01-02 11:23:09', '2015-12-30 15:56:03', '9987654509', null);

-- ----------------------------
-- Table structure for `tbl_role`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `roleid` int(11) NOT NULL,
  `rolename` varchar(50) default NULL,
  PRIMARY KEY  (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO tbl_role VALUES ('1', '系统管理员');
INSERT INTO tbl_role VALUES ('23', '养殖场管理员');

-- ----------------------------
-- Table structure for `tbl_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_authority`;
CREATE TABLE `tbl_role_authority` (
  `roleauthid` int(11) NOT NULL,
  `roleid` int(11) default NULL,
  `authid` varchar(11) default NULL,
  PRIMARY KEY  (`roleauthid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role_authority
-- ----------------------------
INSERT INTO tbl_role_authority VALUES ('1', '1', '11');
INSERT INTO tbl_role_authority VALUES ('2', '1', '12');
INSERT INTO tbl_role_authority VALUES ('3', '1', '21');
INSERT INTO tbl_role_authority VALUES ('4', '1', '22');
INSERT INTO tbl_role_authority VALUES ('5', '1', '31');
INSERT INTO tbl_role_authority VALUES ('6', '1', '41');
INSERT INTO tbl_role_authority VALUES ('7', '1', '42');
INSERT INTO tbl_role_authority VALUES ('8', '1', '51');
INSERT INTO tbl_role_authority VALUES ('9', '1', '23');
INSERT INTO tbl_role_authority VALUES ('10', '1', '24');
INSERT INTO tbl_role_authority VALUES ('11', '1', '25');

-- ----------------------------
-- Table structure for `tbl_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_userinfo`;
CREATE TABLE `tbl_userinfo` (
  `userid` int(11) NOT NULL,
  `userroleid` int(11) default NULL,
  `userrealname` varchar(20) default NULL,
  `username` varchar(20) default NULL,
  `userpwd` varchar(32) default NULL,
  `usersex` varchar(1) default NULL,
  `userposition` varchar(50) default NULL,
  `userphone` varchar(15) default NULL,
  `useremail` varchar(30) default NULL,
  `userstatus` varchar(1) default NULL,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_userinfo
-- ----------------------------
INSERT INTO tbl_userinfo VALUES ('1', '1', '超级管理员', 'admin', '21218CCA77804D2BA1922C33E0151105', '1', '济南', '15589943201', '961213558@qq.com', '0');
INSERT INTO tbl_userinfo VALUES ('19', '23', '养殖场操作员', 'yangzhichang', '21218CCA77804D2BA1922C33E0151105', '1', 'yangzhichang', '15589965252', 'yangzhichang@sina.com', '0');

-- ----------------------------
-- Procedure structure for `PIG_BREED_PROC`
-- ----------------------------
DROP PROCEDURE IF EXISTS `PIG_BREED_PROC`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `PIG_BREED_PROC`(
    -- rfid
    IN rfidid VARCHAR(32),
    -- 内容
    IN content_dd VARCHAR(100),
    -- 时间
    IN date_dd DATETIME, 
    -- 数据库名称 
    IN dbName VARCHAR(19),
    -- 执行结果标示
    OUT opFlag INT)
BEGIN 
/*********************************
  名称：pigbreed_rfid唯一标识
  功能描述：创建小猪养殖记录历史数据存储表
  修订记录：2015-08-18 创建此存储过程
 **********************************/
	-- SQL语句
  DECLARE v_sql VARCHAR(300);
  -- 表名
  DECLARE t_name VARCHAR(100);
  -- 数据库名称
  DECLARE v_dbName VARCHAR(50);
  SET opFlag = -1;
	-- 拼接记录表表名
  SET t_name = CONCAT('pigbreed_',rfidid);
  /************  --查看记录表 是否存在，不存在，就创建  ***********/
  SET v_sql = CONCAT('select count(TABLE_NAME) from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA="',dbName,'" and TABLE_NAME="',t_name,'" into @tblExist');
  SET @ms = v_sql; 
  PREPARE s1 FROM @ms;  
  EXECUTE s1;
  DEALLOCATE PREPARE s1;
  IF @tblExist = 1 THEN
		-- 开启事务控制
		START TRANSACTION;
		-- 往表中插入数据
	
		SET v_sql = CONCAT('insert into ',t_name,' (content,rev_date) values("',content_dd,'",','"',date_dd,'")');
		SET @ms = v_sql; 
		PREPARE s1 FROM @ms;  
		EXECUTE s1;
		DEALLOCATE PREPARE s1;
		
		-- 提交事务
		COMMIT;
		SET opFlag = 1;
		SELECT opFlag;
  ELSE
		-- 建立表
		SET v_sql = CONCAT('create table ',t_name,' (redId int(10) NOT NULL AUTO_INCREMENT,content varchar(100),rev_date DATETIME,PRIMARY KEY (redId))');
		SET @createTblSql = v_sql;
		PREPARE tmpCreate FROM @createTblSql;
		EXECUTE tmpCreate;
		DEALLOCATE PREPARE tmpCreate;
			-- 开启事务控制
			START TRANSACTION;
			-- 往表中插入数据
			SET v_sql = '';
			SET v_sql = CONCAT('insert into ',t_name,' (content,rev_date) values("',content_dd,'",','"',date_dd,'")');
			SET @insertSql = v_sql;
			PREPARE tmpInsert FROM @insertSql;
			EXECUTE tmpInsert;
			DEALLOCATE PREPARE tmpInsert;
			
			-- 提交事务
			COMMIT;
			SET opFlag = 1;
			SELECT opFlag;
		
	END IF;
 END
;;
DELIMITER ;
