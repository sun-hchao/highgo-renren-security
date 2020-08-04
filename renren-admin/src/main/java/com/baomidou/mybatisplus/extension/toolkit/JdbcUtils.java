//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.baomidou.mybatisplus.extension.toolkit;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

public class JdbcUtils {
    private static final Log logger = LogFactory.getLog(JdbcUtils.class);

    public JdbcUtils() {
    }

    public static DbType getDbType(String jdbcUrl) {
        Assert.isFalse(StringUtils.isEmpty(jdbcUrl), "Error: The jdbcUrl is Null, Cannot read database type", new Object[0]);
        if (!jdbcUrl.contains(":mysql:") && !jdbcUrl.contains(":cobar:")) {
            if (jdbcUrl.contains(":mariadb:")) {
                return DbType.MARIADB;
            } else if (jdbcUrl.contains(":oracle:")) {
                return DbType.ORACLE;
            } else if (!jdbcUrl.contains(":sqlserver:") && !jdbcUrl.contains(":microsoft:")) {
                if (jdbcUrl.contains(":sqlserver2012:")) {
                    return DbType.SQL_SERVER;
                } else if (jdbcUrl.contains(":postgresql:")) {
                    return DbType.POSTGRE_SQL;
                } else if (jdbcUrl.contains(":hsqldb:")) {
                    return DbType.HSQL;
                } else if (jdbcUrl.contains(":db2:")) {
                    return DbType.DB2;
                } else if (jdbcUrl.contains(":sqlite:")) {
                    return DbType.SQLITE;
                } else if (jdbcUrl.contains(":h2:")) {
                    return DbType.H2;
                } else if (jdbcUrl.contains(":dm:")) {
                    return DbType.DM;
                } else if (jdbcUrl.contains(":highgo:")) {
                    return DbType.POSTGRE_SQL;
                } else {
                    logger.warn("The jdbcUrl is " + jdbcUrl + ", Mybatis Plus Cannot Read Database type or The Database's Not Supported!");
                    return DbType.OTHER;
                }
            } else {
                return DbType.SQL_SERVER2005;
            }
        } else {
            return DbType.MYSQL;
        }
    }
}
