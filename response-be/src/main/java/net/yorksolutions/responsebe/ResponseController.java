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

//    @DeleteMapping("/deleteResponseForAssignment/{assignmentId}")
//    @CrossOrigin
//    public Response deleteResponseForAssignment(@PathVariable Long assignmentId) {
//        return responseService.deleteResponseForAssignment(assignmentId);
//    }

    @GetMapping("/updateIsComplete")
    @CrossOrigin
    public void updateIsComplete(@RequestParam Long assignmentId) {
        responseService.updateIsComplete(assignmentId);
    }

    @GetMapping("/deleteResponsesForAssignment/")
    @CrossOrigin
    public void deleteResponsesForAssignment(@RequestParam Long assignmentId) {
        responseService.deleteResponsesForAssignment(assignmentId);
    }

    // ********* setters ********
    public void setResponseService(ResponseService editorService) {
        this.responseService = editorService;
    }
}
