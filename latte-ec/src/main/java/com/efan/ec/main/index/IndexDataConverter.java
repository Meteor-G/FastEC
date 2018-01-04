package com.efan.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.efan.latte.ui.recycle.DataConverter;
import com.efan.latte.ui.recycle.ItemType;
import com.efan.latte.ui.recycle.MultipleFields;
import com.efan.latte.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/19.
 */

public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convent() {
        final JSONArray dataArrary = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArrary.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArrary.getJSONObject(i);
            final String imageurl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageurl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageurl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageurl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //Banner的初始化
                final int bannerSiza = banners.size();
                for (int j = 0; j < bannerSiza; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, size)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imageurl)
                    .setField(MultipleFields.BANNERS, bannerImages)
                    .build();
            ENTITYS.add(entity);
        }
        return ENTITYS;
    }
}
