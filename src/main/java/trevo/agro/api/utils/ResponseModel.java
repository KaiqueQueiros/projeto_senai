package trevo.agro.api.utils;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;


@Getter
@Setter
public class ResponseModel {
    private String date;
    private String message;
    private Object objectResponse;

    public ResponseModel(String message, Object objectResponse) {
        this.message = message;
        this.objectResponse = objectResponse;
        this.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(new Date());

    }
}
