package in.kb.repo;

import in.kb.entity.CoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CoursesEntity, Integer> {
}
