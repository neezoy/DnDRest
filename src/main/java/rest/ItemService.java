package rest;

import dataTypes.IItemDTO;
import dataTypes.ItemDTO;
import database.DAO;
import database.IDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("item")
public class ItemService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public IItemDTO getItem(@PathParam("id") int id) throws SQLException {

        IDAO dao = new DAO();

        return dao.getItem(id);

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public ArrayList<IItemDTO> getAllItems(@PathParam("id") String id) throws SQLException {

        IDAO dao = new DAO();


        return dao.getAllItems() ;

    }

    @POST
    @Path("save")
    public Response postItem(@FormParam("itemname") String itemname, @FormParam("weight") double weight, @FormParam("description") String description,
                                  @FormParam("itemid") int itemid, @FormParam("selection") int selection) throws SQLException {

        // 1 create
        // 2 update
        // 3 delete
        System.out.println("Item save reached");

        IDAO dao = new DAO();

        IItemDTO item = new ItemDTO(itemname, description, weight);

        switch (selection){
            case 1:
                System.out.println("Create item reached");
                dao.createItem(item);
                break;

            case 2:
                System.out.println("Update item reached");
                item.setID(itemid);
                dao.overwriteItem(item);
                break;

            case 3:
                System.out.println("Delete item reached");
                dao.deleteItem(itemid);
                break;
        }



        //response is output in this case
        return null;

    }




}
