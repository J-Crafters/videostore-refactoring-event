package be.jcrafters.videostore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.jcafters.videostore.Customer;
import be.jcafters.videostore.Movie;
import be.jcafters.videostore.Rental;

import static be.jcafters.videostore.Movie.CHILDRENS;
import static be.jcafters.videostore.Movie.NEW_RELEASE;
import static be.jcafters.videostore.Movie.REGULAR;
import static org.assertj.core.api.Assertions.assertThat;

class VideoStoreTest {

	@BeforeEach
	void setUp() {
		customer = new Customer("Fred");
	}

	@Test
	void testSingleNewReleaseStatement() {
		customer.addRental(new Rental(new Movie("The Cell", NEW_RELEASE), 3));

		assertThat(customer.statement()).isEqualTo("Rental Record for Fred\n" +
												   "\tThe Cell\t9.0\n" +
												   "You owed 9.0\n" +
												   "You earned 2 frequent renter points\n" +
												   "");
	}

	@Test
	void testDualNewReleaseStatement() {
		customer.addRental(new Rental(new Movie("The Cell", NEW_RELEASE), 3));
		customer.addRental(new Rental(new Movie("The Tigger Movie", NEW_RELEASE), 3));

		assertThat(customer.statement()).isEqualTo("Rental Record for Fred\n" +
												   "\tThe Cell\t9.0\n" +
												   "\tThe Tigger Movie\t9.0\n" +
												   "You owed 18.0\n" +
												   "You earned 4 frequent renter points\n" +
												   "");
	}

	@Test
	void testSingleChildrensStatement() {
		customer.addRental(new Rental(new Movie("The Tigger Movie", CHILDRENS), 3));

		assertThat(customer.statement()).isEqualTo("Rental Record for Fred\n" +
												   "\tThe Tigger Movie\t1.5\n" +
												   "You owed 1.5\n" +
												   "You earned 1 frequent renter points\n" +
												   "");
	}

	@Test
	void testMultipleRegularStatement() {
		customer.addRental(new Rental(new Movie("Plan 9 from Outer Space", REGULAR), 1));
		customer.addRental(new Rental(new Movie("8 1/2", REGULAR), 2));
		customer.addRental(new Rental(new Movie("Eraserhead", REGULAR), 3));

		assertThat(customer.statement()).isEqualTo("Rental Record for Fred\n" +
												   "\tPlan 9 from Outer Space\t2.0\n" +
												   "\t8 1/2\t2.0\n" +
												   "\tEraserhead\t3.5\n" +
												   "You owed 7.5\n" +
												   "You earned 3 frequent renter points\n" +
												   "");
	}

	private Customer customer;
}
