package com.software.studio.delicacies.data;

public class DelicaciesItem {

    private long id;
    private String name;
    private String telnumber;
    private String location;
    private Integer rating;
    private String comment;
    private String opentime;
    private Integer favorite;

    public DelicaciesItem(){

    }
    public DelicaciesItem(long id, String name, String telnumber, String location, Integer rating, String comment, String opentime, Integer favorite) {
        this.id = id;
        this.name = name;
        this.telnumber = telnumber;
        this.location = location;
        this.rating = rating;
        this.comment = comment;
        this.opentime = opentime;
        this.favorite = favorite;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTelnumber() { return telnumber; }
    public void setTelnumber(String telnumber) { this.telnumber = telnumber; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getOpentime() { return opentime; }
    public void setOpentime(String opentime) { this.opentime = opentime; }

    public Integer getFavorite() { return favorite; }
    public void setFavorite(Integer favorite) { this.favorite = favorite; }

}
