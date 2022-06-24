package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class AssignmentController {
    
    private AssignmentService service;
    
    @Autowired
    public AssignmentController(@NonNull AssignmentService service) {
        this.service = service;
    }
    
    public void setService(AssignmentService service) {
        this.service = service;
    }
    
    @PostMapping("/addAssignment")
    @CrossOrigin
    public void addAssignment(@RequestParam Long assignedTo,
                              @RequestParam Long quizTemplateId) {
        service.addAssignment(assignedTo, quizTemplateId);
    }
    
    @PostMapping("/updateGrade")
    @CrossOrigin
    public void updateGrade(@RequestParam Long assignmentId, @RequestParam String grade,
                            @RequestParam Long gradedBy) {
        service.updateGrade(assignmentId, grade, gradedBy);
    }
    
    @GetMapping("/getAllGradedAssignments")
    @CrossOrigin
    public Iterable<Assignment> getAllGradedAssignments(@RequestParam Long assignedTo) {
        return service.getAllGradedAssignments(assignedTo);
    }
    
    @GetMapping("/getAllAssignments")
    @CrossOrigin
    public Iterable<Assignment> getAllAssignments() {
        return service.getAllAssignments();
    }
    
    @GetMapping("/getAssignment")
    @CrossOrigin
    public Optional<Assignment> getAssignment(@RequestParam Long assignedTo,
                                              @RequestParam Long quizTemplateId) {
        return service.getAssignment(assignedTo, quizTemplateId);
    }
    
    @PostMapping("/deleteAssignment")
    @CrossOrigin
    public void deleteAssignment(@RequestParam Long assignmentId) {
        service.deleteAssignment(assignmentId);
    }
    
    @GetMapping("/addResponse")
    @CrossOrigin
    public Assignment addResponse(@RequestParam Long assignmentId, @RequestParam Long questionId,
                                  @RequestParam String questionText,
                                  @RequestParam String response, @RequestParam Boolean completed) {
        return service.addResponse(assignmentId, questionId, questionText, response);
    }
    
    @GetMapping("/deleteResponse")
    @CrossOrigin
    public void deleteResponsesForAssignment(@RequestParam Long id) { // response id (generated)
        service.deleteResponse(id);
    }
    
    @GetMapping("/updateCompleteAssignment")
    @CrossOrigin
    public void updateIsComplete(@RequestParam Long assignmentId) { // id = response id (Generated)
        service.updateIsComplete(assignmentId);
    }
    
    @GetMapping("/getAllCompletedAssignments")
    @CrossOrigin
    public Iterable<Assignment> getAllGradedAssignments(@RequestParam Boolean complete) {
        return service.getAllCompletedAssignments(complete);
    }
}
