package com.cristian;

import com.cristian.DAO.EstudianteDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        EstudianteDAO eDAO = new EstudianteDAO();
        eDAO.listStudents();
        // System.out.print("Ingrese su numero de estudiante: ");
        // int numberId= input.nextInt();
        //eDAO.findById(numberId);
        eDAO.addStudent();
    }
}