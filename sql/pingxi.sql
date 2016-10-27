CREATE TABLE `stock_base_info` (
  `daima` varchar(6) NOT NULL COMMENT '股票代码',
  `zhongwenming` varchar(8) NOT NULL COMMENT '名称',
  `pinyin` varchar(4) NOT NULL COMMENT '简拼',
  `rongzi` tinyint(1) NOT NULL,
  `quyu` varchar(20) DEFAULT NULL COMMENT '区域',
  `bankuai` varchar(50) DEFAULT NULL COMMENT '板块',
  `jilu_date` datetime DEFAULT NULL COMMENT '记录日期',
  PRIMARY KEY (`daima`),
  UNIQUE KEY `zhongwenming_UNIQUE` (`zhongwenming`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基本信息';

CREATE TABLE `stock_ext_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `daima` varchar(6) NOT NULL COMMENT '股票代码',
  `miaoshu` varchar(500) NOT NULL COMMENT '描述',
  `fasheng_date` date NOT NULL COMMENT '发生日期',
  `jilu_date` datetime DEFAULT NULL COMMENT '记录日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='详细信息';

CREATE TABLE `stock_tx_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `daima` varchar(6) NOT NULL COMMENT '股票代码',
  `miaoshu` varchar(500) NOT NULL COMMENT '描述',
  `tixing_date` date NOT NULL COMMENT '提醒日期',
  `jilu_date` datetime DEFAULT NULL COMMENT '记录日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='提醒信息';

CREATE TABLE `stock_tfp_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `daima` varchar(6) NOT NULL COMMENT '股票代码',
  `tingpai_date` date NOT NULL COMMENT '停牌日期',
  `fupai_date` date NOT NULL COMMENT '复牌日期',
  `jilu_date` datetime DEFAULT NULL COMMENT '记录日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='停复牌信息';