/*登录日志*/
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `id` varchar(32)  NOT NULL COMMENT '主键',
    `username` varchar(100)  COMMENT '用户名',
    `ip_address` varchar(100)  NOT NULL COMMENT 'IP地址',
    `ip_source` varchar(100)  NOT NULL COMMENT 'IP来源',
    `message` varchar(100)  NULL DEFAULT NULL COMMENT '日志信息',
    `browser_name` varchar(100)  NULL DEFAULT NULL COMMENT '浏览器名称',
    `system_name` varchar(100)  NOT NULL COMMENT '系统名称',
    `create_date` datetime  NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB
 CHARACTER SET = utf8
 COLLATE = utf8_general_ci
 ROW_FORMAT = Dynamic;