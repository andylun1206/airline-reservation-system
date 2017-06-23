package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentInfoTest {
    private static final String CARD_NUM = "5414526595842653";
    private static final String EXPIRY_DATE = "02/18";
    private static final String FULL_PHONE_NUM = "12048596251";
    private static final String ADDRESS = "42 Bob Bay";
    private static final String NAME = "Bob Bob";
    private static final String CANADA = "Canada";
    private static final String POSTAL_CODE = "R2P3C7";
    private static final String PROVINCE = "Manitoba";

    private PaymentInfo.PaymentInfoBuilder builder;

    @Before
    public void setUp() {
        builder = new PaymentInfo.PaymentInfoBuilder(CARD_NUM, EXPIRY_DATE);
    }

    @After
    public void tearDown() {
        builder = null;
    }

    @Test
    public void testGetters() {
        PaymentInfo info = builder.build();
        assertEquals(CARD_NUM, info.getCardNum());
        assertEquals(EXPIRY_DATE, info.getExpiryDate());
        assertSame(null, info.getCountry());
        assertSame(null, info.getPostalCode());

        builder.setName(NAME)
                .setAddress(ADDRESS)
                .setCountry(CANADA)
                .setPhoneNumber(FULL_PHONE_NUM)
                .setPostalCode(POSTAL_CODE)
                .setProvince(PROVINCE);
        info = builder.build();

        assertEquals(NAME, info.getName());
        assertEquals(ADDRESS, info.getAddress());
        assertEquals(CANADA, info.getCountry());
        assertEquals(FULL_PHONE_NUM, info.getFullPhoneNum());
        assertEquals(POSTAL_CODE, info.getPostalCode());
        assertEquals(PROVINCE, info.getProvince());
    }

}
