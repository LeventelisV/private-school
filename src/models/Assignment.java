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
public class Assignment {

    private String title;
    private String description;
    private Date subDateTime;
    private float oralMark;
    private float totalMark;
    private static ArrayList<Assignment> assignments = new ArrayList<Assignment>();

    public Assignment(String title, String description, Date subDateTime, float oralMark, float totalMark) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public String getTitle() {
        return title;

    }

    public String getDescription() {
        return description;
    }

    public Date getSubDateTime() {
        return subDateTime;
    }

    public float getOralMark() {
        return oralMark;
    }

    public float getTotalMark() {
        return totalMark;
    }

    public static ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public static Assignment createAssignment() throws ParseException {
        Scanner scanner = new Scanner(System.in);

        boolean validString = false;

        System.out.println("Παρακαλώ εισάγετε τον τίτλο της εργασίας: ");
        String title1 = scanner.next();

        String description1 = "";
        while (validString == false) {
            System.out.println("Παρακαλώ εισάγετε την περιγραφή της εργασίας: ");

            
            description1 = scanner.next();
            validString = Main.isStringOnlyAlphabet(description1);
        }

        boolean dateValid = false;
        String subDateTime1 = "";

        while (dateValid == false) {
            System.out.println("Παρακαλώ δώστε την ημερομηνία υποβολής με την μορφή dd-mm-yyyy: ");
            subDateTime1 = scanner.next();
            
            
            dateValid = ValidateDate.isValidDate(subDateTime1);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date subDate = formatter.parse(subDateTime1);
        System.out.println("Παρακαλώ καταχωρήστε προφορική βαθμολόγηση(αν δεν εχει βαθμολογηθεί"
                + "πληκτρολογήστε  -1)");
        float oralMark1 = scanner.nextFloat();
        System.out.println("Παρακαλώ καταχωρήστε συνολική  βαθμολόγηση(αν δεν εχει βαθμολογηθεί"
                + "πληκτρολογήστε  -1)");
        float totalMark1 = scanner.nextFloat();
        Assignment assignment = new Assignment(title1, description1, subDate, oralMark1, totalMark1);
        assignments.add(assignment);
        return assignment;

    }

    @Override
    public String toString() {
        return "Assignment{" + "title=" + title + ", description=" + description + ", subDateTime=" + subDateTime + ", oralMark=" + oralMark + ", totalMark=" + totalMark + '}';
    }

    public static void printTheAssignments() {
        if (!(Assignment.getAssignments().isEmpty())) {
            for (int i = 0; i < Assignment.getAssignments().size(); i++) {
                System.out.print(i + 1 + ".  ");
                System.out.println(Assignment.getAssignments().get(i));
            }
        } else {
            System.out.println("Δεν έχετε δημιουργήσει assignments");
        }

    }

}
