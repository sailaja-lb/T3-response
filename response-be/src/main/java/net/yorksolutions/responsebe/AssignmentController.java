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
    
}
