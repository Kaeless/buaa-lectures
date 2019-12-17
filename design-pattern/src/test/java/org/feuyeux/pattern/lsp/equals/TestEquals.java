package org.feuyeux.pattern.lsp.equals;

import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;

@Log4j2
public class TestEquals {
    @Test
    public void testFinal() {
        FinalBase a = new FinalBase(1, 2);
        FinalBase b = new FinalBase(2, 1);
        FinalBase c = new FinalBase(2, 1);

        Assert.assertNotEquals(a, b);
        log.info("a.equals(b)?{}", a.equals(b));

        Assert.assertEquals(c, b);
        log.info("c.equals(b)?{}", c.equals(b));
    }

    @Test
    public void testNormal() {
        Base a = new Base(2, 1);
        Base b = new Son(2, 1);
        Son c = new Son(2, 1);

        Assert.assertNotEquals(b, a);
        log.info("b.equals(a)?{}", b.equals(a));

        Assert.assertEquals(b, c);
        log.info("c.equals(b)?{}", c.equals(b));
    }
}
