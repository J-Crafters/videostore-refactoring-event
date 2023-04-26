package be.jcrafters.videostore;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.jcafters.videostore.Movie;
import be.jcafters.videostore.Rental;
import be.jcafters.videostore.Statement;

import static org.assertj.core.api.Assertions.assertThat;

class VideoStoreTest {

	private Statement statement;
	private Movie newReleaseMovie1;
	private Movie newReleaseMovie2;
	private Movie childrensMovie;
	private Movie regularMovie1;
	private Movie regularMovie2;
	private Movie regularMovie3;

	@BeforeEach
	void setUp() {
		statement = new Statement("Fred");
		newReleaseMovie1 = new NewReleaseMovie("The Cell");
		newReleaseMovie2 = new NewReleaseMovie("The Tigger Movie");
		childrensMovie = new ChildrensMovie("The Tigger Movie");
		regularMovie1 = new RegularMovie("Plan 9 from Outer Space");
		regularMovie2 = new RegularMovie("8 1/2");
		regularMovie3 = new RegularMovie("Eraserhead");
	}

	@Test
	void testSingleNewReleaseStatement() {
		statement.addRental(new Rental(newReleaseMovie1, 3));

		statement.generate();

		assertThat(statement.getTotalAmount()).isEqualTo(BigDecimal.valueOf(9.0));
		assertThat(statement.getFrequentRenterPoints()).isEqualTo(2);
	}

	@Test
	void testDualNewReleaseStatement() {
		statement.addRental(new Rental(newReleaseMovie1, 3));
		statement.addRental(new Rental(newReleaseMovie2, 3));

		statement.generate();

		assertThat(statement.getTotalAmount()).isEqualTo(BigDecimal.valueOf(18.0));
		assertThat(statement.getFrequentRenterPoints()).isEqualTo(4);
	}

	@Test
	void testSingleChildrensStatement() {
		statement.addRental(new Rental(childrensMovie, 3));

		statement.generate();

		assertThat(statement.getTotalAmount()).isEqualTo(BigDecimal.valueOf(1.5));
		assertThat(statement.getFrequentRenterPoints()).isEqualTo(1);
	}

	@Test
	void testMultipleRegularStatement() {
		statement.addRental(new Rental(regularMovie1, 1));
		statement.addRental(new Rental(regularMovie2, 2));
		statement.addRental(new Rental(regularMovie3, 3));

		statement.generate();

		assertThat(statement.getTotalAmount()).isEqualTo(BigDecimal.valueOf(7.5));
		assertThat(statement.getFrequentRenterPoints()).isEqualTo(3);
	}

	@Test
	void testMultipleRegularStatementsFormat() {
		statement.addRental(new Rental(regularMovie1, 1));
		statement.addRental(new Rental(regularMovie2, 2));
		statement.addRental(new Rental(regularMovie3, 3));

		statement.generate();

		assertThat(statement.generate()).isEqualTo(
				"""
						Rental Record for Fred
						\tPlan 9 from Outer Space\t2.0
						\t8 1/2\t2.0
						\tEraserhead\t3.5
						You owed 7.5
						You earned 3 frequent renter points
						""");
	}
}
