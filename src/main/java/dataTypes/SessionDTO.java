package dataTypes;

import java.util.ArrayList;

public class SessionDTO implements ISessionDTO {
    private int id;
    private String date;
    private String location;
    private ArrayList<ICharacterDTO> characters = new ArrayList<ICharacterDTO>();
    private int amount;

    public SessionDTO(){
    }
    public SessionDTO(String date, String location, int amount){
        setDate(date);
        setLocation(location);
        setAmount(amount);
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id =id;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date=date;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location=location;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount=amount;
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
