
package com.javapro;

import java.sql.*;
import java.util.*;

public class HospitalManagementSystem {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=== Hospital Management System ===");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Add Doctor");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. Generate Bill");
            System.out.println("6. Exit");
            System.out.println("7. AI Hospital Chatbot");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    addPatient();
                    break;

                case 2:
                    viewPatients();
                    break;

                case 3:
                    addDoctor();
                    break;

                case 4:
                    scheduleAppointment();
                    break;

                case 5:
                    generateBill();
                    break;

                case 6:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                case 7:
                    sc.nextLine();
                    hospitalChatbot();
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addPatient() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter name: ");
            String name = sc.next();

            System.out.print("Enter age: ");
            int age = sc.nextInt();

            System.out.print("Enter gender: ");
            String gender = sc.next();

            System.out.print("Enter disease: ");
            String disease = sc.next();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO patient(name, age, gender, disease) VALUES(?,?,?,?)"
            );

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, disease);

            ps.executeUpdate();

            System.out.println("Patient added successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void viewPatients() {

        try {
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM patient");

            System.out.println("ID\tName\tAge\tGender\tDisease");

            while (rs.next()) {

                System.out.println(
                    rs.getInt(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getInt(3) + "\t" +
                    rs.getString(4) + "\t" +
                    rs.getString(5)
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void addDoctor() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter doctor name: ");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.print("Enter specialization: ");
            String spec = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO doctor(name, specialization) VALUES(?,?)"
            );

            ps.setString(1, name);
            ps.setString(2, spec);

            ps.executeUpdate();

            System.out.println("Doctor added successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void scheduleAppointment() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter patient ID: ");
            int pid = sc.nextInt();

            System.out.print("Enter doctor ID: ");
            int did = sc.nextInt();

            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = sc.next();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO appointment(patient_id, doctor_id, date) VALUES(?,?,?)"
            );

            ps.setInt(1, pid);
            ps.setInt(2, did);
            ps.setString(3, date);

            ps.executeUpdate();

            System.out.println("Appointment scheduled!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void generateBill() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter patient ID: ");
            int pid = sc.nextInt();

            System.out.print("Enter amount: ");
            double amt = sc.nextDouble();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO billing(patient_id, amount) VALUES(?,?)"
            );

            ps.setInt(1, pid);
            ps.setDouble(2, amt);

            ps.executeUpdate();

            System.out.println("Bill generated successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    static void hospitalChatbot() {

        OllamaChatbot.startChat(sc);

    }
}
