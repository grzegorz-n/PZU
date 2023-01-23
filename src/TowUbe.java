public class TowUbe {
    private String name;
    private int id;

    public TowUbe(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Nazwa: ");
        sb.append(name);
        sb.append(", id: ");
        sb.append(id);
        return sb.toString();
    }
}
