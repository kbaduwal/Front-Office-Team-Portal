package in.kb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Enquiry_Status")
public class EnquiryStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer statusId;
    String statusName;

}
