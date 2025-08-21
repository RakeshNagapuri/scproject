package spring.basics.paymentservice.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements IPaymentGateway{

    @Value("${stripe.apikey}")
    private String stripeApikey;
    @Override
    public String generatePaymentLink(Long aAmount,String aOrderId, String aPhoneNumber,String aName,String aEmail) {
        Stripe.apiKey = this.stripeApikey;
        try {
            Price price = getPrice(aAmount);

            PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();


            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    private static Price getPrice(Long aAmount) throws StripeException {
        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(aAmount)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        return Price.create(priceCreateParams);
    }
}
