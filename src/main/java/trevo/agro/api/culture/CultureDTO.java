package trevo.agro.api.culture;

public record CultureDTO(
        String name
) {
    public String getName() {
        return name;
    }

    public Object setName() {
        return name;
    }
}
