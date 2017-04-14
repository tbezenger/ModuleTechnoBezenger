
package fr.univtln.tbezenger858.Animaux;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import fr.univtln.tbezenger858.Animaux.DAO.EntityManager;
import fr.univtln.tbezenger858.Animaux.DAO.MammifereM;
import fr.univtln.tbezenger858.Animaux.DAO.Utils;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chien;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Mammifere;
import fr.univtln.tbezenger858.Animaux.Ressources.RessourceMammifere;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {

    private static int getPort(int defaultPort) {
        //grab port from environment, otherwise fall back to default port 9998
        String httpPort = System.getProperty("jersey.test.port");
        if (null != httpPort) {
            try {
                return Integer.parseInt(httpPort);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://0.0.0.0/Animaux/").port(getPort(9998)).build();
    }

    public static final URI BASE_URI = getBaseURI();

    protected static HttpServer startServer() throws IOException {
        ResourceConfig resourceConfig = new PackagesResourceConfig("fr.univtln.tbezenger858");

        System.out.println("Starting grizzly2...");
        return GrizzlyServerFactory.createHttpServer(BASE_URI, resourceConfig);
    }

    public static void main(String[] args) throws Exception {
        // Grizzly 2 initialization
        Class.forName("org.postgresql.Driver").newInstance();

        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...",
                BASE_URI));
        System.in.read();
        httpServer.stop();
    }
}
