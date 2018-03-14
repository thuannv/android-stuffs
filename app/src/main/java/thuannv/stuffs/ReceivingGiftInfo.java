package thuannv.stuffs;

/**
 * @author thuannv
 * @since 13/03/2018
 */

public class ReceivingGiftInfo {

    private String mSenderName;

    private ImageSource mSenderAvatar;

    private String mGiftName;

    public String getSenderName() {
        return mSenderName;
    }

    public void setSenderName(String senderName) {
        mSenderName = senderName;
    }

    public String getGiftName() {
        return mGiftName;
    }

    public void setGiftName(String giftName) {
        mGiftName = giftName;
    }

    public ImageSource getSenderAvatar() {
        return mSenderAvatar;
    }

    public void setSenderAvatar(ImageSource senderAvatar) {
        mSenderAvatar = senderAvatar;
    }
}
