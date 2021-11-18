package Model;

public class Word {

    private final int id;
    private final String Name;
    private final Integer Count;

    public Word(int id, String name, Integer count) {
        this.id = id;
        this.Name = name;
        this.Count = count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public Integer getCount() {
        return Count;
    }

    @Override
    public String toString() {
        return "Word{" +
                "Name='" + Name + '\'' +
                ", Count=" + Count +
                '}';
    }
}
