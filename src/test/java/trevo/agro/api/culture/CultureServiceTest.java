package trevo.agro.api.culture;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CultureServiceTest {

    @Test
    void register() {
        Culture culture = new Culture(new CultureDTO("CEREAIS"));
        CultureService cultureService = new CultureService();
    }

    @Test
    void list() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}