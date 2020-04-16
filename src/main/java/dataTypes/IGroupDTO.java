package dataTypes;

import java.util.ArrayList;

public interface IGroupDTO {
//id
    void setID(int id);
    int getID();
    //name
    String getName();
    void setName(String name);
    //description
    String getDescription();
    void setDescription(String description);
    //characters
    ArrayList<ICharacterDTO> getCharacters();
    void addCharacter(ICharacterDTO character);
    void removeCharacter(ICharacterDTO character);
}
