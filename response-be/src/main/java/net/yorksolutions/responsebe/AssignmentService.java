package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AssignmentService {
    
    final AssignmentRepository assignmentRepository;
    
    @Autowired
    public AssignmentService(@NonNull AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }
    
    /********************
     * Code Starts Here *
     ********************/
    
    public void addAssignment(Long assignedTo, Long quizTemplateId) {
        Optional<Assignment> assignment = assignmentRepository.findByQuizTemplateId(quizTemplateId);
        if (assignment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        assignmentRepository.save(new Assignment(assignedTo, quizTemplateId));
    }
    
    public void updateGrade(Long assignmentId, String grade, Long gradedBy) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        if (assignment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            assignment.get().grade = grade;
            assignment.get().gradedBy = gradedBy;
            assignmentRepository.save(assignment.get());
        }
    }
    
    public Iterable<Assignment> getAllGradedResponses(Long assignedTo, String grade) {
        return assignmentRepository.findAllByAssignedToAndGrade(assignedTo, grade);
    }
    
    public Iterable<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }
    
    
}
