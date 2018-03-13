package thuannv.stuffs;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AnimationDemoActivity extends AppCompatActivity {

    @BindView(R.id.avatar_container)
    FrameLayout mAvatarContainer;

    @BindView(R.id.avatar)
    CircleImageView mAvatar;

    @BindView(R.id.avatar_flash_light)
    ImageView mAvatarFlashLight;

    @BindView(R.id.display_name_container)
    FrameLayout mDisplayNameContainer;

    @BindView(R.id.display_name)
    OutlineTextView mDisplayName;

    @BindView(R.id.display_name_flash_light)
    ImageView mDisplayNameFlashLight;

    @BindView(R.id.display_gift_name_container)
    FrameLayout mDisplayGiftNameContainer;

    @BindView(R.id.display_gift_name)
    OutlineTextView mDisplayGiftName;

    @BindView(R.id.display_gift_name_flash_light)
    ImageView mDisplayGiftNameFlashLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation_demo);

        ButterKnife.bind(this);

        final DisplayMetrics dm = getResources().getDisplayMetrics();
        final float density = dm.density;
        final int w = dm.widthPixels;
        final int h = (int) (1.2 * getResources().getDimensionPixelSize(R.dimen.animation_container_size));
        final int avatarSize = getResources().getDimensionPixelSize(R.dimen.display_avatar_size);

        final Picasso picasso = Picasso.get();
        picasso.load(R.drawable.flash_light)/*.resize(w, h)*/.into(mAvatarFlashLight);
        picasso.load(R.drawable.flash_light)/*.resize(w, h)*/.into(mDisplayNameFlashLight);
        picasso.load(R.drawable.flash_light)/*.resize(w, h)*/.into(mDisplayGiftNameFlashLight);
        picasso.load(R.drawable.background).resize(avatarSize, avatarSize).into(mAvatar);

        mAvatarFlashLight.setAlpha(0.0f);
        mAvatarFlashLight.setVisibility(View.VISIBLE);

        mDisplayNameFlashLight.setAlpha(0.0f);
        mDisplayNameFlashLight.setVisibility(View.VISIBLE);

        mDisplayGiftNameFlashLight.setAlpha(0.0f);
        mDisplayGiftNameFlashLight.setVisibility(View.VISIBLE);

        mAvatar.setAlpha(0.0f);
        mDisplayName.setAlpha(0.0f);
        mDisplayGiftName.setAlpha(0.0f);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                final Animator f1 = flashAndElement(mAvatarFlashLight, mAvatar);
                final Animator f2 = flashAndElement(mDisplayNameFlashLight, mDisplayName);
                final  Animator f3 = flashAndElement(mDisplayGiftNameFlashLight, mDisplayGiftName);
                f2.setStartDelay(200);
                f3.setStartDelay(400);

                final AnimatorSet finalSet = new AnimatorSet();
                finalSet.playTogether(f1, f2, f3);
                finalSet.start();
            }
        }, 1000);

    }


    Animator flashLight(View view) {
        final ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f, 0.0f);
        final ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1.5f, 1.0f);
        final ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.5f, 1.0f);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha, scaleX, scaleY);
        animatorSet.setDuration(800);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        return animatorSet;
    }

    Animator displayElement(View view) {
        final ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1.5f, 1.0f);
        final ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.5f, 1.0f);
        final ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0.5f, 1.0f);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha, scaleX, scaleY);
        animatorSet.setDuration(800);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        return animatorSet;
    }


    Animator flashAndElement(View flash, View element) {
        final Animator flashAnimator = flashLight(flash);
        final Animator elementAnimator = displayElement(element);
        elementAnimator.setStartDelay(100);

        final AnimatorSet combineAnimator = new AnimatorSet();
        combineAnimator.playTogether(flashAnimator, elementAnimator);
        return combineAnimator;
    }
}
