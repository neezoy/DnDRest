package dataTypes;

import java.util.ArrayList;

public class UserDTO implements IUserDTO{

    private int id;
    private String username;
    private String password;
    private int role;
    private ArrayList<ICharacterDTO> characters = new ArrayList<ICharacterDTO>();

    public UserDTO(){
    }
    public UserDTO(String username, String password){
        setName(username);
        setPassword(password);
    }
    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public void setName(String name){
        username = name;
    }
    @Override
    public ArrayList<ICharacterDTO> getCharacters() {
        return characters;
    }

    @Override
    public void addCharacter(ICharacterDTO character) {
        characters.add(character);
    }

    @Override
    public void removeCharacter(ICharacterDTO character) {
        characters.remove(character);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getRole() {
        return role;
    }

    @Override
    public void setRole(int role) {
        this.role = role;
    }
}
