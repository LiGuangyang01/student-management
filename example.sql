/*
Navicat MySQL Data Transfer

Source Server         : loclhost_
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : exmaple

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2021-2-20 06:12:40
*/

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson` (
  `taotiid` int(11) NOT NULL AUTO_INCREMENT,
  `lessonname` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  PRIMARY KEY (`taotiid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `taotiid` int(11) DEFAULT NULL,
  `optiona` varchar(255) DEFAULT NULL,
  `optionb` varchar(255) DEFAULT NULL,
  `optionc` varchar(255) DEFAULT NULL,
  `optiond` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `stu_examresult`;
CREATE TABLE `stu_examresult` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taotiid` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `radioscores` int(11) DEFAULT NULL,
  `checkscores` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `stu_information`;
CREATE TABLE `stu_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `stuid` varchar(255) DEFAULT NULL,
  `stuname` varchar(255) DEFAULT NULL,
  `stutime` date DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `teacher_information`;
CREATE TABLE `teacher_information` (
  `teacherid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `worknumber` int(11) DEFAULT NULL,
  `teachername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacherid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_login`;
CREATE TABLE `t_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roles` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;


