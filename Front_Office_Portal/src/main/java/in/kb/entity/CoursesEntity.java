package in.kb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Courses")
public class CoursesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer courseId;
    String courseName;
}
