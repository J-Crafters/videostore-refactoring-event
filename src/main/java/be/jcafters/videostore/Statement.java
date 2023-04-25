package be.jcafters.videostore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Statement {

	private String customerName;
	private List<Rental> rentals = new ArrayList<>();
	private double totalAmount;
	private int frequentRenterPoints;

	public Statement(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getTotalAmount() {
		return BigDecimal.valueOf(totalAmount);
	}

	public int getFrequentRenterPoints() {
		return frequentRenterPoints;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String generate() {
		initialise();

		return header() +
			   rentalLines() +
			   footer();
	}

	private void initialise() {
		totalAmount = 0;
		frequentRenterPoints = 0;
	}

	private String header() {
		return "Rental Record for %s\n".formatted(getCustomerName());
	}

	private String rentalLines() {
		StringBuilder rentalLines = new StringBuilder();

		for (Rental rental : rentals) {
			rentalLines.append(rentalLine(rental));
		}

		return rentalLines.toString();
	}

	private String rentalLine(Rental rental) {
		StringBuilder rentalLines = new StringBuilder();
		double thisAmount = 0;

		// determines the amount for each line
		switch (rental.getMovie().getPriceCode()) {
		case Movie.REGULAR -> {
			thisAmount += 2;
			if (rental.getDaysRented() > 2) {
				thisAmount += (rental.getDaysRented() - 2) * 1.5;
			}
		}
		case Movie.NEW_RELEASE -> thisAmount += rental.getDaysRented() * 3;
		case Movie.CHILDRENS -> {
			thisAmount += 1.5;
			if (rental.getDaysRented() > 3) {
				thisAmount += (rental.getDaysRented() - 3) * 1.5;
			}
		}
		default -> throw new IllegalArgumentException();
		}

		frequentRenterPoints++;

		if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
			frequentRenterPoints++;
		}

		rentalLines.append("\t").append(rental.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");

		totalAmount += thisAmount;

		return rentalLines.toString();
	}

	private String footer() {
		return "You owed " + totalAmount + "\n" +
			   "You earned " + frequentRenterPoints + " frequent renter points\n";
	}
}
