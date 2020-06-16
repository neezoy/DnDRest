package rest;

import dataTypes.IUserDTO;
import database.DAO;
import database.IDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("login")
public class LoginService {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}")
    public IUserDTO userLogin(@PathParam("name") String name) throws SQLException {

        IDAO dao = new DAO();
        return dao.getUserFromName(name);

    }

}
