package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    private ResponseRepository responseRepository;
    
    @Autowired
    public ResponseService() {
        this.responseRepository = responseRepository;
    }

//    public ResponseService(ResponseRepository responseRepository, RestTemplate rest) {
//        this.responseRepository = responseRepository;
//    }
    
    public Iterable<Response> getAllResponses() {
        return responseRepository.findAll();
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
}
