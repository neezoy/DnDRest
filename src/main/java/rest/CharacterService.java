package rest;

import dataTypes.*;
import database.DAO;
import database.IDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("character")
public class CharacterService {


    @POST
    @Path("approve/{id}/{approval}/{role}")
    public Response approveCharacter(@PathParam("id") int id, @PathParam("approval") int approval, @PathParam("role") int role) throws SQLException {
        System.out.println("Reached");

        String response = "Successfully approved name: " +
                id;

        //role 0 = master
        //role 1 = admin
        //role 2 = gamemaster
        IDAO dao;

        switch (role){
            case 0:
                dao = new DAO();
                break;
            case 1:
                dao = new DAO("administrator", "password");
                break;
            case 2:
                dao = new DAO("gamemaster", "password");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }

        ICharacterDTO a = dao.getCharacter(id);

        if (approval == 0){
            dao.approveCharacter(a, false);
        }
        if (approval == 1){
            dao.approveCharacter(a, true);
        }


        //response is output in this case
        return Response.status(200).entity(response).build();

    }




    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public ArrayList<ICharacterDTO> getAllCharacter() throws SQLException {

        System.out.println("Enter CharacterService, getAllCharacter)");
        try {
            IDAO dao = new DAO();
            System.out.println("Enter try catch (CharacterService, getAllCharacter)");

            ArrayList<ICharacterDTO> d = dao.getAllCharacters();

            System.out.println(d.size());
            return d;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Got caught in the catch clause (CharacterService, getAllCharacter)");
        }

        System.out.println("Success (CharacterService, getAllCharacter)");
        return null;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public ICharacterDTO getCharacter(@PathParam("id") int id) throws SQLException {

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


        switch (selection) {
            case 1:
                dao.createCharacter(charac);
                break;

            case 2:
                charac.setID(characterid);
                System.out.println(characterid);
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
    public Response addItemToCharacter(@PathParam("item") int item, @PathParam("character") int character) throws SQLException {

        String response = "Successfully added item name: " +
                item + " to character: " + character;


        IDAO dao = new DAO();
        dao.addItem(character, item);

        //response is output in this case
        return Response.status(200).entity(response).build();

    }




}
