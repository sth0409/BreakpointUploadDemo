package com.example.sth0409.breakpointuploaddemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_percent;
    private Button btn_upload;
    private OkHttpClient mOkHttpClient;
    private TravelSubmitPhotoAlbumBean travelSubmitPhotoAlbumBean;
    private int chuncks, curChunck = 1;
    private Button btn_reupload;
    FileOutputStream fos = null;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        try {
            fos = new FileOutputStream(fileto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        tv_percent = (TextView) findViewById(R.id.tv_percent);
        btn_upload = (Button) findViewById(R.id.btn_upload);

        btn_upload.setOnClickListener(this);
        btn_reupload = (Button) findViewById(R.id.btn_reupload);
        btn_reupload.setOnClickListener(this);
        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileto.delete();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_upload:
                File file = new File("/storage/emulated/0/Tencent/QQ_Images/5f1f8f03809e2e0a.png");
                Log.i("---------", "onClick: " + file.length());
                //请求id
                // postAsynHttp("{\"userID\":\"14c617b882494a47a74ec25ece86b4e6\"}", "http://192.168.1.52:8080/KPCX/appTnotes/getNoteID");

                pushPhoto("0");
                break;
            case R.id.btn_reupload:
                curChunck = 25;
                pushPhoto("1");
                break;
        }
    }

    private void postAsynHttp(String json, String url) {


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
//            loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor).build();
        Map<String, Object> map = RestSource.createPostMap(json);
        RequestBody formBody = new FormBody.Builder()
                .add("param", (String) map.get("param"))
                .add("sign", (String) map.get("sign"))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        // LogUtil.i((String) map.get("sign"));
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                final NoteIdBean noteIdBean = new Gson().fromJson(str, NoteIdBean.class);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        pushPhoto(noteIdBean.Result.noteID);
                    }
                }).start();


