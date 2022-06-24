package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ResponseController {
    private ResponseService responseService;
    
    @Autowired
    public ResponseController(@NonNull ResponseService responseService) {
        this.responseService = responseService;
    }
    
    @GetMapping("/getAllResponses")
    @CrossOrigin
    public Iterable<Response> getAllResponses() {
        return responseService.getAllResponses();
    }

//    @GetMapping("/addResponse")
//    @CrossOrigin
//    public Assignment addResponse(@RequestParam Long assignmentId, @RequestParam Long
//    questionId, @RequestParam String questionText, @RequestParam String response, @RequestParam
//    Boolean completed) {
//        return responseService.addResponse(assignmentId, questionId, questionText, response,
//        completed);
//    }

//    @GetMapping("/updateIsComplete")
//    @CrossOrigin
//    public void updateIsComplete(@RequestParam Long assignmentId) {
//        responseService.updateIsComplete(assignmentId);
//    }

//    @GetMapping("/deleteResponsesForAssignment")
//    @CrossOrigin
//    public void deleteResponsesForAssignment(@RequestParam Long assignmentId) {
//        responseService.deleteResponsesForAssignment(assignmentId);
//    }
    
    // ********* setters ********
    public void setResponseService(ResponseService editorService) {
        this.responseService = editorService;
    }
}
