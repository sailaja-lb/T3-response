package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // ********* setters ********
    public void setResponseService(ResponseService editorService) {
        this.responseService = editorService;
    }
}
