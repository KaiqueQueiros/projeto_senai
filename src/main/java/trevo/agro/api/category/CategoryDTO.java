package trevo.agro.api.category;

public record CategoryDTO(

        String name
) {
    public String getName(){
        return name;
    }
    public Object setName(){
        return name;
    }
}
