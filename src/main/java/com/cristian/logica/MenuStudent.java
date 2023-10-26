package com.cristian.logica;

import com.cristian.DAO.EstudianteDAO;
import com.cristian.dominio.Estudiante;

import java.io.IOException;
import java.util.Scanner;

public class MenuStudent {

    public void menuStudents() {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        EstudianteDAO eDAO = new EstudianteDAO();
        boolean out = false;
        int option = 0;
        boolean optionBoolean = false;
        while (!out) {
            System.out.println();
            System.out.println("""
                    ******Bienvenido al Menu de edicion de estudiantes******
                    *               Elija la Opcion deseada:               *
                    * 1) Ver el Listado de Estudiantes                     *
                    * 2) Ingresar NUEVO estudiante                         *
                    * 3) MODIFICAR algun dato del estudiante               *
                    * 4) Dar de BAJA estudiante                            *
                    * 5) Salir                                             *
                    ********************************************************
                    """);

            try {
                option = input.nextInt();
            }catch(Exception e){
                System.out.println("Error al ingresar los datos: "+ e.getMessage());
                menuStudents();
            }
            switch (option) {
                case 1: {
                    eDAO.listStudents();
                    break;
                }
                case 2: {
                    eDAO.addStudent();
                    break;
                }
                case 3: {
                    boolean exitSubMenu = false;
                    while (!exitSubMenu) {
                    System.out.println("""
                            ********************************************
                            *      ¿Que dato desea actualizar          *
                            * 1) Cambiar Nombre                        *
                            * 2) Cambiar Apellido                      *
                            * 3) Cambiar Telefono                      *
                            * 4) Cambiar Email                         *
                            * 5) Cambiar TODOS los DATOS               *
                            * 6) <- Regresar al menu anterior          *
                            ********************************************
                            """);
                        int optionSubMenu;
                    try {
                        optionSubMenu  = input.nextInt();
                    }catch (Exception e){
                        System.out.print("Error al elegir la opcion: " + e.getMessage());
                        System.out.print("<-- Regresando al menu anterior...");
                        break;
                    }
                        switch (optionSubMenu) {
                            case 1: {
                                int studentID = 0;
                                String name = "";
                                try {
                                    System.out.print("Ingrese el ID que le fue asignado: ");
                                    studentID = input.nextInt();
                                    System.out.print("Ingrese el nuevo nombre: ");
                                    name = input.next();
                                }catch (Exception e){
                                    System.out.println("Error al elegir la opcion: " + e.getMessage());
                                    System.out.print("<-- Regresando al menu anterior...");
                                }
                                eDAO.updateStudentName(studentID, name);
                                break;
                            }
                            case 2: {
                                int studentID = 0;
                                String lastname = "";
                                try {
                                    System.out.print("Ingrese el ID que le fue asignado: ");
                                    studentID = input.nextInt();
                                    System.out.print("Ingrese el nuevo apellido: ");
                                    lastname = input.next();
                                } catch (Exception e){
                                    System.out.println("Error al elegir la opcion: " + e.getMessage());
                                    System.out.print("<-- Regresando al menu anterior...");
                                }
                                eDAO.updateStudentLastname(studentID, lastname);
                                break;
                            }
                            case 3: {
                                int studentID = 0;
                                String phone = "";
                                try {
                                    System.out.print("Ingrese el ID que le fue asignado: ");
                                    studentID = input.nextInt();
                                    System.out.print("Ingrese el nuevo telefono: ");
                                    phone = input.next();
                                }catch (Exception e){
                                    System.out.println("Error al elegir la opcion: " + e.getMessage());
                                    System.out.print("<-- Regresando al menu anterior...");
                                }
                                eDAO.updateStudentPhone(studentID, phone);
                                break;
                            }
                            case 4: {
                                int studentID = 0;
                                String email = "";
                                try {
                                    System.out.print("Ingrese el ID que le fue asignado: ");
                                    studentID = input.nextInt();
                                    System.out.print("Ingrese el nuevo email: ");
                                    email = input.next();
                                }catch (Exception e){
                                    System.out.println("Error al elegir la opcion: " + e.getMessage());
                                    System.out.print("<-- Regresando al menu anterior...");
                                }

                                eDAO.updateStudentEmail(studentID, email);
                                break;
                            }
                            case 5: {
                                int studentID = 0;
                                String name = "",
                               lastname = "",phone = "",email = "";
                                try {
                                    System.out.println("Atencion! En caso de ingresar mal un campo" +
                                            "\n, se regresara al menu anterior");
                                    System.out.print("Ingrese el ID que le fue asignado: ");
                                    studentID = input.nextInt();
                                    System.out.print("Ingrese el nuevo nombre: ");
                                    name = input.next();
                                    System.out.print("Ingrese el nuevo apellido: ");
                                    lastname = input.next();
                                    System.out.print("Ingrese el nuevo telefono: ");
                                    phone = input.next();
                                    System.out.print("Ingrese el nuevo email: ");
                                    email = input.next();
                                }catch (Exception e){
                                    System.out.println("Error al elegir la opcion: " + e.getMessage());
                                    System.out.print("<-- Regresando al menu anterior...");
                                }
                                Estudiante student = new Estudiante(studentID, name, lastname, phone, email);
                                eDAO.updateStudent(student);
                                break;
                            }
                            case 6: {
                                System.out.println(" <-- Regresando al menu anterior...");
                                exitSubMenu = true;
                                break;
                            }
                            default:
                                System.out.println("Opcion invalida. Intente nuevamente!");
                                break;
                        }
                    }
                    break;
                }
                case 4: {
                    int studentID = 0;
                    String delete = "";
                    try {
                        System.out.print("Ingrese el ID que le fue asignado: ");
                        studentID = input.nextInt();
                        System.out.print("""
                                ¿Confirma la baja del estudiante?
                                Ingrese si o no, para continuar...
                                """);
                         delete = input.next();
                    }catch (Exception e){
                        System.out.println("Error al elegir la opcion: " + e.getMessage());
                    }
                    if (delete.equalsIgnoreCase("si")){
                        eDAO.deleteStudent(studentID);
                        break;
                    } else {
                        System.out.print("""
                                Usted eligio no eliminar al estudiante
                            <--- Regresando al menu anterior...
                                """);
                        break;
                    }
                }
                case 5: {
                    System.out.println(" Gracias por usar la App :)");
                    out = true;
                    break;
                }
                default:
                    System.out.println("Opcion Invalida. Reintente nuevamente");
                    break;
            }
        }
    }
}
