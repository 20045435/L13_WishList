package sg.edu.rp.c346.id20045435.wishlist;

import java.io.Serializable;

public class List implements Serializable {

    private int id;
    private String wish;
    private float wantRating;

    public List(int id, String wish, float wantRating) {
        this.id = id;
        this.wish = wish;
        this.wantRating = wantRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public float getWantRating() {
        return wantRating;
    }

    public void setWantRating(float wantRating) {
        this.wantRating = wantRating;
    }
}
