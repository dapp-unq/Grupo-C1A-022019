package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.model.Provider;
import ar.edu.unq.desapp.grupoa.persistence.ProviderRepository;
import ar.edu.unq.desapp.grupoa.service.dto.ProviderDTO;
import ar.edu.unq.desapp.grupoa.service.exceptions.ProviderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void createProvider(final ProviderDTO providerDTO) {
        Provider newProvider = converterHelper.providerDtoToProvider(providerDTO);
        providerRepository.save(newProvider);
        log.info("Created provider: {}", newProvider);
    }

    @Transactional
    public void deleteProvider(final String email) {
        providerRepository.deleteByEmail(email);
        log.info("Deleted provider: {}", email);
    }

    @Transactional
    public void updateProvider(final ProviderDTO providerDTO) {
        Provider provider = findProvider(providerDTO.getEmail());
        provider.setName(providerDTO.getName());
        provider.setLogo(providerDTO.getLogo());
        provider.setCity(providerDTO.getCity());
        provider.setLocation(providerDTO.getLocation());
        provider.setDescription(providerDTO.getDescription());
        provider.setWebsite(providerDTO.getWebsite());
        provider.setPhoneNumber(providerDTO.getPhoneNumber());
        provider.getOpeningHoursDays().clear();
        provider.getOpeningHoursDays().addAll(converterHelper.serviceHoursDtoListToServiceHoursList(providerDTO.getOpeningHoursDays()));
        provider.getDeliveryCities().clear();
        provider.getDeliveryCities().addAll(providerDTO.getDeliveryCities());
        provider.setBalance(providerDTO.getBalance());
        providerRepository.save(provider);
        log.info("Updated provider: {}", providerDTO.getEmail());
    }

    @Transactional
    public void updateProviderMenus(final Provider provider) {
        providerRepository.save(provider);
        log.info("Updated menus for provider: {}", provider.getEmail());
    }

    public ProviderDTO getProvider(final String email) {
        Provider provider = findProvider(email);
        return converterHelper.providerToProviderDTO(provider);
    }

    public Provider findProvider(final String email) {
        return providerRepository.findByEmail(email).orElseThrow(() -> new ProviderNotFoundException("No se encontró el proveedor"));
    }

}
