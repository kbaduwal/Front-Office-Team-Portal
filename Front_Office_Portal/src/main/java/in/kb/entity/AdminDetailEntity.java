package in.kb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Admin_Detail")
public class AdminDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;
    String name;
    @Column(unique = true)
    String email;
    @Column(unique = true)
    String phoneNumber;
    String password;
    String accountStatus;
}
