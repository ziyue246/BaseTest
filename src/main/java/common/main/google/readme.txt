


程序说明
    功能2个：
        1、谷歌学术关键字，搜索top-k 文章采集
        入口程序：common.main.google.ScholarPaperListStartMain

        2、谷歌学术历年被引采集
        入口程序：common.main.google.ScholarCiteStartMain

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


