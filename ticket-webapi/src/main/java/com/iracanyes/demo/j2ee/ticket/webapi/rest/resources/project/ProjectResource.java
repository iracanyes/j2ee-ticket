package com.iracanyes.demo.j2ee.ticket.webapi.rest.resources.project;

import com.iracanyes.demo.j2ee.ticket.business.ManagerFactory;
import com.iracanyes.demo.j2ee.ticket.business.manager.ProjectManager;
import com.iracanyes.demo.j2ee.ticket.model.bean.project.Project;
import com.iracanyes.demo.j2ee.ticket.webapi.rest.resources.AbstractResource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.iracanyes.demo.j2ee.ticket.model.exception.NotFoundException;
import java.util.List;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource extends AbstractResource {

  /**
   * Send the {@link Project} of ID {@code pId}
   *
   * @param pId ID of {@link Project}
   * @return The {@link Project}
   * @throws NotFoundException If the {@link Project} is not found
   * */
  @GET
  @Path("{id}")
  public Project get(@PathParam("id") Integer pId) throws NotFoundException{
    ProjectManager vProjectManager = getManagerFactory().getProjectManager();
    Project vProject = vProjectManager.getProject(pId);
    return vProject;
  }

  @GET
  public List<Project> get(){
    ProjectManager vProjectManager = getManagerFactory().getProjectManager();
    List<Project> vListProject = vProjectManager.getListProject();
    return vListProject;
  }
}
