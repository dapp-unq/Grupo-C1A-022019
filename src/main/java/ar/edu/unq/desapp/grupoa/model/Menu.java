package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.enums.Category;
import ar.edu.unq.desapp.grupoa.model.exceptions.DataIncompleteException;
import ar.edu.unq.desapp.grupoa.model.exceptions.DescriptionLengthException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyListException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyStringException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidRankingException;
import ar.edu.unq.desapp.grupoa.model.exceptions.IrrationalAmountException;
import ar.edu.unq.desapp.grupoa.model.exceptions.IrrationalPriceException;
import ar.edu.unq.desapp.grupoa.model.exceptions.NameLengthException;
import ar.edu.unq.desapp.grupoa.model.exceptions.OrderDateException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Getter
@NonNull
@Entity
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ElementCollection(targetClass = Category.class)
    private List<Category> category;
    private Integer deliveryPrice;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private EffectivePeriod effectivePeriod;
    @ElementCollection
    @CollectionTable(name = "menu_deliveries_schedules", joinColumns = @JoinColumn(name = "menu_id"))
    @Column(name = "delivery_schedule")
    private List<LocalTime> deliverySchedules;
    private LocalTime averageDeliveryTime;
    private Integer price;
    private Integer dailyStock;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Offer offer1;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Offer offer2;
    @ElementCollection
    @CollectionTable(name = "menu_rankings", joinColumns = @JoinColumn(name = "menu_id"))
    @Column(name = "menu_ranking")
    private List<Integer> ranking;

    public Menu(String name, String description, List<Category> category, Integer deliveryPrice,
                @NonNull EffectivePeriod effectivePeriod, List<LocalTime> deliverySchedules,
                @NonNull LocalTime averageDeliveryTime, Integer price, Integer dailyStock, Offer offer1, Offer offer2) {
        this.name = validateName(name);
        this.description = validateDescription(description);
        this.category = validateNotEmptyList(category, "categorías");
        this.deliveryPrice = validateDeliveryPrice(deliveryPrice);
        this.effectivePeriod = effectivePeriod;
        this.deliverySchedules = validateDeliverySchedules(deliverySchedules);
        this.averageDeliveryTime = averageDeliveryTime; // O = Optional
        this.price = validatePrice(price);
        this.dailyStock = validateDailyStock(dailyStock);
        this.offer1 = validationOffer1(offer1);
        this.offer2 = validationOffer2(offer2); // Offer(0,0) = Optional
        this.ranking = new ArrayList<>();
    }

    private @NonNull Offer validationOffer2(Offer aOffer) {
        if (aOffer.isEffectiveOffer()) {
            this.validationQuantityOffer(aOffer.getQuantity(), 40, 150);
            this.validationPriceOffer(aOffer.getPrice(), 0, 1000);
            this.validationWithOffer1(aOffer.getQuantity(), aOffer.getPrice());
        }
        return aOffer;
    }

    private void validationWithOffer1(Integer aQuantity, Integer aPrice) {
        if (aQuantity <= this.offer1.getQuantity())
            throw new IrrationalAmountException(
                    "La cantidad minima de la oferta 2 debe ser inferior a la cantidad mínima de la oferta 1.");
        if (aPrice >= this.offer1.getPrice())
            throw new IrrationalPriceException("El precio de la oferta 2 debe ser inferior al precio de la oferta 1");
    }

    private @NonNull Offer validationOffer1(Offer aOffer) {
        this.validationQuantityOffer(aOffer.getQuantity(), 10, 70);
        this.validationPriceOffer(aOffer.getPrice(), 0, 1000);
        return aOffer;
    }

    private void validationWithOffer2(Integer aQuantity, Integer aPrice) {
        if (aQuantity >= this.offer2.getQuantity())
            throw new IrrationalAmountException(
                    "La cantidad mínima de la oferta 1 debe ser menor a la cantidad mínima de la oferta 2.");
        if (aPrice <= this.offer2.getPrice())
            throw new IrrationalPriceException("El precio de la oferta 1 debe ser mayor al precio de la oferta 2.");
    }

    private void validationPriceOffer(Integer aPrice, Integer minPrice, Integer maxPrice) {
        if (aPrice >= this.price)
            throw new IrrationalPriceException("El precio de la oferta debe ser menor al precio normal del menú");
        if (aPrice < minPrice)
            throw new IrrationalPriceException("El precio de la oferta debe ser mayor a " + minPrice + ".");
        if (aPrice > maxPrice)
            throw new IrrationalPriceException("El precio de la oferta debe ser menor a " + maxPrice + ".");
    }

    private void validationQuantityOffer(Integer aQuantity, Integer minQuantity, Integer maxQuantity) {
        if (aQuantity > this.dailyStock)
            throw new IrrationalAmountException(
                    "La cantidad mínima de la oferta no puede ser mayor al del stock diario");
        if (aQuantity < minQuantity)
            throw new IrrationalAmountException(
                    "La cantidad mínima de la oferta debe ser mayor a " + minQuantity + " unidades");
        if (aQuantity > maxQuantity)
            throw new IrrationalAmountException(
                    "La cantidad mínima de la oferta no puede superar las " + maxQuantity + " unidades");
    }

    private @NonNull Integer validateDailyStock(Integer dailyStock) {
        if (dailyStock <= 0)
            throw new IrrationalAmountException("El menú debe tener al menos una unidad diaria disponible");
        return dailyStock;
    }

    private @NonNull Integer validatePrice(Integer price) {
        if (price <= 0)
            throw new IrrationalPriceException("El menú debe tener un valor mayor a 0");
        return price;
    }

    private @NonNull List<LocalTime> validateDeliverySchedules(List<LocalTime> deliverySchedules) {
        validateNotEmptyList(deliverySchedules, "horarios de entrega");
        if (deliverySchedules.size() != 2)
            throw new DataIncompleteException("El menú debe tener un horario de entrega inicial y final");
        return deliverySchedules;
    }

    private @NonNull Integer validateDeliveryPrice(Integer price) {
        if (price > 40)
            throw new IrrationalPriceException("El precio máximo de envío es de 40");
        if (price < 10 && price != 0)
            throw new IrrationalPriceException("El precio mínimo de envío es de 10");
        return price;
    }

    private <T> List<T> validateNotEmptyList(List<T> parameter, String parameterName) {
        if (parameter.isEmpty())
            throw new EmptyListException("El campo " + parameterName + " no puede ser vacío");
        return parameter;
    }

    private @NonNull String validateDescription(String description) {
        int size = description.length();
        if (size < 20)
            throw new DescriptionLengthException("La descripción del menú debe tener al menos 20 caracteres.");
        if (size > 40)
            throw new DescriptionLengthException("La descripción del menú debe tener hasta 40 caracteres.");
        return validateNotEmpty(description, "descripción");
    }

    private @NonNull String validateName(String name) {
        int size = name.length();
        if (size < 4)
            throw new NameLengthException("El nombre del menú debe tener al menos 4 caracteres.");
        if (size > 30)
            throw new NameLengthException("El nombre del menú debe tener hasta 30 caracteres.");
        return validateNotEmpty(name, "nombre");
    }

    private String validateNotEmpty(String parameter, String parameterName) {
        if (parameter.isEmpty())
            throw new EmptyStringException("El campo " + parameterName + " no puede ser vacío");
        return parameter;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setDescription(String description) {
        this.description = validateDescription(description);
    }

    public void setCategory(List<Category> category) {
        this.category = validateNotEmptyList(category, "categorías");
    }

    public void setDeliveryPrice(Integer deliveryPrice) {
        this.deliveryPrice = validateDeliveryPrice(deliveryPrice);
    }

    public void setEffectivePeriod(@NonNull EffectivePeriod effectivePeriod) {
        this.effectivePeriod = effectivePeriod;
    }

    public void setDeliverySchedules(List<LocalTime> deliverySchedules) {
        this.deliverySchedules = validateDeliverySchedules(deliverySchedules);
    }

    public void setAverageDeliveryTime(@NonNull LocalTime averageDeliveryTime) {
        this.averageDeliveryTime = averageDeliveryTime;
    }

    public void setPrice(Integer price) {
        this.price = validatePrice(price);
    }

    public void setOffer1(Offer aOffer) {
        this.offer1 = validationSetOffer1(aOffer);
    }

    private @NonNull Offer validationSetOffer1(Offer aOffer) {
        this.validationQuantityOffer(aOffer.getQuantity(), 10, 70);
        this.validationPriceOffer(aOffer.getPrice(), 0, 1000);
        if (this.offer2.isEffectiveOffer()) {
            this.validationWithOffer2(aOffer.getQuantity(), aOffer.getPrice());
        }
        return aOffer;
    }

    public void setOffer2(Offer aOffer) {
        this.offer2 = validationOffer2(aOffer);
    }

    public void setDailyStock(Integer dailyStock) {
        this.dailyStock = validateDailyStock(dailyStock);
    }

    public Boolean hasName(String menuName) {
        return this.name.equals(menuName);
    }

    public Boolean hasNameMatchedWith(String text) {
        return this.name.toLowerCase().contains(text.toLowerCase());
    }

    public Boolean hasCategory(Category category) {
        return this.category.contains(category);
    }

    public void rankIt(Integer ranking) {
        if (ranking > 5 || ranking <= 0)
            throw new InvalidRankingException("Puntuación inválida: La calificación del menú debe ser entre 0 y 5");

        this.ranking.add(ranking);
    }

    public void validationNumberMenuOrdered(Integer aQuantity) {
        if (aQuantity > this.dailyStock)
            throw new IrrationalAmountException(
                    "La cantidad de pedida supera la cantidad de ventas disponibles del menú.");
    }

    public void validationDateDeliveryMenuOrdered(LocalDateTime dateHoursOrder,
                                                  LocalDateTime dateHoursDelivery) {
        if (!this.has48HoursBetween(dateHoursOrder, dateHoursDelivery))
            throw new OrderDateException("El pedido debe hacerse al menos 48hs hábiles antes de la entrega del mismo.");
    }

    private Boolean has48HoursBetween(LocalDateTime from, LocalDateTime to) {
        LocalDateTime from2DaysAfter = from.plus(2, ChronoUnit.DAYS);
        return to.isAfter(from2DaysAfter);
    }

    public Integer valueForQuantity(Integer quantity) {
        Integer price = this.price;
        if (quantity >= this.offer1.getQuantity())
            price = this.offer1.getPrice();
        if (this.offer2.isEffectiveOffer() && (quantity >= this.offer2.getQuantity()))
            price = this.offer2.getPrice();
        return price;
    }

    public boolean hasLowQualityMenu() {
        return (this.ranking.size() == 20) && (this.averageRating() < 3);
    }

    public Integer averageRating() {
        Integer average = 0;
        if (!this.ranking.isEmpty()) {
            Integer sumRanking = this.ranking.stream().reduce(0, Integer::sum);
            average = sumRanking / this.ranking.size();
        }
        return average;
    }
}
