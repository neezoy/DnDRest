package rest;

import dataTypes.*;
import database.DAO;
import database.IDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("user")
public class UserService {


    @POST
    @Path("approve/{id}/{approval}/{role}")
    public Response approveUser(@PathParam("id") int id, @PathParam("approval") int approval, @PathParam("role") int role) throws SQLException {

        //role 0 = master
        //role 1 = admin
        //role 2 = gamemaster

        IDAO dao;

        switch (role) {
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

        IUserDTO a = dao.getUser(id);

        if (approval == 0) {
            dao.approveUser(a, false);
        }
        if (approval == 1) {
            dao.approveUser(a, true);
        }


        String response = "Successfully approved name: " +
                id;

        //response is output in this case
        return Response.status(200).entity(response).build();

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public IUserDTO getUser(@PathParam("id") int id) throws SQLException {

        IDAO dao = new DAO();

        return dao.getUser(id);

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public ArrayList<IUserDTO> getAllUsers() throws SQLException {

        IDAO dao = new DAO();


        return dao.getAllUsers();

    }

    @POST
    @Path("save")
    public Response postUser(@FormParam("username") String username,
                             @FormParam("role") int role,
                             @FormParam("password") String password,
                             @FormParam("userid") int userid,
                             @FormParam("selection") int selection) throws SQLException {
//TODO: Role is an integer, my man.
        // 1 create
        // 2 update
        // 3 delete
        System.out.println("User save reached");

        IDAO dao = new DAO();

        if (role != 0 & role != 1 & role != 2) {
            String s = "Invalid role";
            return Response.status(200).entity(s).build();
        }

        IUserDTO user = new UserDTO(username, password);
        user.setRole(role);

        switch (selection) {
            case 1:
                System.out.println("Create user reached");
                dao.createUser(user);
                break;

            case 2:
                System.out.println("Update user reached");
                user.setID(userid);
                dao.overwriteUser(user);
                break;

            case 3:
                System.out.println("Delete user reached");
                dao.deleteUser(userid);
                break;
        }
        return null;

    }


    @POST
    @Path("addcharacter/{characterid}/{userid}")
    public Response addCharacterToUser(@PathParam("characterid") int characterid, @PathParam("userid") int userid) throws SQLException {

        String response = "Successfully added character: " +
                characterid + " to user: " + userid;

        IDAO dao = new DAO();
        IUserDTO u = new UserDTO();
        ICharacterDTO c = new CharacterDTO();
        u = dao.getUser(userid);
        c = dao.getCharacter(characterid);
        dao.addCharacter(u, c);


        //response is output in this case
        return Response.status(200).entity(response).build();


    }

    @POST
    @Path("removecharacter/{characterid}")
    public Response removeCharacterToUser(@PathParam("characterid") int characterid) throws SQLException {

        String response = "Successfully deleted character: " +
                characterid;

        IDAO dao = new DAO();
        dao.removeCharacter(characterid);


        //response is output in this case
        return Response.status(200).entity(response).build();


    }
}
