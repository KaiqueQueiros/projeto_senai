package trevo.agro.api.client;

public record DetailsClient(Long id, String name, String email, String country, String phone) {
    public DetailsClient(Client client) {
        this((long) client.getId(), client.getName(), client.getEmail(), client.getCountry(), client.getPhone());
    }
}
