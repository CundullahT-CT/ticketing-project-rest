package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.ResponseWrapper;
import com.cydeo.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/api/v1/project")
@Tag(name = "Project Controller",description = "Project API")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @Operation(summary = "Create project")
    @RolesAllowed({"Manager","Admin"})
    public ResponseEntity<ResponseWrapper> insertProject(@RequestBody ProjectDTO project) {
        projectService.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new ResponseWrapper("Project is successfully created", HttpStatus.CREATED));
    }

    @GetMapping
    @Operation(summary = "Read all projects")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getProjects() {
        List<ProjectDTO> projectDTOS = projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectDTOS, HttpStatus.OK));
    }

    @GetMapping("/{code}")
    @Operation(summary = "Read by project code")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("code") String code) {
        ProjectDTO projectDTO = projectService.getByProjectCode(code);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully retrieved", projectDTO, HttpStatus.OK));
    }

    @DeleteMapping("/{projectcode}")
    @Operation(summary = "Delete project")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectcode") String projectcode) {
        projectService.delete(projectcode);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully deleted", HttpStatus.OK));
    }

    @PutMapping("/complete/{projectcode}")
    @Operation(summary = "Complete project")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> completeProject(@PathVariable("projectcode") String projectcode) {
        projectService.complete(projectcode);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully completed", HttpStatus.OK));
    }


    @PutMapping
    @Operation(summary = "Update project")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO project) {
        projectService.update(project);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully updated", HttpStatus.OK));

    }

    @GetMapping("/manager/project-status")
    @Operation(summary = "Read all project details")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getProjectByManager() {
        List<ProjectDTO> projects = projectService.listAllProjectDetails();
       return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved",projects,HttpStatus.OK));
    }

    @PutMapping("/manager/complete/{projectCode}")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.complete(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully completed", HttpStatus.OK));
    }
}
