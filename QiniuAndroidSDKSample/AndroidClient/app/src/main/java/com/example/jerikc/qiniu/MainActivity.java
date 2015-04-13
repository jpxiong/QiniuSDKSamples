package com.example.jerikc.qiniu;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.view.Menu;
import android.view.MenuItem;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONObject;

import java.io.File;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    private static final String TOKEN_URL = "http://192.168.1.113:8080/UploadFileServer/uploadAction";

    private static final String FILENAME_CLOUD = "P50413-222805.jpg";
    private static final String FILENAME_LOCAL = "P50413-222805.jpg";
    private static final String UPLOAD_SUCCESS_HINT = "SUCCESS";
    private static final String UPLOAD_FAIL_HINT = "FAIL";

    private TextView mUploadInfoTextView;
    private Button mUploadBtn;
    private ProgressBar mUploadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUploadInfoTextView = (TextView) findViewById(R.id.unpload_info_txtview);
        mUploadProgressBar = (ProgressBar) findViewById(R.id.upload_pb);
        mUploadBtn = (Button) findViewById(R.id.upload_btn);
        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUploadProgressBar.setProgress(0);
                UploadFileTask uploadTask = new UploadFileTask();
                uploadTask.execute();
            }
        });

    }

    private class UploadFileTask extends AsyncTask<Void, Integer, String> {
        private String uploadMsg;

        @Override
        protected void onPreExecute() {
            mUploadInfoTextView.setText("uploading...");
        }

        @Override
        protected String doInBackground(Void... params) {
            UploadOptions options = new UploadOptions(null, null, false,
                    new UpProgressHandler(){
                        public void progress(String key, double percent){
                            publishProgress((int)(percent * 100));
                        }
                    }, null);
            uploadFile(options);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progresses) {
            mUploadProgressBar.setProgress(progresses[0]);
        }

        @Override
        protected void onPostExecute(String progresses) {

        }

        @Override
        protected void onCancelled() {
            mUploadProgressBar.setProgress(0);
        }
    }

    private void showInforInTask(final TextView txtView, final String msg) {
        if (txtView == null)
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtView.setText(msg);
            }
        });
    }

    private void simpleUpload(final UploadOptions options, String token) {
        String filePath = Environment.getExternalStorageDirectory() + "/DCIM/" + FILENAME_LOCAL;

        UploadManager uploadManager = new UploadManager();
        File file = new File(filePath);

        // don't find the file
        if (file == null) {
            showInforInTask(mUploadInfoTextView, UPLOAD_FAIL_HINT);
            return;
        }

        uploadManager.put(file, FILENAME_CLOUD, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject response) {
                        String msg = info.isOK() ? UPLOAD_SUCCESS_HINT : UPLOAD_FAIL_HINT;
                        showInforInTask(mUploadInfoTextView, msg);
                    }
                }, options);
    }

    private void uploadFile(final UploadOptions options) {
        String response = null;
        try {
            long start = System.currentTimeMillis();
            response = CustomHttpClient.executeHttpPost(TOKEN_URL, null);
            Log.d(TAG, "get token cost:" + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            showInforInTask(mUploadInfoTextView, e.getMessage().toString());
            e.printStackTrace();
            return;
        }

        final String str = response.toString().replaceAll("\\s+", "");
        Log.i(TAG, "response=" + response);
        simpleUpload(options, str);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
