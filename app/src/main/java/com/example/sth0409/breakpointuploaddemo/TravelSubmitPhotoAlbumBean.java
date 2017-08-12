package com.example.sth0409.breakpointuploaddemo;

import java.io.File;

/**
 * Created by suntinghui on 2017/8/12 0012.
 */

public class TravelSubmitPhotoAlbumBean {
    public String imageUrl = "";
    public boolean isShowDel = true;
    public boolean isSubmitSuccess = false;
    public boolean isSubmitFail = false;
    public double submitProgress = 0;
    public String photoDesc;
    public String address;
    public boolean startSubmit = false;
    public File img;
}
