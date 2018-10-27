package org.feuyeux.pattern.structural.flyweight;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class UdsCore {
    private final Map<String, NluConnection> nluConnectionPool = new HashMap<>(10);

    public void dialog(String group, String query) {
        NluConnection nluConnection = getNluConnection(group);
        final String connectionName = nluConnection.getName();
        log.info("connection name={}", connectionName);
        log.info(nluConnection.ask(query));
    }

    private NluConnection getNluConnection(String connectionName) {
        NluConnection nluConnection = nluConnectionPool.get(connectionName);
        if (nluConnection == null) {
            log.info("new nlu connection");
            nluConnection = new NluConnection();
            nluConnection.setName(connectionName);
            nluConnectionPool.put(connectionName, nluConnection);
        } else {
            log.info("reuse nlu connection");
        }
        return nluConnection;
    }
}
