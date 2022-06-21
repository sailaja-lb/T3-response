package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
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
    
    public void addAssignment(Long assignedTo,Long quizTemplateId) {
        Optional<Assignment> assignment = assignmentRepository.findByQuizTemplateId(quizTemplateId);
        if (assignment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        assignmentRepository.save(new Assignment(assignedTo, quizTemplateId));
    }
    
}
