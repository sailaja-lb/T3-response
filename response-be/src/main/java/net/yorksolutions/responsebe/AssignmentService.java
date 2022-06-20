package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class AssignmentService {
    
    private AssignmentRepository assignmentRepository;
    
    @Autowired
    public AssignmentService(@NonNull AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }
    
//    public AssignmentsService(AssignmentsRepository assignmentRepository) {
//        this.assignmentRepository = assignmentRepository;
//    }
    
    /********************
     * Code Starts Here *
     ********************/
    
    public void createAssignment(Long assignedTo, Long assignmentId) {
        Optional<Assignment> assignments = assignmentRepository.findById(assignmentId);
        if (assignments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        assignmentRepository.save(new Assignment(assignedTo, assignmentId));
    }
    
    
//    public void addAssignment(Long userId, Long assignmentId) {
//        if (assignmentRepository.findById(assignmentId).isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        assignmentRepository.
//    }
    
}
