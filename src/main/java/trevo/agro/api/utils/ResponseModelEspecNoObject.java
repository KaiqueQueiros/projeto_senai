package trevo.agro.api.utils;


import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class ResponseModelEspecNoObject extends ResponseModel{



    public ResponseModelEspecNoObject(String message) {
        this.setMessage(message);
        this.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(new Date()));
    }

}
