package beicai.my_shop;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import beicai.my_shop.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mLayout;
    LayoutInflater mInflater;
    List<String> datas=new ArrayList();
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_view);
        initDatas();
        initRecyclerView();
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        mLayout.setColorSchemeResources(android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark);
        mLayout.setDistanceToTriggerSync(100);
        mLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.item_press));
        mLayout.setSize(SwipeRefreshLayout.LARGE);
        mLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <10 ; i++) {
                            myAdapter.addData(i,"newcity"+i);
                        }
                        myAdapter.notifyItemRangeChanged(0, 10);
                        recyclerView.scrollToPosition(0);
                        mLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }

    private void initRecyclerView() {
        myAdapter = new MyAdapter(datas);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,2));//不对称的布局
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        myAdapter.setListener(new MyAdapter.OnItemClickListener(){

            @Override
            public void onClick(View v, int position, String city) {
                Toast.makeText(MainActivity.this, "city:" + city + ",position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDatas(){
        datas.add("New York");
        datas.add("Boston");
        datas.add("Washington");
        datas.add("San Francisco");
        datas.add("California");
        datas.add("Chicago");
        datas.add("Houston");
        datas.add("Phoenix");
        datas.add("Philadelphia");
        datas.add("Pennsylvania");
        datas.add("San Antonio");
        datas.add("Austin");
        datas.add("Milwaukee");
        datas.add("Las Vegas");
        datas.add("Oklahoma");
        datas.add("Portland");
        datas.add("Mexico");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
