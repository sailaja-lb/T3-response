package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class ResponseService {
    private ResponseRepository responseRepository;
    private AssignmentRepository assignmentRepository;
    
    @Autowired
    public ResponseService(@NonNull ResponseRepository responseRepository,
                           @NonNull AssignmentRepository assignmentRepository) {
        this.responseRepository = responseRepository;
        this.assignmentRepository = assignmentRepository;
    }
    
    /************************************************************************************
     * Not sure is getAllResponses is needed, can get Assignment and view all responses *
     ************************************************************************************/
    public Iterable<Response> getAllResponses() {
        return responseRepository.findAllByCompletedIsTrue();
    }

//    public Assignment addResponse(Long assignmentId, Long questionId, String questionText,
//                                  String response, Boolean completed) {
//        Optional<Assignment> assignmentOp = assignmentRepository.findById(assignmentId);
//        ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND,
//        "No" +
//                " assignment exists with the given id.");
//
//        if (assignmentRepository.findById(assignmentId).isEmpty()) {
//            throw exception;
//        }
//        Assignment assignment = assignmentOp.get();
//        Response responseObj = new Response(assignmentId, questionId, questionText, response,
//                completed);
//
//        assignment.addResponse(responseObj);
//        assignmentRepository.save(assignment);
//        return assignment;
//    }

//    public void updateIsComplete(Long assignmentId) {
//        Optional<Assignment> assignmentOp = assignmentRepository.findById(assignmentId);
//        ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND,
//                "Assignment not found.");
//
//        if (assignmentOp.isEmpty()) {
//            throw exception;
//        }
//        Assignment assignment = assignmentOp.get();
//        for (Response response : assignment.responses) {
//            response.completed = true;
//        }
//        assignmentRepository.save(assignment);
//    }

//    public void deleteResponsesForAssignment(Long assignmentId) {
//        Optional<Assignment> assignmentOp = assignmentRepository.findById(assignmentId);
//        Iterable<Response> responses = responseRepository.findAllByAssignmentId(assignmentId);
//        ResponseStatusException exception1 = new ResponseStatusException(HttpStatus.NOT_FOUND,
//                "Assignment not found.");
//        ResponseStatusException exception2 = new ResponseStatusException(HttpStatus.NOT_FOUND,
//                "No responses found for this assignment.");
//
//        if (assignmentOp.isEmpty()) {
//            throw exception1;
//        }
//        Assignment assignment = assignmentOp.get();
//        System.out.println("RESPONSES: " + assignment.responses);
//        if (assignment.responses.size() == 0) {
//            throw exception2;
//        }
//        responseRepository.deleteAllByAssignmentId(assignmentId);
//    }
}
