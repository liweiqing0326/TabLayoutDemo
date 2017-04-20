package lwq.com.tablayoutdemo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Demo2Activity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        ButterKnife.bind(this);
        context = this;

        //设置Toobar的主题
        toolbar.setTitle("ToolbarTabLayout");
        //设置Toolbar作为ActionBar此活动窗口
        setSupportActionBar(toolbar);
        //当显示Toobar时，显示ID为home的返回控件
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置ToolBar的颜色
            this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("tab2"));
        tabLayout.addTab(tabLayout.newTab().setText("tab3"));
        tabLayout.addTab(tabLayout.newTab().setText("tab4"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
