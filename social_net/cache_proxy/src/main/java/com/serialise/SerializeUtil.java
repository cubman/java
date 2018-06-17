package com.serialise;

import java.io.*;

public class SerializeUtil {

    private SerializeUtil() {

    }

    public static void serializeObject(Object obj, String fileName){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))){
            oos.writeObject(obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerializeObject(String fileName) throws ClassNotFoundException{
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))){
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
