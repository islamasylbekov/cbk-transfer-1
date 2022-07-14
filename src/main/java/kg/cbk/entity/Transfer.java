package kg.cbk.entity;

import kg.cbk.entity.base.TimedEntity;
import kg.cbk.entity.reference.CommonReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "transfer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer extends TimedEntity {

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "sender_surname")
    private String senderSurname;

    @Column(name = "sender_patronymic")
    private String senderPatronymic;

    @ManyToOne
    @JoinColumn(name = "sender_bank_id")
    private Bank senderBank;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "recipient_surname")
    private String recipientSurname;

    @Column(name = "recipient_patronymic")
    private String recipientPatronymic;

    @ManyToOne
    @JoinColumn(name = "recipient_bank_id")
    private Bank recipientBank;

    @Column(name = "money_count")
    private Long moneyCount;

    private String code;

    @ManyToOne
    @JoinColumn(name = "status")
    private CommonReference status;

    @ManyToOne
    @CreatedBy
    private Employee employee;

}
