package thuannv.stuffs;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author thuannv
 * @since 12/03/2018
 */

public class GiftSenderAnimatedView extends RelativeLayout {

    private CircleImageView mSenderAvatar;

    private ImageView mSenderNameFlashLight;

    private ImageView mReceivingTextFlashLight;

    private OutlineTextView mSenderUserName;

    private OutlineTextView mReceivingGiftText;

    public GiftSenderAnimatedView(Context context) {
        super(context);
        init(context, null);
    }

    public GiftSenderAnimatedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initViews(context);
    }

    private void initViews(Context context) {
        inflate(context, R.layout.layout_animated_gift_sender, this);
        mSenderAvatar = (CircleImageView) findViewById(R.id.sender_avatar);
        mSenderNameFlashLight = (ImageView) findViewById(R.id.sender_flash_light);
        mSenderUserName = (OutlineTextView) findViewById(R.id.sender_name);
        mReceivingTextFlashLight = (ImageView) findViewById(R.id.receiving_gift_flash_light);
        mReceivingGiftText = (OutlineTextView) findViewById(R.id.receiving_gift_text);

        mSenderAvatar.setVisibility(GONE);
        mSenderUserName.setVisibility(GONE);
        mSenderNameFlashLight.setVisibility(GONE);
        mReceivingTextFlashLight.setVisibility(GONE);
        mReceivingGiftText.setVisibility(GONE);
    }

    void setupAnimations() {
    }
}
