package com.artem.studentdatabase.provider;

import com.artem.studentdatabase.entity.Group;
import com.artem.studentdatabase.entity.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by denis on 19.03.2016.
 */
public class RemoteInformationProvider implements InformationProvider {
    public static final String serversIP = "http://10.0.2.2:8080/StudentDatabase";

    @Override
    public boolean insertStudent(String Name, String SecondName, String ThirdName, Group group) {
        try {
            Student student = new Student();
            student.setName(Name);
            student.setLastName(SecondName);
            student.setThirdName(ThirdName);
            Gson gson = new Gson();
            String jsonRequest = gson.toJson(student);
            Long newId = Long.decode(new AsyncHttpTask().execute(new HttpRequestObject(serversIP + "/students", jsonRequest, "POST")).get());
            new AsyncHttpTask().execute(new HttpRequestObject(serversIP + "/groups/" + group.getId() +"/students", gson.toJson(newId), "POST"));
            return true;
        } catch (InterruptedException | ExecutionException e) {
            return false;
        }
    }

    @Override
    public boolean updateStudent(Long id, String Name, String SecondName, String ThirdName, Group group) {
        try {
            Student student = new Student();
            student.setName(Name);
            student.setLastName(SecondName);
            student.setThirdName(ThirdName);
            Gson gson = new Gson();
            String jsonRequet = gson.toJson(student);
            HttpRequestObject requestObject = new HttpRequestObject(serversIP + "/students/" + id + "/update", jsonRequet, "POST");
            new AsyncHttpTask().execute(requestObject).get();
            new AsyncHttpTask().execute(new HttpRequestObject(serversIP + "/groups/" + group.getId() +"/students", gson.toJson(id), "POST"));
            return true;
        } catch (InterruptedException | ExecutionException e) {
            return false;
        }
    }

    @Override
    public ArrayList<Group> getAllGroups() {
        try {
            String jsonResponse;
            HttpRequestObject requestObject = new HttpRequestObject(serversIP + "/groups", "", "GET");
            jsonResponse = new AsyncHttpTask().execute(requestObject).get();
            Gson gson = new Gson();
            return gson.fromJson(jsonResponse, new TypeToken<ArrayList<Group>>() {
            }.getType());
        } catch (InterruptedException | ExecutionException e) {
            return new ArrayList<Group>();
        }
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        try {
            String jsonResponse;
            HttpRequestObject requestObject = new HttpRequestObject(serversIP + "/students", "", "GET");
            jsonResponse = new AsyncHttpTask().execute(requestObject).get();
            Gson gson = new Gson();
            return gson.fromJson(jsonResponse, new TypeToken<ArrayList<Student>>() {
            }.getType());
        } catch (InterruptedException | ExecutionException e) {
            return new ArrayList<Student>();
        }


    }

    @Override
    public Student getStudentById(Long id) {
        try {
            String jsonResponse;
            HttpRequestObject requestObject = new HttpRequestObject(serversIP + "/students/" + id, "", "GET");
            jsonResponse = new AsyncHttpTask().execute(requestObject).get();
            Gson gson = new Gson();
            return gson.fromJson(jsonResponse, Student.class);
        } catch (InterruptedException | ExecutionException e) {
            return new Student();
        }
    }

    @Override
    public boolean deleteStudent(Long id) {
        try {
            HttpRequestObject requestObject = new HttpRequestObject(serversIP + "/students/" + id + "/delete","", "GET");
            new AsyncHttpTask().execute(requestObject).get();
            return true;
        }catch (InterruptedException | ExecutionException e){
            return false;
        }
    }

    @Override
    public boolean deleteGroup(long id) {
        try {
            HttpRequestObject requestObject = new HttpRequestObject(serversIP + "/groups/" + id + "/delete", "", "GET");
            new AsyncHttpTask().execute(requestObject).get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            return false;
        }
    }

    @Override
    public Group getGroupById(long id) {
        try {
            String jsonResponse;
            HttpRequestObject requestObject = new HttpRequestObject(serversIP + "/groups/" + id, "", "GET");
            jsonResponse = new AsyncHttpTask().execute(requestObject).get();
            Gson gson = new Gson();
            return gson.fromJson(jsonResponse, Group.class);
        } catch (InterruptedException | ExecutionException e) {
            return new Group();
        }
    }

    public boolean insertGroup(String name) {
        try {
            Group group = new Group();
            group.setName(name);
            Gson gson = new Gson();
            String jsonRequet = gson.toJson(group);
            HttpRequestObject requestObject = new HttpRequestObject(serversIP + "/groups", jsonRequet, "POST");
            new AsyncHttpTask().execute(requestObject).get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            return false;
        }
    }

    @Override
    public Group getStudentGroup(long id) {
        try {
            String jsonResponse;
            HttpRequestObject requestObject = new HttpRequestObject(serversIP + "/students/" + id + "/group", "", "GET");
            jsonResponse = new AsyncHttpTask().execute(requestObject).get();
            Gson gson = new Gson();
            return gson.fromJson(jsonResponse, Group.class);
        } catch (InterruptedException | ExecutionException e) {
            return new Group();
        }
    }

    public boolean updateGroup(long group_id, String name) {
        try {
            Group group = new Group();
            group.setId(group_id);
            group.setName(name);
            Gson gson = new Gson();
            String jsonRequet = gson.toJson(group);
            HttpRequestObject requestObject = new HttpRequestObject(serversIP + "/groups/" + group_id + "/update", jsonRequet, "POST");
            new AsyncHttpTask().execute(requestObject).get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            return false;
        }
    }



}
