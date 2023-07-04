package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public static Profile getDataFromFile(File file){

        String[] allStr = new String[5];

        try (FileInputStream fileInputStream = new FileInputStream(file)){
            StringBuilder str = new StringBuilder();
            int ch;
            boolean add = false;
            while((ch=fileInputStream.read())!=-1){
                if ((char)ch == ':') {
                    add = true;
                }
                else if ((char)ch == Character.LINE_SEPARATOR) {
                    add = false;
                }
                else if (add){
                    str.append((char)ch);
                }
            }
            str = new StringBuilder(str.substring(1));
            System.out.println(str);
            allStr = str.toString().split(" ");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new Profile(allStr[0],Integer.parseInt(allStr[1]),allStr[2],Long.parseLong(allStr[3]));
    }
}
