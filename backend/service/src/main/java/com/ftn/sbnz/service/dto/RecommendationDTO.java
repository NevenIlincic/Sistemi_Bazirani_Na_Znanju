package com.ftn.sbnz.service.dto;


import enums.Category;
import enums.ProposedAction;
import lombok.Data;
import models.Recommendation;

@Data
public class RecommendationDTO {

    private String message;
    private ProposedAction action;
    private Category category;

    public RecommendationDTO(Recommendation recommendation) {
        this.message = recommendation.getMessage();
        this.action = recommendation.getAction();
        this.category = recommendation.getCategory();
    }
}
