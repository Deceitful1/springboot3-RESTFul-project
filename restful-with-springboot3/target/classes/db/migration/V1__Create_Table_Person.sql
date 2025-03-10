
CREATE TABLE IF NOT EXISTS `person`
(
    `id`         bigint(20)   NOT NULL AUTO_INCREMENT,
    `address`    varchar(250) NOT NULL,
    `first_name` varchar(80)  NOT NULL,
    `gender`     varchar(10)  NOT NULL,
    `last_name`  varchar(150) NOT NULL,
    PRIMARY KEY (`id`)
);




