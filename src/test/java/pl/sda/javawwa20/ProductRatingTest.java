package pl.sda.javawwa20;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

    @Test(expected = IllegalArgumentException.class)
    public void cannot_set_negative_value() {
        ProductRating pr = new ProductRating();
        pr.setScore((short)-1);
    }

    //ten test mowi nam, ze jezeli pojawi sie w trakcie jego
    //wykonania wyjatek IllegalArgumentException
    //to test jest ZALICZONY
    //w przeciwnym wypadku test NIE PRZECHODZI
    @Test(expected = IllegalArgumentException.class)
    public void cannot_set_value_more_than_10() {
        ProductRating pr = new ProductRating();
        pr.setScore((short)11);
    }
}
