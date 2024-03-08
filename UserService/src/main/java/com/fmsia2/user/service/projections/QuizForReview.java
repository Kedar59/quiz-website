package com.fmsia2.user.service.projections;
import com.fmsia2.user.service.projections.Interaction;
import com.fmsia2.user.service.projections.QtsReview;

import java.util.List;
public class QuizForReview {
    private Interaction interaction;
    private List<QtsReview> listQts;
    public QuizForReview(){}
    public QuizForReview(Interaction interaction, List<QtsReview> listQts) {
        this.interaction = interaction;
        this.listQts = listQts;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

    public List<QtsReview> getListQts() {
        return listQts;
    }

    public void setListQts(List<QtsReview> listQts) {
        this.listQts = listQts;
    }
}
