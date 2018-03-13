package thuannv.stuffs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

/**
 * @author thuannv
 * @since 12/03/2018
 */

public class OutlineTextView extends android.support.v7.widget.AppCompatTextView {

    private static final int DEFAULT_STROKE_COLOR = Color.BLACK;

    private static final int DEFAULT_STROKE_WIDTH = 0;

    private int mStrokeColor;

    private float mStrokeWidth;

    public OutlineTextView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public OutlineTextView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(@NonNull Context context, AttributeSet attrs) {
        mStrokeColor = DEFAULT_STROKE_COLOR;
        mStrokeWidth = DEFAULT_STROKE_WIDTH;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OutlineTextView);
            mStrokeColor = a.getColor(R.styleable.OutlineTextView_stroke_color, DEFAULT_STROKE_COLOR);
            mStrokeWidth = a.getDimensionPixelSize(R.styleable.OutlineTextView_stroke_width, DEFAULT_STROKE_WIDTH);
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mStrokeWidth > 0) {
            final Paint paint = getPaint();
            final int savedTextColor = getCurrentTextColor();
            final float savedStrokeWidth = paint.getStrokeWidth();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(mStrokeWidth);
            setTextColor(mStrokeColor);
            super.onDraw(canvas);


            setTextColor(savedTextColor);
            paint.setStrokeWidth(savedStrokeWidth);
            paint.setStyle(Paint.Style.FILL);
            super.onDraw(canvas);
        } else {
            super.onDraw(canvas);
        }
    }
}
