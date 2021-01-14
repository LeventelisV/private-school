/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import models.Student;
import models.Course;
import models.Trainer;
import models.Assignment;
import models.ValidateDate;

public class Main {

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        String answer = "0";

        while (!(answer.toLowerCase().equals("y")) && !(answer.toLowerCase().equals("n"))) {
            System.out.println("Hello!!\nΘα θελατε να εισάγετε δεδομένα στο σύστημα private school;\nΕπιλέξτε y or n: ");
            answer = sc.next();
        }

        if (answer.equals("n")) {
            System.out.println("Θα χρησιμοποιηθούν synthetic data");
            createSyntheticData();

        } else {
            insertData();
        }
        printData();

    }

    public static void printStudentsWithMoreCourses() {
        int countStudents = 0;
        for (int i = 0; i < Student.getStudentsList().size(); i++) {
            if (Student.getStudentsList().get(i).getCoursesOfTheStudent().size() > 1) {
                System.out.println(i + 1 + ".  " + Student.getStudentsList().get(i));
                countStudents += 1;
            }

        }
        if (countStudents == 0) {
            System.out.println("Δεν υπάρχουν μαθητές με περισσότερα από ενα προγράμματα");
        }

    }

    public static void insertData() throws ParseException {
        Scanner sc = new Scanner(System.in);
        boolean insertData = true;

        while (insertData == true) {

            String answer = " ";
            while ((!(answer.equals("t"))) && (!(answer.equals("c"))) && (!(answer.equals("s"))) && (!(answer.equals("a")))) {
                System.out.println("Tι πεδίο θα ήθελες να εισάγεις?");
                System.out.println("δώσε t για trainer");
                System.out.println("δώσε c για course");
                System.out.println("δώσε s για student");
                System.out.print("δώσε a για assignment:  ");
                answer = sc.next().toLowerCase();

            }

            switch (answer) {

                case "t":
                    Trainer trainer = Trainer.createTrainer();
                    System.out.println("Θέλετε να εντάξετε τον trainer σε κάποιο course; ");
                    String putInCourse = sc.next().toLowerCase();
                    if (putInCourse.equals("y")) {
                        System.out.println("Τα διαθέσιμα σας course είναι");
                        Course.printTheCourses();
                        if (!Course.getCoursesList().isEmpty()) {
                            Course course = Course.chooseCourse();
                            course.addTrainerToTheCourse(trainer);

                        }

                    }

                    break;
                case "c":
                    Course course = Course.createCourse();
                    System.out.println("Θέλετε να εντάξετε το course  σε κάποιο μαθητη; ");
                    String putInStudent = sc.next().toLowerCase();
                    if (putInStudent.equals("y")) {
                        System.out.println("Οι διαθέσιμοι μαθητές είναι");
                        Student.printTheStudents();
                        if (!Student.getStudentsList().isEmpty()) {

                            Student student = Student.chooseStudent();
                            student.addCoursesOfTheStudent(course);
                            course.addStudentToTheCourse(student);

                        }

                    }

                    break;
                case "s":
                    Student student = Student.createStudent();
                    System.out.println("Θέλετε να εντάξετε το μαθητή  σε κάποιο course; ");
                    String putStudInCourse = sc.next().toLowerCase();
                    if (putStudInCourse.equals("y")) {
                        System.out.println("Τα διαθέσιμα courses  είναι");
                        Course.printTheCourses();
                        if (!Course.getCoursesList().isEmpty()) {
                            Course course1 = Course.chooseCourse();
                            course1.addStudentToTheCourse(student);
                            student.addCoursesOfTheStudent(course1);
                        }
                    }
                    break;
                case "a":
                    Assignment assignment = Assignment.createAssignment();

                    System.out.println("Θέλετε να εντάξετε thn εργασία  σε κάποιο course; ");
                    String putAssignInCourse = sc.next().toLowerCase();
                    if (putAssignInCourse.equals("y")) {
                        System.out.println("Τα διαθέσιμα courses  είναι");
                        Course.printTheCourses();
                        if (!Course.getCoursesList().isEmpty()) {
                            Course course1 = Course.chooseCourse();
                            course1.addAssignmentToTheCourse(assignment);

                        }
                    }

                    break;
            }
            String continueInsert = " ";

            while (!(continueInsert.toLowerCase().equals("y")) && (!(continueInsert.toLowerCase().equals("n")))) {
                System.out.println("Θέλετε να συνεχίσετε να εισάγετε δεδομενα;\nΕπιλέξτε y or n: ");
                continueInsert = sc.next();
            }
            if (continueInsert.toLowerCase().equals("n")) {
                insertData = false;
                System.out.println("Τέλος καταχώρησης δεδομένων");
            }
        }
    }

    public static void addStudentToACourse(Student student) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < Course.getCoursesList().size(); i++) {
            System.out.println(Course.getCoursesList().get(i).getTitle());
        }
        System.out.println("Παρακαλώ διαλέξτε κάποιο course");
        String answerOfTheUser = sc.next();
        for (int i = 0; i < Course.getCoursesList().size(); i++) {
            if (Course.getCoursesList().get(i).getTitle().equals(answerOfTheUser)) {
                Course.getCoursesList().get(i).addStudentToTheCourse(student);

            }
        }

    }

    public static void addTrainerToACourse(Trainer Trainer) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < Course.getCoursesList().size(); i++) {
            System.out.println(Course.getCoursesList().get(i).getTitle());
        }
        System.out.println("Παρακαλώ διαλέξτε κάποιο course");
        String answerOfTheUser = sc.next();
        for (int i = 0; i < Course.getCoursesList().size(); i++) {
            if (Course.getCoursesList().get(i).getTitle().equals(answerOfTheUser)) {
                Course.getCoursesList().get(i).addTrainerToTheCourse(Trainer);
            }
        }

    }

    public static void addAssignmentToACourse(Assignment Assignment) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < Course.getCoursesList().size(); i++) {
            System.out.println(Course.getCoursesList().get(i).getTitle());
        }
        System.out.println("Παρακαλώ διαλέξτε κάποιο course");
        String answerOfTheUser = sc.next();
        for (int i = 0; i < Course.getCoursesList().size(); i++) {
            if (Course.getCoursesList().get(i).getTitle().equals(answerOfTheUser)) {
                Course.getCoursesList().get(i).addAssignmentToTheCourse(Assignment);
            }
        }
    }

    public static void printData() throws ParseException {

        Scanner sc = new Scanner(System.in);

        boolean printing = true;
        while (printing == true) {
            String answer = "0";
            while (!(answer.equals("1")) && !(answer.equals("2")) && !(answer.equals("3")) && !(answer.equals("4"))
                    && !(answer.equals("5")) && !(answer.equals("6")) && !(answer.equals("7")) && !(answer.equals("8")) && !(answer.equals("9")) && !(answer.equals("10"))) {
                System.out.println("Τι θα θέλατε να τυπώσετε;");
                System.out.println("1 : λίστα μαθητών");
                System.out.println("2 : λίστα εκπαιδευτών");
                System.out.println("3 : λίστα εργασιών");
                System.out.println("4 : λίστα τμημάτων");
                System.out.println("5 : τους μαθητες κάποιου course");
                System.out.println("6 : τους εκπαιδευτές κάποιου course");
                System.out.println("7 : τις εργασίες κάποιου course");
                System.out.println("8 : τις εργασίες κάποιου μαθητή");
                System.out.println("9 : τους μαθητές που συμμετέχουν σε περισσοτερα από ένα προγράμματα");
                System.out.println("10 : τις εργασίες που πρέπει να παραδοθούν μια συγκεκριμένη εβδομάδα");

                answer = sc.next();

            }
            switch (answer) {
                case ("1"):
                    Student.printTheStudents();
                    break;
                case ("2"):
                    Trainer.printTheTrainers();
                    break;
                case ("3"):
                    Assignment.printTheAssignments();
                    break;
                case ("4"):
                    Course.printTheCourses();
                    break;
                case ("5"):
                    Course.printTheCourses();
                    Course.printStudentsOfTheCourse(Course.chooseCourse());
                    break;

                case ("6"):
                    Course.printTheCourses();
                    Course.printTrainersOfTheCourse(Course.chooseCourse());
                    break;
                case ("7"):
                    Course.printTheCourses();
                    Course.printAssignmentsOfTheCourse(Course.chooseCourse());
                    break;

                case ("8"):
                    Student.printTheAssignmensPerStudent(Student.chooseStudent());
                    break;
                case ("9"):
                    printStudentsWithMoreCourses();
                    break;
                case ("10"):
                    searchForAssignmentsAtAWeek();
                    break;

            }
            String continuePrinting = "";

            while (!(continuePrinting.toLowerCase().equals("y")) && (!(continuePrinting.toLowerCase().equals("n")))) {
                System.out.println("Θέλετε να συνεχίσετε να τυπώνετε δεδομενα;\nΕπιλέξτε y or n: ");
                continuePrinting = sc.next();
            }
            if (continuePrinting.toLowerCase().equals("n")) {
                printing = false;
                System.out.println("Τέλος εκτύπωσης δεδομένων");
            }

        }
    }

    public static void createSyntheticData() throws ParseException {

        String sdate = "10-01-2020";
        String sdate1 = "30-06-2020";
        String sdate2 = "01-01-2020";
        String sdate3 = "30-03-2020";
        String sdate4 = "01-01-2020";
        String sdate5 = "30-03-2020";
        String sdate6 = "02-01-2000";
        String sdate7 = "22-03-1986";
        String sdate8 = "01-04-1990";

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(sdate);
        Date date1 = formatter.parse(sdate1);
        Date date2 = formatter.parse(sdate2);
        Date date3 = formatter.parse(sdate3);
        Date date4 = formatter.parse(sdate4);
        Date date5 = formatter.parse(sdate5);
        Date date6 = formatter.parse(sdate6);
        Date date7 = formatter.parse(sdate7);
        Date date8 = formatter.parse(sdate8);

        Course course1 = new Course("c1", "java", "full time", date, date1);
        Course course2 = new Course("c2", "java", "part time", date2, date3);
        Course course3 = new Course("c3", "c#", "part time", date4, date5);
        Course course4 = new Course("c4", "java", "part time", date4, date5);
        Course course5 = new Course("c5", "c#", "full time", date, date1);

        Course.getCoursesList().add(course1);
        Course.getCoursesList().add(course2);
        Course.getCoursesList().add(course2);
        Course.getCoursesList().add(course4);
        Course.getCoursesList().add(course5);

        Student student01 = new Student("vaggelis", "leventelis", date6, 2200);
        Student student02 = new Student("alexandros", "korovesis", date6, 2200);
        Student student03 = new Student("dimitris", "katsimagkos", date7, 2200);
        Student student04 = new Student("xristos", "mandarakas", date7, 2200);
        Student student05 = new Student("aggelos", "sikelianos", date8, 2200);

        Student.getStudentsList().add(student01);
        Student.getStudentsList().add(student02);
        Student.getStudentsList().add(student03);
        Student.getStudentsList().add(student04);
        Student.getStudentsList().add(student05);

        Trainer trainer1 = new Trainer("nikos", "kales", "oop");
        Trainer trainer2 = new Trainer("kostas", "fragkos", "javascript");
        Trainer trainer3 = new Trainer("katerina", "fragkaki", "databases");

        Trainer.getTrainersList().add(trainer1);
        Trainer.getTrainersList().add(trainer2);
        Trainer.getTrainersList().add(trainer3);

        Assignment assignment1 = new Assignment("privateSchool_java", "IntroductionToOpp", date3, -1, -1);
        Assignment assignment2 = new Assignment("privateSchool_c#", "IntroductionToOpp", date5, -1, -1);

        Assignment.getAssignments().add(assignment1);
        Assignment.getAssignments().add(assignment2);

        course1.addStudentToTheCourse(student01);
        course1.addStudentToTheCourse(student02);
        course2.addStudentToTheCourse(student03);
        course3.addStudentToTheCourse(student01);
        course3.addStudentToTheCourse(student04);
        course4.addStudentToTheCourse(student05);

        student01.addCoursesOfTheStudent(course1);
        student01.addCoursesOfTheStudent(course3);
        student02.addCoursesOfTheStudent(course1);
        student03.addCoursesOfTheStudent(course2);
        student04.addCoursesOfTheStudent(course3);
        student05.addCoursesOfTheStudent(course4);

        course1.addTrainerToTheCourse(trainer1);
        course2.addTrainerToTheCourse(trainer1);
        course3.addTrainerToTheCourse(trainer1);
        course4.addTrainerToTheCourse(trainer1);
        course1.addTrainerToTheCourse(trainer2);
        course2.addTrainerToTheCourse(trainer2);
        course3.addTrainerToTheCourse(trainer2);
        course4.addTrainerToTheCourse(trainer2);
        course1.addTrainerToTheCourse(trainer3);
        course2.addTrainerToTheCourse(trainer3);
        course3.addTrainerToTheCourse(trainer3);
        course4.addTrainerToTheCourse(trainer3);

        course1.addAssignmentToTheCourse(assignment1);
        course2.addAssignmentToTheCourse(assignment1);
        course3.addAssignmentToTheCourse(assignment2);
        course4.addAssignmentToTheCourse(assignment1);

    }

    public static boolean isStringOnlyAlphabet(String str) {
        return ((str != null)
                && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$")));
    }

    public static boolean isValidNumeric(String str) {
        str = str.trim(); // trims the white spaces. 

        if (str.length() == 0) {
            return false;
        }

        // if string is of length 1 and the only 
        // character is not a digit 
        if (str.length() == 1 && !Character.isDigit(str.charAt(0))) {
            return false;
        }

        // If the 1st char is not '+', '-', '.' or digit 
        if (str.charAt(0) != '+' && str.charAt(0) != '-'
                && !Character.isDigit(str.charAt(0))
                && str.charAt(0) != '.') {
            return false;
        }

        // To check if a '.' or 'e' is found in given 
        // string. We use this flag to make sure that 
        // either of them appear only once. 
        boolean flagDotOrE = false;

        for (int i = 1; i < str.length(); i++) {
            // If any of the char does not belong to 
            // {digit, +, -, ., e} 
            if (!Character.isDigit(str.charAt(i))
                    && str.charAt(i) != 'e' && str.charAt(i) != '.'
                    && str.charAt(i) != '+' && str.charAt(i) != '-') {
                return false;
            }

            if (str.charAt(i) == '.') {
                // checks if the char 'e' has already 
                // occurred before '.' If yes, return 0. 
                if (flagDotOrE == true) {
                    return false;
                }

                // If '.' is the last character. 
                if (i + 1 >= str.length()) {
                    return false;
                }

                // if '.' is not followed by a digit. 
                if (!Character.isDigit(str.charAt(i + 1))) {
                    return false;
                }
            } else if (str.charAt(i) == 'e') {
                // set flagDotOrE = 1 when e is encountered. 
                flagDotOrE = true;

                // if there is no digit before 'e'. 
                if (!Character.isDigit(str.charAt(i - 1))) {
                    return false;
                }

                // If 'e' is the last Character 
                if (i + 1 >= str.length()) {
                    return false;
                }

                // if e is not followed either by 
                // '+', '-' or a digit 
                if (!Character.isDigit(str.charAt(i + 1))
                        && str.charAt(i + 1) != '+'
                        && str.charAt(i + 1) != '-') {
                    return false;
                }
            }
        }

        /* If the string skips all above cases, then 
           it is numeric*/
        return true;
    }

    public static void searchForAssignmentsAtAWeek() throws ParseException {
        Scanner sc = new Scanner(System.in);
        
        String str = "01-01-1900";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(str, formatter);
        LocalDate localDate1 = LocalDate.parse(str, formatter);
        
            System.out.println("Παρακαλώ δωστε ημερομηνια για αναζητηση εργασιών (dd-MM-yyy): ");
            str = sc.next();

            localDate = LocalDate.parse(str, formatter);
            
        DayOfWeek dayOfWeek
                = DayOfWeek.from(localDate);
        int val = dayOfWeek.getValue();

        LocalDate localDate2;
        LocalDate localDate3;
        LocalDate localDate4;
        LocalDate localDate5;
        String foramttedString1;
        String foramttedString2;
        String foramttedString3;
        String foramttedString4;
        String foramttedString5;
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter4 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter5 = new SimpleDateFormat("dd-MM-yyyy");
        int metritisErgasiwn=0 ;
        switch (val) {
            case 1:
                metritisErgasiwn = 0;

                localDate2 = localDate.plusDays(1);
                localDate3 = localDate.plusDays(2);
                localDate4 = localDate.plusDays(3);
                localDate5 = localDate.plusDays(4);
                foramttedString1 = localDate.format(formatter);

                foramttedString2 = localDate2.format(formatter);

                foramttedString3 = localDate3.format(formatter);

                foramttedString4 = localDate4.format(formatter);

                foramttedString5 = localDate5.format(formatter);

                Date dateAssign1 = formatter1.parse(foramttedString1);
                Date dateAssign2 = formatter2.parse(foramttedString2);
                Date dateAssign3 = formatter3.parse(foramttedString3);
                Date dateAssign4 = formatter4.parse(foramttedString4);
                Date dateAssign5 = formatter5.parse(foramttedString5);
                System.out.println("H αναζήτηση σας περιλαμβάνει τις παρακάτω ημερομηνίες");
                System.out.println(dateAssign1);
                System.out.println(dateAssign2);
                System.out.println(dateAssign3);
                System.out.println(dateAssign4);
                System.out.println(dateAssign5);

                for (int i = 0; i < Assignment.getAssignments().size(); i++) {

                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign1)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign2)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign3)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign4)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign5)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                }
                break;
            case 2:
                metritisErgasiwn = 0;

                localDate2 = localDate.minusDays(1);
                localDate3 = localDate.plusDays(1);
                localDate4 = localDate.plusDays(2);
                localDate5 = localDate.plusDays(3);
                foramttedString1 = localDate.format(formatter);

                foramttedString2 = localDate2.format(formatter);

                foramttedString3 = localDate3.format(formatter);

                foramttedString4 = localDate4.format(formatter);

                foramttedString5 = localDate5.format(formatter);

                dateAssign1 = formatter1.parse(foramttedString1);
                dateAssign2 = formatter2.parse(foramttedString2);
                dateAssign3 = formatter3.parse(foramttedString3);
                dateAssign4 = formatter4.parse(foramttedString4);
                dateAssign5 = formatter5.parse(foramttedString5);
                System.out.println("H αναζήτηση σας περιλαμβάνει τις παρακάτω ημερομηνίες");
                System.out.println(dateAssign1);
                System.out.println(dateAssign2);
                System.out.println(dateAssign3);
                System.out.println(dateAssign4);
                System.out.println(dateAssign5);

                for (int i = 0; i < Assignment.getAssignments().size(); i++) {

                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign1)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign2)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign3)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign4)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign5)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                }
                break;
            case 3:
                metritisErgasiwn = 0;
                localDate2 = localDate.minusDays(2);
                localDate3 = localDate.minusDays(1);
                localDate4 = localDate.plusDays(1);
                localDate5 = localDate.plusDays(2);
                foramttedString1 = localDate.format(formatter);

                foramttedString2 = localDate2.format(formatter);

                foramttedString3 = localDate3.format(formatter);

                foramttedString4 = localDate4.format(formatter);

                foramttedString5 = localDate5.format(formatter);

                dateAssign1 = formatter1.parse(foramttedString1);
                dateAssign2 = formatter2.parse(foramttedString2);
                dateAssign3 = formatter3.parse(foramttedString3);
                dateAssign4 = formatter4.parse(foramttedString4);
                dateAssign5 = formatter5.parse(foramttedString5);
                System.out.println("H αναζήτηση σας περιλαμβάνει τις παρακάτω ημερομηνίες");
                System.out.println(dateAssign1);
                System.out.println(dateAssign2);
                System.out.println(dateAssign3);
                System.out.println(dateAssign4);
                System.out.println(dateAssign5);

                for (int i = 0; i < Assignment.getAssignments().size(); i++) {

                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign1)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign2)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign3)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign4)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign5)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                }
                break;
            case 4:
                metritisErgasiwn = 0;
                localDate2 = localDate.minusDays(3);
                localDate3 = localDate.minusDays(2);
                localDate4 = localDate.minusDays(1);
                localDate5 = localDate.plusDays(1);
                foramttedString1 = localDate.format(formatter);

                foramttedString2 = localDate2.format(formatter);

                foramttedString3 = localDate3.format(formatter);

                foramttedString4 = localDate4.format(formatter);

                foramttedString5 = localDate5.format(formatter);

                dateAssign1 = formatter1.parse(foramttedString1);
                dateAssign2 = formatter2.parse(foramttedString2);
                dateAssign3 = formatter3.parse(foramttedString3);
                dateAssign4 = formatter4.parse(foramttedString4);
                dateAssign5 = formatter5.parse(foramttedString5);
                System.out.println("H αναζήτηση σας περιλαμβάνει τις παρακάτω ημερομηνίες");
                System.out.println(dateAssign1);
                System.out.println(dateAssign2);
                System.out.println(dateAssign3);
                System.out.println(dateAssign4);
                System.out.println(dateAssign5);

                for (int i = 0; i < Assignment.getAssignments().size(); i++) {

                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign1)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign2)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign3)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign4)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign5)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                }
                break;
            case 5:
                metritisErgasiwn = 0;
                localDate2 = localDate.minusDays(4);
                localDate3 = localDate.minusDays(3);
                localDate4 = localDate.minusDays(2);
                localDate5 = localDate.minusDays(1);
                foramttedString1 = localDate.format(formatter);

                foramttedString2 = localDate2.format(formatter);

                foramttedString3 = localDate3.format(formatter);

                foramttedString4 = localDate4.format(formatter);

                foramttedString5 = localDate5.format(formatter);

                dateAssign1 = formatter1.parse(foramttedString1);
                dateAssign2 = formatter2.parse(foramttedString2);
                dateAssign3 = formatter3.parse(foramttedString3);
                dateAssign4 = formatter4.parse(foramttedString4);
                dateAssign5 = formatter5.parse(foramttedString5);
                System.out.println("H αναζήτηση σας περιλαμβάνει τις παρακάτω ημερομηνίες");
                System.out.println(dateAssign1);
                System.out.println(dateAssign2);
                System.out.println(dateAssign3);
                System.out.println(dateAssign4);
                System.out.println(dateAssign5);

                for (int i = 0; i < Assignment.getAssignments().size(); i++) {

                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign1)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign2)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign3)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign4)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign5)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                }
                break;
        case 6:
                metritisErgasiwn = 0;
                localDate1 = localDate.minusDays(5);
                localDate2 = localDate.minusDays(4);
                localDate3 = localDate.minusDays(3);
                localDate4 = localDate.minusDays(2);
                localDate5 = localDate.minusDays(1);
                foramttedString1 = localDate.format(formatter);

                foramttedString2 = localDate2.format(formatter);

                foramttedString3 = localDate3.format(formatter);

                foramttedString4 = localDate4.format(formatter);

                foramttedString5 = localDate5.format(formatter);

                dateAssign1 = formatter1.parse(foramttedString1);
                dateAssign2 = formatter2.parse(foramttedString2);
                dateAssign3 = formatter3.parse(foramttedString3);
                dateAssign4 = formatter4.parse(foramttedString4);
                dateAssign5 = formatter5.parse(foramttedString5);
                System.out.println("H αναζήτηση σας περιλαμβάνει τις παρακάτω ημερομηνίες");
                System.out.println(dateAssign1);
                System.out.println(dateAssign2);
                System.out.println(dateAssign3);
                System.out.println(dateAssign4);
                System.out.println(dateAssign5);

                for (int i = 0; i < Assignment.getAssignments().size(); i++) {

                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign1)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign2)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign3)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign4)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign5)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                }
                break;
                case 7:
                metritisErgasiwn = 0;
                localDate1 = localDate.minusDays(6);
                localDate2 = localDate.minusDays(5);
                localDate3 = localDate.minusDays(4);
                localDate4 = localDate.minusDays(3);
                localDate5 = localDate.minusDays(2);
                foramttedString1 = localDate.format(formatter);

                foramttedString2 = localDate2.format(formatter);

                foramttedString3 = localDate3.format(formatter);

                foramttedString4 = localDate4.format(formatter);

                foramttedString5 = localDate5.format(formatter);

                dateAssign1 = formatter1.parse(foramttedString1);
                dateAssign2 = formatter2.parse(foramttedString2);
                dateAssign3 = formatter3.parse(foramttedString3);
                dateAssign4 = formatter4.parse(foramttedString4);
                dateAssign5 = formatter5.parse(foramttedString5);
                System.out.println("H αναζήτηση σας περιλαμβάνει τις παρακάτω ημερομηνίες");
                System.out.println(dateAssign1);
                System.out.println(dateAssign2);
                System.out.println(dateAssign3);
                System.out.println(dateAssign4);
                System.out.println(dateAssign5);

                for (int i = 0; i < Assignment.getAssignments().size(); i++) {

                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign1)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign2)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign3)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign4)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                    if (Assignment.getAssignments().get(i).getSubDateTime().equals(dateAssign5)) {
                        System.out.println("H παρακάτω εργασία πρέπει να υποβληθεί την εβδομάδα αναζήτησης.");
                        System.out.println(Assignment.getAssignments().get(i));
                        metritisErgasiwn += 1;
                    }
                }
                break;
        
        }
        if (metritisErgasiwn == 0) {
            System.out.println("Δεν υπάρχουν εργασίες για παράδοση την συγκεκριμένη εβδομάδα");
            
        }
    }

   
}
