package com.fangzhi.yao.fzcms.tools.generator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;

public class TyqxCmsGenerator {

    // 根据命名规范，只修改此常量值即可
    private static String MODULE = "fzcms";
//    private static String TABLE_PREFIX = "fz_";
    private static String TABLE_PREFIX = "sys_";
    private static String PARENT_PACKAGE_NAME = "com.fangzhi.yao";

    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test2?useUnicode=true&amp;characterEncoding=UTF-8&amp;tinyInt1isBit=false";
    private static String JDBC_USERNAME = "root";
    private static String JDBC_PASSWORD = "root";

    private static String OUTPUT_API_DIR = "api";
    private static String OUTPUT_SERVICE_DIR = "provider";
//    private static String OUTPUT_CONTROLLER_DIR = "\\Users\\yaoping\\Documents\\study\\java\\tyqx-cms\\consumer\\src\\main\\java";
//    private static String OUTPUT_CONTROLLER_DIR = "app";
    private static String OUTPUT_CONTROLLER_DIR = "consumer";

    /**
     * 自动代码生成
     * @param args
     */
    public static void main(String[] args) {

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(JDBC_DRIVER);
        dsc.setUsername(JDBC_USERNAME);
        dsc.setPassword(JDBC_PASSWORD);
        dsc.setUrl(JDBC_URL);

        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT_PACKAGE_NAME);
        pc.setModuleName(MODULE);

        DefaultGenerator defaultGenerator = new DefaultGenerator();
        defaultGenerator.setDataSource(dsc);
        defaultGenerator.setPackageInfo(pc);
//        defaultGenerator.setIncludeTables(TABLE_PREFIX,"sys_permission","sys_role","sys_user","sys_menu_type");
        defaultGenerator.setIncludeTables(TABLE_PREFIX,"sys_dictionary");

        // 生成API
        defaultGenerator.setOutputDir(OUTPUT_API_DIR);
        defaultGenerator.createClass(
                "entity"
                ,"service"
        );
        // 生成服务
        defaultGenerator.setOutputDir(OUTPUT_SERVICE_DIR);
        defaultGenerator.createClass(
                "Mapper"
                , "mapper"
                , "service.impl"
        );
        // 生成Controller
        defaultGenerator.setOutputDir(OUTPUT_CONTROLLER_DIR);
        defaultGenerator.createClass("controller");
    }

}