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

    String name;

    @Column(unique = true)
    String email;

    @Column(unique = true)
    String password;

    String phoneNumber;

    String accountStatus;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<StudentEnquiriesEntity> enquiries;
}
