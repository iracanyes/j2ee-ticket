package com.iracanyes.demo.j2ee.ticket.business.manager;

import com.iracanyes.demo.j2ee.ticket.model.bean.project.Project;
import com.iracanyes.demo.j2ee.ticket.model.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager of Beans from package Project
 */
public class ProjectManager {
  /**
   * Retrieve the project by ID
   *
   * @param pId ID of the project
   * @return The {@link Project}
   * @throws NotFoundException If the project is not  found
   */
  public Project getProject(Integer pId) throws NotFoundException{
    if (pId < 1) {
      throw new NotFoundException("Projet non trouvé : ID=" + pId);
    }

    // TODO: Implement Dao

    Project vProject = new Project(pId);
    vProject.setName("Project n°"+ pId);
    return vProject;
  }

  public List<Project> getListProject(){
    List<Project> vList = new ArrayList<>();
    // TODO: Implement Dao
    for(int vI = 0; vI < 9; vI++){
      Project vProject = new Project();
      vProject.setName("Project n°"+ vI);
      vList.add(vProject);
    }

    return vList;
  }
}
