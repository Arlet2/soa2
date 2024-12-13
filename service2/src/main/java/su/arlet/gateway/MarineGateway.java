package su.arlet.gateway;

import su.arlet.controller.ConnectionException;
import su.arlet.core.StarshipCreator;

import javax.ejb.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Singleton
public class MarineGateway {

    private final WebTarget webTarget;

    public MarineGateway() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] noopTrustManager = new TrustManager[]{
                new X509TrustManager() {

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }

                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("ssl");
        sc.init(null, noopTrustManager, null);

        try {
            var client = ClientBuilder.newBuilder()
                    .sslContext(sc)
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
            this.webTarget = client.target("https://localhost/api/v1");
        } catch (Exception e) {
            throw new ConnectionException("Can't connect to the server");
        }
    }

    public Response unloadSpaceMarine(long id, long starshipId) {
        try {
            return webTarget.path("/space-marines/" + id + "/starships/" + starshipId + "/deploy").request().post(null);
        }catch (Exception e){
            return Response.status(500).build();
        }
    }

    public Response undeployAll(long starshipId) {
        try {
            return webTarget.path("/starships/" + starshipId + "/undeploy-all").request().post(null);
        }
        catch (Exception e){
            return Response.status(500).build();
        }
    }

    public Response createStarship(StarshipCreator starship) {
        try {
            return webTarget.path("/starships").request().post(Entity.xml(starship));
        }  catch (Exception e){
            return Response.status(500).build();
        }

    }
}
