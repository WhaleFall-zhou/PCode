/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 50732
Source Host           : 172.16.7.203:3306
Source Database       : PCode

Target Server Type    : MYSQL
Target Server Version : 50732
File Encoding         : 65001

Date: 2021-03-05 18:15:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for browse
-- ----------------------------
DROP TABLE IF EXISTS `browse`;
CREATE TABLE `browse` (
  `id` varchar(255) NOT NULL,
  `color` varchar(255) DEFAULT NULL COMMENT '显示的颜色',
  `name` varchar(255) DEFAULT NULL COMMENT '组员id，数组',
  `logo` varchar(255) DEFAULT NULL COMMENT '项目标识',
  `created_at` bigint(11) DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人id',
  `team` varchar(255) DEFAULT NULL COMMENT '所属团队id',
  `template_type` varchar(255) DEFAULT NULL COMMENT '项目模板类型',
  `updated_at` bigint(11) DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of browse
-- ----------------------------
INSERT INTO `browse` VALUES ('yParVWWfYU8FGKLAUx0t', null, '敏捷示例项目', 'DEMO', '1613725544439', 'gk721d8mny1i', 'gk721d8mny1i', 'scrum', null, null);

-- ----------------------------
-- Table structure for cus_info
-- ----------------------------
DROP TABLE IF EXISTS `cus_info`;
CREATE TABLE `cus_info` (
  `cus_id` varchar(36) NOT NULL COMMENT '成员ID',
  `cus_pwd` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `cus_name` varchar(100) DEFAULT NULL COMMENT '登录用户名称',
  `depart_id` varchar(255) DEFAULT '' COMMENT '部门id',
  `login_status` tinyint(4) DEFAULT NULL COMMENT '0未登录，1登录',
  `phone_no` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `cus_email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `create_cus_id` varchar(36) DEFAULT NULL COMMENT '创建人',
  `create_time` bigint(11) DEFAULT NULL COMMENT '创建时间',
  `update_cus_id` varchar(36) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  `sex` bigint(11) DEFAULT NULL COMMENT '性别 0：女  1：男',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '成员名称',
  `login_time` bigint(11) DEFAULT NULL COMMENT '登录时间',
  `color` varchar(300) DEFAULT NULL COMMENT '头像图片背景颜色',
  `ticket_auth_flag` tinyint(4) DEFAULT NULL COMMENT '查看工作项权限标识，0查看所有的工作项（默认），1查看自己组内的工作项，2查看分配给自己的工作项',
  `reply_auth_flag` tinyint(4) DEFAULT NULL COMMENT '回复权限标识，0公开和私密回复，1仅私密回复（默认）',
  `user_auth_flag` tinyint(4) DEFAULT NULL COMMENT '查看普通用户权限标识，0允许管理普通用户（默认），1只能查看普通用户',
  `ticket_doc_auth_flag` tinyint(4) DEFAULT NULL COMMENT '查看知识库权限，0查看所有的文档（默认），1增删改查所有的文档（操作之后都需要审核）',
  `audit_ticket_doc_flag` tinyint(4) DEFAULT NULL COMMENT '正对管理员，是否可审核知识库，0可审核（默认），1不可审核（可审核时只针对管理员）',
  `pwd_type` int(4) DEFAULT '1' COMMENT '密码类型 0 重置密码  1 非重置密码',
  `position` varchar(255) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`cus_id`),
  UNIQUE KEY `IDX_CUS_EMAIL` (`cus_email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成员表';

-- ----------------------------
-- Records of cus_info
-- ----------------------------
INSERT INTO `cus_info` VALUES ('gk721d8mny1i', '123456', '周', '0', '0', '15083820752', '1640830027@qq.com', null, null, null, null, null, null, null, 'rgb(134, 138, 246)', null, null, null, null, null, '1', null);

-- ----------------------------
-- Table structure for department_info
-- ----------------------------
DROP TABLE IF EXISTS `department_info`;
CREATE TABLE `department_info` (
  `depart_id` varchar(36) NOT NULL COMMENT '部门id',
  `depart_name` varchar(40) NOT NULL COMMENT '登录用户名称',
  `depart_level` tinyint(4) DEFAULT NULL COMMENT '部门级别',
  `parent_id` varchar(36) DEFAULT '0' COMMENT '父级部门id',
  `depart_level_no` varchar(200) DEFAULT NULL COMMENT '部门等级id列表',
  `source_no` int(10) DEFAULT NULL COMMENT '同一级别的序号',
  `create_time` bigint(11) DEFAULT NULL COMMENT '创建时间',
  `create_id` varchar(36) DEFAULT NULL COMMENT '创建人id',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  `update_id` varchar(36) DEFAULT NULL COMMENT '更新人id',
  `depart_name_pinying` varchar(255) DEFAULT NULL COMMENT '部门拼音',
  PRIMARY KEY (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department_info
-- ----------------------------
INSERT INTO `department_info` VALUES ('yh6cey4bdkX123', '周', null, '0', null, null, '1611676800', 'gk721d8mny1i', null, null, 'zhou');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` varchar(255) NOT NULL,
  `created_at` bigint(11) DEFAULT NULL COMMENT '创建的时间',
  `principal_id` varchar(255) DEFAULT NULL COMMENT '负责人id',
  `Participant` varchar(255) DEFAULT NULL COMMENT '参与者',
  `statue` tinyint(11) DEFAULT NULL COMMENT '工作项当前状态',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `browse_id` varchar(255) DEFAULT NULL COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------

-- ----------------------------
-- Table structure for item_details
-- ----------------------------
DROP TABLE IF EXISTS `item_details`;
CREATE TABLE `item_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` varchar(255) DEFAULT NULL COMMENT '工作项id',
  `start_time` bigint(11) DEFAULT NULL COMMENT '开始时间',
  `end_time` bigint(11) DEFAULT NULL COMMENT '截至时间',
  `type` tinyint(11) DEFAULT NULL COMMENT '工作项类别 0：史诗，1：用户故事，2：缺陷，3：特性，4：测试用例，5：迭代，6：工时',
  `description` varchar(255) DEFAULT '' COMMENT '内容',
  `feature` varchar(255) DEFAULT '' COMMENT '指标特征',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_details
-- ----------------------------

-- ----------------------------
-- Table structure for item_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `item_dictionary`;
CREATE TABLE `item_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `severity` int(11) DEFAULT NULL COMMENT '严重程度',
  `duplicate_version` int(11) DEFAULT NULL COMMENT '复现版本',
  `recurrence_probability` int(11) DEFAULT NULL COMMENT '复现概率',
  `defect_category` int(11) DEFAULT NULL COMMENT '缺陷类别',
  `risk` int(11) DEFAULT NULL COMMENT '风险 ',
  `demand_type` int(11) DEFAULT NULL COMMENT '需求类型',
  `demand_source` int(11) DEFAULT NULL COMMENT '需求来源',
  `use_case_type` int(11) DEFAULT NULL COMMENT '用例类型',
  `importance` int(11) DEFAULT NULL COMMENT '重要程度',
  `module` int(11) DEFAULT NULL COMMENT '所属模块',
  `test_library` int(11) DEFAULT NULL COMMENT '所属测试库',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_dictionary
-- ----------------------------
INSERT INTO `item_dictionary` VALUES ('1', '1', '0', '0', '0', '0', '1', '1', '1', '0', '0', '0', '0', '史诗');
INSERT INTO `item_dictionary` VALUES ('2', '2', '0', '0', '0', '0', '2', '2', '2', '0', '0', '0', '0', '用户故事');
INSERT INTO `item_dictionary` VALUES ('3', '3', '3', '3', '3', '3', '0', '0', '0', '0', '0', '0', '0', '缺陷');
INSERT INTO `item_dictionary` VALUES ('4', '4', '0', '0', '0', '0', '4', '4', '4', '0', '0', '0', '0', '特性');
INSERT INTO `item_dictionary` VALUES ('5', '0', '0', '0', '0', '0', '0', '0', '0', '5', '5', '5', '5', '测试用例');

-- ----------------------------
-- Table structure for position_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `position_dictionary`;
CREATE TABLE `position_dictionary` (
  `position_id` int(11) NOT NULL AUTO_INCREMENT,
  `position_name` varchar(255) DEFAULT NULL COMMENT '职位名称',
  `sort` varchar(255) DEFAULT NULL COMMENT '职位分类 0：技术 1：产品',
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position_dictionary
-- ----------------------------
INSERT INTO `position_dictionary` VALUES ('1', '架构师', '1');
INSERT INTO `position_dictionary` VALUES ('2', '前端工程师', '1');
INSERT INTO `position_dictionary` VALUES ('3', '开发工程师', '1');
INSERT INTO `position_dictionary` VALUES ('4', '测试工程师', '1');
INSERT INTO `position_dictionary` VALUES ('5', '运维工程师', '1');
INSERT INTO `position_dictionary` VALUES ('6', '	\r\n产品总监', '2');
INSERT INTO `position_dictionary` VALUES ('7', '产品经理', '2');
INSERT INTO `position_dictionary` VALUES ('8', '产品助理', '2');
INSERT INTO `position_dictionary` VALUES ('9', '设计师', '2');

-- ----------------------------
-- Table structure for service_depart_rel
-- ----------------------------
DROP TABLE IF EXISTS `service_depart_rel`;
CREATE TABLE `service_depart_rel` (
  `rel_id` varchar(36) NOT NULL,
  `depart_id` varchar(36) NOT NULL COMMENT '部门id',
  `service_id` varchar(36) NOT NULL COMMENT '成员id',
  `manager_flag` tinyint(4) NOT NULL COMMENT '管理员标记 1-管理员，2-普通成员',
  `create_id` varchar(36) DEFAULT NULL COMMENT '添加成员id',
  `create_time` bigint(11) DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service_depart_rel
-- ----------------------------
