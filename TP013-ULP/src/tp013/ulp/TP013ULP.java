
package tp013.ulp;

//import org.mariadb.jdbc.Connection;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TP013ULP {


    public static void main(String[] args) {
        Connection con =Conexion.getConexion();
        
        //Statement ps = con.createStatement();
        
        // Insertar 3 alumnos -----------------------------------------------------------------------------
        String sql = "INSERT INTO `alumno`(`dni`, `apellido`, `nombre`, `fechaNacimiento`, `estado`)"
                                  +"VALUES (23367987,'Gomez','Federico','1993-09-15',1),"
                                         +"(35367987,'Quiroga','Lucia','2009-01-10',1),"
                                         +"(21367987,'Codina','Lucas','2010-03-08',1)";
        
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Alumnos ingresados correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de "+ex.getMessage());
        }

        // Insertar 4 materias -----------------------------------------------------------------------------
        String sql1 = "INSERT INTO `materia`(`nombre`, `año`, `estado`)" 
                    +"VALUES ('Matematica',1,1),"
                            +"('FISICA',1,1),"
                            + "('WEB',2,1),"
                            + "('QUIMICA',2,1)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql1);
            ps.executeUpdate();
            System.out.println("Materias ingresadas correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de "+ex.getMessage());
        }

        // Inscribir a los 3 alumnos en 2 materias cada uno ------------------------------------------------
        String sql2 = "INSERT INTO `inscripcion`( `nota`, `idAlumno`, `idMateria`)" 
					+"VALUES (8,1,1),"
                                               +"(8,1,2),"
                                               +"(7,2,2),"
                                               +"(7,2,1),"
                                               +"(10,3,3),"
                                               +"(10,3,1)";
        try {
            PreparedStatement ps = con.prepareStatement(sql2);
            ps.executeUpdate();
            System.out.println("Alumnos inscriptos correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de "+ex.getMessage());
        }
        
        // Listar los datos de los alumnos con calificaciones superiores a 8 ----------------------------------
        String sql3 = "SELECT a.idAlumno, dni, apellido, nombre, fechaNacimiento, estado, nota " 
                     + "FROM alumno a, inscripcion i "
                     + "WHERE a.idAlumno = i.idAlumno "
                     + "AND i.nota>8";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql3);
            
           ResultSet resultSet = ps.executeQuery(sql3);
           System.out.println("Alumnos con calificaciones mayores a 8");
            while (resultSet.next()) {
              
                System.out.print(" Id Alumno: " + resultSet.getInt("idAlumno"));
                System.out.print(" / DNI: " + resultSet.getInt("dni"));
                System.out.print(" / Apellido: " + resultSet.getNString("apellido"));
                System.out.print(" / Nombre: " + resultSet.getString("nombre"));
                System.out.print(" / FechaNacimiento: " + resultSet.getDate("fechaNacimiento"));
                System.out.print(" / Estado: " + resultSet.getBoolean("estado"));
                System.out.print(" / Nota: " + resultSet.getInt("nota"));
                System.out.println("");
            }            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a los datos ");
            System.out.println("error "+ex.getMessage());
            ex.printStackTrace();
        }
        
        // Desinscribir un alumno de una de la materias. ----------------------------------
      
        String sql4 = "DELETE "
                    +"FROM inscripcion "
                    +"WHERE idAlumno=1 "
                    +"AND idMateria=1";   
        try {
            PreparedStatement ps = con.prepareStatement(sql4);
            ps.executeUpdate();
            System.out.println("Alumno desinscripto correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a los datos ");
            System.out.println("error "+ex.getMessage());
            ex.printStackTrace();
            
        }
        
        
    }
    
}
