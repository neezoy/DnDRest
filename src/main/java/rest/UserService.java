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
    @Path("approve/{id}/{approval}")
    public Response approveUser(@PathParam("id") int id, @PathParam("approval") boolean approval) throws SQLException {

        String response = "Successfully approved name: " +
                id;

        IDAO dao = new DAO();

        IUserDTO a = dao.getUser(id);
        dao.approveUser(a, approval);

        //response is output in this case
        return Response.status(200).entity(response).build();

    }

    @GET
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

        IDAO dao = new DAO();

        IUserDTO user = new UserDTO(username, password);

        switch (selection) {
            case 1:
                dao.createUser(user);
                break;

            case 2:
                user.setID(userid);
                dao.overwriteUser(user);
                break;

            case 3:
                dao.deleteUser(userid);
                break;
        }
        return null;

    }



}
