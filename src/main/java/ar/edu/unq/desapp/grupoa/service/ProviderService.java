package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.model.Provider;
import ar.edu.unq.desapp.grupoa.persistence.ProviderRepository;
import ar.edu.unq.desapp.grupoa.service.dto.ProviderDTO;
import ar.edu.unq.desapp.grupoa.service.exceptions.ProviderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProviderService {

    private ProviderRepository providerRepository;

    private ConverterHelper converterHelper;

    @Autowired
    public ProviderService(final ProviderRepository providerRepository, final ConverterHelper converterHelper) {
        this.providerRepository = providerRepository;
        this.converterHelper = converterHelper;
    }

    public void createProvider(final ProviderDTO providerDTO) {
        Provider newProvider = converterHelper.providerDtoToProvider(providerDTO);
        providerRepository.save(newProvider);
        log.info("Created provider: {}", newProvider);
    }

    public void deleteProvider(final String email) {
        providerRepository.deleteByEmail(email);
        log.info("Deleted provider: {}", email);
    }

    public void updateProvider(final ProviderDTO providerDTO) {
        providerRepository.updateProvider(providerDTO.getEmail(), providerDTO.getName(), providerDTO.getLogo(), providerDTO.getCity(),
                providerDTO.getLocation(), providerDTO.getDescription(), providerDTO.getWebsite(), providerDTO.getPhoneNumber(),
                converterHelper.serviceHoursDtoListToServiceHoursList(providerDTO.getOpeningHoursDays()), providerDTO.getDeliveryCities(),
                converterHelper.menuDtoListToMenuList(providerDTO.getCurrentMenus()), providerDTO.getBalance());
    }

    public ProviderDTO getProvider(final String email) {
        Provider provider = providerRepository.findByEmail(email).orElseThrow(ProviderNotFoundException::new);
        return converterHelper.providerToProviderDTO(provider);
    }
}
