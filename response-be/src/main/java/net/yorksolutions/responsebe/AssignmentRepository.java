package net.yorksolutions.responsebe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    
    Optional<Assignment> findByAssignmentId(Long assignmentId);
    
    Optional<Assignment> findByQuizTemplateId(Long quizTemplateId);
    
    Optional<Assignment> findByAssignedToAndQuizTemplateId(Long assignedTo, Long quizTemplateId);
    
    Iterable<Assignment> findAllByAssignedToAndGradeNotNull(Long assignedTo);
    
    Optional<Assignment> findAssignmentByResponsesId(Long id);
    
    Iterable<Assignment> findAllByCompleted(Boolean complete);

    Iterable<Assignment> findAllByOrderByQuizTemplateId();
}
