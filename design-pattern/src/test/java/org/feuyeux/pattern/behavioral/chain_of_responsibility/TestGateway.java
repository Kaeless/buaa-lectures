package org.feuyeux.pattern.behavioral.chain_of_responsibility;

import org.junit.Test;

public class TestGateway {
    @Test
    public void test() {
        RouteGateway routeGateway = RouteGateway.builder().build();
        AccountGateway accountGateway = AccountGateway.builder().next(routeGateway).build();
        ApiGateway apiGateway = ApiGateway.builder().next(accountGateway).build();

        apiGateway.proccess();
    }
}
