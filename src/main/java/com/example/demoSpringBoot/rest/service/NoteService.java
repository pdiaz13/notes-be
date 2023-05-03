package com.example.demoSpringBoot.rest.service;

import com.example.demoSpringBoot.rest.model.Notes;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;

import java.sql.*;
import java.util.*;
import java.util.Date;

@Service
public class NoteService {

    public List<Notes> getAllNotes(){
        List<Notes> notesList = new ArrayList<>();
        notesList.add(new Notes(1L, "hola", true, new Date()));

        // Paso 1: crear la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/notescrud";
        String user = "root";
        String password = "39058429Lauti";

        try (
                Connection conn = DriverManager.getConnection(url, user, password))
        {
            // Paso 2: crear y ejecutar la consulta SQL
            String sql = "SELECT * FROM notes"; // Selecciona todas las notas en la tabla "notes"
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Paso 3: iterar sobre los resultados y mapearlos a objetos Notes
            while (rs.next()) { // Itera sobre los resultados obtenidos de la consulta SQL
                Long id = rs.getLong("id");
                String text = rs.getString("text");
                boolean status = rs.getBoolean("status");
                Date date = rs.getDate("date");

                Notes note = new Notes(id, text, status, date); // Crea Notes
                notesList.add(note); // Agrega el objeto Notes a la lista de notas
            }

        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return notesList;


    }

    public void addNote(Notes note) {
        // Paso 1: crear la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/notescrud";
        String user = "root";
        String password = "39058429Lauti";

        try (
                Connection conn = DriverManager.getConnection(url, user, password))
        {
            // Paso 2: crear y ejecutar la consulta SQL
            // Inserta una nueva fila en la tabla "notes" con los valores de los parámetros
            String sql = "INSERT INTO notes (text, status, date) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, note.getText());
            stmt.setBoolean(2, note.isStatus());
            stmt.setDate(3, new java.sql.Date(note.getDate().getTime()));

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new note was inserted successfully!"); // Imprime un mensaje si la consulta SQL fue ejecutada correctamente
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void updateNoteById(Long id, Notes note) {
        // Paso 1: crear la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/notescrud";
        String user = "root";
        String password = "39058429Lauti";

        try (
                Connection conn = DriverManager.getConnection(url, user, password)) {

            // Paso 2: crear y ejecutar la consulta SQL
            String sql = "UPDATE notes SET text=?, status=?, date=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, note.getText());
            stmt.setBoolean(2, note.isStatus());
            stmt.setDate(3, new java.sql.Date(note.getDate().getTime()));
            stmt.setLong(4, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("A note was updated successfully"); // Imprime un mensaje si la consulta SQL fue ejecutada correctamente
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteNoteById(Long id) {
        // Paso 1: crear la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/notescrud";
        String user = "root";
        String password = "39058429Lauti";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Paso 2: crear y ejecutar la consulta SQL DELETE
            String sql = "DELETE FROM notes WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();

            // Paso 3: verificar si se eliminó correctamente
            if (rowsDeleted > 0) {
                System.out.println("Note deleted successfully!");
            } else {
                System.out.println("Failed to delete note with id = " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
