package trevo.agro.api.utils;


import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ResponseModelEspec extends ResponseModel {
    private Object objectResponse;

    public ResponseModelEspec(String message, Object objectResponse) {
        this.setMessage(message);
        this.objectResponse = objectResponse;
        this.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(new Date()));
    }

}
