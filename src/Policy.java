public class Policy {
    private int id;
    private String date;
    private  int value;
    private int productId;
    private  int costumerId;
    private int agentId;

    public Policy(int id, String date, int value, int productId, int costumerId, int agentId) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.productId = productId;
        this.costumerId = costumerId;
        this.agentId = agentId;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }

    public int getProductId() {
        return productId;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public int getAgentId() {
        return agentId;
    }

    @Override
    public String toString() {
        return "Polisa zawarta: " + date + ", wartość polisy: " + value;
    }
}
