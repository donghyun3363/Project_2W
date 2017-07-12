package com.example.donghyunlee.project2w;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    final int ITEM_SIZE = 8;
    ContentItem[] item = new ContentItem[ITEM_SIZE];
    List<ContentItem> items = new ArrayList<>();

    @Bind(R.id.orderDist)
    Button distButton;
    @Bind(R.id.orderPopular)
    Button PopularButton;
    @Bind(R.id.orderRecent)
    Button recentButton;
    ImageButton changeCard;
    RecyclerView recyclerView;
    StaggeredGridLayoutManager mLayoutManager;
    LinearLayoutManager mLinearLayoutManger;
    RecyclerAdapter mAdapter;

    final static int GRID_LAYOUT = 0;
    final static int LIST_LAYOUT = 1;
    int willChange_flag = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        settingItem();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        changeCard = (ImageButton) findViewById(R.id.changeCard);

        mLayoutManager = new StaggeredGridLayoutManager(2,1);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        mLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);

        mLinearLayoutManger = new LinearLayoutManager(getApplicationContext());
        mAdapter = new RecyclerAdapter(this, items, R.layout.cardview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        changeCard.setImageResource(R.drawable.ic_listbutton);

        Collections.sort(items, new DistDescCompare());
        mAdapter.notifyDataSetChanged();
        distButton.setTextColor(getResources().getColor(R.color.colorGreen));

        changeCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                settingLayoutButton();
            }
        });

    }

    @OnClick(R.id.orderDist)
    void distMethod(View v) {
        Collections.sort(items, new DistDescCompare());
        distButton.setTextColor(getResources().getColor(R.color.colorGreen));
        PopularButton.setTextColor(getResources().getColor(R.color.colorBlack));
        recentButton.setTextColor(getResources().getColor(R.color.colorBlack));
        mAdapter.notifyDataSetChanged();
    }
    @OnClick(R.id.orderPopular)
    void popularMethod(View v) {
        Collections.sort(items, new PopularAscCompare());
        distButton.setTextColor(getResources().getColor(R.color.colorBlack));
        PopularButton.setTextColor(getResources().getColor(R.color.colorGreen));
        recentButton.setTextColor(getResources().getColor(R.color.colorBlack));
        mAdapter.notifyDataSetChanged();
    }
    @OnClick(R.id.orderRecent)
    void recentMethod(View v){
        Collections.sort(items, new RecentAscCompare());
        distButton.setTextColor(getResources().getColor(R.color.colorBlack));
        PopularButton.setTextColor(getResources().getColor(R.color.colorBlack));
        recentButton.setTextColor(getResources().getColor(R.color.colorGreen));
        mAdapter.notifyDataSetChanged();
    }
    public void settingItem() {

        item[0] = new ContentItem(R.drawable.img_mobidic, "모비딕", getApplicationContext().getString(R.string.item_content0), "1000개", "10개", "3", 0);
        item[1] = new ContentItem(R.drawable.img_zerotwonine, "제로 투 나인", getApplicationContext().getString(R.string.item_content1),"900개", "11개", "2일", 0);
        item[2] = new ContentItem(R.drawable.img_vintage, "빈티지 1988", getApplicationContext().getString(R.string.item_content2), "100개", "15개", "4일", 0);
        item[3] = new ContentItem(R.drawable.img_italy, "이탈리", getApplicationContext().getString(R.string.item_content3), "2000개", "12개", "5일", 0);
        item[4] = new ContentItem(R.drawable.img_magnolia, "매그놀리아", getApplicationContext().getString(R.string.item_content4), "3000개", "22개", "10일", 0);
        item[5] = new ContentItem(R.drawable.hansot, "한솥", getApplicationContext().getString(R.string.item_content5),"1500개", "9개", "2일", 0);
        item[6] = new ContentItem(R.drawable.img_mmth, "메머드 커피", getApplicationContext().getString(R.string.item_content6), "1300개", "9개","1일", 0);
        item[7] = new ContentItem(R.drawable.img_shybana, "샤이바나", getApplicationContext().getString(R.string.item_content7), "1400개", "3개", "2일", 0);


        for(int i = 0 ; i<ITEM_SIZE ; i++)
        {
            items.add(item[i]);

        }
    }
    public void settingLayoutButton(){
        if(willChange_flag == GRID_LAYOUT || willChange_flag == 3){
            willChange_flag = LIST_LAYOUT;
        }
        else{
            willChange_flag = GRID_LAYOUT;
        }
        if(willChange_flag == GRID_LAYOUT) {
            recyclerView.setLayoutManager(mLayoutManager);
            changeCard.setImageResource(R.drawable.ic_listbutton);
        }
        else{
            recyclerView.setLayoutManager(mLinearLayoutManger);
            changeCard.setImageResource(R.drawable.ic_dashbutton);
        }
    }

    static class DistDescCompare implements Comparator<ContentItem> {
      @Override
        public int compare(ContentItem o1, ContentItem o2) {

          return o2.getDist().compareTo(o1.getDist());
        }
    }
    static class PopularAscCompare implements Comparator<ContentItem> {
        @Override
        public int compare(ContentItem o1, ContentItem o2) {

            return o1.getPopular().compareTo(o2.getPopular());
        }
    }
    static class RecentAscCompare implements Comparator<ContentItem> {
        @Override
        public int compare(ContentItem o1, ContentItem o2) {

            return o1.getRecent().compareTo(o2.getRecent());
        }
    }
}