CREATE TABLE `ehr_company_mail_config` (
  `mail_config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '企业邮箱Id',
  `company_id` bigint(50) NOT NULL COMMENT '公司id',
  `mail_type` varchar(20) DEFAULT NULL COMMENT '邮箱类型',
  `send_to` varchar(100) DEFAULT NULL COMMENT '发信箱',
  `mail_password` varchar(200) DEFAULT NULL COMMENT '邮箱密码',
  `mail_server` varchar(20) DEFAULT NULL COMMENT '发信箱服务器',
  `safe_flag` int(2) DEFAULT NULL COMMENT '是否使用安全协议SSL 1是 0 否',
  `mail_port` int(10) DEFAULT NULL COMMENT '端口',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`mail_config_id`),
  UNIQUE KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET='utf8' COLLATE='utf8_general_ci' COMMENT='企业邮箱配置'