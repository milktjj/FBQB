package com.iecas.oceanologybigdata.util;


import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;



public class PostgresTest {



    private Connection conn = null;

    private PreparedStatement ps = null;



    private final String driver = "org.postgresql.Driver";

    private final String ip = "192.168.1.149";

    private final String port = "5432";

    private final String database_name = "oceanologybigdata";

    private final String user = "postgres";

    private final String password = "123456";



    public PostgresTest() {



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

    private Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName(driver);

        conn = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/" + database_name, user, password);

        return conn;

    }



    /**

     * 向数据库上传图片

     *

     * @param path

     * @param name

     */

    public boolean uploadImage(String path, String name) {



        String sql = "INSERT INTO public.tbl_station(photoname, photo)VALUES (?, ?)";

        boolean result=true;

        try {

            ps = conn.prepareStatement(sql);



            // 设置图片名称

            ps.setString(1, name);



            // 设置图片文件

            File file = new File(path + "\\" + name);

            FileInputStream inputStream = new FileInputStream(file);

            ps.setBinaryStream(2, inputStream, (int) file.length());



            // 执行SQL

            ps.execute();

            ps.close();


            System.out.println(path + "\\" + name+" 已上传");
            System.out.println(result);

        } catch (SQLException e) {
            result=false;
            System.err.println("SQL " + sql + " 错误");

        } catch (FileNotFoundException e) {
            result=false;
            System.err.println("图片 " + path + "\\" + name + " 没有找到");

        }
        return result;

    }



    /**

     * 从数据库下载图片

     *

     * @param path

     */

    public void downloadImage(String path) {



        String sql = "SELECT photoname, photo FROM public.tbl_station";

        String name = "";

        try {

            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                name = rs.getString(1);

                InputStream inputStream = rs.getBinaryStream(2);

                FileOutputStream outputStream = new FileOutputStream(new File(path + "\\_" + name));

                int i = inputStream.read();

                while (i != -1) {

                    outputStream.write(i);

                    i = inputStream.read();

                }

                outputStream.close();



                System.out.println(path + "\\_" + name + " 已下载");

            }



            rs.close();

            ps.close();



        } catch (SQLException e) {

            System.err.println("SQL " + sql + " 错误");

        } catch (FileNotFoundException e) {

            System.err.println(path + "\\_" + name + " 创建失败");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }



//    /**
//
//     * @param args
//
//     */
//
//    public static void main(String[] args) {
//
//
//
//        PostgresTest o = new PostgresTest();
//
//        o.uploadImage("d:", "23.jpg");
//
//        o.uploadImage("d:", "24.jpg");
//
//        o.downloadImage("d:");
//
//
//
//    }



}
