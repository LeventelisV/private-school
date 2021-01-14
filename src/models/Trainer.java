/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Scanner;
import privateschool.Main;

/**
 *
 * @author vaggelis
 */
public class Trainer {

    
    
    private String firstName;
    private String lastName;
    private String subject;
    private static ArrayList<Trainer> trainersList = new ArrayList<Trainer>();

    public Trainer(String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public static ArrayList<Trainer> getTrainersList() {
        return trainersList;
    }

    public static Trainer createTrainer() {
        String firstName = "";
        String lastName = "";
        String subject = "";
        boolean validString = false;
        Scanner scanner = new Scanner(System.in);
        while (validString == false) {
            System.out.println("Παρακαλώ εισάγετε το όνομα του εκπαιδευτή: ");
            firstName = scanner.next();
            validString = Main.isStringOnlyAlphabet(firstName);
        }
        validString = false;
        while (validString == false) {
            System.out.println("Παρακαλώ εισάγετε το επώνυμο του εκπαιδευτή: ");
            lastName = scanner.next();
            validString = Main.isStringOnlyAlphabet(lastName);
        }
        validString = false;
        while (validString == false) {

            System.out.println("Παρακαλώ εισάγετε το μάθημα που διδάσκει: ");
            subject = scanner.next();
            validString = Main.isStringOnlyAlphabet(subject);
        }
        Trainer trainer = new Trainer(firstName, lastName, subject);
        trainersList.add(trainer);
        return trainer;

    }

    public static void printTheTrainers() {
        if (!(Trainer.getTrainersList().isEmpty())) {
            for (int i = 0; i < Trainer.getTrainersList().size(); i++) {
                System.out.print(i + 1 + ".  ");
                System.out.println(Trainer.getTrainersList().get(i));
            }
        } else {
            System.out.println("Δεν έχετε δημιουργήσει trainers");
        }

    }

    @Override
    public String toString() {
        return "Trainer{" + "firstName=" + firstName + ", lastName=" + lastName + ", subject=" + subject + '}';
    }

}
