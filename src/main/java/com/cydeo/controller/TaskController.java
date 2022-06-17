package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.ResponseWrapper;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createTask(@RequestBody TaskDTO task) {
        taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Task is successfully created", HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getTasks() {
        List<TaskDTO> taskDTOS = taskService.listAllTasks();
        return ResponseEntity.ok(new ResponseWrapper("Tasks are retrieved.",taskDTOS, HttpStatus.OK));
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> getTasksById(@PathVariable("taskId") Long taskId) {
        TaskDTO taskDTO = taskService.findById(taskId);
        return ResponseEntity.ok(new ResponseWrapper("Tasks are retrieved.",taskDTO, HttpStatus.OK));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseWrapper("Task is successfully deleted", HttpStatus.NO_CONTENT));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper> updateTask(@RequestBody TaskDTO task) {
        taskService.update(task);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated", HttpStatus.OK));
    }

    @GetMapping("/employee/pending-tasks")
    public ResponseEntity<ResponseWrapper> employeePendingTasks() {
        List<TaskDTO> taskDTOList = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);
        return ResponseEntity.ok(new ResponseWrapper("Task are successfully retrieved",taskDTOList, HttpStatus.OK));
    }

    @PutMapping("/employee/update/{id}")
    public ResponseEntity<ResponseWrapper> employeeUpdateTask(@RequestBody TaskDTO task) {
        taskService.updateStatus(task);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated", HttpStatus.OK));
    }

    @GetMapping("/employee/archive")
    public ResponseEntity<ResponseWrapper> employeeArchivedTasks() {
        List<TaskDTO> taskDTOS = taskService.listAllTasksByStatus(Status.COMPLETE);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated", taskDTOS, HttpStatus.OK));
    }
}
