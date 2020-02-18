package ar.edu.unq.desapp.grupoa.webservice;

import ar.edu.unq.desapp.grupoa.model.exceptions.InsufficientCurrencyException;
import ar.edu.unq.desapp.grupoa.service.PurchaseService;
import ar.edu.unq.desapp.grupoa.service.dto.PurchaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@CrossOrigin
@RequestMapping("/rest/purchase")
public class PurchaseController {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<String> createPurchase(final @RequestBody PurchaseDTO purchaseDTO) {
        ResponseEntity<String> response;
        try{
            purchaseService.createPurchase(purchaseDTO);
            response = ResponseEntity.ok("Purchase created successfully");
        } catch (InsufficientCurrencyException e) {
            response = new ResponseEntity<>("Insufficient Currency for purchase", INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/test")
    public ResponseEntity<String> triggerProcessPurchases(){
        purchaseService.processOrders();
        return ResponseEntity.ok("Orders processed");
    }
}
