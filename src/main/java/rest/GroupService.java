package rest;

import dataTypes.*;
import database.DAO;
import database.IDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("group")
public class GroupService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public IGroupDTO getGroup(@PathParam("id") int id) throws SQLException {

        IDAO dao = new DAO();

        return dao.getGroup(id);

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public ArrayList<IGroupDTO> getAllGroups() throws SQLException {

        IDAO dao = new DAO();


        return dao.getAllGroups();

    }

    @POST
    @Path("save")
    public Response postGroup(@FormParam("groupname") String groupname, @FormParam("description") String description,
                              @FormParam("groupid") int groupid, @FormParam("selection") int selection) throws SQLException {

        // 1 create
        // 2 update
        // 3 delete
        System.out.println("Group save reached");

        IDAO dao = new DAO();

        IGroupDTO group = new GroupDTO(groupname, description);

        switch (selection) {
            case 1:
                System.out.println("Create group reached");
                dao.createGroup(group);
                break;

            case 2:
                System.out.println("Update group reached");
                group.setID(groupid);
                dao.overwriteGroup(group);
                break;

            case 3:
                System.out.println("Delete group reached");
                dao.deleteGroup(groupid);
                break;
        }
        return null;

    }

    @POST
    @Path("addcharacter/{characterid}/{group}")
    public Response addCharacterToUser(@PathParam("characterid") int characterid, @PathParam("groupid") int groupid) throws SQLException {

        String response = "Successfully added item name: " +
                characterid + " to group: " + groupid;

        IDAO dao = new DAO();
        IGroupDTO g;
        ICharacterDTO c;
        g = dao.getGroup(groupid);
        c = dao.getCharacter(characterid);
        dao.addToGroup(c, g);

        //response is output in this case
        return Response.status(200).entity(response).build();


    }


}
