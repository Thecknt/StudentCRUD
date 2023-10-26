package com.cristian.logica;

import com.cristian.DAO.EstudianteDAO;
import com.cristian.dominio.Estudiante;

import java.util.Scanner;

public class MenuStudent {

    public void menuStudents() {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        EstudianteDAO eDAO = new EstudianteDAO();
        boolean out = false;

        while (!out) {
            System.out.println();
            System.out.println("""
                    ******Bienvenido al Menu de edicion de estudiantes******
                    *               Elija la Opcion deseada:               *
                    * 1) Ver el Listado de Estudiantes                     *
                    * 2) MODIFICAR algun dato del estudiante               *
                    * 3) Dar de BAJA estudiante                            *
                    * 4) Salir                                             *
                    ********************************************************
                    """);
            int option = input.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("Ingresando el Menu case 1");
                    eDAO.listStudents();
                    break;
                }
                case 2: {
                    boolean exitSubMenu = false;
                    while (!exitSubMenu) {
                    System.out.println("Ingresando el Menu case 2");
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
                    int optionSubMenu = input.nextInt();

                        switch (optionSubMenu) {
                            case 1: {
                                System.out.println("Ingresando el Submenu case1");
                                System.out.print("Ingrese el ID que le fue asignado: ");
                                int studentID = input.nextInt();
                                System.out.print("Ingrese el nuevo nombre: ");
                                String name = input.next();
                                eDAO.updateStudentName(studentID, name);
                                break;
                            }
                            case 2: {
                                System.out.println("Ingresando el Submenu case2");
                                System.out.print("Ingrese el ID que le fue asignado: ");
                                int studentID = input.nextInt();
                                System.out.print("Ingrese el nuevo apellido: ");
                                String lastname = input.next();
                                eDAO.updateStudentLastname(studentID, lastname);
                                break;
                            }
                            case 3: {
                                System.out.println("Ingresando el Submenu case3");
                                System.out.print("Ingrese el ID que le fue asignado: ");
                                int studentID = input.nextInt();
                                System.out.print("Ingrese el nuevo telefono: ");
                                String phone = input.next();
                                eDAO.updateStudentPhone(studentID, phone);
                                break;
                            }
                            case 4: {
                                System.out.println("Ingresando el Submenu case4");
                                System.out.print("Ingrese el ID que le fue asignado: ");
                                int studentID = input.nextInt();
                                System.out.print("Ingrese el nuevo email: ");
                                String email = input.next();
                                eDAO.updateStudentEmail(studentID, email);
                                break;
                            }
                            case 5: {
                                System.out.println("Ingresando el Submenu case5");
                                System.out.print("Ingrese el ID que le fue asignado: ");
                                int studentID = input.nextInt();
                                System.out.print("Ingrese el nuevo nombre: ");
                                String name = input.next();
                                System.out.print("Ingrese el nuevo apellido: ");
                                String lastname = input.next();
                                System.out.print("Ingrese el nuevo telefono: ");
                                String phone = input.next();
                                System.out.print("Ingrese el nuevo email: ");
                                String email = input.next();
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
                case 3: {
                    System.out.println("Ingresando el Menu case 3");
                    System.out.print("Ingrese el ID que le fue asignado: ");
                    int studentID = input.nextInt();
                    System.out.print("""
                            ¿Confirma la baja del estudiante?
                            Ingrese si o no, para continuar...
                            """);
                    String delete = input.next();
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
                case 4: {
                    System.out.println("Ingresando el Menu case 4");
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
