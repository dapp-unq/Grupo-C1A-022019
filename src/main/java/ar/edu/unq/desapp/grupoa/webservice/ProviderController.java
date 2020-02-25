package ar.edu.unq.desapp.grupoa.webservice;

import ar.edu.unq.desapp.grupoa.model.enums.City;
import ar.edu.unq.desapp.grupoa.service.ProviderService;
import ar.edu.unq.desapp.grupoa.service.dto.CityDTO;
import ar.edu.unq.desapp.grupoa.service.dto.ProviderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@CrossOrigin
@RequestMapping("/rest/provider")
public class ProviderController {

    private ProviderService providerService;

    @Autowired
    public ProviderController(final ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<ProviderDTO> getProvider(final @PathVariable("email") String email) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(providerService.getProvider(email));
        } catch (RuntimeException ex) {
            response = new ResponseEntity("No se encontró al proveedor: " + email, INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<String> createProvider(final @RequestBody ProviderDTO providerDTO) {
        providerService.createProvider(providerDTO);
        return new ResponseEntity<>("Provider created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteProvider(final @PathVariable("email") String email) {
        providerService.deleteProvider(email);
        return new ResponseEntity<>("Provider deleted successfully", HttpStatus.valueOf(204));
    }

    @PutMapping
    public ResponseEntity<String> updateProvider(final @RequestBody ProviderDTO providerDTO) {
        providerService.updateProvider(providerDTO);
        return new ResponseEntity<>("Provider updated successfully", HttpStatus.valueOf(204));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDTO>> cities() {
        return ResponseEntity.ok(Arrays.stream(City.values()).map(city -> new CityDTO(city, city.getFullName())).collect(Collectors.toList()));
    }
}
