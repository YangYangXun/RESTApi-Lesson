CREATE TABLE `country` (
  `countryid` char(5) NOT NULL,
  `countryname` varchar(45) NOT NULL,
  PRIMARY KEY (`countryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `customers` (
  `customerid` char(5) NOT NULL,
  `companyname` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `countryid` char(5) NOT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`customerid`),
  KEY `countryid_idx` (`countryid`),
  CONSTRAINT `country_fk` FOREIGN KEY (`countryid`) REFERENCES `country` (`countryid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客戶資料';
