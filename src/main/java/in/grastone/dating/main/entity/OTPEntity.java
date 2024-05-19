package in.grastone.dating.main.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity(name ="otp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OTPEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;

    @Column(length = 15)
    private String mobileNo;

    @Column(length = 36)
    private String otpKey;

    @Column(length = 6)
    private String otp;

    @Column(length = 1)
    private String status;

    @CreatedDate
    @CreationTimestamp
    private Timestamp timestamp;
}
