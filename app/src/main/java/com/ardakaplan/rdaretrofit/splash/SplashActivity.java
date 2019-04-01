package com.ardakaplan.rdaretrofit.splash;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.ardakaplan.rdalibrary.base.ui.screen.RDAActivity;
import com.ardakaplan.rdalogger.RDALogger;
import com.ardakaplan.rdaretrofit.R;
import com.ardakaplan.rdaretrofit.requests.GoogleService;
import com.ardakaplan.rdaretrofitlib.RDARequestListener;
import com.ardakaplan.rdaretrofitlib.exceptions.RDARequestException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends RDAActivity implements SplashContract.SplashViewContract {


    @BindView(R.id.button_test)
    Button testButton;

    @Inject
    SplashContract.SplashPresenterContract presenter;

    @Inject
    GoogleService googleService;

    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData = "";


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState, R.layout.splash_activity);

        presenter.attach(this);

        presenter.testPresenterContract();

        testButton.setText("DENEME");


    }


    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    @OnClick(R.id.button_test)
    void test() {

        RDALogger.info("LAHAANA");


        googleService.makeRequest("", new RDARequestListener<String>() {
            @Override
            public void onSuccess(String s) {

//                RDALogger.info("ssss +" + s);
            }

            @Override
            public void onError(RDARequestException e) {

                e.printStackTrace();
            }
        });


    }

    private void saveFile() {
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {

            RDALogger.info("EXTERNAL YOK ");
        } else {

            if (Build.VERSION.SDK_INT >= 23) {
                if (checkPermission()) {
                    // Code for above or equal 23 API Oriented Device
                    // Your Permission granted already .Do next code
                } else {
                    requestPermission(); // Code for permission
                }
            } else {

                save();
            }


        }
    }

    private File checkRootFolder() {

        File file = new File(Environment.getExternalStorageDirectory().toString() + "/arda");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TravellerLog :: ", "Problem creating Image folder");
            }
        }

        return file;
    }

    private void save() {
        RDALogger.info("EXTERNAL VAR ");
        myExternalFile = new File(checkRootFolder(), filename);

        try {
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            fos.write("ARDA".getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");

                    save();
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private static final int PERMISSION_REQUEST_CODE = 1;


    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detach();
    }
}
