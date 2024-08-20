package in.kb.repo;

import in.kb.entity.EnquiryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryStatusRepository extends JpaRepository<EnquiryStatus, Integer> {
}
