package spring.basics.paymentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InitiatePaymentRequestDto {
    Long amount;
    String orderId;
    String phoneNumber;
    String name;
    String email;

}
