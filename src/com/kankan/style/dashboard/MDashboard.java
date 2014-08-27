package com.kankan.style.dashboard;

import com.example.kandashboard.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * 
 * @author kankan
 *      仪表盘效果
 *
 */

public class MDashboard extends ImageView {

	private Bitmap mPointerBitmap;

	private int max = 100;
	private int progress = 0;

	//构�?�?
	public MDashboard(Context context) {
		super(context);
		init();
	}

	public MDashboard(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MDashboard(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
    //初始化布局   Initializes layout
	private void init() {
		setScaleType(ScaleType.FIT_CENTER);
		//设置表盘图片
		setBackgroundDrawableId(R.drawable.base__view_dashboard_bg);
		//设置表针图片Set the dial pictures
		setPointerDrawableId(R.drawable.base__view_dashboard);
	   //设置进度，与当前进度Sets the progress and current progress
		setMax(100);
		setProgress(0);
	}

	//设置仪表盘背景Set the dashboard background
	public void setBackgroundDrawableId(int id) {
		setImageResource(id);
		invalidate();
	}
    //设置指针的
	public void setPointerDrawableId(int id) {
		mPointerBitmap = BitmapFactory.decodeResource(getResources(), id);
		invalidate();
	}

	public void setMax(int max) {
		this.max = max;
		invalidate();
	}

	public int getMax() {
		return max;
	}

	public void setProgress(int progress) {
		this.progress = progress;
		//刷新进度
		invalidate();
	}

	public int getProgress() {
		return progress;
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int width = this.getWidth();
		int height = this.getHeight();
		int rotate = (int) ((float) ((float) progress / (float) max) * (float) 180);
		Matrix m = new Matrix();
		m.preRotate(rotate);
		Bitmap bitmap = Bitmap.createBitmap(mPointerBitmap, 0, 0,
				mPointerBitmap.getWidth(), mPointerBitmap.getHeight(), m, true);
		int dx = width / 2;
		int dy = height - bitmap.getHeight();
		if (rotate == 90) {
			dx -= bitmap.getWidth() / 2;
		} else if (rotate < 90) {
			dx -= bitmap.getWidth();
			float f = ((float) rotate / (float) 180)
					* (float) mPointerBitmap.getHeight() / 2;
			dx += f;
		} else if (rotate > 90) {
			float f = ((float) rotate / (float) 180)
					* (float) mPointerBitmap.getHeight() / 2;
			dx -= f;
		}
		canvas.drawBitmap(bitmap, dx, dy, null);
	}

}
