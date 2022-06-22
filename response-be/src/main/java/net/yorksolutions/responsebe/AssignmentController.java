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

//    @PostMapping("/updateGradePOST")
//    @CrossOrigin
//    public void updateGradePOST(@RequestBody Long assignmentId, @RequestParam String grade,
//                            @RequestParam Long gradedBy) {
//        service.updateGrade(assignmentId, grade, gradedBy);
//    }
    
    @GetMapping("/getAllGradedResponses")
    @CrossOrigin
    public Iterable<Assignment> getAllGradedResponses(@RequestParam Long assignedTo,
                                                      @RequestParam String grade) {
        return service.getAllGradedResponses(assignedTo, grade);
    }
    
    // TODO ask Leng to show how to use Iterable<Assignment>get all assignments for a user
    
}
