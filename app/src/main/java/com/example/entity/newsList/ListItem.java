package com.example.entity.newsList;

/**
 * Created by ly on 17-3-23.
 */

public class ListItem {
    int type=0; // 0: one_pic 1: three_pic 2 video
    String id;
    String description;
    boolean isComment;
    String mname;
    String nid;
    String time;
    String title;
    String url;
    String sign;
    String hotRecNew;
    String[] pictureList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPictureList() {
        return pictureList;
    }

    public void setPictureList(String[] pictureList) {
        this.pictureList = pictureList;
    }

    public String getHotRecNew() {
        return hotRecNew;
    }

    public void setHotRecNew(String hotRecNew) {
        this.hotRecNew = hotRecNew;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public boolean isComment() {
        return isComment;
    }

    public void setComment(boolean comment) {
        isComment = comment;
    }
}
