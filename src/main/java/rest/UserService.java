package rest;

import dataTypes.GroupDTO;
import dataTypes.IGroupDTO;
import dataTypes.IUserDTO;
import dataTypes.UserDTO;
import database.DAO;
import database.IDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("user")
public class UserService {

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
                             @FormParam("role") String role,
                             @FormParam("password") String password,
                             @FormParam("userid") int userid,
                             @FormParam("selection") int selection) throws SQLException {

        // 1 create
        // 2 update
        // 3 delete

        IDAO dao = new DAO();

        IUserDTO user = new UserDTO(username, role);

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
