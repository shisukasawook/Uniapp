package org.uts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentDataHelper {

    private static final String FILE_NAME = "students.data";

    public static Map<String, String> readStudentData() {
        Map<String, String> studentData = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String email = parts[0].trim();
                    String password = parts[1].trim();
                    studentData.put(email, password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentData;
    }
}
