package fr.univtln.tbezenger858.Animaux.Ressources;

import fr.univtln.tbezenger858.Animaux.DAO.ChienM;
import fr.univtln.tbezenger858.Animaux.DAO.EntityManager;
import fr.univtln.tbezenger858.Animaux.DAO.MammifereM;
import fr.univtln.tbezenger858.Animaux.DAO.Utils;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chien;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Mammifere;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Tomy Bezenger on 28/02/2017.
 */

@Path("/mammiferes/chiens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RessourceChien {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Chien> getAllChiens() throws Exception {
        Connection connection = DriverManager.getConnection(Utils.url,Utils.user,Utils.password);
        EntityManager<Chien> eMC = new ChienM(connection);
        return eMC.findall();
    }
}
