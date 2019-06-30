/*
 Navicat MySQL Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : nextone

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 30/06/2019 23:12:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_adminuserroles
-- ----------------------------
DROP TABLE IF EXISTS `t_adminuserroles`;
CREATE TABLE `t_adminuserroles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adminUserId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `adminUserId` (`adminUserId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `t_adminuserroles_ibfk_1` FOREIGN KEY (`adminUserId`) REFERENCES `t_adminusers` (`id`),
  CONSTRAINT `t_adminuserroles_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `t_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_adminuserroles
-- ----------------------------
BEGIN;
INSERT INTO `t_adminuserroles` VALUES (1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_adminusers
-- ----------------------------
DROP TABLE IF EXISTS `t_adminusers`;
CREATE TABLE `t_adminusers` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '登陆名称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(50) NOT NULL COMMENT '盐',
  `isenable` tinyint(4) DEFAULT '0' COMMENT '是否启用',
  `last_login` datetime DEFAULT NULL COMMENT '最后一次登陆时间',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_adminusers
-- ----------------------------
BEGIN;
INSERT INTO `t_adminusers` VALUES (1, 'admin', 'da4799f536c020929a55f2631039118f', '111111', 0, '2019-05-08 17:31:44', '12345678', '小徐', '2019-06-30 20:13:37');
INSERT INTO `t_adminusers` VALUES (2, 'dandan', 'da4799f536c020929a55f2631039118f', '111111', 0, '2019-05-08 17:31:47', '1234567', '蛋蛋', '2019-06-30 20:13:40');
COMMIT;

-- ----------------------------
-- Table structure for t_permissions
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions`;
CREATE TABLE `t_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) NOT NULL,
  `description` text,
  `isDeleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permissions
-- ----------------------------
BEGIN;
INSERT INTO `t_permissions` VALUES (1, '/test/list', '查看', 0);
INSERT INTO `t_permissions` VALUES (2, '/test/add', '添加', 0);
INSERT INTO `t_permissions` VALUES (3, '/test/update', '修改', 0);
INSERT INTO `t_permissions` VALUES (4, '/test/delete', '删除', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_rolepermissions
-- ----------------------------
DROP TABLE IF EXISTS `t_rolepermissions`;
CREATE TABLE `t_rolepermissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleId` bigint(20) DEFAULT NULL,
  `permissionId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  KEY `permissionId` (`permissionId`),
  CONSTRAINT `t_rolepermissions_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_roles` (`id`),
  CONSTRAINT `t_rolepermissions_ibfk_2` FOREIGN KEY (`permissionId`) REFERENCES `t_permissions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_rolepermissions
-- ----------------------------
BEGIN;
INSERT INTO `t_rolepermissions` VALUES (1, 1, 1);
INSERT INTO `t_rolepermissions` VALUES (2, 1, 2);
COMMIT;

-- ----------------------------
-- Table structure for t_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_roles`;
CREATE TABLE `t_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `isDeleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_roles
-- ----------------------------
BEGIN;
INSERT INTO `t_roles` VALUES (1, '超级管理员', '上帝般的存在，拥有对系统的所有权限', 0);
INSERT INTO `t_roles` VALUES (2, '普通管理员', '基本操作', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_syslog
-- ----------------------------
DROP TABLE IF EXISTS `t_syslog`;
CREATE TABLE `t_syslog` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '日志编号',
  `name` varchar(255) DEFAULT NULL COMMENT '操作人',
  `host` varchar(255) DEFAULT NULL COMMENT '主机',
  `address` varchar(255) DEFAULT NULL COMMENT '操作地点',
  `createTime` datetime DEFAULT NULL COMMENT '操作时间',
  `requestPath` varchar(1000) DEFAULT NULL COMMENT '请求地址',
  `requestParam` varchar(1000) DEFAULT NULL COMMENT '请求参数',
  `requestMethod` varchar(1000) DEFAULT NULL COMMENT '请求方法',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_syslog
-- ----------------------------
BEGIN;
INSERT INTO `t_syslog` VALUES (1, 'admin', '127.0.0.1', ' 测试', '2019-06-30 22:10:42', '../index', '', NULL, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
