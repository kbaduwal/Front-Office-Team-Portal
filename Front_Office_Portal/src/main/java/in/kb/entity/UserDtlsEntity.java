package in.kb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Admin_Detail")
public class UserDtlsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;
    String username;
    @Column(unique = true)
    String email;
    @Column(unique = true)
    String password;
    String phoneNumber;
    String accountStatus;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<StudentEnquiriesEntity> enquiries;
}
