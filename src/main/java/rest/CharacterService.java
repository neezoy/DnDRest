package rest;

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
    public ArrayList<ICharacterDTO> getAllCharacter() throws SQLException {

        IDAO dao = new DAO();


        return dao.getAllCharacters();

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public ICharacterDTO getCharacter(@PathParam("id") int id) throws SQLException {

        /* Json Conversion
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);

         */

        IDAO dao = new DAO();

        return dao.getCharacter(id);

    }

    @POST
    @Path("save")
    public Response postCharacter(@FormParam("charactername") String charactername, @FormParam("location") String location,
                                  @FormParam("strength") int strength, @FormParam("bonus") int bonus,
                                  @FormParam("characterid") int characterid, @FormParam("selection") int selection) throws SQLException {

        // 1 create
        // 2 update
        // 3 delete

        IDAO dao = new DAO();

        ICharacterDTO charac = new CharacterDTO(charactername, strength, bonus);
        charac.setLocation(location);


        switch (selection){
            case 1:
                dao.createCharacter(charac);
                break;

            case 2:
                charac.setID(characterid);
                dao.overwriteCharacter(charac);
                break;

            case 3:
                dao.deleteCharacter(characterid);
                break;
        }



        //response is output in this case
        return null;

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
