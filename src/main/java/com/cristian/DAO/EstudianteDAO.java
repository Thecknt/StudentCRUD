package com.cristian.DAO;

import com.cristian.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.cristian.connection.ConnectionDB.getConnectionDB;

//DAO: Data Access Object
public class EstudianteDAO {
    Scanner input = new Scanner(System.in);

    //Mostrar el listado de todos los estudiantes:
    public List<Estudiante> listStudents() {
        List<Estudiante> students = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnectionDB();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //Ejecuto un while por si tengo varios registros con el metodo next()
            while (rs.next()) {
                var student = new Estudiante();
                //Ahora voy a llenando cada uno de los valores trayendolo de las columnas
                student.setIdStudent(rs.getInt("id_estudiante")); //traigo la columna con el id
                student.setName(rs.getString("nombre"));
                student.setLastname(rs.getString("apellido"));
                student.setPhone(rs.getString("telefono"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Error en la sentencia de pedido a la base de datos: " + "(" + e.getMessage() + ")");
        } finally {
            try {
                //cierro la conexion con la base de datos.
                con.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion en la base de datos. " + e.getMessage());
            }
        }
        for (Estudiante e : students) {
            System.out.println("***********************************");
            System.out.println(e);
            System.out.println("***********************************");
            System.out.println();
        }
        return students;
    }

    //Buscar estudiante por ID:
    public boolean findById(int numberId) {
        Estudiante student = new Estudiante();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnectionDB();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?"; //lo dejo como incognita para pasarlo como parametro
        try {
            ps = con.prepareStatement(sql);
            //Con esta sentencia busco el estudiante
            ps.setInt(1, numberId);
            rs = ps.executeQuery();
            if (rs.next()) {
                student.setIdStudent(rs.getInt("id_estudiante"));
                student.setName(rs.getString("nombre"));
                student.setLastname(rs.getString("apellido"));
                student.setPhone(rs.getString("telefono"));
                student.setEmail(rs.getString("email"));
                System.out.println("Alumno encontrado: " + "\n" + student);
                return true;
            } else
                System.out.println("Estudiante no encontrado");

        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar el estudiante en la base de datos: " + "(" + e.getMessage() + ")");
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion. " + e.getMessage());
            }
        }
        return false;
    }

    //Agregar un estudiante nuevo:
    public boolean addStudent() {
        String name = "", lastname = "", phone = "", email = "";
        boolean ok = false;
        while (!ok) {
            try {
                System.out.print("Ingrese el nombre del estudiante: -> ");
                name = input.next();
                System.out.print("Ingrese el apellido del estudiante: -> ");
                lastname = input.next();
                System.out.print("Ingrese el numero de telefono: -> ");
                phone = input.next();
                System.out.print("y por ultimo el email: -> ");
                email = input.next();
                ok = true;
            } catch (Exception e) {
                System.out.println("Error al ingresar los datos, intente nuevamente!" + e.getMessage());
            }
        }

        Estudiante student = new Estudiante(name, lastname, phone, email);

        PreparedStatement ps;
        Connection con = getConnectionDB();
        String sql = "INSERT INTO estudiante(nombre,apellido,telefono,email) " +
                "VALUES(?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getName()); //estos son los parametros que corresponden por posicion a cada -> ?
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getEmail());
            ps.execute(); // como ahora no recupero datos solo inserto, no uso execute query, solo execute
            System.out.println("Estudiante " + student.getName() + ", agregado satisfactoriamente!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al guardar el estudiante en la base de datos. " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al finalizar la conexion con la base de datos. " + e.getMessage());
            }
        }
        return false;
    }

    //Actualizar un estudiante con todos sus campos:
    public boolean updateStudent(Estudiante student) {
        PreparedStatement ps;
        Connection con = getConnectionDB();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getEmail());
            ps.setInt(5, student.getIdStudent());
            ps.execute();
            System.out.println("Los Datos del estudiante " + student.getName() + ", han sido modificados correctamente.");
            return true;
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al intentar actualizar el estudiante. " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion con la base de datos. " + e.getMessage());
            }
        }
        return false;
    }

    //Actualizar el nombre del estudiante solamente:
    public boolean updateStudentName(int studentId, String name){
        PreparedStatement ps;
        Connection con = getConnectionDB();
        String sql ="UPDATE estudiante SET nombre=? WHERE id_estudiante =?";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1,name); //1  porque corresponde al primer parametro del set nombre=?
            ps.setInt(2,studentId); //2 porque corresponde al segundo parametro del id_estudiante=?
            ps.execute();
            System.out.println("Nombre del studiante actualizado satisfactoriamente!");
            return true;
        }catch(SQLException e){
            System.out.println("Error al actulizar el nombre del estudiante. "+ e.getMessage());
        }finally{
            try {
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar la conexion con la base de datos. "+e.getMessage());
            }
        }
        return false;
    }

//Actualizar el Apellido del alumno solamente:
    public boolean updateStudentLastname(int studentId, String lastname){
        PreparedStatement ps;
        Connection con = getConnectionDB();
        String sql ="UPDATE estudiante SET apellido=? WHERE id_estudiante=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,lastname);
            ps.setInt(2,studentId);
            ps.execute();
            System.out.println("El apellido del estudiante, ha sido cambiado con exito!");
            return true;
        }catch(SQLException e){
            System.out.println("Error al actualizar el apellido del estudiante. "+e.getMessage());
        }finally {
            try {
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar la conexion con la base de datos. "+ e.getMessage());
            }
        }
        return false;
    }

    //Actualizar el telefono del alumno solamente:
    public boolean updateStudentPhone(int studentId, String phone){
        PreparedStatement ps;
        Connection con = getConnectionDB();
        String sql ="UPDATE estudiante SET telefono=? WHERE id_estudiante=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,phone);
            ps.setInt(2,studentId);
            ps.execute();
            System.out.println("El telefono del estudiante, ha sido cambiado con exito!");
            return true;
        }catch(SQLException e){
            System.out.println("Error al actualizar el telefono del estudiante. "+e.getMessage());
        }finally {
            try {
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar la conexion con la base de datos. "+ e.getMessage());
            }
        }
        return false;
    }

    //Actualizar el Email del alumno solamente:
    public boolean updateStudentEmail(int studentId, String email){
        PreparedStatement ps;
        Connection con = getConnectionDB();
        String sql ="UPDATE estudiante SET email=? WHERE id_estudiante=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,email);
            ps.setInt(2,studentId);
            ps.execute();
            System.out.println("El email del estudiante, ha sido cambiado con exito!");
            return true;
        }catch(SQLException e){
            System.out.println("Error al actualizar el email del estudiante. "+e.getMessage());
        }finally {
            try {
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar la conexion con la base de datos. "+ e.getMessage());
            }
        }
        return false;
    }
    //Eliminar estudiante:
    public boolean deleteStudent(int studentID) {
        PreparedStatement ps;
        Connection con = getConnectionDB();
        String sql = "DELETE FROM estudiante WHERE id_estudiante =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, studentID);
            ps.execute();
            System.out.println("Estudiante eliminado con exito!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el Estudiante " + studentID);
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al terminar el proceso: " + e.getMessage());
            }
        }
        return false;
    }

}
