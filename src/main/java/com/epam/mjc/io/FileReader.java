package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {
    public Profile getDataFromFile(File file) {
        String fileData = readFileData(file);
        return parseProfileData(fileData);
    }

    private String readFileData(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private Profile parseProfileData(String fileData) {
        String[] lines = fileData.split(System.lineSeparator());
        String name = null;
        int age = 0;
        String email = null;
        Long phone = null;

        for (String line : lines) {
            if (line.startsWith("Name:")) {
                name = line.substring(line.indexOf(":") + 1).trim();
            } else if (line.startsWith("Age:")) {
                age = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
            } else if (line.startsWith("Email:")) {
                email = line.substring(line.indexOf(":") + 1).trim();
            } else if (line.startsWith("Phone:")) {
                phone = Long.parseLong(line.substring(line.indexOf(":") + 1).trim());
            }
        }

        return new Profile(name, age, email, phone);
    }
}
