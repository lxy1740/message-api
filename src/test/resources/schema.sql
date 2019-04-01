SET MODE MYSQL;
CREATE SCHEMA IF NOT EXISTS "smartcity";

DROP TABLE "device_install_zone" IF EXISTS;
DROP TABLE "geo_region" IF EXISTS;
DROP TABLE "geo_way" IF EXISTS;
DROP TABLE "geo_way_region" IF EXISTS;
DROP TABLE "device_position" IF EXISTS;
DROP TABLE "device_position_type" IF EXISTS;
DROP TABLE "device_detail" IF EXISTS;
DROP TABLE "device_model" IF EXISTS;
DROP TABLE "device_type" IF EXISTS;
DROP TABLE "issue_state" IF EXISTS;
DROP TABLE "issue" IF EXISTS;
DROP TABLE "issue_type" IF EXISTS;
DROP TABLE "device_property" IF EXISTS;
DROP TABLE "device_model_property" IF EXISTS;
DROP TABLE IF EXISTS `device_model_property_enum`;
DROP TABLE "street_light_rule_time" IF EXISTS;
DROP TABLE "street_light_rule_region" IF EXISTS;
DROP TABLE "street_light_rule_date" IF EXISTS;
DROP TABLE "street_light_rule" IF EXISTS;
DROP TABLE "street_light_log" IF EXISTS;
DROP TABLE "street_light" IF EXISTS;
DROP TABLE "admin_authority" IF EXISTS;
DROP TABLE "customer_authority" IF EXISTS;
DROP TABLE IF EXISTS `device_property_log`;
DROP TABLE IF EXISTS `admin_user`;
DROP TABLE IF EXISTS `device_model_service`;


CREATE TABLE `device_property_log` (
  `id` int(11) NOT NULL COMMENT '1 -- 灯头内\n2 -- 灯杆外挂\n3 -- 灯杆设备箱\n4 -- 近距离无线接入',
  `device_id` int(11) NOT NULL,
  `property_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `data_key` varchar(50) NOT NULL,
  `data_value` varchar(255) DEFAULT NULL,
  `last_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)COMMENT='某个设备的参数值';

CREATE TABLE "customer_authority" (
  "customer_id" int(11) NOT NULL,
  "authority_id" varchar(50) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  PRIMARY KEY ("customer_id","authority_id")
);

CREATE TABLE "admin_authority" (
  "id" varchar(50) NOT NULL,
  "name" varchar(50) NOT NULL,
  "is_menu" tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否在菜单上',
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "geo_region" (
  "id" varchar(8) NOT NULL COMMENT '省为2位数字\n市为4位数字，头2位为省\n区为6位数字，头4位为市\n街为8位数字，头6位为区',
  "name" varchar(30) NOT NULL,
  "level" int(11) NOT NULL COMMENT '1--省\n2--市\n3--区\n4--街',
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  "lng" decimal(9,6) NOT NULL,
  "lat" decimal(9,6) NOT NULL,
  PRIMARY KEY ("id")
) COMMENT='地理行政区域';

CREATE TABLE "geo_way"(
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(30) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  PRIMARY KEY("id")
) COMMENT='街道信息';

CREATE TABLE "geo_way_region"(
  "way_id" int(11) NOT NULL,
  "region_id" varchar(8) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  PRIMARY KEY("way_id","region_id"),
  KEY "FKgeo_way_re818325" ("way_id"),
  CONSTRAINT "FKgeo_way_re818325" FOREIGN KEY ("way_id") REFERENCES "geo_way" ("id")
)COMMENT='街道行政区域关联信息';

CREATE TABLE "device_online_log"(
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "device_id" int(11) NOT NULL,
  "is_online" tinyint(1) NOT NULL DEFAULT '0',
  "last_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY("id")
) COMMENT='设备上下线日志信息';


CREATE TABLE "device_install_zone" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "region_id" varchar(8) NOT NULL,
  "name" varchar(30) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  "customer_id" int(11) NOT NULL,
  PRIMARY KEY ("id"),
  KEY "FKdevice_ins485715" ("region_id"),
  CONSTRAINT "FKdevice_ins485715" FOREIGN KEY ("region_id") REFERENCES "geo_region" ("id")
) COMMENT='设备安装区域';



