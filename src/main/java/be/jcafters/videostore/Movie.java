package be.jcafters.videostore;

public class Movie {

	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	private String title;
	private int priceCode;

	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int code) {
		priceCode = code;
	}

	public String getTitle() {
		return title;
	}

	double determineAmount(int daysRented) {
		return switch (getPriceCode()) {
			case REGULAR -> {
				double rentalAmount = 0;
				rentalAmount += 2;
				if (daysRented > 2) {
					rentalAmount += (daysRented - 2) * 1.5;
				}
				yield rentalAmount;
			}
			case NEW_RELEASE -> {
				double rentalAmount = 0;
				rentalAmount += daysRented * 3;
				yield rentalAmount;
			}
			case CHILDRENS -> {
				double rentalAmount = 0;
				rentalAmount += 1.5;
				if (daysRented > 3) {
					rentalAmount += (daysRented - 3) * 1.5;
				}
				yield rentalAmount;
			}
			default -> throw new IllegalArgumentException();
		};
	}
}
