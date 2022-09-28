package project.management.system;

import controller.ProjectController;
import java.util.List;
import model.Project;

public class App {
    public String getGreeting() {
        
        ProjectController projectController = new ProjectController();
        /*Project project = new Project();
        
        project.setId(1);
        project.setName("Projeto teste novo");
        project.setDescription("new description");
        project.setCreatedAt(new Date());
        project.setUpdatedAt(new Date());
        projectController.update(project);*/
        
        List<Project> projects = projectController.getAll();
        System.out.println("Total projects: " + projects.size());
        
        return "ok";
        
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
