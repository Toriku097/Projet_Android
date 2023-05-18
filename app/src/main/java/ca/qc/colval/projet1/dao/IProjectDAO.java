package ca.qc.colval.projet1.dao;

import java.util.List;

import ca.qc.colval.projet1.entities.Project;

public interface IProjectDAO {
    //List<Project> getAllProjects();
    Project getProjectbyId(int id);
//    String getProjectNamebyId(int id);
//    Project addProject(Project project);
//    Project updateProjectbyId(int id, Project project);
//    Project deleteProjectbyId(int id);
}
