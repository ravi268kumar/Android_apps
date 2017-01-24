package tdevm.chatui;

/**
 * Created by Tridev on 28-08-2016.
 */
public class TestModel {
    private boolean isImage, isMine;
    private String content;

    public TestModel(String content, boolean isMine, boolean isImage) {
        this.isImage = isImage;
        this.isMine = isMine;
        this.content = content;
    }

    public boolean isImage() {
        return isImage;
    }


    public boolean isMine() {
        return isMine;
    }


    public String getContent() {
        return content;
    }


}
