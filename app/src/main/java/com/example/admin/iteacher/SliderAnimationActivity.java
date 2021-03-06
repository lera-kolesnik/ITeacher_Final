package com.example.admin.iteacher;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.iteacher.calendar.CalendarActivity;


public class SliderAnimationActivity extends AppCompatActivity {

    private static final String SAVING_STATE_SLIDER_ANIMATION = "SliderAnimationSavingState";
    private boolean isSliderAnimation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.setAdapter(new ViewPagerAdapter(R.array.icons, R.array.titles, R.array.hints));

        CirclePageIndicator mIndicator  = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);



        viewPager.setPageTransformer(true, new CustomPageTransformer());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {

                View landingBGView = findViewById(R.id.landing_backgrond);
                int colorBg[] = getResources().getIntArray(R.array.landing_bg);


                ColorShades shades = new ColorShades();
                shades.setFromColor(colorBg[position % colorBg.length])
                        .setToColor(colorBg[(position + 1) % colorBg.length])
                        .setShade(positionOffset);

                landingBGView.setBackgroundColor(shades.generate());

            }

            public void onPageSelected(int position) {

            }

            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    public class ViewPagerAdapter extends PagerAdapter {

        final private int CALENDAR_POSITION = 1;

        private int mIconResId, mTitleArrayResId, mHintArrayResId;

        public ViewPagerAdapter(int iconResId, int titleArrayResId, int hintArrayResId) {
            mIconResId = iconResId;
            mTitleArrayResId = titleArrayResId;
            mHintArrayResId = hintArrayResId;
        }

        @Override
        public int getCount() {
            return getResources().getIntArray(mIconResId).length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            Drawable icon = getResources().obtainTypedArray(mIconResId).getDrawable(position);
            String title = getResources().getStringArray(mTitleArrayResId)[position];
            String hint = getResources().getStringArray(mHintArrayResId)[position];

            View itemView = getLayoutInflater().inflate(R.layout.viewpager_item, container, false);

            ImageView iconView = itemView.findViewById(R.id.landing_img_slide);
            TextView titleView = itemView.findViewById(R.id.landing_txt_title);
            TextView hintView = itemView.findViewById(R.id.landing_txt_hint);

            if (position == CALENDAR_POSITION) {
                iconView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent intent = new Intent(SliderAnimationActivity.this, CalendarActivity.class);
                        startActivity(intent);
                    }
                });
            }

            iconView.setImageDrawable(icon);
            titleView.setText(title);
            hintView.setText(hint);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }

    public class CustomPageTransformer implements ViewPager.PageTransformer {

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            View imageView = view.findViewById(R.id.landing_img_slide);
            View contentView = view.findViewById(R.id.landing_txt_hint);
            View txt_title = view.findViewById(R.id.landing_txt_title);

            if (position < -1) { // [-Infinity,-1)
                // Эта страница находится вне экрана слева
            } else if (position <= 0) { // [-1,0]
                // Эта страница перемещается влево

                // Не удалять страницу по умолчанию
                setTranslationX(view,pageWidth * -position);
                if (contentView != null) {
                    // Провести по экрану влево
                    setTranslationX(contentView,pageWidth * position);
                    setTranslationX(txt_title,pageWidth * position);

                    setAlpha(contentView,1 + position);
                    setAlpha(txt_title,1 + position);
                }

                if (imageView != null) {
                    // Затушить изображение
                    setAlpha(imageView,1 + position);
                }

            } else if (position <= 1) { // (0,1]
                // Страница перемещается справа

                // Опять же не удалять эту страницу по умолчанию
                setTranslationX(view, pageWidth * -position);
                if (contentView != null) {
                    // Провести по экрану вправо
                    setTranslationX(contentView,pageWidth * position);
                    setTranslationX(txt_title,pageWidth * position);

                    setAlpha(contentView, 1 - position);
                    setAlpha(txt_title, 1 - position);

                }
                if (imageView != null) {
                    // Затухание изображения
                    setAlpha(imageView,1 - position);
                }

            }
        }
    }


    private void setAlpha(View view, float alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && ! isSliderAnimation) {
            view.setAlpha(alpha);
        }
    }

    /*
     * @param view - вид к которому применяется альфа.
     * @param translationX - значение translationX .
     */
    private void setTranslationX(View view, float translationX) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && ! isSliderAnimation) {
            view.setTranslationX(translationX);
        }
    }

    public void onSaveInstanceState(Bundle outstate) {

        if (outstate != null) {
            outstate.putBoolean(SAVING_STATE_SLIDER_ANIMATION,isSliderAnimation);
        }

        super.onSaveInstanceState(outstate);
    }

    public void onRestoreInstanceState(Bundle inState) {

        if (inState != null) {
            isSliderAnimation = inState.getBoolean(SAVING_STATE_SLIDER_ANIMATION,false);
        }

        super.onRestoreInstanceState(inState);
    }
}
