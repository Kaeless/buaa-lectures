package feuyeux.pattern.lsp.equals;

import org.junit.Assert;
import org.junit.Test;

public class TestEquals {
    @Test
    public void testFinal() {
        FinalBase thiz1 = new FinalBase(1, 2);
        FinalBase thiz2 = new FinalBase(2, 1);
        FinalBase thiz3 = new FinalBase(2, 1);
        Assert.assertNotEquals(null, thiz1);
        Assert.assertNotEquals(thiz1, thiz2);
        Assert.assertEquals(thiz3, thiz2);
    }

    @Test
    public void testNormal() {
        Base thiz1 = new Base(1, 2);
        Base thiz2 = new Base(2, 1);
        Base thiz3 = new Base(2, 1);
        Son thiz4 = new Son(2, 1);
        Assert.assertNotEquals(null, thiz1);
        Assert.assertNotEquals(thiz1, thiz2);
        Assert.assertEquals(thiz3, thiz2);
        Assert.assertNotEquals(thiz3, thiz4);
    }
}
