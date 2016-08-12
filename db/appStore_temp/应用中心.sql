CREATE TABLE `ehr_app_store` (
`APP_ID`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '应用id' ,
`SYSTEM_ID`  bigint(20) NULL DEFAULT NULL COMMENT '应用对应的平台id' ,
`APP_NAME`  varchar(50) NULL DEFAULT NULL COMMENT '应用名称' ,
`ICON_PATH`  varchar(255) NULL DEFAULT NULL COMMENT '应用展示图标路径' ,
`APP_SORT`  int(3) NULL DEFAULT NULL COMMENT '应用显示顺序（从1开始，数字越小，显示越靠前）' ,
`PARENT_ID`  bigint(20) NULL DEFAULT 0 COMMENT '父应用ID（默认为0--父级应用）' ,
`IS_ONLINE`  int(1) NULL DEFAULT 1 COMMENT '是否已上线（0--未上线，敬请期待；1--已上线）' ,
`IS_DELETED`  int(1) NULL DEFAULT 0 COMMENT '逻辑删除标识（0--未删除；1--已删除）' ,
PRIMARY KEY (`APP_ID`)
)
;


ALTER TABLE `ehr_app_store`
ADD COLUMN `OPENED_URL`  varchar(255) NULL DEFAULT NULL COMMENT '开通后访问地址' AFTER `PARENT_ID`,
ADD COLUMN `UNOPENED_URL`  varchar(255) NULL DEFAULT NULL COMMENT '未开通时访问地址' AFTER `OPENED_URL`;


INSERT INTO `ehr_menu` (`menu_id`, `menu_name`, `menu_type`, `menu_url`, `parent_id`, 

`create_user_id`, `create_user_name`, `create_date`, `last_modify_user_id`, 

`last_modify_user_name`, `last_modify_date`, `IS_DELETED`, `menu_seq`) VALUES ('23', '应用

中心', '31', NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, '0', '9');


INSERT INTO `ehr_menu` (`menu_id`, `menu_name`, `menu_type`, `menu_url`, `parent_id`, 

`create_user_id`, `create_user_name`, `create_date`, `last_modify_user_id`, 

`last_modify_user_name`, `last_modify_date`, `IS_DELETED`, `menu_seq`) VALUES ('230001', '

应用管理', '30', '/appStore/to_appStore', '23', NULL, NULL, NULL, NULL, NULL, NULL, '0', 

'99');



