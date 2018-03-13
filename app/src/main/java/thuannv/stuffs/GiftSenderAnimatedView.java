package thuannv.stuffs;

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

    private ViewAnimationController<GiftSenderAnimatedView> mAnimatorController;

    public GiftSenderAnimatedView(Context context) {
        super(context);
        init(context, null);
    }

    public GiftSenderAnimatedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
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

    public void setGiftInfo(GiftInfo gift) {
        if (gift != null) {
            //Picasso.get().load(gift.getSenderAvatar()).resize(mAvatarSize, mAvatarSize).into(mAvatar);
            Picasso.get().load(R.drawable.hot_girl_1).resize(mAvatarSize, mAvatarSize).into(mAvatar);
            mDisplayName.setText(gift.getSenderName());
            mDisplayGiftName.setText(gift.getGiftName());
        }
    }

    public void setController(ViewAnimationController<GiftSenderAnimatedView> controller) {
        mAnimatorController = controller;
    }

    public void animateGiftReceiving() {
        if (mAnimatorController != null) {
            mAnimatorController.animate(this);
        }
    }

    public ImageView getAvatarFlashLight() {
        return mAvatarFlashLight;
    }

    public CircleImageView getAvatar() {
        return mAvatar;
    }
}
