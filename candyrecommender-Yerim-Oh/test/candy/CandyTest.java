/**
 * @author Yerim Oh
 * @date April 12, 2023
 */

package candy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CandyTest {
	private List<String> ingredients = new ArrayList<>();
	private String[] candyIng;

	@BeforeEach
	void setUp() throws Exception {
		ingredients = new ArrayList<>();
		candyIng = new String[3];
		candyIng[0] = "chocolate";
		candyIng[1] = "peanuts";
		candyIng[2] = "caramel";
	}

	@Test
	void testCandyConstructor() {
		/*
	     * Purpose: test if the Candy constructor works properly
	     * Method: getName()
	     * Initialization: String array list of ingredients 
	     * Parameters: String candy name, String array of ingredients
	     * Correct result: get candy name and ingredients correctly
	     */
		Candy c = new Candy("Snickers", candyIng);
		// check if the candy name is stored properly
		assertEquals("Snickers", c.getName());
		// check if all the ingredients are stored
		assertEquals(3, c.getIngredients().size());
		// check if all the ingredients are stored properly
		assertTrue(c.getIngredients().contains("chocolate"));
		assertTrue(c.getIngredients().contains("peanuts"));
		assertTrue(c.getIngredients().contains("caramel"));
	}
	
	@Test
	void testScore(){
		/*
	     * Purpose: test if the score method gives the correct score for the liked ingredients
	     * Method: score(), add()
	     * Parameters: String array of ingredients, String array list of likes
	     * Correct result: get a correct score
	     */
		Candy c = new Candy("Snickers", candyIng);
		ingredients = c.getIngredients();
		
		// part 1
		// make liked ingredients list - all ingredients are liked
		List<String> likes = new ArrayList<>();
		likes.add("chocolate");
		likes.add("peanuts");
		likes.add("caramel");
		assertEquals(10, c.score(likes));

		// part 2
		// make liked ingredients list - one ingredient is liked
		List<String> onelike = new ArrayList<>();
		onelike.add("chocolate");
		assertEquals(3, c.score(onelike));
		
		// part 3
		// make disliked ingredients list - all ingredients are disliked
		List<String> allDislike = new ArrayList<>();
		assertEquals(0, c.score(allDislike));
	}
}
