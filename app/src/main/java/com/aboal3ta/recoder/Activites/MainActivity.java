package com.aboal3ta.recoder.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.aboal3ta.recoder.Adapter.Mytabadpater;
import com.aboal3ta.recoder.R;
import com.astuetz.PagerSlidingTabStrip;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.tabs) PagerSlidingTabStrip tabs;
    @BindView(R.id.ToolBar) Toolbar toolbar;
    @BindView(R.id.view_pager) ViewPager viewPager;
    Bundle savedInstanceState ;
    private String time;
    private String lengh;

    String[] perms = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        requsetpermission();

        viewPager.setAdapter(new Mytabadpater(getSupportFragmentManager()));
        tabs.setViewPager(viewPager);
        setSupportActionBar(toolbar);

    }

    @AfterPermissionGranted(123)
    private void requsetpermission() {
        if (EasyPermissions.hasPermissions(this, perms)) {

        }
        else {
            EasyPermissions.requestPermissions(this, "We need permissions To run this Application ",
                    123, perms);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {

        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }

    }

    @Override
    protected void onStart() {

        super.onStart();
        requsetpermission();
    }
}
