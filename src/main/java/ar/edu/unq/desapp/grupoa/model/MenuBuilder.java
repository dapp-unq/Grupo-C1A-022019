package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.enums.Category;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MenuBuilder {
	private String name = "no name";
	private String description = "no menu description 1";
	private List<Category> category = initializeCategory();
	private Integer deliveryPrice = 30;
	private EffectivePeriod effectivePeriod = initializeEffectivePeriod();
	private List<LocalTime> deliverySchedules = initializeDeliverySchedules();
	private LocalTime averageDeliveryTime = LocalTime.of(0, 30);
	private Integer price = 10;
	private Integer dailyStock = 50;
	private Offer offer1 = new Offer(10, 8);
	private Offer offer2 = new Offer(0, 0);

	private List<Category> initializeCategory() {
		List<Category> categorias = new ArrayList<Category>();
		categorias.add(Category.GREEN);
		return categorias;
	}

	private List<LocalTime> initializeDeliverySchedules() {
		List<LocalTime> horariosDeEntrega = new ArrayList<LocalTime>();
		horariosDeEntrega.add(LocalTime.of(10, 0));
		horariosDeEntrega.add(LocalTime.of(20, 0));
		return horariosDeEntrega;
	}

	private EffectivePeriod initializeEffectivePeriod() {
		LocalDate initialDate = LocalDate.of(2019,1,1);
		LocalDate endDate = LocalDate.of(2019,12,30);
		return new EffectivePeriod(initialDate, endDate);
	}

	public static MenuBuilder aMenu() {
		return new MenuBuilder();
	}

	public Menu build() {
		return new Menu(name, description, category, deliveryPrice, effectivePeriod, deliverySchedules,
				averageDeliveryTime, price, dailyStock, offer1, offer2);
	}

	public MenuBuilder withName(String aName) {
		this.name = aName;
		return this;
	}

	public MenuBuilder withDescription(String aDescription) {
		this.description = aDescription;
		return this;
	}

	public MenuBuilder withCategory(List<Category> categories) {
		this.category = categories;
		return this;
	}

	public MenuBuilder withDeliveryPrice(Integer aPrice) {
		this.deliveryPrice = aPrice;
		return this;
	}

	public MenuBuilder withEffectivePeriod(EffectivePeriod dates) {
		this.effectivePeriod = dates;
		return this;
	}

	public MenuBuilder withDeliverySchedules(List<LocalTime> times) {
		this.deliverySchedules = times;
		return this;
	}

	public MenuBuilder withAverageDeliveryTime(LocalTime aTime) {
		this.averageDeliveryTime = aTime;
		return this;
	}

	public MenuBuilder withPrice(Integer aPrice) {
		this.price = aPrice;
		return this;
	}

	public MenuBuilder withOffer1(Offer aOffer) {
		this.offer1 = aOffer;
		return this;
	}

	public MenuBuilder withOffer1MinQuantity(Integer aQuantity) {
		this.offer1 = new Offer(aQuantity, offer1.getPrice());
		return this;
	}

	public MenuBuilder withOffer1Price(Integer aPrice) {
		this.offer1 = new Offer(offer1.getQuantity(), aPrice);
		return this;
	}

	public MenuBuilder withOffer2(Offer aOffer) {
		this.offer2 = aOffer;
		return this;
	}

	public MenuBuilder withOffer2MinQuantity(Integer aQuantity) {
		this.offer2 = new Offer(aQuantity, 6);
		return this;
	}

	public MenuBuilder withOffer2Price(Integer aPrice) {
		this.offer2 = new Offer(40, aPrice);
		return this;
	}

	public MenuBuilder withDailyStock(Integer aStock) {
		this.dailyStock = aStock;
		return this;
	}

}
