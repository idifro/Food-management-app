package io.brink.thoughtfood;

public class MyData {

    private String itemName;
    private int scorenumber;
    private String feedbackcomment;

    public MyData(String itemName, int scorenumber, String feedbackcomment) {
        this.itemName = itemName;
        this.scorenumber = scorenumber;
        this.feedbackcomment = feedbackcomment;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getScorenumber() {
        return scorenumber;
    }

    public void setScorenumber(int scorenumber) {
        this.scorenumber = scorenumber;
    }

    public String getFeedbackcomment() {
        return feedbackcomment;
    }

    public void setFeedbackcomment(String feedbackcomment) {
        this.feedbackcomment = feedbackcomment;
    }
}
