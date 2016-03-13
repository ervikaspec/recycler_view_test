package star.agro.com.agrostartest.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import star.agro.com.agrostartest.R;

public class ImageSliderAdapter extends PagerAdapter {

    int MAX_PAGES_TO_SHOW = 6;

    public ImageSliderAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return MAX_PAGES_TO_SHOW;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(R.drawable.banner);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(container.getContext(), 100));
        imageView.setLayoutParams(params);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    private int dpToPx(Context context, int dp) {
        float density = getDensity(context);
        int px = (int) (dp * density);
        return px;
    }

}