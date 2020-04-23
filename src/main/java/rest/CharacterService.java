package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dataTypes.CharacterDTO;
import dataTypes.ICharacterDTO;
import database.DAO;
import database.IDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("character")
public class CharacterService {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public ArrayList<ICharacterDTO> getAllCharacter(@PathParam("id") String id) throws SQLException {

        IDAO dao = new DAO();


        return dao.getAllCharacters();

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    public Response postCharacter(@FormParam("charactername") String charactername, @FormParam("location") String location,
                                  @FormParam("strength") int strength, @FormParam("bonuscapacity") int bonuscapacity) throws SQLException {

        String response = "Successfully added character name: "+
                charactername+" and level: "+ location;

        ICharacterDTO charac = new CharacterDTO(charactername, strength, bonuscapacity);
        charac.setLocation(location);

        IDAO dao = new DAO();
        dao.createCharacter(charac);
        
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
