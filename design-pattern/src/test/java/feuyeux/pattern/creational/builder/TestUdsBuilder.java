package feuyeux.pattern.creational.builder;

import feuyeux.pattern.creational.abstract_factory.TestBuildingNlu;
import feuyeux.pattern.pojo.Qas;
import feuyeux.pattern.pojo.StreamingNlu;
import feuyeux.pattern.pojo.Uds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class TestUdsBuilder {
    private static final Logger log = LogManager.getLogger(TestBuildingNlu.class);

    private UdsBuilder udsBuilder;

    @Before
    public void before() {
        udsBuilder = new UdsBuilder();
    }

    @Test
    public void testBuilder() {
        Uds uds = udsBuilder.nlu(new StreamingNlu()).qas(new Qas()).build();
        log.info(uds.getNlu().whoAmI());
    }
}
