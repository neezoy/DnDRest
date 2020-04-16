package dataTypes;

import java.util.ArrayList;

public interface IUserDTO {

    int getID();
    void setID(int id);
    String getName();
    void setName(String name);
    ArrayList<ICharacterDTO> getCharacters();
    void addCharacter(ICharacterDTO character);
    void removeCharacter(ICharacterDTO character);
    String getPassword();
    void setPassword(String password);
    int getRole();
    void setRole(int role);

}
