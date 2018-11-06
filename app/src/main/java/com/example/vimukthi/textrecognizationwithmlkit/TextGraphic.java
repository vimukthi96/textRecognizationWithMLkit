package com.example.vimukthi.textrecognizationwithmlkit;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

public class TextGraphic extends GraphicOverlay.Graphic {

    private  static final int TEXT_COLOR = Color.BLUE;
    private static final float TEXT_SIZE =54.0f;
    private static final float STROKE_WIDTH =4.0f;

    private final Paint recPaint ,textPaint;
    private final FirebaseVisionText.Element text;

    public TextGraphic(GraphicOverlay overlay,FirebaseVisionText.Element text) {
        super(overlay);
        this.text =text;

        recPaint=new Paint();
        recPaint.setColor(TEXT_COLOR);
        recPaint.setStyle(Paint.Style.STROKE);
        recPaint.setStrokeWidth(STROKE_WIDTH);

        textPaint=new Paint();
        textPaint.setColor(TEXT_COLOR);
        textPaint.setTextSize(TEXT_SIZE);

        postInvalidate();
    }

    @Override
    public void draw(Canvas canvas) {

        if (text==null){
            throw new IllegalStateException("Attempting to draw a null text");
        }
        RectF rectF=new RectF(text.getBoundingBox());
        rectF.left=translateX(rectF.left);
        rectF.right=translateX(rectF.right);
        rectF.top=translateY(rectF.top);
        rectF.bottom=translateY(rectF.bottom);
        canvas.drawRect(rectF,recPaint);
        canvas.drawText(text.getText(),rectF.left,rectF.bottom,textPaint);

    }
}
