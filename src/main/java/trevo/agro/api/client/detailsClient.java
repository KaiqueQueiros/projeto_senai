package trevo.agro.api.client;

public record detailsClient(Long id, String name, String email, String country, String phone) {
    public detailsClient (Client client){
        this((long) client.getId(),client.getName(), client.getEmail(),client.getCountry(),client.getPhone());
    }
}
