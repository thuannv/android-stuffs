package thuannv.stuffs;

import android.os.Bundle;
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

    private GiftSenderAnimationController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Picasso.get().load(R.drawable.background).into(mCover);

        final ReceivingGiftInfo receivingGiftInfo = new ReceivingGiftInfo();
        receivingGiftInfo.setGiftName("Tặng 1 Pháo Tết");
        ImageSource.fromResource(R.drawable.hot_girl_1);
        receivingGiftInfo.setSenderName("Nguyễn Giang Đông");
        receivingGiftInfo.setSenderAvatar(ImageSource.fromResource(R.drawable.hot_girl_1));

        mController = new GiftSenderAnimationController();
        mAnimatedView.setController(mController);
        mAnimatedView.setGiftInfo(receivingGiftInfo);
        mAnimatedView.animateReceivingGift();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
