package spring.basics.paymentservice.paymentgateways;

import com.razorpay.PaymentLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Component
public class RazorPaymentGateway implements IPaymentGateway{

    @Autowired
    private RazorpayClient razorpayClient;

    @Override
    public String generatePaymentLink(Long aAmount,String aOrderId, String aPhoneNumber,String aName,String aEmail) {
        try {

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",aAmount);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("accept_partial",true);
            paymentLinkRequest.put("first_min_partial_amount",100);
            paymentLinkRequest.put("expire_by",1818090584);
            paymentLinkRequest.put("reference_id",aOrderId);
            paymentLinkRequest.put("description","Payment for policy no #23456");
            JSONObject customer = new JSONObject();
            customer.put("name",aPhoneNumber);
            customer.put("contact",aName);
            customer.put("email",aEmail);
            paymentLinkRequest.put("customer",customer);
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("reminder_enable",true);
            JSONObject notes = new JSONObject();
            notes.put("policy_name","Life Insurance Policy");
            paymentLinkRequest.put("notes",notes);
            paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
            paymentLinkRequest.put("callback_method","get");

            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url").toString();
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }
}
