package fr.univtln.tbezenger858.Animaux.Ressources;

import fr.univtln.tbezenger858.Animaux.DAO.EntityManager;
import fr.univtln.tbezenger858.Animaux.DAO.MammifereM;
import fr.univtln.tbezenger858.Animaux.DAO.Utils;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chat;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chien;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Mammifere;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Tomy Bezenger on 23/02/2017.
 */

@Path("/mammiferes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RessourceMammifere {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mammifere> getAllMammiferes() throws Exception {
        Connection connection = DriverManager.getConnection(Utils.url,Utils.user,Utils.password);
        EntityManager<Mammifere> eMM = new MammifereM(connection);
        return eMM.findall();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Mammifere getMammifereById(@PathParam("id") final int id) throws Exception {
        Connection connection = DriverManager.getConnection(Utils.url,Utils.user,Utils.password);
        EntityManager<Mammifere> eMM = new MammifereM(connection);
        return eMM.find(Integer.toString(id));
    }

    //////////////////////////////////////////////////////////////////
    @PUT
    @Path("/chat")
    @Produces(MediaType.APPLICATION_JSON)
    public String createChat(Chat chat) throws Exception {
        Connection connection = DriverManager.getConnection(Utils.url,Utils.user,Utils.password);
        EntityManager<Mammifere> eMM = new MammifereM(connection);
        return Boolean.toString(eMM.persist(chat));
    }

    @PUT
    @Path("/chien")
    @Produces(MediaType.APPLICATION_JSON)
    public String createChien(Chien chien) throws Exception {
        Connection connection = DriverManager.getConnection(Utils.url, Utils.user, Utils.password);
        EntityManager<Mammifere> eMM = new MammifereM(connection);
        return Boolean.toString(eMM.persist(chien));
    }
    ///////////////////////////////////////////////////////////////

    @DELETE
    @Path("/{id}")
    public void deleteMammifere(@PathParam("id") final int id) throws Exception{
        System.out.println("lol");
        Connection connection = DriverManager.getConnection(Utils.url, Utils.user, Utils.password);
        EntityManager<Mammifere> eMM = new MammifereM(connection);
        eMM.remove(id);
    }

    ///////////////////////////////////////////////////////////////////
    @POST
    @Path("/chat")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateChat(Chat chat) throws Exception {
        Connection connection = DriverManager.getConnection(Utils.url,Utils.user,Utils.password);
        EntityManager<Mammifere> eMM = new MammifereM(connection);
        return Boolean.toString(eMM.update(chat));
    }

    @POST
    @Path("/chien")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateChien(Chien chien) throws Exception {
        Connection connection = DriverManager.getConnection(Utils.url,Utils.user,Utils.password);
        EntityManager<Mammifere> eMM = new MammifereM(connection);
        return Boolean.toString(eMM.update(chien));
    }
}
