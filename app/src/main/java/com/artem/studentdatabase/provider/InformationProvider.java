package com.artem.studentdatabase.provider;

import com.artem.studentdatabase.entity.Group;
import com.artem.studentdatabase.entity.Student;

import java.util.ArrayList;

/**
 * Created by denis on 19.03.2016.
 */
public interface InformationProvider {

    boolean insertStudent(String Name, String SecondName, String ThirdName, Group group);
    boolean updateStudent(Long id, String Name, String SecondName, String ThirdName, Group group);
    ArrayList<Group> getAllGroups();
    ArrayList<Student> getAllStudents();
    Student getStudentById(Long id);

    boolean deleteStudent(Long value);

    boolean deleteGroup(long value);

    Group getGroupById(long id);

    boolean updateGroup(long id, String name);

    boolean insertGroup(String name);

    Group getStudentGroup(long id);
}
