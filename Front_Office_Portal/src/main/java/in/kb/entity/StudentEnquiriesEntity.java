package in.kb.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    Date createdDate;

    @UpdateTimestamp
    Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserDtlsEntity user;
}
