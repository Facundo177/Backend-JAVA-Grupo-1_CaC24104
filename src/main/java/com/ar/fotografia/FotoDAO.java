package com.ar.fotografia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FotoDAO {

    public Integer insertarFoto(Foto foto){
        String insertQuery = "INSERT INTO fotografias_api(url_imagen, url_fotografo, nombre_fotografo, url_origen) VALUES(?, ?, ?, ?)";

        Conexion conexion = new Conexion();

        try (Connection cn = conexion.conectar()) {
            
            try (PreparedStatement pstm = cn.prepareStatement(insertQuery)) 
            {
                pstm.setString(1, foto.getUrlImagen());
                pstm.setString(2, foto.getUrlFotografo());
                pstm.setString(3, foto.getNombreFotografo());
                pstm.setString(4, foto.getUrlOrigen());

                int result = pstm.executeUpdate();

                if (result > 0) {
                    ResultSet rs = pstm.getGeneratedKeys();

                    if (rs.next()) {
                        System.out.println("Foto insertada exitosamente");
                        return rs.getInt(1);
                    } else {
                        System.out.println("Error al obtener id de la foto");
                        return null;
                    }
                }
                else {
                    System.out.println("Error al insertar la foto");
                    return null;
                }

            } catch (SQLException e) {
                System.err.println("Error al insertar la foto");
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


    public List<Foto> getAllFotos(){
        String selectQuery = "SELECT * FROM fotografias_api";

        Conexion conexion = new Conexion();

        try (Connection cn = conexion.conectar()) {
            
            try (PreparedStatement pstm = cn.prepareStatement(selectQuery)) 
            {
                var rs = pstm.executeQuery();
                
                List<Foto> fotos = new ArrayList<>();
                
                while (rs.next()) { 
                    Integer id = rs.getInt("id");
                    String urlImagen = rs.getString("url_imagen");
                    String urlFotografo = rs.getString("url_fotografo");
                    String nombreFotografo = rs.getString("nombre_fotografo");
                    String urlOrigen = rs.getString("url_origen");

                    Foto foto = new Foto(id, nombreFotografo, urlFotografo, urlImagen, urlOrigen);
                    fotos.add(foto);
                }
                
                return fotos;

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
