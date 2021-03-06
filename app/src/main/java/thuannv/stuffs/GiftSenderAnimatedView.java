package thuannv.stuffs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author thuannv
 * @since 12/03/2018
 */

public class GiftSenderAnimatedView extends FrameLayout {

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

    private int mAvatarSize;

    private Animator mPlayingAnimation;

    private ViewAnimationController<GiftSenderAnimatedView> mAnimatorController;

    private boolean mIsLayoutPassed = false;

    private final Animator.AnimatorListener ANIMATION_LISTENER = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            mPlayingAnimation = null;
            GiftSenderAnimatedView.this.setVisibility(INVISIBLE);
        }
    };

    private final Runnable ANIMATOR = new Runnable() {
        @Override
        public void run() {
            animateInternal();
        }
    };

    public GiftSenderAnimatedView(Context context) {
        super(context);
        init(context, null);
    }

    public GiftSenderAnimatedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mIsLayoutPassed = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        cancelAnimation();
        removeCallbacks(ANIMATOR);
        super.onDetachedFromWindow();
    }

    private void init(Context context, AttributeSet attrs) {
        View view = inflate(context, R.layout.layout_animated_gift_sender, this);
        ButterKnife.bind(this, view);

        final Resources res = context.getResources();
        mAvatarSize = res.getDimensionPixelSize(R.dimen.display_avatar_size);

        final Picasso picasso = Picasso.get();
        picasso.load(R.drawable.flash_light)/*.resize(w, h)*/.into(mAvatarFlashLight);
        picasso.load(R.drawable.flash_light)/*.resize(w, h)*/.into(mDisplayNameFlashLight);
        picasso.load(R.drawable.flash_light)/*.resize(w, h)*/.into(mDisplayGiftNameFlashLight);

        mAvatar.setVisibility(View.VISIBLE);
        mAvatarFlashLight.setVisibility(View.VISIBLE);

        mAvatarFlashLight.setAlpha(0.0f);
        mAvatarFlashLight.setVisibility(View.VISIBLE);

        mDisplayNameFlashLight.setAlpha(0.0f);
        mDisplayNameFlashLight.setVisibility(View.VISIBLE);

        mDisplayGiftNameFlashLight.setAlpha(0.0f);
        mDisplayGiftNameFlashLight.setVisibility(View.VISIBLE);

        mAvatar.setAlpha(0.0f);
        mDisplayName.setAlpha(0.0f);
        mDisplayGiftName.setAlpha(0.0f);
    }

    private void setAvatar(ImageSource source) {
        if (source.isPath()) {
            Picasso.get().load(source.getPath()).resize(mAvatarSize, mAvatarSize).into(mAvatar);
        } else if (source.isFile()) {
            Picasso.get().load(source.getFile()).resize(mAvatarSize, mAvatarSize).into(mAvatar);
        } else if (source.isUri()) {
            Picasso.get().load(source.getUri()).resize(mAvatarSize, mAvatarSize).into(mAvatar);
        } else if (source.isLocalResourceId()) {
            Picasso.get().load(source.getResourceId()).resize(mAvatarSize, mAvatarSize).into(mAvatar);
        }
    }

    public void setGiftInfo(ReceivingGiftInfo info) {
        if (info != null) {
            setAvatar(info.getSenderAvatar());
            mDisplayName.setText(info.getSenderName());
            mDisplayGiftName.setText(info.getGiftName());
        }
    }

    public void setController(ViewAnimationController<GiftSenderAnimatedView> controller) {
        mAnimatorController = controller;
    }

    private void cancelAnimation() {
        if (mPlayingAnimation != null) {
            mPlayingAnimation.cancel();
            mPlayingAnimation = null;
        }
    }

    private void animateInternal() {
        cancelAnimation();
        setVisibility(View.VISIBLE);
        if (mAnimatorController != null) {
            mPlayingAnimation = mAnimatorController.animate(this, ANIMATION_LISTENER);
        }
    }

    public void animateReceivingGift() {
        if (mAnimatorController != null) {
            removeCallbacks(ANIMATOR);
            if (mIsLayoutPassed) {
                post(ANIMATOR);
            } else {
                postDelayed(ANIMATOR, 1000);
            }
        }
    }
}
