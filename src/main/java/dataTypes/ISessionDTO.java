package dataTypes;

import java.util.ArrayList;

public interface ISessionDTO {

    int getID();
    void setID(int id);
    String getDate();
    void setDate(String date);
    String getLocation();
    void setLocation(String location);
    int getAmount();
    void setAmount(int amount);
    ArrayList<ICharacterDTO> getCharacters();
    void addCharacter(ICharacterDTO character);
    void removeCharacter(ICharacterDTO character);
}
