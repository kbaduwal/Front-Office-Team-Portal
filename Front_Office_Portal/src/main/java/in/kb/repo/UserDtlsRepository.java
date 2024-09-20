package in.kb.repo;

import in.kb.entity.UserDtlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDtlsRepository extends JpaRepository<UserDtlsEntity, Integer> {
    //Checking whether that mail have been already used or not
    public UserDtlsEntity findByEmail(String email);

    public UserDtlsEntity findByEmailAndPassword(String email, String password);

}
