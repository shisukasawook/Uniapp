package org.uts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.uts.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    public static List<Student> readDatabase() throws IOException {
        List<Student> studentList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("database.data"));
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            ObjectMapper objectMapper = new ObjectMapper();
            studentList.add(objectMapper.readValue(line, Student.class));
        }
        return studentList;
    }

    public static void saveDatabase(List<Student> studentList) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("database.data"));
        ObjectMapper objectMapper = new ObjectMapper();
        for(Student student: studentList) {
            writer.write(objectMapper.writeValueAsString(student));
            writer.write("\n");
            
        }
        writer.close();
    }
}
