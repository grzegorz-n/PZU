public class Costumer {
    private int kod;
    private String name;
    private String Address;

    public Costumer(int kod, String name, String address) {
        this.kod = kod;
        this.name = name;
        Address = address;
    }

    public int getKod() {
        return kod;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return Address;
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