CREATE TABLE "device_position_type" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(30) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "device_position" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "position_type_id" int(11) NOT NULL COMMENT '0 -- Unknown\n1 -- 灯杆\n2 -- 井盖',
  "name" varchar(50) NOT NULL,
  "number" varchar(30) NOT NULL,
  "install_zone_id" int(11) NOT NULL,
  "region_id" varchar(8) NOT NULL COMMENT '在安装区域下的行政区域id',
  "lng" decimal(9,6) NOT NULL,
  "lat" decimal(9,6) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `way_id` int(11) DEFAULT NULL,
  PRIMARY KEY ("id"),
  KEY "FKdevice_pos323304" ("position_type_id"),
  KEY "FKdevice_pos702075" ("install_zone_id"),
  KEY "FKdevice_pos602512" ("region_id"),
  CONSTRAINT "FKdevice_pos323304" FOREIGN KEY ("position_type_id") REFERENCES "device_position_type" ("id"),
  CONSTRAINT "FKdevice_pos602512" FOREIGN KEY ("region_id") REFERENCES "geo_region" ("id"),
  CONSTRAINT "FKdevice_pos702075" FOREIGN KEY ("install_zone_id") REFERENCES "device_install_zone" ("id")
) COMMENT='设备位置';


CREATE TABLE "device_type" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(30) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  PRIMARY KEY ("id")
);


CREATE TABLE "device_model" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "key" varchar(20) NOT NULL,
  "name" varchar(30) NOT NULL,
  "is_gateway" tinyint(1) NOT NULL DEFAULT '0',
  "device_type_id" int(11) NOT NULL,
  "description" varchar(100) DEFAULT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  `iot_platform` int(11) DEFAULT '0',
  PRIMARY KEY ("id"),
  UNIQUE KEY "key" ("key"),
  KEY `FKdevice_mod711226` (`device_type_id`),
  CONSTRAINT `FKdevice_mod711226` FOREIGN KEY (`device_type_id`) REFERENCES `device_type` (`id`)
);

CREATE TABLE "device_install_record"(
   "id" int(11)  NOT NULL AUTO_INCREMENT,
   "add_time" DATETIME NOT NULL,
   "user_id" VARCHAR(45) DEFAULT NULL,
   `device_id` int(11) DEFAULT NULL,
   "position_id" INT(11) DEFAULT NULL,
   PRIMARY KEY ("id")
 );

CREATE TABLE "device_detail" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "model_id" int(11) NOT NULL,
  "parent_id" int(11) DEFAULT NULL,
  "name" varchar(30) NOT NULL,
  "iot_id" varchar(50) DEFAULT NULL,
  "secret" varchar(50) DEFAULT NULL,
  "install_method_id" int(11) DEFAULT NULL,
  "position_id" int(11) DEFAULT NULL,
  "lng" decimal(9,6) DEFAULT NULL,
  "lat" decimal(9,6) DEFAULT NULL,
  "is_active" tinyint(1) NOT NULL DEFAULT '0',
  "is_online" tinyint(1) NOT NULL DEFAULT '0',
  "is_error" tinyint(1) NOT NULL DEFAULT '0',
  "is_disabled" tinyint(1) NOT NULL DEFAULT '0',
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  "description" varchar(255) DEFAULT NULL,
  "customer_id" int(11) NOT NULL,
  PRIMARY KEY ("id"),
  UNIQUE KEY "iot_id" ("iot_id"),
  KEY "FKdevice_det935280" ("model_id"),
  KEY "FKdevice_det514830" ("parent_id"),
  CONSTRAINT "FKdevice_det514830" FOREIGN KEY ("parent_id") REFERENCES "device_detail" ("id"),
  CONSTRAINT "FKdevice_det935280" FOREIGN KEY ("model_id") REFERENCES "device_model" ("id")
);

