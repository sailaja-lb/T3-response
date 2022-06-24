package net.yorksolutions.responsebe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseRepository extends CrudRepository<Response, Long> {
//    Iterable<Response> findAllByAssignmentId(Long assignmentId);

//    int countByAssignmentId(Long assignmentId);
    
    Iterable<Response> findAllByCompletedIsTrue();
    
    Optional<Object> findResponseById(Long id);
    
    Optional<Response> findResponsesById(Long id);

//    void deleteAllByAssignmentId(Long assignmentId);
}
