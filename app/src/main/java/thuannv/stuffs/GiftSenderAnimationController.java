package thuannv.stuffs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * @author thuannv
 * @since 13/03/2018
 */

public final class GiftSenderAnimationController implements ViewAnimationController<GiftSenderAnimatedView> {

    private Animator animateFlashlight(View view) {
        final ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f, 0.0f);
        final ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1.5f, 1.0f);
        final ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.5f, 1.0f);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha, scaleX, scaleY);
        animatorSet.setDuration(800);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        return animatorSet;
    }

    private Animator animateViewDisplay(View view) {
        final ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1.5f, 1.0f);
        final ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.5f, 1.0f);
        final ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0.5f, 1.0f);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha, scaleX, scaleY);
        animatorSet.setDuration(800);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        return animatorSet;
    }


    private Animator animateFlashLightAndViewDisplay(View flash, View element) {
        final AnimatorSet combineAnimator = new AnimatorSet();
        final Animator flashAnimator = animateFlashlight(flash);
        final Animator elementAnimator = animateViewDisplay(element);
        elementAnimator.setStartDelay(100);

        combineAnimator.playTogether(flashAnimator, elementAnimator);
        return combineAnimator;
    }

    public void animate(final GiftSenderAnimatedView view, Animator.AnimatorListener listener) {
        if (view != null) {
            final AnimatorSet compoundAnimator = new AnimatorSet();
            final Animator f1 = animateFlashLightAndViewDisplay(view.mAvatarFlashLight, view.mAvatar);
            final Animator f2 = animateFlashLightAndViewDisplay(view.mDisplayNameFlashLight, view.mDisplayName);
            final Animator f3 = animateFlashLightAndViewDisplay(view.mDisplayGiftNameFlashLight, view.mDisplayGiftName);
            f2.setStartDelay(200);
            f3.setStartDelay(400);

            if (listener != null) {
                compoundAnimator.addListener(listener);
            } else {
                compoundAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.INVISIBLE);
                    }
                });
            }
            compoundAnimator.playTogether(f1, f2, f3);
            compoundAnimator.start();
        }
    }
}
