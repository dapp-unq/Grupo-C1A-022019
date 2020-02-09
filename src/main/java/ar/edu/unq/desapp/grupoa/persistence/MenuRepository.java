package ar.edu.unq.desapp.grupoa.persistence;

import ar.edu.unq.desapp.grupoa.model.Menu;
import ar.edu.unq.desapp.grupoa.model.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends PagingAndSortingRepository<Menu, Long>, JpaRepository<Menu, Long> {

    Page<Menu> findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(final Integer minPrice, final Integer maxPrice, final Pageable page);

    Page<Menu> findAllByCategoryEquals(final Category aCategory, final Pageable page);

}
