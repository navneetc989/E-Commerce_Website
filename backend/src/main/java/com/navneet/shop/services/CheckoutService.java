package com.navneet.shop.services;

import com.navneet.shop.dto.controller.PaymentInfoDTO;
import com.navneet.shop.dto.controller.PurchaseDTO;
import com.navneet.shop.dto.controller.PurchaseResponseDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {
    PurchaseResponseDTO placeOrder(PurchaseDTO purchase);
    PaymentIntent createPaymentIntent(PaymentInfoDTO paymentInfoDTO) throws StripeException;
}
