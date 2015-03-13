package com.example.listviewdemo;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

/**
 * 点击图片变暗
 * @author Administrator
 *
 */
public class ImageViewTouch implements OnTouchListener {

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		 switch (event.getAction()) {
         case MotionEvent.ACTION_UP:
        	 	changeLight((ImageView)view, 0);
                 break;
         case MotionEvent.ACTION_DOWN:
                 changeLight((ImageView)view, -50);
                 break;
         case MotionEvent.ACTION_MOVE:
                 // changeLight(view, 0);
                 break;
         case MotionEvent.ACTION_CANCEL:
                 changeLight((ImageView)view, 0);
                 break;
         default:
                 break;
         }
         return true;
	}

	// 参数imageview 就是你要改变的图片对象 brightness就是亮度了。 面前测试 0就是不改变 恢复亮度，50
	// 相对来说的效果面前感觉最好。

	public  void changeLight(ImageView imageView, int brightness) {
		ColorMatrix cMatrix = new ColorMatrix();
		cMatrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
				brightness,// 改变亮度
				0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
		imageView.setColorFilter(new ColorMatrixColorFilter(cMatrix));
	}

}
