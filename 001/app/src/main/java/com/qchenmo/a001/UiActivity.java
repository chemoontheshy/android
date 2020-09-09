package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.qchenmo.a001.gridView.GridViewActivity;
import com.qchenmo.a001.listview.ListViewActivity;
import com.qchenmo.a001.recyclerView.RecyclerViewActivity;

public class UiActivity extends AppCompatActivity {

    private Button btn_toTextView,btn_toButton,btn_toEditText,btn_toRadio,btn_toCheckBox,btn_toImageView,btn_toListView,btn_toGridView,btn_toRecyclerView;
    private Button btn_toWebView,btn_toToast,btn_toDialog,btn_toProgress,btn_toCustomDialog,btn_toPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        //功能界面
        btn_toTextView = findViewById(R.id.toTextView);
        btn_toButton = findViewById(R.id.toButton);
        btn_toEditText = findViewById(R.id.toEditText);
        btn_toRadio = findViewById(R.id.toRadio);
        btn_toCheckBox = findViewById(R.id.toCheckBox);
        btn_toImageView = findViewById(R.id.toImageView);
        btn_toListView = findViewById(R.id.toListView);
        btn_toGridView = findViewById(R.id.toGridView);
        btn_toRecyclerView = findViewById(R.id.toRecyclerView);
        btn_toWebView  = findViewById(R.id.toWebView);
        btn_toToast = findViewById(R.id.toToast);
        btn_toDialog = findViewById(R.id.toDialog);
        btn_toProgress = findViewById(R.id.toProgress);
        btn_toCustomDialog = findViewById(R.id.toCustomDialog);
        btn_toPopupWindow = findViewById(R.id.toPopupWindow);
        setListeners();
    }

    private void setListeners(){
        Onclick onclick = new Onclick();
        btn_toTextView.setOnClickListener(onclick);
        btn_toButton.setOnClickListener(onclick);
        btn_toEditText.setOnClickListener(onclick);
        btn_toRadio.setOnClickListener(onclick);
        btn_toCheckBox.setOnClickListener(onclick);
        btn_toImageView.setOnClickListener(onclick);
        btn_toListView.setOnClickListener(onclick);
        btn_toGridView.setOnClickListener(onclick);
        btn_toRecyclerView.setOnClickListener(onclick);
        btn_toWebView.setOnClickListener(onclick);
        btn_toToast.setOnClickListener(onclick);
        btn_toDialog.setOnClickListener(onclick);
        btn_toProgress.setOnClickListener(onclick);
        btn_toCustomDialog.setOnClickListener(onclick);
        btn_toPopupWindow.setOnClickListener(onclick);
    }

    private class Onclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){

                case R.id.toTextView:
                    //跳转到Text演示界面
                    intent = new Intent(UiActivity.this,TextViewActivity.class);
                    break;
                case R.id.toButton:
                    //跳转到Button演示界面
                    intent = new Intent(UiActivity.this,ButtonActivity.class);
                    break;
                case R.id.toEditText:
                    //跳转到EditText演示界面
                    intent = new Intent(UiActivity.this,EditTextActivity.class);
                    break;
                case R.id.toRadio:
                    //跳转到Radio演示界面
                    intent = new Intent(UiActivity.this,RadioActivity.class);
                    break;
                case R.id.toCheckBox:
                    //跳转到CheckBox演示界面
                    intent = new Intent(UiActivity.this,CheckBoxActivity.class);
                    break;
                case R.id.toImageView:
                    //跳转到ImageView演示界面
                    intent = new Intent(UiActivity.this,ImageViewActivity.class);
                    break;
                case R.id.toListView:
                    //跳转到ListView演示界面
                    intent = new Intent(UiActivity.this, ListViewActivity.class);
                    break;
                case R.id.toGridView:
                    //跳转到GridView演示界面
                    intent = new Intent(UiActivity.this, GridViewActivity.class);
                    break;
                case R.id.toRecyclerView:
                    //跳转到RecyclerView演示界面
                    intent = new Intent(UiActivity.this, RecyclerViewActivity.class);
                    break;
                case R.id.toWebView:
                    //跳转到toWebView演示界面
                    intent = new Intent(UiActivity.this, WebViewActivity.class);
                    break;
                case R.id.toToast:
                    //跳转到toToast演示界面
                    intent = new Intent(UiActivity.this, ToastActivity.class);
                    break;
                case R.id.toDialog:
                    //跳转到toToast演示界面
                    intent = new Intent(UiActivity.this, DialogActivity.class);
                    break;
                case R.id.toProgress:
                    //跳转到toToast演示界面
                    intent = new Intent(UiActivity.this, ProgressActivity.class);
                    break;
                case R.id.toCustomDialog:
                    //跳转到toToast演示界面
                    intent = new Intent(UiActivity.this, CustomDialogActivity.class);
                    break;
                case R.id.toPopupWindow:
                    //跳转到toToast演示界面
                    intent = new Intent(UiActivity.this, PopupWindowActivity.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
            startActivity(intent);
        }
    }

}
