package database;



import dataTypes.*;

import java.sql.*;
import java.util.ArrayList;

public class DAO implements IDAO {


    private Connection c;

    public DAO() {
        String url = "jdbc:mysql://database-1.chwxwa8nyn1v.eu-central-1.rds.amazonaws.com:3306/cdio?characterEncoding=latin1";
        String username = "master";
        String password = "Password123"; //find det rigtige

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public DAO(String username, String password) {
        String url = "jdbc:mysql://database-1.chwxwa8nyn1v.eu-central-1.rds.amazonaws.com:3306/cdio?characterEncoding=latin1";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void closeConnection() throws Exception {
        try{
            c.close();
        } catch (Exception e) {
            throw e;
        }

    }
    @Override
    public void createUser(IUserDTO user) throws SQLException {
        try {
            String query = "INSERT INTO cdio.User (Username, Password, Roles) VALUES (?, ?, ?)";
            PreparedStatement statement = c.prepareStatement(query);

            statement.setString(1, user.getName());
            statement.setString(2,  user.getPassword());
            statement.setInt(3, user.getRole());

            statement.execute();
           //

        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public IUserDTO getUserFromName(String username) throws SQLException {
        IUserDTO user = new UserDTO();
        try {
            String query = "SELECT * FROM cdio.User WHERE Username = ?;";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

           //

            if (!result.next()) {
                return null;
            }
            user.setID(result.getInt("UserID"));
            user.setName(username);
            user.setPassword(result.getString("Password"));
            user.setRole(result.getInt("Roles"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }
    @Override
    public IUserDTO getUser(int userid) throws SQLException {
        UserDTO user = new UserDTO();
        try {
            String query = "SELECT * FROM cdio.User WHERE UserID = " + userid + ";";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();



            if (!result.next()) {
                return null;
            }
            user.setID(userid);
            user.setName(result.getString("Username"));
            user.setPassword(result.getString("Password"));
            user.setRole(result.getInt("Roles"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }
    @Override
    public void createCharacter(ICharacterDTO character) throws SQLException {
        try {

            String query = "INSERT INTO cdio.Character (CName, CLocation, Strength, BonusCapacity) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = c.prepareStatement(query);

            statement.setString(1, character.getName());
            statement.setString(2, character.getLocation());
            statement.setInt(3, character.getStrength());
            statement.setInt(4, character.getBonus());

            statement.execute();


        } catch (SQLException p) {
            throw p;

        }
    }
    @Override
    public int getCharacterID(String charactername) throws SQLException {
        int ID;
        try {
            String query = "SELECT CharacterID FROM cdio.Character WHERE cname = ?";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, charactername);
            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                return 0;
            }

            ID = result.getInt("CharacterID");

        } catch (SQLException e) {

            e.printStackTrace();
            throw e;
        }
        return ID;
    }
    @Override
    public ICharacterDTO getCharacter(int characterid) throws SQLException {
        ICharacterDTO character = new CharacterDTO();
        try {
            String query = "SELECT * FROM cdio.Character WHERE CharacterID = " + characterid + ";";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                return null;
            }
            character.setID(characterid);
            character.setName(result.getString("CName"));
            character.setLocation(result.getString("CLocation"));
            character.setStrength(result.getInt("Strength"));
            character.setBonus(result.getInt("BonusCapacity"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return character;
    }
    @Override
    public void createItem(IItemDTO item) throws SQLException {
        try {

            String query = "INSERT INTO cdio.Item (ItemName, Weight, IDescription) VALUES (?, ?, ?)";
            PreparedStatement statement = c.prepareStatement(query);

            statement.setString(1, item.getName());
            statement.setDouble(2, item.getWeight());
            statement.setString(3, item.getDescription());

            statement.execute();



        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public int getItemID(String itemname) throws SQLException {
        int ID;
        try {
            String query = "SELECT cdio.ItemID FROM cdio.Item WHERE ItemName = ?;";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, itemname);
            ResultSet result = statement.executeQuery();



            if (!result.next()) {
                return 0;
            }

            ID = result.getInt("ItemID");

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return ID;
    }
    @Override
    public IItemDTO getItem(int itemid) throws SQLException {
        IItemDTO item = new ItemDTO();
        try {
            String query = "SELECT * FROM cdio.Item WHERE ItemID = " + itemid + ";";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();



            if (!result.next()) {
                return null;
            }
            item.setID(itemid);
            item.setName(result.getString("ItemName"));
            item.setWeight(result.getDouble("Weight"));
            item.setDescription(result.getString("IDescription"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return item;
    }
    @Override
    public void createGroup(IGroupDTO group) throws SQLException {
        try {
            String query = "INSERT INTO cdio.Group (GroupName, GDescription) VALUES (?, ?)";
            PreparedStatement statement = c.prepareStatement(query);

            statement.setString(1, group.getName());
            statement.setString(2, group.getDescription());

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public int getGroupID(String groupname) throws SQLException {
        int ID;
        try {
            String query = "SELECT GroupID FROM cdio.Group WHERE groupname = ?;";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, groupname);
            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                return 0;
            }

            ID = result.getInt("GroupID");

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return ID;

    }
    @Override
    public IGroupDTO getGroup(int groupid) throws SQLException {
        IGroupDTO group = new GroupDTO();
        try {
            String query = "SELECT * FROM cdio.Group WHERE GroupID = " + groupid;
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                return null;
            }
            group.setID(groupid);
            group.setName(result.getString("GroupName"));
            group.setDescription(result.getString("GDescription"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return group;

    }
    @Override
    public void addCharacter(IUserDTO user, ICharacterDTO character) throws SQLException {
        try {

            String query = "INSERT INTO cdio.CharacterRelation (CharacterID, UserID) VALUES (?, ?)";
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, character.getID());
            statement.setInt(2, user.getID());

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }

        user.addCharacter(character);
    }

    @Override
    public void deleteCharacter(int characterid) throws SQLException {
        try {
            String query = "DELETE FROM cdio.Character WHERE characterID ='" + characterid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }

    @Override
    public void removeCharacter(int characterid) throws SQLException {
        //TODO: I'm not sure about whether we should remove the character from the UserDTO. We probably should, to keep the data consistent.
        try {

            String query = "DELETE FROM cdio.CharacterRelation WHERE CharacterID ='" + characterid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public ArrayList getCharacterIDs(int userid) throws SQLException {
        ArrayList characterids = new ArrayList();
        try {
            String query = "SELECT CharacterID FROM cdio.CharacterRelation WHERE UserID ='" + userid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                characterids.add(result.getInt("CharacterID"));
            }

        } catch (SQLException p) {
            throw p;
        }
        return characterids;
    }
    @Override
    public void addItem(int characterid, int itemid) throws SQLException {
        //TODO make add and remove items check if the item already exists.
        try {

            String query = "INSERT INTO cdio.ItemRelation (ItemID, CharacterID, Amount) VALUES (?, ?, ?)";
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, itemid);
            statement.setInt(2, characterid);
            statement.setInt(3, 1);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public void addItems(int characterid, int itemid, int amount) throws SQLException {
        try {

            String query = "INSERT INTO cdio.ItemRelation (ItemID, CharacterID, Amount) VALUES (?, ?, ?)";
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, itemid);
            statement.setInt(2, characterid);
            statement.setInt(3, amount);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public void removeItem(int characterid, int itemid) throws SQLException {
        //TODO how do we delete one.
        try {

            String query = "DELETE FROM cdio.ItemRelation WHERE ItemID ='" + itemid + "' AND CharacterID ='" + characterid + "' ";
            PreparedStatement statement = c.prepareStatement(query);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public void removeItems(int characterid, int itemid, int amount) throws SQLException {
        try {

            String query = "DELETE FROM cdio.ItemRelation WHERE ItemID ='" + itemid + "' AND CharacterID ='" + characterid + "' ";
            PreparedStatement statement = c.prepareStatement(query);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public ArrayList getItemIDs(int characterid) throws SQLException {
        ArrayList itemids = new ArrayList();
        try {
            String query = "SELECT ItemID FROM cdio.ItemRelation WHERE CharacterID ='" + characterid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                itemids.add(result.getInt("ItemID"));
            }

        } catch (SQLException p) {
            throw p;
        }
        return itemids;
    }
    @Override
    public void addToGroup(ICharacterDTO character, IGroupDTO group) throws SQLException {
        try {

            String query = "INSERT INTO cdio.GroupRelation (GroupID, CharacterID) VALUES (?, ?)";
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, group.getID());
            statement.setInt(2, character.getID());

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }

        group.addCharacter(character);
        character.addGroup(group);
    }
    @Override
    public void removeFromGroup(int characterid, int groupid) throws SQLException {
        //TODO: I'm not sure about whether we should remove the relation from the DTO. We probably should, to keep the data consistent.
        try {

            String query = "DELETE FROM cdio.GroupRelation WHERE CharacterID = '" + characterid + "' AND GroupID = '" + groupid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public ArrayList getGroupIDs(int characterid) throws SQLException {
        ArrayList groupids = new ArrayList();
        try {
            String query = "SELECT GroupID FROM cdio.GroupRelation WHERE CharacterID ='" + characterid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                groupids.add(result.getInt("GroupID"));
            }

        } catch (SQLException p) {
            throw p;
        }
        return groupids;
    }
    @Override
    public ArrayList getMembers(int groupid) throws SQLException {
        ArrayList characterids = new ArrayList();
        try {
            String query = "SELECT CharacterID FROM cdio.GroupRelation WHERE GroupID ='" + groupid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                characterids.add(result.getInt("CharacterID"));
            }

        } catch (SQLException p) {
            throw p;
        }
        return characterids;
    }
    @Override
    public void deleteUser(int userid) throws SQLException {
        try {

            String query = "DELETE FROM cdio.User WHERE UserID ='" + userid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public void overwriteUser(IUserDTO user) throws SQLException {
        try {

            String query = "UPDATE cdio.User SET Username = ?, Password = ?, Roles = ? WHERE UserID = '" + user.getID() + "'";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole());
            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public void overwriteCharacter(ICharacterDTO character) throws SQLException {
        try {

            String query = "UPDATE cdio.Character SET Cname = ?, CLocation = ?, Strength = ?, BonusCapacity = ? WHERE CharacterID = '" + character.getID() + "'";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, character.getName());
            statement.setString(2, character.getLocation());
            statement.setInt(3, character.getStrength());
            statement.setInt(4, character.getBonus());
            System.out.println(query);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public void overwriteItem(IItemDTO item) throws SQLException {
        try {

            String query = "UPDATE cdio.Item SET ItemName = ?, Weight = ?, IDescription = ? WHERE ItemID = '" + item.getID() + "'";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getWeight());
            statement.setString(3, item.getDescription());

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public void deleteItem(int itemid) throws SQLException {
        try {
            String query = "DELETE FROM cdio.Item WHERE ItemID ='" + itemid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public void overwriteGroup(IGroupDTO group) throws SQLException {
        try {
            String query = "UPDATE cdio.Group SET GroupName = ?, GDescription = ? WHERE GroupID = '" + group.getID() + "'";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, group.getName());
            statement.setString(2, group.getDescription());

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public void deleteGroup(int groupid) throws SQLException {
        try {
            String query = "DELETE FROM cdio.Group WHERE GroupID ='" + groupid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }
    public ArrayList<IUserDTO> getAllUsers() throws SQLException{
        ArrayList<IUserDTO> users = new ArrayList<IUserDTO>();
        try {

            String query = "SELECT * FROM cdio.User ORDER BY Username DESC";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            IUserDTO user;
            while (result.next()) {
                user = new UserDTO();
                user.setID(result.getInt("UserID"));
                user.setName(result.getString("Username"));
                user.setPassword(result.getString("Password"));
                user.setRole(result.getInt("Roles"));
                users.add(user);
            }

        } catch (SQLException p) {
        throw p;
        }
        return users;
    }

    public ArrayList<ICharacterDTO> getAllCharacters() throws SQLException{
        ArrayList<ICharacterDTO> characters = new ArrayList<ICharacterDTO>();
        try {
            String query = "SELECT * FROM cdio.Character ORDER BY CName DESC";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            ICharacterDTO character;
            while (result.next()) {
                character = new CharacterDTO();
                character.setID(result.getInt("CharacterID"));
                character.setName(result.getString("CName"));
                character.setLocation(result.getString("CLocation"));
                character.setStrength(result.getInt("Strength"));
                character.setBonus(result.getInt("BonusCapacity"));
                characters.add(character);
            }

        } catch (SQLException p) {
            throw p;
        }
        return characters;

    }

        public ArrayList<IItemDTO> getAllItems() throws SQLException{
        ArrayList<IItemDTO> items = new ArrayList<IItemDTO>();
        try {

            String query = "SELECT * FROM cdio.Item ORDER BY ItemName DESC";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            IItemDTO item;
            while (result.next()) {
                item = new ItemDTO();
                item.setID(result.getInt("ItemID"));
                item.setName(result.getString("ItemName"));
                item.setWeight(result.getDouble("Weight"));
                item.setDescription(result.getString("IDescription"));
                items.add(item);
            }

        } catch (SQLException p) {
            throw p;
        }
        return items;
    }

    public  ArrayList<IGroupDTO> getAllGroups() throws SQLException{
        ArrayList<IGroupDTO> groups = new ArrayList<IGroupDTO>();
        try {

            String query = "SELECT * FROM cdio.Group ORDER BY GroupName DESC";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            IGroupDTO group;
            while (result.next()) {
                group = new GroupDTO();
                group.setID(result.getInt("GroupID"));
                group.setName(result.getString("GroupName"));
                group.setDescription(result.getString("GDescription"));
                groups.add(group);
            }

        } catch (SQLException p) {
            throw p;
        }
        return groups;
    }
    //TODO comment to be deleted
    @Override
    public void approveCharacter(ICharacterDTO character, boolean approval) throws SQLException{
        try {

            String query = "UPDATE cdio.Character SET CStatus = ? WHERE characterid = '" + character.getID() + "'";
            PreparedStatement statement = c.prepareStatement(query);
            if(approval) {
                statement.setInt(1, 1);
            }
            else
            {
                statement.setInt(1, 2);
            }
            statement.execute();

        } catch (SQLException p) {
            throw p;
        }
    }
    @Override
    public void approveUser(IUserDTO user, boolean approval) throws SQLException{
        try {

            String query = "UPDATE cdio.User SET UStatus = ? WHERE userid = '" + user.getID() + "'";
            PreparedStatement statement = c.prepareStatement(query);
            if(approval) {
                statement.setInt(1, 1);
            }
            else
            {
                statement.setInt(1, 2);
            }
            statement.execute();

        } catch (SQLException p) {
            throw p;
        }
    }

    @Override
    public void createSession(ISessionDTO session) throws SQLException {
        try {
            String query = "INSERT INTO cdio.Session (`date`, Slocation, amount) VALUES (?, ?, ?)";
            PreparedStatement statement = c.prepareStatement(query);

            statement.setString(1, session.getDate());
            statement.setString(2, session.getLocation());
            statement.setInt(3, session.getAmount());

            statement.execute();
            //

        } catch (SQLException p) {
            throw p;
        }
    }

    @Override
    public ISessionDTO getSession(int sessionid) throws SQLException {
        ISessionDTO session = new SessionDTO();
        try {
            String query = "SELECT * FROM cdio.Session WHERE SessionID = " + sessionid + ";";
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                return null;
            }
            session.setID(sessionid);
            session.setDate(result.getString("Date"));
            session.setLocation(result.getString("SLocation"));
            session.setAmount(result.getInt("Amount"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return session;
    }

    @Override
    public void overwriteSession(ISessionDTO session) throws SQLException {
        try {
            String query = "UPDATE cdio.Session SET `Date` = ?, SLocation = ?, Amount = ? WHERE SessionID = '" + session.getID() + "'";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, session.getDate());
            statement.setString(2, session.getLocation());
            statement.setInt(3, session.getAmount());

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
    }

    @Override
    public ArrayList getSessionIDs(int characterid) throws SQLException {
        ArrayList sessionids = new ArrayList();
        try {
            String query = "SELECT SessionID FROM cdio.SessionRelation WHERE CharacterID ='" + characterid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                sessionids.add(result.getInt("SessionID"));
            }

        } catch (SQLException p) {
            throw p;
        }
        return sessionids;
    }

    @Override
    public ArrayList getAttendees(int sessionid) throws SQLException {
        ArrayList characterids = new ArrayList();
        try {
            String query = "SELECT CharacterID FROM cdio.SessionRelation WHERE SessionID ='" + sessionid + "'";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                characterids.add(result.getInt("CharacterID"));
            }

        } catch (SQLException p) {
            throw p;
        }
        return characterids;
    }

    @Override
    public void addToSession(ICharacterDTO character, ISessionDTO session) throws SQLException{

        if(session.getCharacters().size() == session.getAmount()) {
            //TODO Make custom exception
            throw new SQLException();
        }

        try {


            String query = "INSERT INTO cdio.SessionRelation (SessionID, CharacterID) VALUES (?, ?)";
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, session.getID());
            statement.setInt(2, character.getID());

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }

        session.addCharacter(character);
        character.addSession(session);

    }

    @Override
    public void removeFromSession(ICharacterDTO character, ISessionDTO session) throws SQLException {
        try {

            String query = "DELETE FROM cdio.SessionRelation WHERE CharacterID = '" + character.getID() + "' AND SessionID = '" + session.getID()+ "'";
            PreparedStatement statement = c.prepareStatement(query);

            statement.execute();


        } catch (SQLException p) {
            throw p;
        }
        character.removeSession(session);
        session.removeCharacter(character);
    }

    @Override
    public ArrayList getCharacterByStatus(int status) throws SQLException {
        ArrayList characterids = new ArrayList();
        try {
            String query = "SELECT CharacterID FROM cdio.Character WHERE CStatus ='" + status + "'";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                characterids.add(result.getInt("CharacterID"));
            }

        } catch (SQLException p) {
            throw p;
        }
        return characterids;
    }

    @Override
    public ArrayList getUserByStatus(int status) throws SQLException {
        ArrayList userids = new ArrayList();
        try {
            String query = "SELECT UserID FROM cdio.User WHERE UStatus ='" + status + "'";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                userids.add(result.getInt("UserID"));
            }

        } catch (SQLException p) {
            throw p;
        }
        return userids;
    }
}