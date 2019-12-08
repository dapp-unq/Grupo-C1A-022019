package ar.edu.unq.desapp.grupoa.webservice;

import ar.edu.unq.desapp.grupoa.service.PurchaseService;
import ar.edu.unq.desapp.grupoa.service.dto.PurchaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/purchase")
@CrossOrigin
public class PurchaseController {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity createPurchase(final @RequestBody PurchaseDTO purchaseDTO) {
        purchaseService.createPurchase(purchaseDTO);
        return ResponseEntity.ok("Purchase created successfully");
    }
}
