package ar.edu.unq.desapp.grupoa.webservice;

import ar.edu.unq.desapp.grupoa.service.ProviderService;
import ar.edu.unq.desapp.grupoa.service.dto.ProviderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/provider")
public class ProviderController {

    private ProviderService providerService;

    @Autowired
    public ProviderController(final ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<ProviderDTO> getProvider(final @PathVariable("email") String email) {
        ProviderDTO provider = providerService.getProvider(email);
        return ResponseEntity.ok(provider);
    }

    @PostMapping
    public ResponseEntity createProvider(final @RequestBody ProviderDTO providerDTO) {
        providerService.createProvider(providerDTO);
        return new ResponseEntity<>("Provider created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity deleteProvider(final @PathVariable("email") String email) {
        providerService.deleteProvider(email);
        return new ResponseEntity<>("Provider deleted successfully", HttpStatus.valueOf(204));
    }

    @PutMapping
    public ResponseEntity updateProvider(final @RequestBody ProviderDTO providerDTO) {
        providerService.updateProvider(providerDTO);
        return new ResponseEntity<>("Provider updated successfully", HttpStatus.valueOf(204));
    }
}
