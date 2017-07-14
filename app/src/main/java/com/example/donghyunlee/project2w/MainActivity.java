package com.example.donghyunlee.project2w;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    final static int GRID_LAYOUT = 0;
    final static int LIST_LAYOUT = 1;
    int willChange_flag = 3;
    private final int ITEM_SIZE = 8;
    private ContentItem[] item = new ContentItem[ITEM_SIZE];
    private List<ContentItem> items = new ArrayList<>();
    /*
        버터나이프
     */
    @Bind(R.id.orderDist)
    Button distButton;
    @Bind(R.id.orderPopular)
    Button PopularButton;
    @Bind(R.id.orderRecent)
    Button recentButton;
    @Bind(R.id.changeCard)
    ImageButton changeCard;
    ImageButton selectMenu;

    RecyclerView recyclerView;
    RecyclerAdapter mAdapter;
    StaggeredGridLayoutManager mStaggeredLayoutManager;
    LinearLayoutManager mLinearLayoutManger;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fm.beginTransaction();
   // AddRegister addregister = new AddRegister();
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview); // 리사이클러뷰는 버터나이프가 안됨

        settingItem();

        /*
            Staggered 레이아웃매니저 setting
         */
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(2,1);
        mStaggeredLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        mStaggeredLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);

        /*
            Linear 레이아웃매니저 setting
         */
        mLinearLayoutManger = new LinearLayoutManager(getApplicationContext());

        /*
            리사이클러뷰 setting
         */
        mAdapter = new RecyclerAdapter(this, items, R.layout.cardview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setAdapter(mAdapter);
        changeCard.setImageResource(R.drawable.ic_listbutton);
        distButton.setTextColor(getResources().getColor(R.color.colorGreen));

        //    Item List sorting
        Collections.sort(items, new DistDescCompare());
        mAdapter.notifyDataSetChanged();
        selectMenu = (ImageButton) findViewById(R.id.selectMenu);
        selectMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item0:
                                //openFragment(addregister);

                                Intent intent = new Intent(MainActivity.this, AddRegisterActivity.class) ;
                                startActivity(intent) ;
                                return true;
                            case R.id.item1:
                                Toast.makeText(getApplicationContext(), "-(미정)", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.item2 :
                                Toast.makeText(getApplicationContext(), "-(미정)", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.item3:
                                Toast.makeText(getApplicationContext(), "-(미정)", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.item4:
                                Toast.makeText(getApplicationContext(), "-(미정)", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return true;
                        }
                    }


                });
            }
        });

    }
    /* fragment
    private void openFragment(final AddRegister fragment)   {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.add_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
*/

    @OnClick(R.id.changeCard)
    void settingLayoutButton(){
        if(willChange_flag == GRID_LAYOUT || willChange_flag == 3){
            willChange_flag = LIST_LAYOUT;
        }
        else{
            willChange_flag = GRID_LAYOUT;
        }
        if(willChange_flag == GRID_LAYOUT) {
            recyclerView.setLayoutManager(mStaggeredLayoutManager);
            changeCard.setImageResource(R.drawable.ic_listbutton);
        }
        else{
            recyclerView.setLayoutManager(mLinearLayoutManger);
            changeCard.setImageResource(R.drawable.ic_dashbutton);
        }
    }

    @OnClick(R.id.orderDist)
    void distMethod() {
        Collections.sort(items, new DistDescCompare());
        mAdapter.notifyDataSetChanged();
        colorChange("distMethod");
    }
    @OnClick(R.id.orderPopular)
    void popularMethod() {
        Collections.sort(items, new PopularAscCompare());
        mAdapter.notifyDataSetChanged();
        colorChange("popularMethod");
    }
    @OnClick(R.id.orderRecent)
    void recentMethod(){
        Collections.sort(items, new RecentAscCompare());
        mAdapter.notifyDataSetChanged();
        colorChange("recentMethod");
    }
    /*
       글씨 색깔 체인지
    */
    void colorChange(String howMethod)
    {
        if(howMethod == "distMethod")
        {
            distButton.setTextColor(getResources().getColor(R.color.colorGreen));
            PopularButton.setTextColor(getResources().getColor(R.color.colorBlack));
            recentButton.setTextColor(getResources().getColor(R.color.colorBlack));
        }
        else if(howMethod == "popularMethod"){
            distButton.setTextColor(getResources().getColor(R.color.colorBlack));
            PopularButton.setTextColor(getResources().getColor(R.color.colorGreen));
            recentButton.setTextColor(getResources().getColor(R.color.colorBlack));
        }
        else if(howMethod == "recentMethod")
        {
            distButton.setTextColor(getResources().getColor(R.color.colorBlack));
            PopularButton.setTextColor(getResources().getColor(R.color.colorBlack));
            recentButton.setTextColor(getResources().getColor(R.color.colorGreen));
        }
    }
    public void settingItem() {
        TypedArray storeimgs = getResources().obtainTypedArray(R.array.storeimg);
        String[] storenames = getResources().getStringArray(R.array.storename);
        String[] storecontents = getResources().getStringArray(R.array.storecontent);
        String[] dists = getResources().getStringArray(R.array.dist);
        String[] populars = getResources().getStringArray(R.array.popular);
        String[] recents = getResources().getStringArray(R.array.recent);
        int[] checks = getResources().getIntArray(R.array.check);

        for(int i = 0 ; i < ITEM_SIZE ; i++)
        {
            item[i] = new ContentItem(storeimgs.getResourceId(i, -1), storenames[i], storecontents[i], dists[i], populars[i], recents[i], checks[i]);
            items.add(item[i]);
        }

    }
    /*
        Item List 정렬
     */
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