package com.iecas.oceanologybigdata.util;

import org.ehcache.impl.copy.ReadWriteCopier;
import org.ehcache.spi.copy.Copier;

import java.io.*;

public class FBQBUtils {

    private FBQBUtils() {
        throw new AssertionError();
    }

    public static <T extends Serializable> T clone(Object object) throws IOException,
            ClassNotFoundException {
        // 说明：调用ByteArrayOutputStream或ByteArrayInputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外资源(如文件流)的释放
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }


    public static Object copy(Object o) {
        try {
            return clone(o);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }

}
