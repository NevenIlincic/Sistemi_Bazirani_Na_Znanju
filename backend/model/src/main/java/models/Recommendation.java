package models;

import enums.Category;
import enums.ProposedAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {
    private Integer incidentId;
    private String playerId;
    private String message;
    private ProposedAction action;
    private Category category;
}
