package com.techelevator.model.invitation;

public class InviteeVotes {

    private boolean thumbs_up;
    private boolean thumbs_down;

    public InviteeVotes() {
    }

    public boolean isThumbs_up() {
        return thumbs_up;
    }

    public void setThumbs_up(boolean thumbs_up) {
        this.thumbs_up = thumbs_up;
    }

    public boolean isThumbs_down() {
        return thumbs_down;
    }

    public void setThumbs_down(boolean thumbs_down) {
        this.thumbs_down = thumbs_down;
    }
}
