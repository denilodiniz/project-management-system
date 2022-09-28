package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {
    
    public void save(Project project) {
        String sql = "INSERT INTO projects("
                + "name, "
                + "description, "
                + "createdAt, "
                + "updatedAt) "
                + "VALUES (?, ?, ?, ?)";
        
        
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            statement = conn.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));

            //Executa a sql para inser??o dos dados
            statement.execute();
        } 
        catch (Exception e) {
           throw new RuntimeException("Error creating project " + e.getMessage(), e);
       }
       finally {
           ConnectionFactory.closeConnection(conn, statement);
       }
    }
    
     public void update(Project project) {

        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            //Cria uma conex?o com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            statement = conn.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());

            //Executa a sql para inser??o dos dados
            statement.execute();
        }
        catch (Exception e) {
           throw new RuntimeException("Error update project " + e.getMessage(), e);
       }
       finally {
           ConnectionFactory.closeConnection(conn, statement);
       }
    }

    public List<Project> getAll() {
        String sql = "SELECT * FROM projects";

        List<Project> projects = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet result = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            result = statement.executeQuery();

            //Enquanto existir dados no banco de dados, fa?a
            while (result.next()) {

                Project project = new Project();

                project.setId(result.getInt("id"));
                project.setName(result.getString("name"));
                project.setDescription(result.getString("description"));
                project.setCreatedAt(result.getDate("createdAt"));
                project.setCreatedAt(result.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                projects.add(project);
            }
        } 
        catch (Exception e) {
            throw new RuntimeException("Error fetching all projects " + e.getMessage(), e);
        }
        finally {
            ConnectionFactory.closeConnection(conn, statement, result);
        }
        
        return projects;
    }

    public void removeById(int id) {

        String sql = "DELETE FROM projects WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        }
        catch (Exception e) {
            throw new RuntimeException("Error when deleting project" + e.getMessage(), e);
        }
        finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
}
