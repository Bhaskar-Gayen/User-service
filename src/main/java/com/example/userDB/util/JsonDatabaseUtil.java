package com.example.userDB.util;


import com.example.userDB.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonDatabaseUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<User> readUsers(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void writeUsers(String fileName, List<User> users) {
        try {
            mapper.writeValue(new File(fileName), users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
