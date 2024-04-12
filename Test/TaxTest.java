import org.junit.Before;

import static org.junit.Assert.*;

public class TaxTest {

    TaxTest taxTest = new TaxTest();


    @Before
    public void setUp() throws Exception{

    }

    @org.junit.Test
    public void onAccept() {

    }

    @org.junit.Test
    public void onReject() {
       double expected = 0.10;
       double actual = 0.10;
        assertEquals(expected, actual);

    }

    @org.junit.Test
    public void onLand() {
    }
}