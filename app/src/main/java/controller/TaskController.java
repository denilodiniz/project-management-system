package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {
    
    public void save(Task task) {
       String sql = "INSERT INTO tasks("
               + "idProject, "
               + "name, "
               + "description, "               
               + "notes, "
               + "completed, "
               + "deadline, "
               + "createdAt, "
               + "updatedAt) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
       Connection conn = null;
       PreparedStatement statement = null;
       
       try {
           conn = ConnectionFactory.getConnection();
           statement = conn.prepareStatement(sql);
           statement.setInt(1, task.getIdProject());
           statement.setString(2, task.getName());
           statement.setString(3, task.getDescription());           
           statement.setString(4, task.getNotes());
           statement.setBoolean(5, task.isIsCompleted());
           statement.setDate(6, new Date(task.getDeadline().getTime()));
           statement.setDate(7, new Date(task.getCreatedAt().getTime()));
           statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
           statement.execute();
       }
       catch (Exception e) {
           throw new RuntimeException("Error creating task " + e.getMessage(), e);
       }
       finally {
           ConnectionFactory.closeConnection(conn, statement);
       }
    }
    
    public void update(Task task) {
        String sql = "UPDATE tasks SET "
               + "idProject = ?, "
               + "name = ?, "
               + "description = ?, "
               + "notes = ?, "
               + "completed = ?, "
               + "deadline = ?, "
               + "createdAt = ?, "
               + "updatedAt = ? "
               + "WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
           conn = ConnectionFactory.getConnection();
           statement = conn.prepareStatement(sql);
           statement.setInt(1, task.getIdProject());
           statement.setString(2, task.getName());
           statement.setString(3, task.getDescription());           
           statement.setString(4, task.getNotes());
           statement.setBoolean(5, task.isIsCompleted());
           statement.setDate(6, new Date(task.getDeadline().getTime()));
           statement.setDate(7, new Date(task.getCreatedAt().getTime()));
           statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
           statement.setInt(9, task.getId());
           statement.execute();
       }
       catch (Exception e) {
           throw new RuntimeException("Error update task " + e.getMessage(), e);
       }
       finally {
           ConnectionFactory.closeConnection(conn, statement);
       }
    }
    
    public void removeById(int taskId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        }
        catch (Exception e) {
            throw new RuntimeException("Error when deleting " + e.getMessage(), e);
        }
        finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public List<Task> getAllByIdProject(int idProject) {
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        
        List<Task> tasks = new ArrayList<>();
                
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProject);
            result = statement.executeQuery();
            
            while(result.next()) {
                Task task = new Task();
                
                task.setId(result.getInt("id"));
                task.setIdProject(result.getInt("idProject"));
                task.setName(result.getString("name"));
                task.setDescription(result.getString("description"));
                task.setNotes(result.getString("notes"));
                task.setIsCompleted(result.getBoolean("completed"));
                task.setDeadline(result.getDate("deadline"));
                task.setCreatedAt(result.getDate("createdAt"));
                task.setUpdatedAt(result.getDate("updatedAt"));
                
                tasks.add(task);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching all tasks " + e.getMessage(), e);
        }
        finally {
            ConnectionFactory.closeConnection(conn, statement, result);
        }
     
        return tasks;
    }
 
}
