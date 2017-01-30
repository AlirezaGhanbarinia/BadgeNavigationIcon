package com.sirelon.library.badgenavigationicon.badge;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

/**
 * @author romanishin
 * @since 30.01.17 on 12:48.
 */

class BadgeDrawable extends ShapeDrawable {

    private final Paint textPaint;
    private final int height;
    private final int width;
    private final int fontSize;
    private final int color;
    private String text;

    public BadgeDrawable(int size, int textColor, int fontSize, int color) {
        super(new OvalShape());

        this.height = size;
        this.width = size;

        this.fontSize = fontSize;
        this.color = color;


        // text paint settings
        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(true);
        textPaint.setStyle(Paint.Style.FILL);
//        textPaint.setTypeface(builder.font);
        textPaint.setTextAlign(Paint.Align.CENTER);
//        textPaint.setStrokeWidth(builder.borderThickness);

        // drawable paint color
        Paint paint = getPaint();
        paint.setColor(color);
    }

    public void updateText(String text) {
        this.text = text;
        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {
        if (text == null)
            return;

        super.draw(canvas);
        Rect r = getBounds();

        int count = canvas.save();
        canvas.translate(r.left, r.top);

        // draw text
        int width = this.width < 0 ? r.width() : this.width;
        int height = this.height < 0 ? r.height() : this.height;
        int fontSize = this.fontSize < 0 ? (Math.min(width, height) / 2) : this.fontSize;
        textPaint.setTextSize(fontSize);
        canvas.drawText(text, width / 2, height / 2 - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint);

        canvas.restoreToCount(count);
    }

    @Override
    public void setAlpha(int alpha) {
        textPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        textPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return width;
    }

    @Override
    public int getIntrinsicHeight() {
        return height;
    }

}
