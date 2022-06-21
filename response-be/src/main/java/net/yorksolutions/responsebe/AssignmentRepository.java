package net.yorksolutions.responsebe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    
    Optional<Assignment> findById(Long assignmentId);
    
    Optional<Assignment> findByQuizTemplateId(Long quizTemplateId);
}
