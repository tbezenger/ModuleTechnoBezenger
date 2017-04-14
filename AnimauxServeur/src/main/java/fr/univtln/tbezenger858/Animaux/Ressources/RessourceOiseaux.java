package fr.univtln.tbezenger858.Animaux.Ressources;

import fr.univtln.tbezenger858.Animaux.Animal;
import fr.univtln.tbezenger858.Animaux.CrudServiceBean.CrudServiceBean;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Mammifere;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Aigle;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Oiseau;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Pigeon;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static fr.univtln.tbezenger858.Animaux.CrudServiceBean.QueryParameter.with;

/**
 * Created by Tomy Bezenger on 30/03/2017.
 */

@Path("/oiseaux")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class RessourceOiseaux {
    CrudServiceBean<Animal> crudBean = new CrudServiceBean();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oiseau> getAllOiseaux() throws Exception {
        return crudBean.findWithNamedQuery("oiseauByAll");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public List<Oiseau> getOiseauById(@PathParam("id") final int id) throws Exception {
        return crudBean.findWithNamedQuery("oiseauById",with("Pid",id).parameters());
    }

    //////////////////////////////////////////////////////////////////
    @PUT
    @Path("pigeon")
    @Produces(MediaType.APPLICATION_JSON)
    public void createPigeon(Pigeon pigeon) throws Exception {
        crudBean.create(pigeon);
    }

    @PUT
    @Path("aigle")
    @Produces(MediaType.APPLICATION_JSON)
    public void createAigle(Aigle aigle) throws Exception {
        crudBean.create(aigle);
    }
    ///////////////////////////////////////////////////////////////

    @DELETE
    @Path("/{id}")
    public void deleteOiseau(@PathParam("id") final int id) throws Exception{
        crudBean.delete(Animal.class,id);
    }

    ////////////////////////////////////////////////////////////////////

    @POST
    @Path("pigeon")
    @Produces(MediaType.APPLICATION_JSON)
    public void updatePigeon(Pigeon pigeon) throws Exception {
        crudBean.update(pigeon);
    }

    @POST
    @Path("aigle")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateAigle(Aigle aigle) throws Exception {
        crudBean.update(aigle);
    }
}
