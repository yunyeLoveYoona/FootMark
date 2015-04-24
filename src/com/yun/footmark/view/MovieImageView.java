package com.yun.footmark.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.footmark.R;

/**
 * 模仿足迹 类似电影画面的图片
 * 
 * @author yunye
 */
public class MovieImageView extends ImageView {
	private int width;
	private int height;
	private String chineseDialogue;
	private String englishDialogue;
	private boolean isChangeColor = false;

	public MovieImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setDialogue(String chineseDialogue, String englishDialogue) {
		this.chineseDialogue = chineseDialogue;
		this.englishDialogue = englishDialogue;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		width = getMeasuredWidth();
		height = getMeasuredHeight();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		BitmapDrawable drawable = (BitmapDrawable) getDrawable();
		Paint mPaint = new Paint();
		if (isChangeColor) {
			// 过滤颜色
			ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(
					new float[] { 0.33F, 0.59F, 0.11F, 0, 0, 0.33F, 0.59F,
							0.11F, 0, 0, 0.33F, 0.59F, 0.11F, 0, 0, 0, 0, 0, 1,
							0, });
			mPaint.setColorFilter(colorFilter);
			// LightingColorFilter colorFilter = new
			// LightingColorFilter(0xFFFFFF00,
			// 0x00000022);
			// mPaint.setColorFilter(colorFilter);
		}
		canvas.drawBitmap(drawable.getBitmap(), 0.0f, 0.0f, mPaint);
		// 添加上下的黑色背景
		Paint mPaint2 = new Paint();
		mPaint2.setFilterBitmap(false);
		mPaint2.setStyle(Paint.Style.FILL);
		PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
		mPaint2.setXfermode(mode);
		Bitmap localBitmap = Bitmap.createBitmap(getWidth(), getHeight(),
				Bitmap.Config.ARGB_8888);
		Canvas localCanvas = new Canvas(localBitmap);
		localCanvas.drawRect(0, height / 10, width, height - height / 10,
				new Paint());
		
		canvas.drawBitmap(localBitmap, 0, 0, mPaint2);

		if (chineseDialogue != null) {
			Paint mPaint1 = new Paint();
			mPaint1.setTextSize(30);
			mPaint1.setColor(getResources().getColor(R.color.white));
			canvas.drawText(chineseDialogue,
					(width - chineseDialogue.length() * 30) / 2, height - height/5,
					mPaint1);
		}
		if (englishDialogue != null) {
			Paint mPaint1 = new Paint();
			mPaint1.setTextSize(20);
			mPaint1.setColor(getResources().getColor(R.color.white));
			canvas.drawText(englishDialogue,
					(width - chineseDialogue.length() * 30) / 2, height - height/8,
					mPaint1);
		}
	}

}
