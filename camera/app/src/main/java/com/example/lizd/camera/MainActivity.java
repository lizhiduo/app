package com.example.lizd.camera;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.TextureView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;


import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity implements TextureView.SurfaceTextureListener{

    public static String TAG = "CameraActivity";


    private TextureView mTextureView;
    private Camera mCamera;

    private int cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        setContentView(R.layout.activity_main);


        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();

        Log.d(TAG, "screenHeight : " + screenHeight + " screenWidth: " + screenWidth);


        mTextureView = (TextureView) findViewById(R.id.cam);

        mTextureView.setSurfaceTextureListener(this);


        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();


//        for(int cameraId=0; cameraId<Camera.getNumberOfCameras(); cameraId++)
        cameraId = cameraInfo.CAMERA_FACING_FRONT;// CAMERA_FACING_BACK

        {
            Camera.getCameraInfo( cameraId, cameraInfo);
            Camera camera = Camera.open(cameraId);
            Camera.Parameters params = camera.getParameters();
            List<Camera.Size> previewSIzes = params.getSupportedVideoSizes();

            Log.d(TAG, "Video supported sizes: " + cameraSizeToSting(previewSIzes) );
        }

    }


    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface,
                                            int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        return true;
    }

    @Override
    public void onSurfaceTextureAvailable(
            SurfaceTexture surface,
            int width, int height) {


        mCamera = Camera.open(cameraId);
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(1280, 720);

        mCamera.setParameters(parameters);
        mCamera.setPreviewCallback(camCallback);

        try {

            mCamera.setPreviewTexture(surface);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();
        } catch (IOException ioe) {
            // Something bad happened
        }
    }

    public static String cameraSizeToSting(Iterable<Camera.Size> sizes)
    {
        StringBuilder s = new StringBuilder();
        for (Camera.Size size : sizes)
        {
            if (s.length() != 0)
                s.append(",");
            s.append(size.width).append('x').append(size.height);
        }
        return s.toString();
    }

    Camera.PreviewCallback camCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] bytes, Camera camera) {
//            Log.d("Test","width:"+camera.getParameters().getPreviewSize().width+""+"    height:"+camera.getParameters().getPreviewSize().height);
        }
    };

}
