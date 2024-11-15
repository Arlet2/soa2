package su.arlet.service;

import su.arlet.gateway.MarineGateway;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class StarshipService {

    @Inject
    private MarineGateway marineGateway;

    public String testGetting() {
        try {
            marineGateway.unloadSpaceMarine();
        } catch (EJBTransactionRolledbackException e) {
            System.out.println(e);
            return "disconnect";
        }

        return "done";
    }
}
