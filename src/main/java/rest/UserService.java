package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("user")
public class UserService {

    @GET
    @Path("{id}")
    public String getUser(@PathParam("id") String id){

        return "Your character is: " + id;

    }

    @POST
    @Path("save")
    public Response postUser(@FormParam("name") String name, @FormParam("level") String level){

        String response = "Successfully added user name: "+
                name+" and password: "+ level;

        //response is output in this case
        return Response.status(200).entity(response).build();

    }


    @POST
    @Path("addcharacter/{character}/{user}")
    public Response addCharacterToUser(@PathParam("character") String character, @PathParam("user") String user) {

        String response = "Successfully added character name: "+
                character +" to user: "+ user;

        //response is output in this case
        return Response.status(200).entity(response).build();

    }



}
