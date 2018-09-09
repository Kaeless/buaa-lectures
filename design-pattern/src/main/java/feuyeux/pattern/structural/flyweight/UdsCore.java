package feuyeux.pattern.structural.flyweight;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class UdsCore {
    private Map<String, NluConnection> nluConnectionPool = new HashMap<>(10);

    public void dialog(String group, String query) {
        NluConnection nluConnection = nluConnectionPool.get(group);
        if (nluConnection == null) {
            log.info("new nlu connection");
            nluConnection = new NluConnection();
            nluConnection.setName(group);
            nluConnectionPool.put(group, nluConnection);
        } else {
            log.info("reuse nlu connection");
        }
        log.info("connection name={}", nluConnection.getName());
        log.info(nluConnection.ask(query));
    }
}
