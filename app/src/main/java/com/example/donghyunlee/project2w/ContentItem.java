package com.example.donghyunlee.project2w;

/**
 * Created by DONGHYUNLEE on 2017-07-11.
 */

public class ContentItem {

    private int storeImg;
    private String storeName;
    private String storeContent;
    private String dist;
    private String popular;
    private String recent;
    private int checkbutton;

    public ContentItem(int storeImg, String storeName, String storeContent, String dist, String popular, String recent,int checkbutton)
    {
        this.storeImg = storeImg;
        this.storeContent = storeContent;
        this.storeName = storeName;
        this.dist = dist;
        this.popular = popular;
        this.recent = recent;
        this.checkbutton = checkbutton;
    }
    /*
        Getter
     */
    public int getStoreImg() { return storeImg;}
    public int getCheckbutton() { return checkbutton;}
    public String getStoreContent() {
        return storeContent;
    }
    public String getStoreName() { return storeName; }
    public String getDist() { return dist; }
    public String getPopular() { return popular; }
    public String getRecent() { return recent; }
   /*
        Setter
   */
   public void setCheckbutton(int checkbutton) { this.checkbutton = checkbutton; }
}
