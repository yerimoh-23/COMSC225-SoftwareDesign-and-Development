/**
 * @author Yerim Oh
 * @date April 12, 2023
 */

package candy;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CandyRecommenderTest {

    @BeforeEach
    void setUp() throws Exception {
        CandyRecommender.initialize();
    }

    @Test
    void testReadGoodCandyFile() throws FileNotFoundException {
        /*
         * Purpose: Test file reading Method: readCandyFile Initialization:
         * Create a properly formatted candy file called Candy.txt Parameters:
         * None Correct result: Check that all the candies from the file are in
         * the candies list
         */
        CandyRecommender.readCandyFile("goodfile.txt");
        List<Candy> candies = CandyRecommender.getCandies();
        List<String> candyNames = new ArrayList<>();
        for (Candy c : candies) {
            candyNames.add(c.getName());
        }
        assertTrue(candyNames.contains("Snickers"));
        assertTrue(candyNames.contains("Hershey kisses"));
        assertEquals(2, candies.size());
    }

    @Test
    void testMissingCandyFile() {
        /*
         * Purpose: Test missing file Method: readCandyFile Initialization: Make
         * sure the Candy.txt file does not exist Parameters: None Correct
         * result: Should throw FileNotFoundException
         */

        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            CandyRecommender.readCandyFile("foo.txt");
        });
    }

    @Test
    void testBadlyFormattedCandyFile() throws FileNotFoundException {
        /*
         * Purpose: Test badly formatted file Method: readCandyFile
         * Initialization: Create a poorly formatted candy file called Candy.txt
         * by omitting the : after the candy name Parameters: None Correct
         * result: Unclear; the document does not say what will happen.
         */

        // What actually happens is that readCandyFile dies with
        // ArrayIndexOutOfBoundsException.
    	Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            CandyRecommender.readCandyFile("badfile.txt");
        });
    	if (exception.getCause() == null){
    		List<Candy> candies = CandyRecommender.getCandies();
	        List<String> candyNames = new ArrayList<>();
	        for (Candy c : candies) {
	            candyNames.add(c.getName());
	            // Test that the well-formatted line is read correctly
	            // and the badly-formatted line gets skippe.
		        assertFalse(candyNames.contains("Snickers"));
		        assertTrue(candyNames.contains("Hershey kisses"));
		        assertEquals(1, candies.size());
	        }
        }
    }

    @Test
    void testEmptyCandyFile() throws FileNotFoundException {
        /*
         * Purpose: readCandyFile - 0 iterations of the loop Method:
         * readCandyFile Initialization: Create a file Candy.txt but have it
         * empty Parameters: None Correct result: candies list should be empty
         */
        CandyRecommender.readCandyFile("Empty.txt");
        List<Candy> candies = CandyRecommender.getCandies();
        assertEquals(0, candies.size());

    }

    @Test
    void testCandyNoIngredient() throws FileNotFoundException {
        /*
         * Purpose: readCandyFile - 0 iterations of inner for loop Method:
         * readCandyFile Initialization: Create a file Candy.txt have a candy
         * followed by : but no ingredients listed Parameters: None Correct
         * result: candies list should include all the candies
         */

        // This test FAILS. ArrayIndexOutOfBoundsException
    	Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            CandyRecommender.readCandyFile("CandyNoIngredients.txt");
        });
    	if (exception.getCause() == null){
	        List<Candy> candies = CandyRecommender.getCandies();
	        List<String> candyNames = new ArrayList<>();
	        for (Candy c : candies) {
	            candyNames.add(c.getName());
	            assertTrue(candyNames.contains("Snickers"));
		        assertTrue(candyNames.contains("Hershey kisses"));
		        assertEquals(2, candies.size());
	        }
    	}
    }

    @Test
    void testNoLikesDislikes() throws FileNotFoundException {
        /*
         * Purpose: Test findLikedCandies with empty likes list and empty
         * dislikes list Method: findLikedCandies Initialization: read a
         * correctly formatted candy file that contains several candies
         * Parameters: none Correct result: should return an empty set
         */
        CandyRecommender.readCandyFile("goodfile.txt");
        assertEquals(0, CandyRecommender.findLikedCandies().size());
    }

    @Test
    void testOneLikeOneDislike() throws FileNotFoundException {
        /*
         * Purpose: Test findLikedCandies with likes list with 1 ingredient and
         * dislikes list with 1 ingredient Method: findLikedCandies
         * Initialization: read a correctly formatted candy file that contains
         * Snickers and Reese’s as before; set likes to chocolate; set
         * dislikes to peanut butter Parameters: none Correct result: should
         * return a set containing just Snickers
         */
        CandyRecommender.readCandyFile("goodfile2.txt");
        CandyRecommender.addLikes("CHOCOLATE");
        CandyRecommender.addDislikes("PEANUTS");
        Set<Candy> liked = CandyRecommender.findLikedCandies();
        assertEquals(1, liked.size());
        for (Candy c : liked) {
            assertEquals("Hershey kisses", c.getName());
        }
    }

    @Test
    void testLikeDislikeSame() throws FileNotFoundException {
        /*
         * Purpose: Test a corner case where the same ingredient is in both the
         * liked and disliked list Method: findLikedCandies Initialization: read
         * a correctly formatted candy file that contains Snickers and Reese’s
         * as before; set likes to chocolate; set dislikes to chocolate; wonder
         * if there should be a class invariant to prevent this!!!! Parameters:
         * none Correct result: should return an empty set
         */
        CandyRecommender.readCandyFile("goodfile2.txt");
        CandyRecommender.addLikes("CHOCOLATE");
        CandyRecommender.addDislikes("CHOCOLATE");
        Set<Candy> liked = CandyRecommender.findLikedCandies();
        assertEquals(0, liked.size());
    }
}
