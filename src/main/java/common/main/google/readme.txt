


程序说明
    功能2个：
        1、谷歌学术关键字，搜索top-k 文章采集
        入口程序：common.main.google.ScholarPaperListStartMain

        2、谷歌学术历年被引采集
        入口程序：common.main.google.ScholarCiteStartMain

    环境要求：
        mysql-5.6及以上
        java-8



    配置：
        config/googleKeyword.txt   配置需要采集的top-k谷歌搜索关键字

        config/googlePaper.txt     配置需要采集的文章逐年被引文章名字

        config/config.txt

        searchTopKeyNum=30   //关键字搜索的前k个
        mysqlHost=192.168.3.78:3306  // mysql 数据库host和端口号
        mysqlDatabaseName=junkewei   // 数据库名称
        mysqlUserName=pxqb           // 用户名称
        mysqlPassword=1!p@ssword     // 登录密码
        httpProxy=127.0.0.1:9666     // 代理ip和端口号


    提示：
        如果没有代理，可以 config/U1802.exe，启动，并配置httpProxy=127.0.0.1:9666 即可使用



数据表的创建语句：


    表1、googlePaperSearchTopK

        SET FOREIGN_KEY_CHECKS=0;
        -- ----------------------------
        -- Table structure for `googlePaperSearchTopK`
        -- ----------------------------
        DROP TABLE IF EXISTS `googlePaperSearchTopK`;
        CREATE TABLE `googlePaperSearchTopK` (
          `id` int(11) NOT NULL AUTO_INCREMENT,
          `title` text CHARACTER SET latin1,
          `orderId` int(11) DEFAULT NULL COMMENT '关键字搜索下的google scholar排序',
          `authors` text CHARACTER SET latin1,
          `authorsUrl` text CHARACTER SET latin1,
          `brief` text CHARACTER SET latin1,
          `pubYear` int(11) DEFAULT NULL,
          `searchKeyword` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
          PRIMARY KEY (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=636 DEFAULT CHARSET=utf8;

    表2、googlePaperYearsCite ：

        SET FOREIGN_KEY_CHECKS=0;
        -- ----------------------------
        -- Table structure for `googlePaperYearsCite`
        -- ----------------------------
        DROP TABLE IF EXISTS `googlePaperYearsCite`;
        CREATE TABLE `googlePaperYearsCite` (
          `id` int(11) NOT NULL AUTO_INCREMENT,
          `title` text CHARACTER SET latin1,
          `authors` text CHARACTER SET latin1,
          `pubYear` int(11) DEFAULT NULL,
          `citeNum` int(11) DEFAULT NULL,
          `citeUrl` text CHARACTER SET latin1,
          `yearsCite` text CHARACTER SET latin1,
          PRIMARY KEY (`id`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8;







