package com.ar.fotografia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioDAO {

    public Integer insertarUsuario(Usuario usuario){
        String insertQuery = "INSERT INTO usuarios(nombre, apellido, email, contrasena, fecha_nacimiento, pais) VALUES(?, ?, ?, ?, ?, ?)";

        Conexion conexion = new Conexion();

        try (Connection cn = conexion.conectar()) {
            
            try (PreparedStatement pstm = cn.prepareStatement(insertQuery)) 
            {
                pstm.setString(1, usuario.getNombre());
                pstm.setString(2, usuario.getApellido());
                pstm.setString(3, usuario.getEmail());
                pstm.setString(4, usuario.getContrasena());
                pstm.setDate(5, (java.sql.Date) usuario.getFechaNacimiento());
                pstm.setString(6, usuario.getPais());
    
                int result = pstm.executeUpdate();
    
                if (result > 0) {
                    ResultSet rs = pstm.getGeneratedKeys();
    
                    if (rs.next()) {
                        System.out.println("Usuario insertado exitosamente");
                        return rs.getInt(1);
                    } else {
                        System.out.println("Error al obtener id del usuario");
                        return null;
                    }
                }
                else {
                    System.out.println("Error al insertar el usuario");
                    return null;
                }
    
            } catch (SQLException e) {
                System.err.println("Error al insertar el usuario");
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } catch (Exception e) {
            System.err.println("Error al conectar la base de datos");
                e.printStackTrace();
                return null;
        }
        
    }


    public List<Usuario> getAllUsuarios(){
        String selectQuery = "SELECT * FROM usuarios";

        Conexion conexion = new Conexion();

        try (Connection cn = conexion.conectar()) {
            
            try (PreparedStatement pstm = cn.prepareStatement(selectQuery)) 
            {
                var rs = pstm.executeQuery();
                
                List<Usuario> usuarios = new ArrayList<>();
                
                while (rs.next()) { 
                    Integer UserId = rs.getInt("id");
                    String UserNombre = rs.getString("nombre");
                    String UserApellido = rs.getString("apellido");
                    String UserEmail = rs.getString("email");
                    String UserContrasena = rs.getString("contrasena");
                    Date UserFechaNacimiento = rs.getDate("fecha_nacimiento");
                    String UserPais = rs.getString("pais");

                    Usuario usuario = new Usuario(UserId, UserNombre, UserApellido, UserEmail, UserContrasena, UserFechaNacimiento, UserPais);
                    usuarios.add(usuario);
                }
                
                return usuarios;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error al conectar la base de datos");
                e.printStackTrace();
                return null;
        }
    }
    

}
