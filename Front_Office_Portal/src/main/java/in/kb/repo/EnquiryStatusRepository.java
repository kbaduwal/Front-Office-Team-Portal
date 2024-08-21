package in.kb.repo;

import in.kb.entity.EnquiryStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryStatusRepository extends JpaRepository<EnquiryStatusEntity, Integer> {
}
