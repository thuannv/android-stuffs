package thuannv.stuffs;

import android.animation.Animator;

/**
 * @author thuannv
 * @since 13/03/2018
 */

public interface ViewAnimationController<V> {
    void animate(V view, Animator.AnimatorListener listener);
}
