package dataTypes;

import java.util.ArrayList;

public class GroupDTO implements IGroupDTO {

    private int id;
    private String name;
    private String description;
    private ArrayList<ICharacterDTO> characters = new ArrayList<ICharacterDTO>();
    public GroupDTO(){
    }
    public GroupDTO(String name, String description){
        setName(name);
        setDescription(description);
    }
    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description=description;
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

}
