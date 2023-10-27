package com.navneet.shop.resources.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.navneet.shop.dto.controller.PaymentInfoDTO;
import com.navneet.shop.dto.controller.PurchaseDTO;
import com.navneet.shop.dto.controller.PurchaseResponseDTO;
import com.navneet.shop.resources.CheckoutAPI;
import com.navneet.shop.services.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
public class CheckoutResource implements CheckoutAPI {

    @Autowired
    private CheckoutService service;

    public ResponseEntity<PurchaseResponseDTO> placeOrder(
	    PurchaseDTO purchase) {
	PurchaseResponseDTO purchaseResponse = service
		.placeOrder(purchase);
	return ResponseEntity.ok().body(purchaseResponse);
    }

    public ResponseEntity<String> createPaymentIntent(
	    PaymentInfoDTO paymentInfo) throws StripeException {
	PaymentIntent paymentIntent = service
		.createPaymentIntent(paymentInfo);
	String paymentStr = paymentIntent.toJson();
	return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }
}
