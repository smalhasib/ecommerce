package com.hasib.organization.controller;

import com.hasib.organization.dto.PurchaseDto;
import com.hasib.organization.exception.TransactionFailedException;
import com.hasib.organization.exception.TransactionNotFoundException;
import com.hasib.organization.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase/")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@RequestBody PurchaseDto purchaseDto) {
        try {
            return new ResponseEntity<>(purchaseService.createPurchase(purchaseDto), HttpStatus.CREATED);
        } catch (TransactionFailedException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("pending-deliveries")
    public ResponseEntity<?> getPendingDeliverBySeller(
            @RequestParam int sellerId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        try {
            return ResponseEntity.ok(purchaseService.getUnDeliveredPurchases(sellerId, pageNumber, pageSize));
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("purchased")
    public ResponseEntity<?> getPurchasesByBuyer(
            @RequestParam int buyerId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        try {
            return ResponseEntity.ok(purchaseService.getPurchasesByBuyer(buyerId, pageNumber, pageSize));
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("validate-transaction")
    public ResponseEntity<?> validateTransaction(@RequestParam int transactionId) {
        try {
            return ResponseEntity.ok(purchaseService.validateTransaction(transactionId));
        } catch (TransactionNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("deliver")
    public ResponseEntity<?> deliverPurchases(@RequestParam int purchaseItemId) {
        try {
            return ResponseEntity.ok(purchaseService.deliverPurchases(purchaseItemId));
        } catch (TransactionNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deletePurchase(@PathVariable("id") int purchaseId) {
        purchaseService.deletePurchase(purchaseId);
        return ResponseEntity.ok("Purchase deleted successfully ");
    }

}
