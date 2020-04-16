package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("item")
public class ItemService {

    @GET
    @Path("{id}")
    public String getItem(@PathParam("id") String id){

        return "Your item is: " + id;

    }

    @POST
    @Path("save")
    public Response postGroup(@FormParam("name") String name, @FormParam("level") String level){

        String response = "Successfully added character name: "+
                name+" and level: "+ level;

        //response is output in this case
        return Response.status(200).entity(response).build();

    }

}