CREATE TABLE "disaster_monitor"(
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "device_id" int(11) NOT NULL,
  "alarm" tinyint(1) NOT NULL DEFAULT '0',
  "last_time" datetime DEFAULT NULL,
  PRIMARY KEY ("id")
);



CREATE TABLE "issue_type" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(50) DEFAULT NULL,
  "priority" int(11) DEFAULT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  PRIMARY KEY ("id")
);


CREATE TABLE "issue" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "issue_type_id" int(11) NOT NULL,
  "device_id" int(11) NOT NULL,
  "position_id" int(11) NOT NULL,
  "region_id" varchar(8) NOT NULL COMMENT '在安装区域下的行政区域id',
  "lng" decimal(9,6) NOT NULL,
  "lat" decimal(9,6) NOT NULL,
  "state" int(11) NOT NULL DEFAULT '0' COMMENT '0--待处理\n1--处理中\n2--处理完成',
  "is_closed" tinyint(1) NOT NULL DEFAULT '0',
  "open_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "close_time" datetime DEFAULT NULL,
  "assignee_id" CHAR(36) DEFAULT NULL,
  PRIMARY KEY ("id"),
  KEY FKissue600779 ("issue_type_id"),
  CONSTRAINT FKissue600779 FOREIGN KEY ("issue_type_id") REFERENCES "issue_type" ("id")
);

CREATE TABLE "issue_state" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "issue_id" int(11) NOT NULL,
  "state" int(11) NOT NULL,
  "comment" varchar(255) DEFAULT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "assignee_id" CHAR(36) DEFAULT NULL,
  PRIMARY KEY ("id"),
  KEY FKissue_stat697733 ("issue_id"),
  CONSTRAINT FKissue_stat697733 FOREIGN KEY ("issue_id") REFERENCES "issue" ("id")
);


CREATE TABLE "device_model_property" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "model_id" int(11) NOT NULL,
  "name" varchar(50)  NOT NULL,
  "read_only" tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否只读',
  "data_type" varchar(10)  NOT NULL COMMENT '数据类型（byte，int32，int16，float等）',
  "data_key" varchar(50)  NOT NULL COMMENT '数据的键',
  "data_resolution" float DEFAULT NULL,
  "data_min" float DEFAULT NULL,
  "data_max" float DEFAULT NULL,
  "data_length" int(11) DEFAULT NULL,
  "data_unit" varchar(50)  DEFAULT NULL,
  "add_user" char(36)  NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36)  DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  `data_order` smallint(6) NOT NULL DEFAULT '0' COMMENT '数据在上传数据包中的顺序',
  `display_order` smallint(6) NOT NULL DEFAULT '0' COMMENT '数据显示顺序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_PROPERTY_MODEL_KEY` (`model_id`,`data_key`),
  KEY FKdevice_mod939209 (`model_id`),
  CONSTRAINT FKdevice_mod939209 FOREIGN KEY (`model_id`) REFERENCES `device_model` (`id`)
);

CREATE TABLE `device_model_property_enum` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '枚举id',
  `property_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '枚举名称',
  `value` int(11) NOT NULL DEFAULT '0' COMMENT '枚举值，如布尔值则为0或1．',
  `add_user` char(36) NOT NULL,
  `add_time` datetime NOT NULL,
  `last_user` char(36) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_MODEL_PROPERTY_ENUM` (`property_id`,`value`)
);


