-- USE module_management;
CREATE TABLE `module` (
  `id` varchar(36) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `module_id` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_group` (
  `id` varchar(36) NOT NULL,
  `group_id` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` varchar(36) NOT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `user_id` varchar(20) NOT NULL,
  `user_group_fk` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_to_user_group_fk_key` (`user_group_fk`),
  CONSTRAINT `user_to_user_group_fk_key` FOREIGN KEY (`user_group_fk`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_group_modules` (
  `id` varchar(255) NOT NULL,
  `order_number` int(11) NOT NULL,
  `module_fk` varchar(36) NOT NULL,
  `user_group_fk` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_number_index` (`order_number`),
  KEY `user_group_modules_to_module_fk_key` (`module_fk`),
  KEY `user_group_modules_to_user_group_fk_key` (`user_group_fk`),
  CONSTRAINT `user_group_modules_to_module_fk_key` FOREIGN KEY (`module_fk`) REFERENCES `module` (`id`),
  CONSTRAINT `user_group_modules_to_user_group_fk_key` FOREIGN KEY (`user_group_fk`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
