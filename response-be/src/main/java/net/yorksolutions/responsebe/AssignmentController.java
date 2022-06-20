package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping("/addAssignment")
    @CrossOrigin
    public void createAssignment(@RequestParam Long assignedTo, @RequestParam Long assignmentId) {
        service.createAssignment(assignedTo, assignmentId);
    }
    
    
}
