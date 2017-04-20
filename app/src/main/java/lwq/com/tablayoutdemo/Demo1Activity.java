package lwq.com.tablayoutdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Demo1Activity extends AppCompatActivity {

    @BindView(R.id.tab_title)
    TabLayout tab_title;
    @BindView(R.id.vp_pager)
    ViewPager vp_pager;

    private int[] tabImg;
    private List<View> listViews;
    //定义新闻页面
    private View newsView;
    //定义体育页面
    private View sportView;
    //定义娱乐页面
    private View funView;
    //tab名称列表
    private List<String> list_title;
    private viewAdapter vAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        ButterKnife.bind(this);

        //为tabLayout上的图标赋值
        tabImg = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        listViews = new ArrayList<>();
        LayoutInflater mInflater = getLayoutInflater();
        newsView = mInflater.inflate(R.layout.fragment_news, null);
        sportView = mInflater.inflate(R.layout.fragment_sports, null);
        funView = mInflater.inflate(R.layout.fragment_fun, null);
        listViews.add(newsView);
        listViews.add(sportView);
        listViews.add(funView);

        list_title = new ArrayList<>();
        list_title.add("新闻");
        list_title.add("体育");
        list_title.add("娱乐");

        //设置TabLayout的模式,这里主要是用来显示tab展示的情况的
        tab_title.setTabMode(TabLayout.MODE_FIXED);

        //为TabLayout添加tab名称
        tab_title.addTab(tab_title.newTab().setText(list_title.get(0)));
        tab_title.addTab(tab_title.newTab().setText(list_title.get(1)));
        tab_title.addTab(tab_title.newTab().setText(list_title.get(2)));

        //配置viewAdapter
        vAdapter = new viewAdapter(this, listViews, list_title, tabImg);
        vp_pager.setAdapter(vAdapter);

        //将tabLayout与viewpager连起来
        tab_title.setupWithViewPager(vp_pager);
    }
}
