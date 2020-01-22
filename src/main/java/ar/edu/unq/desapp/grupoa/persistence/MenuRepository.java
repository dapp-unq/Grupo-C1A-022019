package ar.edu.unq.desapp.grupoa.persistence;

import ar.edu.unq.desapp.grupoa.model.Menu;
import ar.edu.unq.desapp.grupoa.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends PagingAndSortingRepository<Menu, Long>, JpaRepository<Menu, Long> {

    List<Menu> findByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(final Integer minPrice, final Integer maxPrice);

    List<Menu> findByCategoryEquals(final Category aCategory);

}
