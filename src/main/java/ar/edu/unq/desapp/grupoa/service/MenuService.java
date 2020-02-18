package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.model.Menu;
import ar.edu.unq.desapp.grupoa.model.Provider;
import ar.edu.unq.desapp.grupoa.model.enums.Category;
import ar.edu.unq.desapp.grupoa.persistence.MenuRepository;
import ar.edu.unq.desapp.grupoa.service.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void createMenu(final MenuDTO menuDTO) {
        Provider provider = getProvider(menuDTO.getProviderEmail());
        Menu newMenu = converterHelper.menuDtoToMenu(menuDTO);
        provider.addMenu(newMenu);
        providerService.updateProviderMenus(provider);
    }

    private Provider getProvider(final String providerEmail) {
        return providerService.findProvider(providerEmail);
    }

    @Transactional
    public void deleteMenu(final String providerEmail, final String menuName) {
        Provider provider = getProvider(providerEmail);
        provider.removeMenuWithName(menuName);
        providerService.updateProviderMenus(provider);
    }

    @Transactional
    public void updateMenu(final MenuDTO menuDTO) {
        Provider provider = getProvider(menuDTO.getProviderEmail());
        Menu menuToUpdate = provider.searchMenu(menuDTO.getName());
        menuToUpdate.setName(menuDTO.getName());
        menuToUpdate.setDescription(menuDTO.getDescription());
        menuToUpdate.setImage(menuDTO.getImage());
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

    public Page<Menu> getBetweenMinPriceAndMaxPrice(final Integer minPrice, final Integer maxPrice, final int page, final int itemsPerPage) {
        return menuRepository.findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(minPrice, maxPrice, PageRequest.of(page, itemsPerPage));
    }

    public List<Menu> getBetweenMinRankAndMaxRank(final Integer minRank, final Integer maxRank) {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream().filter(menu -> {
            Integer averageRank = menu.averageRating();
            return averageRank >= minRank && averageRank <= maxRank;
        }).collect(Collectors.toList());
    }

    public Page<Menu> getByCategory(final Category category, final int page, final int itemsPerPage) {
        return menuRepository.findAllByCategoryEquals(category, PageRequest.of(page, itemsPerPage));
    }


}
