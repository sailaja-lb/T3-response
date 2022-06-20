package net.yorksolutions.responsebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResponseService {
    private ResponseRepository responseRepository;

    @Autowired
    public ResponseService(){
        this.responseRepository = responseRepository;
    }

//    public ResponseService(ResponseRepository responseRepository, RestTemplate rest) {
//        this.responseRepository = responseRepository;
//    }

    public Iterable<Response> getAllResponses() {
        return responseRepository.findAll();
    }
}
