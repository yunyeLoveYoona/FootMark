package com.yun.footmark.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
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
		Drawable localDrawable = getDrawable();
		localDrawable.draw(canvas);
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_4444);
		Paint mPaint = new Paint();
		mPaint.setColor(getResources().getColor(R.color.hyaline));
		mPaint.setFilterBitmap(false);
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		canvas.drawBitmap(
				BitmapFactory.decodeResource(getResources(), R.drawable.a),
				0.0f, 0.0f, mPaint);

		if (chineseDialogue != null) {
			Paint mPaint1 = new Paint();
			mPaint1.setTextSize(30);
			mPaint1.setColor(getResources().getColor(R.color.white));
			canvas.drawText(chineseDialogue, (width -chineseDialogue.length()*30)/2, height - 90, mPaint1);
		}
		if (englishDialogue != null) {
			Paint mPaint1 = new Paint();
			mPaint1.setTextSize(20);
			mPaint1.setColor(getResources().getColor(R.color.white));
			canvas.drawText(englishDialogue, (width -chineseDialogue.length()*30)/2, height -50, mPaint1);
		}
	}

}
