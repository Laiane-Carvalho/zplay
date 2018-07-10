package zup.com.br.zplay.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class Rating implements Serializable {

    private String source;
    private String value;

}
