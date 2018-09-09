package feuyeux.pattern.structural.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static feuyeux.pattern.structural.decorator.CaseInputStream.CASE_FLAG.LOWER;
import static feuyeux.pattern.structural.decorator.CaseInputStream.CASE_FLAG.UPPER;

public class TestCaseInputStream {
    final byte[] bytes = "tHis IS a tESt.".getBytes();
    private Logger logger = LogManager.getLogger(TestCaseInputStream.class);

    @Test
    public void testUpperCaseRead() {
        logger.info("UpperCaseRead:");
        try (InputStream in = new CaseInputStream(new ByteArrayInputStream(bytes), UPPER)) {
            byte[] b = new byte[bytes.length];
            in.read(b);
            logger.info(new String(b));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Test
    public void testLowerCaseRead() {
        logger.info("LowerCaseRead:");
        try (InputStream in = new CaseInputStream(new ByteArrayInputStream(bytes), LOWER)) {
            int c;
            StringBuilder bf = new StringBuilder();
            while ((c = in.read()) >= 0) {
                bf.append((char)c);
            }
            logger.info(bf.toString());
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
