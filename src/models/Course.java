/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author vaggelis
 */
public class Course {

    private String title;
    private String stream;
    private String type;
    private Date start_date;
    private Date end_date;
    private static ArrayList<Course> coursesList = new ArrayList<Course>();
    private ArrayList<Student> studentsOfTheCourse = new ArrayList<Student>();
    private ArrayList<Trainer> trainersOfTheCourse = new ArrayList<Trainer>();
    private ArrayList<Assignment> assignmentsOfTheCourse = new ArrayList<Assignment>();

    public Course(String title, String stream, String type, Date start_date, Date end_date) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void SetStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public static ArrayList<Course> getCoursesList() {
        return coursesList;
    }

    public ArrayList<Student> getStudentsOfTheCourse() {
        return studentsOfTheCourse;
    }

    public ArrayList<Assignment> getAssignmentsOfTheCourse() {
        return assignmentsOfTheCourse;
    }

    public ArrayList<Trainer> getTrainersOfTheCourse() {
        return trainersOfTheCourse;
    }

    public void addStudentToTheCourse(Student student) {
        studentsOfTheCourse.add(student);
    }

    public void addTrainerToTheCourse(Trainer trainer) {
        trainersOfTheCourse.add(trainer);
    }

    public void addAssignmentToTheCourse(Assignment assignment) {
        assignmentsOfTheCourse.add(assignment);
    }

    @Override
    public String toString() {
        return "Course{" + "title=" + title + ", stream=" + stream + ", type=" + type + ", start_date=" + start_date + ", end_date=" + end_date + '}';
    }

    public static Course createCourse() throws ParseException {

        String courseType = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Παρακαλώ δώστε το ονομα του course: ");
        String title1;
        String type1;

        title1 = scanner.next();
        while (!(courseType.toLowerCase().equals("java")) && !(courseType.toLowerCase().equals("c#"))) {
            System.out.println("Παρακαλώ δώστε το ειδος του course:  ");
            courseType = scanner.next();
        }
        System.out.println("Παρακαλώ δώστε το είδος του προγράμματος");
        type1 = scanner.nextLine();
        type1 = scanner.nextLine();
        String startDate = " ";
        boolean dateValid = false;

        while (dateValid == false) {
            System.out.println("Παρακαλώ δώστε ημερομηνία έναρξης του προγραμματος με την μορφή dd-mm-yyyy");

            startDate = scanner.next();
            dateValid = ValidateDate.isValidDate(startDate);
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        Date start_date = formatter.parse(startDate);
        dateValid = false;
        String endDate = " ";
        while (dateValid == false) {
            System.out.println("Παρακαλώ δώστε ημερομηνία λήξης του προγραμματος με την μορφή dd-mm-yyyy");

            endDate = scanner.next();
            dateValid = ValidateDate.isValidDate(endDate);
        }
        
        
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-mm-yyyy");
        Date end_date = formatter1.parse(endDate);
        Course course = new Course(title1, courseType, type1, start_date, end_date);
        coursesList.add(course);
        return course;

    }

    public static void printTheCourses() {
        if (!(Course.getCoursesList().isEmpty())) {
            for (int i = 0; i < Course.getCoursesList().size(); i++) {
                System.out.print(i + 1 + ".  ");
                System.out.println(Course.getCoursesList().get(i));
            }
        } else {
            System.out.println("Δεν έχετε δημιουργήσει courses");
        }

    }

    public static Course chooseCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Παρακαλώ διαλέξτε τιτλο course");
        String course = sc.next();
        for (int i = 0; i < Course.getCoursesList().size(); i++) {
            if (course.equals(Course.getCoursesList().get(i).getTitle())) {
                return Course.getCoursesList().get(i);
            }

        }
        return null;

    }

    public static void printStudentsOfTheCourse(Course course) {
        if (!(course.getStudentsOfTheCourse().isEmpty())) {
            for (int i = 0; i < course.getStudentsOfTheCourse().size(); i++) {
                System.out.print(i + 1 + ".  ");
                System.out.println(course.getStudentsOfTheCourse().get(i));

            }

        } else {
            System.out.println("Δεν υπάρχουν καταχωρήσεις μαθητων για το συγκεκριμένο course");
        }
    }

    public static void printTrainersOfTheCourse(Course course) {
        if (!(course.getTrainersOfTheCourse().isEmpty())) {
            for (int i = 0; i < course.getTrainersOfTheCourse().size(); i++) {
                System.out.print(i + 1 + ".  ");
                System.out.println(course.getTrainersOfTheCourse().get(i));

            }

        } else {
            System.out.println("Δεν υπάρχουν καταχωρήσεις μαθητων για το συγκεκριμένο course");

        }

    }

    public static void printAssignmentsOfTheCourse(Course course) {
        if (!(course.getAssignmentsOfTheCourse().isEmpty())) {
            for (int i = 0; i < course.getAssignmentsOfTheCourse().size(); i++) {
                System.out.print(i + 1 + ".  ");
                System.out.println(course.getAssignmentsOfTheCourse().get(i));

            }

        } else {
            System.out.println("Δεν υπάρχουν καταχωρήσεις εργασιών για το συγκεκριμένο course");

        }
    }
}
