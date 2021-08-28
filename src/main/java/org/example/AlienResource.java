package org.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("aliens")
public class AlienResource {

    AlienRepository repository = new AlienRepository();

    @GET
//    @Produces(MediaType.APPLICATION_XML)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Alien> getAliens() {
        System.out.println(">>>>>>get-aliens called<<<<<<<<<<<");
        return repository.getAliens();
    }

    @GET
    @Path("alien/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alien getAlien(@PathParam("id") int id) {
        System.out.println(">>>>>>get alien called<<<<<<<<<<<");
        return repository.getAlien(id);
    }

    @POST
    @Path("alien")
//    @Consumes(MediaType.APPLICATION_XML)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Alien createAlien(Alien a) {
        System.out.println(a);
        repository.create(a);
        return a;
    }

    @PUT
    @Path("alien")
//    @Consumes(MediaType.APPLICATION_XML)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Alien updateAlien(Alien a) {
        System.out.println(">>>>update " + a);
        if (repository.getAlien(a.getId()).getId() == 0)
            repository.create(a);
        else
            repository.update(a);
        return a;
    }

    @DELETE
    @Path("alien/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alien deleteAlien(@PathParam("id") int id) {
        System.out.println(">>>>>>deleted alien<<<<<<<<<<<");
        Alien alien = repository.getAlien(id);
        if (alien.getId() != 0)
            repository.deleteAlien(id);
        return alien;
    }

}
