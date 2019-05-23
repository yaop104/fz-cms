package com.fangzhi.yao.fzcms.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * Created by yao
 */
@Component
public class NodeManager {
    private Logger logger = LoggerFactory.getLogger(NodeManager.class);
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
    public static final String DEFAULT_LOG_ID = "9999";
//    public static final String NODE_QUERY_SQL = "select * from kp_node where module = ? and ip = ?";
    public static String ip;
    public static String logId = DEFAULT_LOG_ID;

    //网卡信息
    private String module;
    private String nic;
    private String driver;
    private String connUrl;
    private String userName;
    private String passWord;

    public NodeManager() {
    }

    public void init() {
        //第一步,获取本机IP
//        if (getLocalIp() == null) {
//            logger.error("get ipAddr faild");
//            return;
//        }
//
//        //第二步,获取数据库节点信息
//        Connection connection = null;
//        PreparedStatement prep = null;
//        ResultSet resultSet = null;
//        try {
//            Class.forName(driver);
//            connection = DriverManager.getConnection(connUrl, userName, passWord);
//            logger.info("node sql: " + NODE_QUERY_SQL);
//            prep = connection.prepareStatement(NODE_QUERY_SQL);
//            prep.setString(1, module);
//            prep.setString(2, NodeManager.ip);
//            resultSet = prep.executeQuery();
//            while (resultSet.next()) {
//                logId = resultSet.getString("logId");
//                logger.info("get logId: " + logId);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            close(resultSet);
//            close(prep);
//            close(connection);
//        }
    }

    private void close(AutoCloseable resource) {
        if (null != resource) {
            try {
                resource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getConnUrl() {
        return connUrl;
    }

    public void setConnUrl(String connUrl) {
        this.connUrl = connUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getLocalIp() {
        try {
            NetworkInterface networkInterface = NetworkInterface.getByName(nic);
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                String ipAddr = addresses.nextElement().getHostAddress();
                if (IPV4_PATTERN.matcher(ipAddr).matches()) {
                    logger.info("get ipaddr success : " + ipAddr);
                    NodeManager.ip = ipAddr;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return NodeManager.ip;
    }
}
