package net.yorksolutions.responsebe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentsRepository extends CrudRepository<Assignments, Long> {
    
    Optional<Assignments> findById(Long id);
    
}
