package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file){

        String[] allStr = new String[10];

        try (FileInputStream fileInputStream = new FileInputStream(file)){
            String str = "";
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
                    str += (char) ch;
                }
            }
            allStr = str.trim().split(" ");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return new Profile(allStr[0],Integer.parseInt(allStr[1]),allStr[2],Long.parseLong(allStr[3]));
    }

}
