package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.model.Menu;
import ar.edu.unq.desapp.grupoa.model.Provider;
import ar.edu.unq.desapp.grupoa.persistence.MenuRepository;
import ar.edu.unq.desapp.grupoa.service.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private MenuRepository menuRepository;

    private ProviderService providerService;

    private ConverterHelper converterHelper;

    @Autowired
    public MenuService(final MenuRepository menuRepository, final ProviderService providerService,
                       final ConverterHelper converterHelper) {
        this.menuRepository = menuRepository;
        this.providerService = providerService;
        this.converterHelper = converterHelper;
    }

    public void createMenu(final MenuDTO menuDTO) {
        Provider provider = getProvider(menuDTO.getProviderEmail());
        Menu newMenu = converterHelper.menuDtoToMenu(menuDTO);
        provider.addMenu(newMenu);
        providerService.updateProviderMenus(provider);
    }

    private Provider getProvider(String providerEmail) {
        return providerService.findProvider(providerEmail);
    }

    public void deleteMenu(String providerEmail, String menuName) {
        Provider provider = getProvider(providerEmail);
        provider.removeMenuWithName(menuName);
        providerService.updateProviderMenus(provider);
    }

    public void updateMenu(MenuDTO menuDTO) {
        Provider provider = getProvider(menuDTO.getProviderEmail());
        Menu menuToUpdate = provider.searchMenu(menuDTO.getName());
        menuToUpdate.setName(menuDTO.getName());
        menuToUpdate.setDescription(menuDTO.getDescription());
        menuToUpdate.getCategory().clear();
        menuToUpdate.getCategory().addAll(menuDTO.getCategory());
        menuToUpdate.setDeliveryPrice(menuDTO.getDeliveryPrice());
        menuToUpdate.setEffectivePeriod(converterHelper.effectivePeriodDtoToEffectivePeriod(menuDTO.getEffectivePeriod()));
        menuToUpdate.getDeliverySchedules().clear();
        menuToUpdate.getDeliverySchedules().addAll(menuDTO.getDeliverySchedules());
        menuToUpdate.setAverageDeliveryTime(menuDTO.getAverageDeliveryTime());
        menuToUpdate.setPrice(menuDTO.getPrice());
        menuToUpdate.setDailyStock(menuDTO.getDailyStock());
        menuToUpdate.setOffer1(converterHelper.offerDtoToOffer(menuDTO.getOffer1()));
        menuToUpdate.setOffer2(converterHelper.offerDtoToOffer(menuDTO.getOffer2()));
        providerService.updateProviderMenus(provider);
    }
}
