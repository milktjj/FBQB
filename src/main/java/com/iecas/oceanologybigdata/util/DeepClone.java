package com.iecas.oceanologybigdata.util;

import java.io.*;

public class DeepClone implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 利用序列化和反序列化进行对象的深拷贝
     * @return
     * @throws Exception
     */

    public Object deepClone() throws Exception{
        //序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(this);

        //反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();
    }
}
