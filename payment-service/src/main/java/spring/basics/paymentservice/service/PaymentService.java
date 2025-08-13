package spring.basics.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.basics.paymentservice.paymentgateways.PaymentGatewayChooserStrategy;

@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    public String initiatePayment(Long amount,
                                  String orderId,
                                  String phoneNumber,
                                  String name,
                                  String email) {
        return paymentGatewayChooserStrategy
                .getPaymentGateway()
                .generatePaymentLink(amount,
                        orderId,
                        phoneNumber,
                        name,
                        email);
    }
}
