package in.kb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Student_Enquiries")
public class StudentEnquiriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer enquiryId;
    String studentName;
    @Column(unique = true)
    String phoneNumber;
    String classMode;
    String courseName;
    String enquiryStatus;
    Date createdDate;
    Date updatedDate;
   @JoinColumn(name = "userId")
    String userId;
}
