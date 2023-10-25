package com.cristian.dominio;

public class Estudiante {

    private int idStudent;
    private String name;
    private String lastname;
    private String phone;
    private String email;

    public Estudiante() {
    }

    //Si quiero eliminar un estudiante, por ejemplo, podria hacerlo por el id
    public Estudiante(int idStudent){
        this.idStudent = idStudent;
    }

    public Estudiante(String name, String lastname, String phone, String email) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public Estudiante(int idStudent, String name, String lastname,
                      String phone, String email) {
        this.idStudent = idStudent;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Datos del Estudiante N°:" +
                idStudent +
                "\nNombre: '" + name + '\'' +
                "\nApellido: '" + lastname+ '\'' +
                "\nTelefono: " + phone +
                "\nEmail: " + email;
    }
}
