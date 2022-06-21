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
    public void addAssignment(@RequestParam Long assignedTo,
                              @RequestParam Long quizTemplateId) {
        service.addAssignment(assignedTo, quizTemplateId);
    }
    
    //TODO updateGrade

    // TODO ask Leng to show how to use Iterable<Assignment>get all assignments for a user

}
