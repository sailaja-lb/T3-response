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
    
    public Iterable<Assignment> getAllGradedAssignments(Long assignedTo) {
        Iterable<Assignment> isAssignedTo =
                assignmentRepository.findAllByAssignedToAndGradeNotNull(assignedTo);
//        Iterable<Assignment> hasGrade = assignmentRepository.findAllByGradeNotNull(assignedTo);
        if (isAssignedTo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        } else if (hasGrade == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return assignmentRepository.findAllByAssignedToAndGradeNotNull(assignedTo);
        }
    }
    
    public Iterable<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }
    
    // getAssignment by assignedTo and quizTemplateId
    public Optional<Assignment> getAssignment(Long assignedTo, Long quizTemplateId) {
        return assignmentRepository.findByAssignedToAndQuizTemplateId(assignedTo,
                quizTemplateId);
    }
    
}
