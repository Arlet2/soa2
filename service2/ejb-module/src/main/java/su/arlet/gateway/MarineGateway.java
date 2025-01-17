package su.arlet.gateway;

import org.jboss.ejb3.annotation.Pool;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://example.com/webservices")
@Stateless
@Pool("slsb-strict-max-pool")
public interface MarineGateway {
    @WebMethod
    void deploy(@WebParam(name = "id") long id, @WebParam(name = "starship-id") long starshipId);

    @WebMethod
    void undeployAll(@WebParam(name="starship-id") long starshipId);
}
