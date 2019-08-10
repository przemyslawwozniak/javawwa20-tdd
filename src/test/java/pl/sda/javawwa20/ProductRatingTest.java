package pl.sda.javawwa20;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductRatingTest {

    @Test
    public void initialize_product_rating_object() {
        ProductRating pr = new ProductRating();
        assertEquals(1, pr.getScore());
    }

    @Test
    public void can_set_value_within_range() {
        ProductRating pr = new ProductRating();
        pr.setScore((short)5);
        assertEquals(5, pr.getScore());
    }

    //dopisac test ktory rzuca wyjatek IllegalArgumentException
    //kiedy przekazemy do metody setScore jako argument
    //1) < 0
    //2) > 10
    //3) < 1

}
