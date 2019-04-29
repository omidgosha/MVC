package selab.mvc.models.entities;

import selab.mvc.models.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;
    private String courses;
    private ArrayList<Integer> grades;
    private int average;
    public Student(){
        this.average = 0;
        this.courses = "";
        this.grades = new ArrayList<Integer>() ;
    }
    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }
    public void addGrade(String points){
        this.grades.add(Integer.parseInt(points));
    }
    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public void setCourse(String courseNo){
        this.courses = courseNo;
    }
    public void addCourse(String courseNo){
        this.courses = this.courses.concat(courseNo.concat(","));
    }

    public float getAverage() {
        // TODO: Calculate and return the average of the points
        int avg = 0;
        for (Integer grade:grades
             ) {
            avg = avg + grade;
        }
        avg = avg / grades.size();
        return avg;
    }

    public String getCourses() {
        // TODO: Return a comma separated list of course names
        return this.courses;
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }
}
