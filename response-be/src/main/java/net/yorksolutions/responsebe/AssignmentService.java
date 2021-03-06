package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {
    
    final AssignmentRepository assignmentRepository;
    
    private ResponseRepository responseRepository;
    
    @Autowired
    public AssignmentService(@NonNull AssignmentRepository assignmentRepository,
                             @NonNull ResponseRepository responseRepository) {
        this.assignmentRepository = assignmentRepository;
        this.responseRepository = responseRepository;
    }
    
    /********************
     * Code Starts Here *
     ********************/

    public void addAssignment(Long assignedTo, Long quizTemplateId) {
        Optional<Assignment> assignment = assignmentRepository.findByAssignedToAndQuizTemplateId(assignedTo,quizTemplateId);
        if(assignment.isEmpty()){
            assignmentRepository.save(new Assignment(assignedTo, quizTemplateId));}
        else {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
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
        if (isAssignedTo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return assignmentRepository.findAllByAssignedToAndGradeNotNull(assignedTo);
        }
    }
    
    public Iterable<Assignment> getAllAssignments() {
        return assignmentRepository.findAllByOrderByQuizTemplateId();
    }
    
    public Optional<Assignment> getAssignment(Long assignedTo, Long quizTemplateId) {
        return assignmentRepository.findByAssignedToAndQuizTemplateId(assignedTo,
                quizTemplateId);
    }
    
    public void deleteAssignment(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository.findByAssignmentId(assignmentId);
        if (assignment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            assignmentRepository.delete(assignment.get());
        }
    }
    
    public Assignment addResponse(Long assignmentId, Long questionId, String questionText,
                                  String response) {
        Optional<Assignment> assignmentOp = assignmentRepository.findById(assignmentId);
        if (assignmentRepository.findById(assignmentId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No" +
                    " assignment exists with the given id.");
            
        }
        // find all response records for that assignment id
        // within those, is there a record for the question id
        //
        Assignment assignment = assignmentOp.get();
        boolean safeToAdd = true;
        List<Response> responseList = assignment.responses;
        for (Response resp : responseList) {
            if (resp.questionId == questionId) {
                safeToAdd = false;
            }
            break;
        }

        if (!safeToAdd) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        }

        Response responseObj = new Response(questionId, questionText, response);
        assignment.addResponse(responseObj);
        assignmentRepository.save(assignment);
        return assignment;



//        else if (responseRepository.findResponseByQuestionId(questionId).isPresent() &&
//                assignmentRepository.findById(assignmentId).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
//
//        } else {
//            Assignment assignment = assignmentOp.get();
//            Response responseObj = new Response(questionId, questionText, response);
//            assignment.addResponse(responseObj);
//            assignmentRepository.save(assignment);
//            return assignment;
//        }
    }
    
    
    public void deleteResponse(Long id) { // id = response id (Generated)
        Optional<Response> responseOptional = responseRepository.findResponsesById(id);
        Optional<Assignment> assignmentOptional =
                assignmentRepository.findAssignmentByResponsesId(id);
        if (responseRepository.findResponseById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Assignment assignment = assignmentOptional.get();
        Response response = responseOptional.get();
        assignment.getResponses().remove(response);
        assignmentRepository.save(assignment);
        responseRepository.delete(response);
    }
    
    public void updateIsComplete(Long assignmentId) {
        Optional<Assignment> assignmentOptional =
                assignmentRepository.findById(assignmentId);
        ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Assignment not found.");
        
        if (assignmentOptional.isEmpty()) {
            throw exception;
        }
        Assignment assignment = assignmentOptional.get();
        assignment.completed = true;
        assignmentRepository.save(assignment);
    }
    
    public Iterable<Assignment> getAllCompletedAssignments(Boolean complete) {
        Iterable<Assignment> isAssignedTo =
                assignmentRepository.findAllByCompleted(complete);
        if (isAssignedTo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return assignmentRepository.findAllByCompleted(complete);
        }
    }
}
