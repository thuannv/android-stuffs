package thuannv.stuffs;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.gift_sender_animation)
    GiftSenderAnimatedView mAnimatedView;

    @BindView(R.id.cover)
    ImageView mCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Picasso.get().load(R.drawable.background).into(mCover);

        GiftInfo giftInfo = new GiftInfo();
        giftInfo.setGiftName("Tặng 1 Pháo Tết");
        giftInfo.setSenderAvatar("https://st.f.360game.vn/livestream/events/womens-day/images/top_img.png");
        giftInfo.setSenderName("Nguyễn Giang Đông");

        mAnimatedView.setGiftInfo(giftInfo);
        mAnimatedView.setController(new GiftSenderAnimationController());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAnimatedView.animateGiftReceiving();
            }
        }, 1000);
    }
}
