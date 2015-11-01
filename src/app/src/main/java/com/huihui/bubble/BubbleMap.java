package com.huihui.bubble;

import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvfei on 15/10/18.
 */
public class BubbleMap {

    public class BubbleInfo {

    }

    public class BubbleItem {
        private PointF center;
        private BubbleInfo info;
        private int clickCount = 0;

        public BubbleInfo getInfo() {
            return info;
        }

        public void setInfo(BubbleInfo info) {
            this.info = info;
        }

        public PointF getCenter() {
            return center;
        }

        public void setCenter(PointF center) {
            this.center = center;
        }

        public int getClickCount() {
            return clickCount;
        }

        public void setClickCount(int clickCount) {
            this.clickCount = clickCount;
        }
    }

    private PointF leftTop;
    private final float width;
    private final float height;
    private final float radius;

    private int cols = 0;
    private int rows = 0;
    private BubbleItem[][] items;

    public BubbleMap(PointF leftTop, float width, float height, float radius) {
        if (height < 2 * radius || width < 2 * radius) {
            throw new RuntimeException("param invalid");
        }
        this.leftTop = leftTop;
        this.width = width;
        this.height = height;
        this.radius = radius;

    }

    public void build() {

        this.cols = (int) Math.floor(width / (this.radius * 2)) + 1;
        this.rows = calcRowCount();

        this.items = new BubbleItem[this.rows][this.cols];
        for (int rowIndex = 0; rowIndex < this.rows; rowIndex++) {
            float yPos = (float) (radius + rowIndex * Math.sqrt(3) * radius);
            for (int colIndex = 0; colIndex < this.cols; colIndex++) {
                float xPos = 0;
                if (rowIndex % 2 == 0) {
                    xPos = colIndex * 2 * radius;
                } else {
                    xPos = (colIndex + 1) * 2 * radius - radius;
                }
                PointF point = new PointF(xPos, yPos);
                items[rowIndex][colIndex] = new BubbleItem();
                items[rowIndex][colIndex].setCenter(point);
                Log.d("lvfei", "point " + xPos + ":" + yPos);
            }
        }
    }

    public List<BubbleItem> getItems() {
        List<BubbleItem> bubbleItemList = new ArrayList<BubbleItem>(cols * rows);
        for (int rowIndex = 0; rowIndex < this.rows; rowIndex++) {
            for (int colIndex = 0; colIndex < this.cols; colIndex++) {
                BubbleItem item = this.items[rowIndex][colIndex];
                if (item != null) {
                    bubbleItemList.add(item);
                }
            }
        }
        return bubbleItemList;
    }

    private int calcRowCount() {
        double cellHeight = Math.sqrt(3) * radius;
        double y = 1 + Math.floor((height - 2 * radius) / cellHeight);
        return ((int) y);
    }

    public void testTouch(float x, float y) {
        int rowIndex = (int) (Math.ceil(y / (Math.sqrt(3) * this.radius)) - 1);
        int colIndex = 0;
        if (rowIndex % 2 == 0) {
            colIndex = (int) (Math.ceil((x + this.radius) / (2 * this.radius)) - 1);
        } else {
            colIndex = (int) (Math.ceil(x / (2 * this.radius)) - 1);
        }
        if (rowIndex < this.rows && colIndex < this.cols) {
            BubbleItem item = this.items[rowIndex][colIndex];
            if (item != null) {
                item.setClickCount(item.getClickCount() + 1);
            }
            Log.d("pp", "rowIndex, colIndex: " + rowIndex + "," + colIndex);
        }
    }

    public BubbleItem getBubble(float x, float y) {
        float xPos = x - leftTop.x;
        float yPos = y - leftTop.y;

//        if (yPos <= centerDistance / 2) {
//
//        } else {
//            float n = (yPos - centerDistance / 2);
//        }
        return null;
    }

    public static void main(String[] args) {
        BubbleMap bubbleMap = new BubbleMap(new PointF(0f, 0f), 200f, 200f, 20);
        bubbleMap.build();
    }


}
