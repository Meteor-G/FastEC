package com.efan.latte.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.efan.latte.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/22.
 */

public class BannerCreator {
    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener clickListener) {
        convenientBanner.setPages(new HolderCreator(), banners)
                //设置小球
                .setPageIndicator(new int[]{R.drawable.dot_focus, R.drawable.dot_normal})
                //小球位置
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //监听事件
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                //允许循环
                .setCanLoop(true);
    }
}
