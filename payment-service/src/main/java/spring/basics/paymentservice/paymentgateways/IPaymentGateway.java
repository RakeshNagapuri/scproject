package spring.basics.paymentservice.paymentgateways;

public interface IPaymentGateway {
    String generatePaymentLink(Long aAmount,String aOrderId, String aPhoneNumber,String aName,String aEmail);
}
