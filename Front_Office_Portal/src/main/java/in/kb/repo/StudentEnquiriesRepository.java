package in.kb.repo;

import in.kb.entity.StudentEnquiries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnquiriesRepository extends JpaRepository<StudentEnquiries, Integer> {
}
