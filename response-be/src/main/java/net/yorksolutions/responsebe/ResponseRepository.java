package net.yorksolutions.responsebe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseRepository extends CrudRepository<Response, Long> {
    
    Optional<Object> findResponseById(Long id);
    
    Optional<Response> findResponsesById(Long id);
    
    Optional<Response> findResponseByQuestionId(Long questionId);
}
