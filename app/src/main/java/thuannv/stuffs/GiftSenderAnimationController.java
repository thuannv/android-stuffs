package thuannv.stuffs;

import android.animation.Animator;
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


    private Animator animateFlashAndViewDisplayTogether(View flash, View element) {
        final Animator flashAnimator = animateFlashlight(flash);
        final Animator elementAnimator = animateViewDisplay(element);
        elementAnimator.setStartDelay(100);

        final AnimatorSet combineAnimator = new AnimatorSet();
        combineAnimator.playTogether(flashAnimator, elementAnimator);
        return combineAnimator;
    }

    public void animate(GiftSenderAnimatedView view) {
        final Animator f1 = animateFlashAndViewDisplayTogether(view.mAvatarFlashLight, view.mAvatar);
        final Animator f2 = animateFlashAndViewDisplayTogether(view.mDisplayNameFlashLight, view.mDisplayName);
        final  Animator f3 = animateFlashAndViewDisplayTogether(view.mDisplayGiftNameFlashLight, view.mDisplayGiftName);
        f2.setStartDelay(200);
        f3.setStartDelay(400);

        final AnimatorSet finalSet = new AnimatorSet();
        finalSet.playTogether(f1, f2, f3);
        finalSet.start();
    }
}
