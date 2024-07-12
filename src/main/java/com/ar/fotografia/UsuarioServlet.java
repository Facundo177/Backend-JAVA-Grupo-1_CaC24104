package com.ar.fotografia;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Usuario usuario = objectMapper.readValue(req.getInputStream(), Usuario.class);

        Integer id = usuarioDAO.insertarUsuario(usuario);
        
        String jsonResponse = objectMapper.writeValueAsString(id);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);

        resp.setStatus(HttpServletResponse.SC_CREATED);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {
            List<Usuario> usuarios = usuarioDAO.getAllUsuarios();

            String jsonResponse = objectMapper.writeValueAsString(usuarios);
            resp.setContentType("application/json");
            resp.getWriter().write(jsonResponse);

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID invalido");
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
