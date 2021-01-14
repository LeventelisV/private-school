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
import privateschool.Main;

/**
 *
 * @author vaggelis
 */
public class Student {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int tuitionFees;
    private ArrayList<Course> coursesOfTheStudent = new ArrayList<Course>();

    private static ArrayList<Student> studentsList = new ArrayList<Student>();

    public Student(String firstName, String lastName, Date dateOfBirth, int tuitionFees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getFees() {
        return tuitionFees;
    }

    public ArrayList<Course> getCoursesOfTheStudent() {
        return coursesOfTheStudent;
    }

    public void addCoursesOfTheStudent(Course course) {
        coursesOfTheStudent.add(course);

    }

    public static ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    @Override
    public String toString() {
        return "Student{" + "firstName=" + firstName + ", lastName=" + lastName + ", tuitionFees=" + tuitionFees + '}';
    }

    public static Student createStudent() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String firstName = "";
        String lastName = "";
        boolean validString = false;
        while (validString == false) {
            System.out.println("Παρακαλώ εισάγετε το όνομα του νέου μαθητή");
            firstName = scanner.next();
            validString = Main.isStringOnlyAlphabet(firstName);
        }
        validString = false;
        while (validString == false) {

            System.out.println("Παρακαλώ εισάγετε το επώνυμο του νέου μαθητή");
            lastName = scanner.next();
            validString = Main.isStringOnlyAlphabet(lastName);
        }

        String dateBirth = " ";
        boolean dateValid = false;

        while (dateValid == false) {
            System.out.println("Παρακαλώ εισάγετε την ημερομηνία γέννησης του νέου μαθητή dd-mm-yyyy");

            dateBirth = scanner.next();
            dateValid = ValidateDate.isValidDate(dateBirth);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");

        Date dateOfBirth = formatter.parse(dateBirth);
        int tuitionFees = -1;
        String fees = " ";
        boolean validNumber = false;
        while (validNumber == false) {
            System.out.println("Παρακαλώ εισάγετε τα δίδακτρα του νέου μαθητή");
            fees = scanner.next();

            validNumber = Main.isValidNumeric(fees);
        }
        tuitionFees = Integer.parseInt(fees);
        Student student = new Student(firstName, lastName, dateOfBirth, tuitionFees);
        studentsList.add(student);

        return student;

    }

    public static void printTheStudents() {
        if (!(Student.getStudentsList().isEmpty())) {
            for (int i = 0; i < Student.getStudentsList().size(); i++) {
                System.out.print(i + 1 + ".  ");
                System.out.println(Student.getStudentsList().get(i));
            }
        } else {
            System.out.println("Δεν έχετε δημιουργήσει students");
        }

    }

    public static Student chooseStudent() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Παρακαλώ διαλέξτε επώνυμο μαθητή");
        for (int i = 0; i < Student.getStudentsList().size(); i++) {
            System.out.println(Student.getStudentsList().get(i).getLastName());

        }

        String student = sc.next();
        for (int i = 0; i < Student.getStudentsList().size(); i++) {
            if (student.equals(Student.getStudentsList().get(i).getLastName())) {
                return Student.getStudentsList().get(i);
            }

        }
        return null;

    }

    public static void printTheAssignmensPerStudent(Student student) {

        if (!(student.getCoursesOfTheStudent().isEmpty())) {

            for (int i = 0; i < student.getCoursesOfTheStudent().size(); i++) {

                if (!(student.getCoursesOfTheStudent().get(i).getAssignmentsOfTheCourse().isEmpty())) {

                    for (int j = 0; j < student.getCoursesOfTheStudent().get(i).getAssignmentsOfTheCourse().size(); j++) {

                        System.out.println(student.getCoursesOfTheStudent().get(i).getAssignmentsOfTheCourse().get(j));
                    }
                } else {
                    System.out.println("Στα courses του μαθητή δεν υπάρχουν εργασίες");
                }

            }
        } else {
            System.out.println("Στο συγκεκριμένο μαθητή δεν έχουν ανατεθεί εργασίες");
        }
    }

}
