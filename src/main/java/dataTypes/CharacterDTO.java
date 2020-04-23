package dataTypes;

import java.util.ArrayList;

public class CharacterDTO implements ICharacterDTO {
    private int id;
    private String name;
    private String location;
    private int strength;
    private int bonuscapacity;
    private ArrayList<IItemDTO> items = new ArrayList<IItemDTO>();
    private ArrayList<IGroupDTO> groups = new ArrayList<IGroupDTO>();

    public CharacterDTO(){
    }
    public CharacterDTO(String name, int strength, int bonuscapacity){
        setName(name);
        setStrength(strength);
        setBonus(bonuscapacity);
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setLocation(String location) {
        this.location=location;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setStrength(int strength) {
        this.strength=strength;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setBonus(int bonuscapacity) {
        this.bonuscapacity=bonuscapacity;
    }

    @Override
    public int getBonus() {
        return bonuscapacity;
    }

    //TODO: How do we save amounts?
    @Override
    public ArrayList<IItemDTO> getItemsList() {
        return items;
    }

    @Override
    public void addItem(IItemDTO item) {
        items.add(item);
    }

    @Override
    public void removeItem(IItemDTO item) {
        items.remove(item);
    }

    @Override
    public ArrayList<IGroupDTO> getGroupList() {
        return groups;
    }

    @Override
    public void addGroup(IGroupDTO group) {
        groups.add(group);
    }

    @Override
    public void removeGroup(IGroupDTO group) {
        groups.remove(group);
    }
}
