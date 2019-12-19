SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '主键ID',
    `authority`   varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '权限名称',
    `name`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
    `create_time` datetime(0)                                             NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES ('b8174920f33f4b17ad5f415c700aacd2', 'ROLE_ADMIN', '管理员', '2019-12-12 21:42:43');
INSERT INTO `sys_role`
VALUES ('fb89ffc6aff04f4cbed0776155baaa15', 'ROLE_USER', '普通用户', '2019-12-12 21:43:04');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '主键ID',
    `name`        varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '姓名',
    `password`    varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
    `create_time` datetime(0)                                             NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES ('64c40c54ef21495da322901107a7ad00', 'admin', '$2a$10$S5EFZRK6PLsTGWHE1T4w1.SqlsGHxUAOXk0Rf2ARU8sQ39QXxs8Tq',
        '2019-12-12 21:41:53');
INSERT INTO `sys_user`
VALUES ('8cd77cb345e94c31a5b884ed6e3616a2', 'thyme', '$2a$10$S5EFZRK6PLsTGWHE1T4w1.SqlsGHxUAOXk0Rf2ARU8sQ39QXxs8Tq',
        '2019-12-12 21:42:15');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键',
    `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键'
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES ('64c40c54ef21495da322901107a7ad00', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_user_role`
VALUES ('8cd77cb345e94c31a5b884ed6e3616a2', 'fb89ffc6aff04f4cbed0776155baaa15');

SET FOREIGN_KEY_CHECKS = 1;

/*菜单表*/
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id` varchar(32)  NOT NULL COMMENT '菜单主键',
    `parent_id` varchar(32)  COMMENT '父菜单主键',
    `menu_name` varchar(100)  NOT NULL COMMENT '菜单名称',
    `menu_href` varchar(100)  NOT NULL COMMENT '菜单链接',
    `menu_icon` varchar(100)  NOT NULL COMMENT '菜单图标',
    `menu_level` varchar(100)  NOT NULL COMMENT '菜单级别',
    `menu_weight` varchar(100)  NOT NULL COMMENT '菜单权重',
    `is_show` char(1) NOT NULL COMMENT '是否显示（1显示 0隐藏）',
    `create_date` datetime  NOT NULL COMMENT '创建时间',
    `create_by` varchar(20)  NOT NULL COMMENT '创建人',
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB
 CHARACTER SET = utf8
 COLLATE = utf8_general_ci
 ROW_FORMAT = Dynamic;

/*菜单权限表*/
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`
(
    `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单主键',
    `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键'
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

