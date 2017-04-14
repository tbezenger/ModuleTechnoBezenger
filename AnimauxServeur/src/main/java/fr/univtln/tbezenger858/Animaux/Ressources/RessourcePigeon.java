package fr.univtln.tbezenger858.Animaux.Ressources;

import fr.univtln.tbezenger858.Animaux.CrudServiceBean.CrudServiceBean;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Pigeon;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

/**
 * Created by Tomy Bezenger on 30/03/2017.
 */

@Path("/oiseaux/pigeons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RessourcePigeon {

    CrudServiceBean<Pigeon> crudBean = new CrudServiceBean();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pigeon> getAllPigeons() throws Exception {
        return crudBean.findWithNamedQuery("pigeonByAll");
    }
}
