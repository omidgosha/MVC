package selab.mvc.controllers;

import org.json.JSONObject;
import selab.mvc.models.DataContext;
import selab.mvc.models.DataSet;
import selab.mvc.models.entities.Course;
import selab.mvc.models.entities.Student;
import selab.mvc.views.JsonView;
import selab.mvc.views.View;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddStudentToCourseController extends Controller {

    ArrayList<Course> courses;
    ArrayList<Student> students;
    public AddStudentToCourseController(DataContext dataContext) {
        
        super(dataContext);
        courses = dataContext.getCourses().getAll();
        students = dataContext.getStudents().getAll();
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject input = readJson(body);
        String studentNo = input.getString("studentNo");
        String courseNo = input.getString("courseNo");
        String points = input.getString("points");
//        System.out.println(courses);

        for (Course course:courses
             ) {
//
            if (course.getCourseNo().equals(courseNo)){
                course.addStudents(studentNo);
                course.addGrade(points);
                break;
            }
        }
        for (Student student:students
        ) {
//
            if (student.getStudentNo().equals(studentNo)){
                student.addCourse(courseNo);
                student.addGrade(points);
                break;
            }
        }

        // TODO: Add required codes to associate the student with course
        Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return new JsonView(new JSONObject(result));
    }
    @Override
    protected View getExceptionView(Exception exception) {
        Map<String, String> result = new HashMap<>();
        result.put("message", exception.getMessage());
        return new JsonView(new JSONObject(result));
    }
}
