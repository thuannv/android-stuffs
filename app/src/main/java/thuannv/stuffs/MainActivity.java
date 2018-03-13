package thuannv.stuffs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Point size  = new Point();
        getWindow().getWindowManager().getDefaultDisplay().getSize(size);
        ImageView cover = findViewById(R.id.background);
        Picasso.get().load(R.drawable.background).resize(size.x, size.y).into(cover);

        final OutlineTextView textView = findViewById(R.id.text);
        final ImageView flashlight = findViewById(R.id.flash_light);
        flashlight.post(new Runnable() {
            @Override
            public void run() {
                Picasso.get()
                        .load(R.drawable.flash_light)
                        .into(flashlight);
                flashlight.setVisibility(View.VISIBLE);
            }
        });

        final Handler h = new Handler();

        final Runnable r = new Runnable() {
            @Override
            public void run() {
                final Runnable self = this;
                final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                float normalTextSize = displayMetrics.scaledDensity * 14;
                float scaledTextSize = displayMetrics.scaledDensity * 28;
                textView.setTextSize(scaledTextSize);
                ValueAnimator textScaledAnimator = ObjectAnimator.ofFloat(scaledTextSize, normalTextSize);
                textScaledAnimator.setDuration(350);
                textScaledAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        textView.setTextSize((float) animation.getAnimatedValue());
                    }
                });
                textScaledAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        textView.setVisibility(View.VISIBLE);
                        flashlight.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        flashlight.setVisibility(View.GONE);
                        h.postDelayed(self, 1000);
                    }
                });
                textScaledAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                textScaledAnimator.start();
            }
        };

        h.post(r);
    }


}
