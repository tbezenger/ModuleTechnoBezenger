package fr.univtln.tbezenger858.Animaux.Ressources;

import fr.univtln.tbezenger858.Animaux.DAO.ChatM;
import fr.univtln.tbezenger858.Animaux.DAO.ChienM;
import fr.univtln.tbezenger858.Animaux.DAO.EntityManager;
import fr.univtln.tbezenger858.Animaux.DAO.Utils;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chat;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chien;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * Created by Tomy Bezenger on 28/02/2017.
 */
@Path("/mammiferes/chats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class RessourceChat {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Chat> getAllChats() throws Exception {
        Connection connection = DriverManager.getConnection(Utils.url,Utils.user,Utils.password);
        EntityManager<Chat> eMC = new ChatM(connection);
        return eMC.findall();
    }


}