CREATE TABLE `device_model_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '1 -- 灯头内\n2 -- 灯杆外挂\n3 -- 灯杆设备箱\n4 -- 近距离无线接入',
  `model_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `add_user` char(36) NOT NULL,
  `add_time` datetime NOT NULL,
  `last_user` char(36) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `identifier` varchar(50) NOT NULL,
  `synchronized` tinyint(1) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_MODEL_SERICE_IDENTIFIER` (`model_id`,`identifier`)
) CHARSET=utf8mb4;

DROP TABLE IF EXISTS `device_model_service_param`;
CREATE TABLE `device_model_service_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '1 -- 灯头内\n2 -- 灯杆外挂\n3 -- 灯杆设备箱\n4 -- 近距离无线接入',
  `model_service_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `data_type` varchar(10) NOT NULL COMMENT '数据类型（byte，int32，int16，float等）',
  `data_key` varchar(50) NOT NULL COMMENT '数据的键',
  `data_min` float DEFAULT NULL,
  `data_max` float DEFAULT NULL,
  `data_length` int(11) DEFAULT NULL COMMENT '字符数据类型的长度',
  `data_unit` varchar(50) DEFAULT NULL,
  `data_order` smallint(6) NOT NULL DEFAULT '0' COMMENT '数据在上传数据包中的顺序',
  `is_output` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为输出参数',
  `add_user` char(36) NOT NULL,
  `add_time` datetime NOT NULL,
  `last_user` char(36) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `data_resolution` float DEFAULT NULL COMMENT '浮点数的分辨率（精度）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_MODEL_SERVICE_PARAM` (`model_service_id`,`data_key`),
  CONSTRAINT `FKdevice_mod873604` FOREIGN KEY (`model_service_id`) REFERENCES `device_model_service` (`id`)
) CHARSET=utf8mb4;

DROP TABLE IF EXISTS `device_model_service_param_enum`;
CREATE TABLE `device_model_service_param_enum` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '枚举id',
  `service_param_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '枚举名称',
  `value` int(11) NOT NULL DEFAULT '0' COMMENT '枚举值，如布尔值则为0或1．',
  `add_user` char(36) NOT NULL,
  `add_time` datetime NOT NULL,
  `last_user` char(36) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_MODEL_SERVICE_PARAM_ENUM` (`service_param_id`,`value`),
  CONSTRAINT `FKdevice_mod880492` FOREIGN KEY (`service_param_id`) REFERENCES `device_model_service_param` (`id`)
);

CREATE TABLE IF NOT EXISTS "device_property" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "device_id" int(11) NOT NULL,
  "name" varchar(50) NOT NULL,
  "data_key" varchar(50) NOT NULL,
  "data_value" varchar(255) DEFAULT NULL,
  "last_time" datetime DEFAULT CURRENT_TIMESTAMP,
  "property_id" int(11) NOT NULL,
  PRIMARY KEY ("id")
) COMMENT='某个设备的参数值';



CREATE TABLE "street_light" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "device_id" int(11) NOT NULL,
  "light_level" smallint(6) NOT NULL COMMENT '亮度，0为关灯，最大亮度为100',
  "light_volt" float NOT NULL DEFAULT '0' COMMENT '电压',
  "light_current" float NOT NULL DEFAULT '0' COMMENT '电流',
  "last_time" datetime DEFAULT CURRENT_TIMESTAMP,
  "rule_id" int(11) DEFAULT NULL,
  `power` float NOT NULL DEFAULT '0',
  `power_consumption` float DEFAULT '0',
  PRIMARY KEY ("id")
);

CREATE TABLE "street_light_log" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "device_id" int(11) NOT NULL,
  "light_level" smallint(6) NOT NULL COMMENT '亮度，0为关灯，最大亮度为100',
  "light_volt" float NOT NULL DEFAULT '0' COMMENT '电压',
  "light_current" float NOT NULL DEFAULT '0' COMMENT '电流',
  "rule_id" int(11) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "street_light_rule" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(30) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "street_light_rule_date" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "rule_id" int(11) NOT NULL,
  "start_month" smallint(6) NOT NULL,
  "start_day" smallint(6) NOT NULL,
  "end_month" smallint(6) NOT NULL,
  "end_day" smallint(6) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  PRIMARY KEY ("id"),
  KEY "FKstreet_lig244220" ("rule_id"),
  CONSTRAINT "FKstreet_lig244220" FOREIGN KEY ("rule_id") REFERENCES "street_light_rule" ("id")
);

