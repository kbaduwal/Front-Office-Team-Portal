package in.kb.repo;

import in.kb.entity.StudentEnquiriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnquiriesRepository extends JpaRepository<StudentEnquiriesEntity, Integer> {
}
