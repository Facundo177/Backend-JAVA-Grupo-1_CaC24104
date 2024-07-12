package com.ar.fotografia;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fotos")/* http://elServidor/fotos  */
public class FotoServlet extends HttpServlet {

   private FotoDAO fotoDAO = new FotoDAO();

   private ObjectMapper objectMapper = new ObjectMapper();

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      resp.setHeader("Access-Control-Allow-Origin", "*");
      resp.setHeader("Access-Control-Allow-Methods", "*");
      resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

      req.setCharacterEncoding("UTF-8");      
      resp.setCharacterEncoding("UTF-8");

      Foto foto = objectMapper.readValue(req.getInputStream(), Foto.class);

      int id = fotoDAO.insertarFoto(foto);

      String jsonResponse = objectMapper.writeValueAsString(id);

      resp.setContentType("application/json");

      resp.getWriter().write(jsonResponse);

      resp.setStatus(HttpServletResponse.SC_CREATED);

      //super.doPost(req, resp);
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      resp.setHeader("Access-Control-Allow-Origin", "*");
      resp.setHeader("Access-Control-Allow-Methods", "*");
      resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

      req.setCharacterEncoding("UTF-8");      
      resp.setCharacterEncoding("UTF-8");

      try {
         
         List<Foto> fotos = fotoDAO.getAllFotos();
         
         String jsonResp = objectMapper.writeValueAsString(fotos);
         resp.setContentType("application/json");
         resp.getWriter().write(jsonResp);

      } catch (NumberFormatException e) {
         resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"ID invalido");
         e.printStackTrace();
      }
   }

  
   
}


