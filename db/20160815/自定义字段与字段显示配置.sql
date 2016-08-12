--字段显示配置表
CREATE TABLE `ehr_field_display_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL COMMENT '企业ID',
  `module` int(4) DEFAULT NULL COMMENT '模块',
  `function` int(8) DEFAULT NULL COMMENT '功能',
  `column_name` varchar(50) DEFAULT NULL COMMENT '字段名',
  `config_type` int(2) DEFAULT '1' COMMENT '配置类型：1-列表，2-维护',
  `sort` int(4) DEFAULT '0' COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=467 DEFAULT CHARSET=utf8;

--自定义字段值表
CREATE TABLE `ehr_table_data` (
  `table_data_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `table_field_def_id` bigint(20) NOT NULL COMMENT '字段定义ID',
  `key_value` varchar(50) NOT NULL COMMENT '关联字段ID',
  `value` varchar(2000) NOT NULL COMMENT '列值',
  PRIMARY KEY (`table_data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

--自定义字段关联表
CREATE TABLE `ehr_table_def` (
  `table_def_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL COMMENT '公司ID',
  `key_table` varchar(50) DEFAULT NULL COMMENT '关联表',
  `key_field` varchar(50) DEFAULT NULL COMMENT '关联字段',
  `relation` int(2) DEFAULT '1' COMMENT '关系：1一对一，2多对一',
  `memo` varchar(2000) DEFAULT NULL COMMENT '备注',
  `is_deleted` int(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`table_def_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--自定义字段表
CREATE TABLE `ehr_table_field_def` (
  `table_field_def_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `table_def_id` bigint(20) NOT NULL COMMENT '表定义ID',
  `display_name` varchar(50) NOT NULL COMMENT '显示名称',
  `column_name` varchar(50) NOT NULL COMMENT '字段名称',
  `display_type` int(4) DEFAULT NULL COMMENT '显示类型',
  `data_type` int(4) DEFAULT NULL COMMENT '数据类型',
  `ref_id` int(11) DEFAULT NULL COMMENT '参照基础数据ID',
  `is_necessary` int(1) DEFAULT '0' COMMENT '是否必填',
  `is_using` int(1) DEFAULT '1' COMMENT '是否启用',
  `is_display_in_list` int(1) DEFAULT '0' COMMENT '是否列表显示',
  `is_display_in_detail` int(1) DEFAULT '0' COMMENT '是否明细显示',
  `sort_list` int(4) DEFAULT '0' COMMENT '列表显示顺序',
  `sort_detail` int(4) DEFAULT '0' COMMENT '明细显示顺序',
  `memo` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_field_def_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;