CREATE TABLE "street_light_rule_region" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "rule_id" int(11) NOT NULL,
  "region_id" varchar(8) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  PRIMARY KEY ("id"),
  KEY "FKstreet_lig580911" ("region_id"),
  KEY "FKstreet_lig232520" ("rule_id"),
  CONSTRAINT "FKstreet_lig232520" FOREIGN KEY ("rule_id") REFERENCES "street_light_rule" ("id"),
  CONSTRAINT "FKstreet_lig580911" FOREIGN KEY ("region_id") REFERENCES "geo_region" ("id")
);

CREATE TABLE "street_light_rule_time" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "rule_date_id" int(11) NOT NULL,
  "start_hour" smallint(6) NOT NULL,
  "start_minute" smallint(6) NOT NULL,
  "stop_hour" smallint(6) DEFAULT NULL,
  "stop_minute" smallint(6) DEFAULT NULL,
  "is_holiday" tinyint(1) NOT NULL DEFAULT '0',
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  "light_level" smallint(6) NOT NULL DEFAULT '0',
  "is_smart" tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY ("id"),
  KEY "FKstreet_lig623920" ("rule_date_id"),
  CONSTRAINT "FKstreet_lig623920" FOREIGN KEY ("rule_date_id") REFERENCES "street_light_rule_date" ("id")
);

CREATE TABLE "customer" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(30) NOT NULL,
  "add_user" char(36) NOT NULL,
  "add_time" datetime NOT NULL,
  "last_user" char(36) DEFAULT NULL,
  "last_time" datetime DEFAULT NULL,
  "code" varchar(10) NOT NULL,
  PRIMARY KEY ("id"),
  UNIQUE KEY "code" ("code")
) COMMENT='用户信息';

CREATE TABLE `admin_user` (
  `id` char(36) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL COMMENT '用户密码的Bcrypt哈希值',
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(13) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `gender` smallint(6) NOT NULL COMMENT '0-- 未知\n1 -- 男性\n2 -- 女性',
  `avatar_url` varchar(255) DEFAULT NULL,
  `add_user` char(36) NOT NULL,
  `add_time` datetime NOT NULL,
  `last_user` char(36) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKadmin_user732109` (`customer_id`),
  CONSTRAINT `FKadmin_user732109` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

CREATE TABLE "manhole_cover_monitor"(
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "device_id" int(11) NOT NULL,
  "alarm" smallint(6) NOT NULL DEFAULT '0',
  "low_battery" tinyint(1) NOT NULL DEFAULT '0',
  "last_time" datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY("id"),
);
CREATE TABLE "air_monitor_log"(
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "device_id" int(11) NOT NULL,
  "pm25" FLOAT DEFAULT NULL,
  "pm10" FLOAT DEFAULT NULL,
  "tvoc" FLOAT DEFAULT NULL,
  "temperature" FLOAT DEFAULT NULL,
  "humidity" FLOAT DEFAULT NULL,
  "last_time" DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY("id"),
);

CREATE TABLE "air_monitor"(
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "device_id" int(11) NOT NULL,
  "pm25" FLOAT DEFAULT NULL,
  "pm10" FLOAT DEFAULT NULL,
  "tvoc" FLOAT DEFAULT NULL,
  "temperature" FLOAT DEFAULT NULL,
  "humidity" FLOAT DEFAULT NULL,
  "last_time" DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY("id"),
);

CREATE TABLE "camera"(
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "device_id" int(11) NOT NULL,
  "video_url" VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY("id"),
);
