package dataTypes;

public class ItemDTO implements IItemDTO {
    private int id;
    private String name;
    private String description;
    private double weight;
    public ItemDTO(){
    }
    public ItemDTO(String name, String description, double weight){
        setName(name);
        setDescription(description);
        setWeight(weight);
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
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description=description;
    }
}
