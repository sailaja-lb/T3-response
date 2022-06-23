package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class ResponseService {
    private ResponseRepository responseRepository;
    private AssignmentRepository assignmentRepository;

    @Autowired
    public ResponseService(@NonNull ResponseRepository responseRepository, @NonNull AssignmentRepository assignmentRepository){
        this.responseRepository = responseRepository;
        this.assignmentRepository = assignmentRepository;
    }

    // constructor may be needed for Mockito
//    public ResponseService(ResponseRepository responseRepository, AssignmentRepository assignmentRepository) {
//        this.responseRepository = responseRepository;
//        this.assignmentRepository = assignmentRepository;
//    }
    
    public Iterable<Response> getAllResponses() {
        return responseRepository.findAllByCompletedIsTrue();
    }

    public Assignment addResponse(Long assignmentId, Long questionId, String questionText, String response, Boolean completed) {
        Optional<Assignment> assignmentOp = assignmentRepository.findById(assignmentId);
        ResponseStatusException exception = new ResponseStatusException(HttpStatus.CONFLICT, "Assignment already exists. Please try a new one.");

        if (assignmentRepository.findById(assignmentId).isEmpty()) {
            throw exception;
        }
        Assignment assignment = assignmentOp.get();
        Response responseObj = new Response(assignmentId, questionId, questionText, response, completed);

        assignment.addResponse(responseObj);
        return assignmentRepository.save(assignment);
    }

//    public Assignment addResponse(Response responseO) {
//        Optional<Assignment> assignmentOp = assignmentRepository.findById(responseO.assignmentId);
//        ResponseStatusException exception = new ResponseStatusException(HttpStatus.CONFLICT, "Assignment already exists. Please try a new one.");
//
//        if (assignmentOp.isEmpty()) {
//            throw exception;
//        }
//        Assignment assignment = assignmentOp.get();
//
//        assignment.addResponse(responseO);
//        return assignmentRepository.save(assignment);
//    }

    public void updateIsComplete(Long assignmentId) {
        Optional<Assignment> assignmentOp = assignmentRepository.findById(assignmentId);
        ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found.");

        if (assignmentOp.isEmpty()) {
            throw exception;
        }
        Assignment assignment = assignmentOp.get();
        for (Response response : assignment.responses) {
            response.completed = true;
        }
        assignmentRepository.save(assignment);
    }

    public void deleteResponsesForAssignment(Long assignmentId) {
        Optional<Assignment> assignmentOp = assignmentRepository.findById(assignmentId);
        Iterable<Response> responses = responseRepository.findAllByAssignmentId(assignmentId);
        ResponseStatusException exception1 = new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found.");
        ResponseStatusException exception2 = new ResponseStatusException(HttpStatus.NOT_FOUND, "No responses found for this assignment.");

        if (assignmentOp.isEmpty()) {
            throw exception1;
        }
        Assignment assignment = assignmentOp.get();
        System.out.println("RESPONSES: " + assignment.responses);
        if (assignment.responses.size() == 0) {
            throw exception2;
        }
        responseRepository.deleteAllByAssignmentId(assignmentId);
    }

//    public Response deleteResponsesForAssignment(Long assignmentId) {
//        ResponseStatusException exception = new ResponseStatusException(HttpStatus.BAD_REQUEST,
//        "Cannot delete an assignment that does not exist");
//
//        if(!responseRepository.existsById(assignmentId)) {
//            throw exception;
//        }
//        Response responseToDelete = responseRepository.findById(assignmentId).get();
//
//        responseRepository.deleteById(assignmentId);
//        return responseToDelete;
//    }


    //    public Iterable<Response> deleteResponsesForAssignment(Long assignmentId) {
////        List<Response> responses = List.of(responseRepository.findAllByAssignmentId
// (assignmentId));
//        Iterable<Response> test = responseRepository.deleteAllByAssignmentId(assignmentId);
//
//    }

//    public Iterable<Response> deleteResponsesForAssignment(Long assignmentId) {
////        List<Response> responseList = new ArrayList<Response>();
//        Iterable<Response> responses = responseRepository.findAllByAssignmentId(assignmentId);
//        ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND, "No responses with that assignment Id were found.");
//        int count = responseRepository.countByAssignmentId(assignmentId);
//        if (count == 0) {
//            throw exception;
//        }
////        responses.forEach(responseList::add);
//
////        if (responseList.size() == 0) {
////            throw exception;
////        }
//
//        for (Response response : responses) {
//            responseRepository.deleteById(response.id);
//        }
//        return responses;
//    }
}