//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //  editText.setText(str);
////                        tBEAN tBEAN = new Gson().fromJson(str, tBEAN.class);
////                     //   tBEAN.getResult().getRoomInfos().get(0).getRoomimages().get(0).getLocations().get(0).getValue();
////
////                        Toast.makeText(Test.this, tBEAN.getResult().getRoomInfos().get(0).getRoomimages().get(0).getLocations().get(0).getValue(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

        });
    }

    static String BOUNDARY = "----------" + System.currentTimeMillis();
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("multipart/form-data;boundary=" + BOUNDARY);
    File file = new File("/storage/emulated/0/Tencent/QQ_Images/aaa.jpg");
    File fileto = new File("/storage/emulated/0/bbb.jpg");

    private void pushPhoto(String noteID) {


        int blockLength = 1024 * 5;
        if (file.length() % blockLength == 0) {
            chuncks = (int) file.length() / blockLength;
        } else {
            chuncks = (int) file.length() / blockLength + 1;

        }

//        bodyMap.put("img\"; filename=\"" + System.currentTimeMillis(), createCustomRequestBody(MediaType.parse("image/jpg"), travelSubmitPhotoAlbumBean.img,
//                new ProgressListener() {
//                    @Override
//                    public void onProgress(long totalBytes, long remainingBytes, boolean done) {
//                        if (travelSubmitPhotoAlbumBean.startSubmit) {
//                            LogUtil.i(((totalBytes - remainingBytes) * 100 / totalBytes) + "");
//                            if (((totalBytes - remainingBytes) * 100 / totalBytes) % 10 == 0) {
//                                travelSubmitPhotoAlbumBean.submitProgress = ((totalBytes - remainingBytes) * 100 / totalBytes);
//                                Message message = new Message();
//                                message.arg1 = orderNo;
//                                message.what = 1;
//                                handler.sendMessage(message);
//                                try {
//                                    Thread.sleep(250);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                }
//        ));

        while (curChunck <= chuncks)

        {
            if (noteID.equals("0")) {
                if (curChunck == 25) {
                    break;
                }
            }

            try {
                try {
                    //文件输出流

                    //写数据
                    byte[] bytes = getBlock((curChunck - 1) * blockLength, file, blockLength);
                    fos.write(bytes);
                    Message message = new Message();
                    message.arg1 = curChunck;
                    message.arg2 = chuncks;
                    message.obj = bytes.length;
                    handler.sendMessage(message);
                    //关闭文件流
                    curChunck++;


                } catch (Exception e) {
                    e.printStackTrace();

                }


//                final Map<String, RequestBody> bodyMap = new HashMap<>();
//                TravelSubmitPhotoAlbumEntity travelSubmitPhotoAlbumEntity = new TravelSubmitPhotoAlbumEntity();
//                travelSubmitPhotoAlbumEntity.userID = "14c617b882494a47a74ec25ece86b4e6";
//                travelSubmitPhotoAlbumEntity.address = "%e7%9f%b3%e5%ae%b6%e5%ba%84";
//                travelSubmitPhotoAlbumEntity.photoDesc = "%e6%96%ad%e7%82%b9%e6%b5%8b%e8%af%95";
//                travelSubmitPhotoAlbumEntity.title = "%e6%96%ad%e7%82%b9%e6%b5%8b%e8%af%95";
//                travelSubmitPhotoAlbumEntity.noteID = noteID;
//                travelSubmitPhotoAlbumEntity.orderNo = 0 + "";
//                travelSubmitPhotoAlbumEntity.postion = (curChunck - 1) * blockLength;
//                String json = new Gson().toJson(travelSubmitPhotoAlbumEntity);
//                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
//                bodyMap.put("param", body);
//                RequestBody sign = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), RestSource.createSign(json));
//                bodyMap.put("sign", sign);
//                bodyMap.put("img\"; filename=\"" + file.getName(), RequestBody.create(MEDIA_TYPE_MARKDOWN, getBlock((curChunck - 1) * blockLength, file, blockLength)));
//                try {
//                    retrofit2.Response<HttpResponse<CommonDataBean>> responseBean = RestSource.getApiService().photoPublished(bodyMap).execute();
//                    if (responseBean.body().getCode().equals("0")) {
//                        Log.i("---------------", curChunck + "-----pushPhoto: success");
//                        onCallBack();
//                        curChunck++;
//                    } else {
//                        break;
//                        //                    uploadStatus = UploadStatus.UPLOAD_STATUS_ERROR;
//                        //                    onCallBack();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//            Request request = new Request.Builder()
//                    .url("http://192.168.1.52:8080/KPCX/appTnotes/photoPublished")
//                    .post(bodyMap)
//                    .build();
//            try {
//                mOkHttpClient.newCall(request).execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


        }
//        try {
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Log.i("*****************", "pushPhoto: success");
        Glide.with(this).load(new File("/storage/emulated/0/bbb.jpg")).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_percent.setText((msg.arg1 * 100 / msg.arg2) + "%");
            Log.i("----------------", "curChunck--" + msg.arg1 + "chuncks--" + msg.arg2 + "pushPhoto: " + (int) (msg.obj) + "////" + (msg.arg1 * 100 / msg.arg2));

        }
    };

    private void addParams(MultipartBody.Builder builder, Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, params.get(key)));
            }
        }
    }

    public static byte[] getBlock(long offset, File file, int blockSize) {
        byte[] result = new byte[blockSize];
        RandomAccessFile accessFile = null;
        try {
            accessFile = new RandomAccessFile(file, "r");
            accessFile.seek(offset);
            int readSize = accessFile.read(result);
            if (readSize == -1) {
                return null;
            } else if (readSize == blockSize) {
                return result;
            } else {
                byte[] tmpByte = new byte[readSize];
                System.arraycopy(result, 0, tmpByte, 0, readSize);
                return tmpByte;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (accessFile != null) {
                try {
                    accessFile.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }
}
