public class Products {
    private int kod;
    private String description;
    private int kodTowUbe;

    public Products(int kod, String description, int kodTowUbe) {
        this.kod = kod;
        this.description = description;
        this.kodTowUbe = kodTowUbe;
    }

    public int getKod() {
        return kod;
    }

    public String getDescription() {
        return description;
    }

    public int getKodTowUbe() {
        return kodTowUbe;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Opis: ");
        sb.append(description);
        sb.append(", id: ");
        sb.append(kod);
        return sb.toString();
    }
}
