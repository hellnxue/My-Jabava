
--通用数据类型
INSERT INTO ehr_common_data_type (id, NAME) VALUES (4, '短信模板');
INSERT INTO ehr_common_data_type (id, NAME) VALUES (5, '自定义字段信息项类型');

--通用数据
INSERT INTO ehr_common_data (common_data_type, common_data_code, common_data_name, is_valid) VALUES (5, '1', 'text', 1);
INSERT INTO ehr_common_data (common_data_type, common_data_code, common_data_name, is_valid) VALUES (5, '2', 'select', 1);
INSERT INTO ehr_common_data (common_data_type, common_data_code, common_data_name, is_valid) VALUES (5, '3', 'datepicker', 1);
