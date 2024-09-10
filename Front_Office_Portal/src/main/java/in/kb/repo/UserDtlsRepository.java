package in.kb.repo;

import in.kb.entity.UserDtlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDtlsRepository extends JpaRepository<UserDtlsEntity, Integer> {
}
