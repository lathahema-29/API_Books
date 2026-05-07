package payload;

public class ApiClientPayload {

    private String clientName;
    private String clientEmail;

    public ApiClientPayload(String clientName, String clientEmail) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }
}