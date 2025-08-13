package spring.basics.paymentservice.paymentgateways;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements IPaymentGateway{
    @Override
    public String generatePaymentLink(Long aAmount,String aOrderId, String aPhoneNumber,String aName,String aEmail) {
        return "";
    }
}
