package lwq.com.tablayoutdemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 此adpter是在一个fragment中切换不同的view，好处是在一个activity中可以取得所有view中的值
 * Created by Administrator on 2017/3/6.
 */

public class viewAdapter extends PagerAdapter {

    private List<View> list_view;
    private List<String> list_Title;
    private int[] tabImg;
    private Context context;

    public viewAdapter(Context context, List<View> list_view, List<String> list_Title, int[] tabImg) {
        this.list_view = list_view;
        this.list_Title = list_Title;
        this.tabImg = tabImg;
        this.context = context;
    }

    /**
     * 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
     */
    @Override
    public int getCount() {
        return list_view.size();
    }

    /**
     * 来判断显示的是否是同一个Fragment，这里我们将两个参数相比较返回即可
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 如果滑动的Fragment，会调用这个方法进行新的Fragment的初始化，我们将要显示的Fragment加入到ViewGroup中，然后作为返回值返回即可
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list_view.get(position), 0);
        return list_view.get(position);
    }

    /**
     * PagerAdapter，如果滑动的Fragment，就会调用这个方法，将销毁Fragment
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list_view.get(position));
    }

    /**
     * 此方法是给tablayout中的tab赋值的，就是显示名称,并且给其添加icon的图标
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        Drawable dImage = context.getResources().getDrawable(tabImg[position]);
        dImage.setBounds(0, 0, dImage.getIntrinsicWidth()/2, dImage.getIntrinsicHeight()/2);
        //这里前面加的空格就是为图片显示
        SpannableString sp = new SpannableString("  " + list_Title.get(position));
        ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BOTTOM);
        sp.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }
}
