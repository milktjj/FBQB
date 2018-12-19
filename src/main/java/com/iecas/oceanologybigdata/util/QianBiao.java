package com.iecas.oceanologybigdata.util;


import com.iecas.oceanologybigdata.model.QBInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class QianBiao {


    private static Connection conn = null;

    private static PreparedStatement ps = null;


    private static final String driver = "org.postgresql.Driver";

    private static final String ip = "192.168.241.68";

    private static final String port = "5432";

    private static final String database_name = "qianbiao";

    private static final String user = "postgres";

    private static final String password = "123";


    public QianBiao() {


        try {

            this.getConnection();

            System.out.println("数据库 " + ip + "/" + database_name + " 连接成功");

        } catch (ClassNotFoundException e) {

            System.err.println("没有找到驱动程序:" + driver);

        } catch (SQLException e) {

            System.err.println("数据库 " + ip + "/" + database_name + " 连接失败");

        }

    }


    /**
     * 获得数据库连接
     *
     * @return 数据库连接
     * @throws ClassNotFoundException
     * @throws SQLException
     */

    static synchronized private Connection getConnection() throws ClassNotFoundException, SQLException {

        if(conn == null) {
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/" + database_name, user, password);
        }

        return conn;

    }


    /**
     * 从数据库下载图片
     *
     */

    public static List<QBInfo> getFBLL() {


        String sql = "SELECT id, lon, lat,depth FROM public.dami_ds";
        List<QBInfo> qbList = new ArrayList<>();


        try {

            ps = getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                QBInfo qb = new QBInfo();
                qb.setQbId(rs.getInt(1));
                qb.setLon(rs.getDouble(2));
                qb.setLat(rs.getDouble(3));
                qb.setDepth(rs.getDouble(4));
                qbList.add(qb);
            }


            rs.close();

            ps.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return qbList;
    }


}
