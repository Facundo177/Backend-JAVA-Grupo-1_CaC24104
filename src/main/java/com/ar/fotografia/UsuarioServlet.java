package com.ar.fotografia;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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

        super.doPost(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doGet(req, resp);
    }

}
