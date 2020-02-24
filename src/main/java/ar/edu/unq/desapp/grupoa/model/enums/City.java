package ar.edu.unq.desapp.grupoa.model.enums;

public enum City{

	LUIS_GUILLON("Luis Guillón"),
	NUEVE_DE_JULIO("9 de Julio"),
	MONTE_GRANDE("Monte Grande"),
	CANNING("Canning"),
	BERNAL_OESTE("Bernal Oeste"),
	BERNAL_ESTE("Bernal Este"),
	QUILMES_OESTE("Quilmes Oeste"),
	QUILMES_CENTRO("Quilmes Centro"),
	BERAZATEGUI("Berazategui"),
	EL_JAGUEL("El Jagüel");

	private String fullName;

	City(String fullName) { this.fullName = fullName; }

	public String getFullName() {
		return this.fullName;
	}
}
