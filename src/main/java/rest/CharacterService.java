package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("character")
public class CharacterService {

    @GET
    @Path("{id}")
    public String getCharacter(@PathParam("id") String id){

        /* Json Conversion
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);

         */
        return "Your character is: " + id;

    }

    @POST
    @Path("save")
    public Response postCharacter(@FormParam("name") String name, @FormParam("level") String level){

        String response = "Successfully added character name: "+
                name+" and level: "+ level;

        //response is output in this case
        return Response.status(200).entity(response).build();

    }


    @POST
    @Path("additem/{item}/{character}")
    public Response addItemToCharacter(@PathParam("item") String item, @PathParam("character") String character) {

        String response = "Successfully added item name: "+
                item +" to character: "+ character;

        //response is output in this case
        return Response.status(200).entity(response).build();

    }


    @POST
    @Path("addgroup/{group}/{character}")
    public Response addGroupToCharacter(@PathParam("group") String group, @PathParam("character") String character) {

        String response = "Successfully added group name: "+
                character +" to character: "+ character;

        //response is output in this case
        return Response.status(200).entity(response).build();
    }




}
