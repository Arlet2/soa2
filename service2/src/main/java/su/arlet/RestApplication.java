package su.arlet;

import su.arlet.controller.StarshipController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.xml.ws.Endpoint;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class RestApplication extends Application {
    public static void main(String[] args) {

        Endpoint.publish("http://localhost:8080/starships-service", new StarshipController());
    }
}