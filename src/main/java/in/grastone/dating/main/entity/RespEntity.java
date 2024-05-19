package in.grastone.dating.main.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespEntity {
    private int code;
    private String msg;
    private Object data;
}
