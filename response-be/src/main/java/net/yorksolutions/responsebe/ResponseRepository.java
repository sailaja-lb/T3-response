package net.yorksolutions.responsebe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends CrudRepository<Response, Long> {
    Iterable<Response> findAllByAssignmentId(Long assignmentId);

    int countByAssignmentId(Long assignmentId);
//    Iterable<Response> findResponseByCompletedIsTrue();
    Iterable<Response> findAllByCompletedIsTrue();

}
