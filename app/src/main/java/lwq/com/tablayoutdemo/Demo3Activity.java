package lwq.com.tablayoutdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Demo3Activity extends AppCompatActivity {

    private static View mStatusBarView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);
        ButterKnife.bind(this);
        //设置Toobar的主题
        toolbar.setTitle("DrawerToolbarTabLayout");
        //设置Toolbar作为ActionBar此活动窗口
        setSupportActionBar(toolbar);
        //ActionBarDrawerToggle 是 DrawerLayout.DrawerListener 实现。和 NavigationDrawer 搭配使用
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //增加DrawerLayout.DrawerListener监听器
        drawerLayout.addDrawerListener(toggle);
        //设置ActionBarDrawerToggle状态与DrawerLayout的状态同步
        toggle.syncState();

        //针对安卓4.4（API为19）
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            ViewGroup contentLayout = (ViewGroup) this.findViewById(android.R.id.content);
            contentLayout.getChildAt(0).setFitsSystemWindows(false);
            coordinator.setFitsSystemWindows(true);
            setKKStatusBar(this, R.color.statusBar);
        }

        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("tab2"));
        tabLayout.addTab(tabLayout.newTab().setText("tab3"));
        tabLayout.addTab(tabLayout.newTab().setText("tab4"));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private static void setKKStatusBar(Activity activity, int statusBarColor) {
        ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
        mStatusBarView = contentView.getChildAt(0);
        //改变颜色时避免重复添加statusBarView
        if (mStatusBarView != null && mStatusBarView.getMeasuredHeight() == getStatusBarHeight(activity)) {
            mStatusBarView.setBackgroundColor(ContextCompat.getColor(activity, statusBarColor));
            return;
        }
        mStatusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        mStatusBarView.setBackgroundColor(ContextCompat.getColor(activity, statusBarColor));
        contentView.addView(mStatusBarView, lp);
    }

    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
