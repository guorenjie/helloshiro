/*
 Navicat Premium Data Transfer

 Source Server         : mymysql
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.123.117:3306
 Source Schema         : my

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 06/06/2020 18:25:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for perm
-- ----------------------------
DROP TABLE IF EXISTS `perm`;
CREATE TABLE `perm`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `permname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of perm
-- ----------------------------
INSERT INTO `perm` VALUES (1, 'user:insert');
INSERT INTO `perm` VALUES (2, 'user:update');
INSERT INTO `perm` VALUES (3, 'user:delete');
INSERT INTO `perm` VALUES (4, 'user:select');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员');
INSERT INTO `role` VALUES (2, '普通用户');

-- ----------------------------
-- Table structure for roleperm
-- ----------------------------
DROP TABLE IF EXISTS `roleperm`;
CREATE TABLE `roleperm`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `roleid` int(0) NULL DEFAULT NULL COMMENT '角色id',
  `permid` int(0) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roleperm
-- ----------------------------
INSERT INTO `roleperm` VALUES (1, 1, 1);
INSERT INTO `roleperm` VALUES (2, 1, 2);
INSERT INTO `roleperm` VALUES (3, 1, 3);
INSERT INTO `roleperm` VALUES (4, 1, 4);
INSERT INTO `roleperm` VALUES (5, 2, 4);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `solt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '盐',
  `createtime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `status` int(0) NULL DEFAULT NULL COMMENT '0=正常；1=禁用；2=锁定',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', 'b30b8fd6fb81a2b6d76833c7b09df4b6', '管理员', 'c779b8c8-ead9-4513-8907-617bf7927ff4', '2020-06-04 18:16:29', '2020-06-05 10:04:04', NULL);
INSERT INTO `user` VALUES (2, 'guo', '1a046d9eccd6f53b44fb5e4432e36131', 'guo', '8ed4e692-2ce0-416c-876b-532105abf915', '2020-06-04 18:18:28', '2020-06-05 10:04:47', NULL);

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `userid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `roleid` int(0) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES (1, 1, 1);
INSERT INTO `userrole` VALUES (2, 2, 2);
INSERT INTO `userrole` VALUES (3, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
