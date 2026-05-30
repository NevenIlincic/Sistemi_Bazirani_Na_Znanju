package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCriteria {

    private String element;
    private String status;
    private String expectedStatus;
    private String parent;
    private String type;
}
