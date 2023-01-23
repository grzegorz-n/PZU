public class Agent {
    private int kod;
    private String name;
    private String address;

    public Agent(int kod, String name, String address) {
        this.kod = kod;
        this.name = name;
        this.address = address;
    }

    public int getKod() {
        return kod;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Nazwa: ");
        sb.append(name);
        sb.append(", id: ");
        sb.append(kod);
        return sb.toString();
    }
}
