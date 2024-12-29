package com.mr.adminlocker;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DevicePolicyManager devicePolicyManager;
    private ComponentName componentName;
    private Button btnActivateAdmin;
    private Button btnDeactivateAdmin;
    private Button btnPinApp;
    private Button btnUnpinApp;
    private Button btnForceLock;
    private Button btnWipeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, MyDeviceAdminReceiver.class);

        btnActivateAdmin = findViewById(R.id.btnActivateAdmin);
        btnDeactivateAdmin = findViewById(R.id.btnDeactivateAdmin);
        btnPinApp = findViewById(R.id.btnPinApp);
        btnUnpinApp = findViewById(R.id.btnUnpinApp);
        btnForceLock = findViewById(R.id.btnForceLock);
        btnWipeData = findViewById(R.id.btnWipeData);

        btnActivateAdmin.setOnClickListener(v -> {
            if (!devicePolicyManager.isAdminActive(componentName)) {
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "This app needs device admin privileges.");
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(MainActivity.this, "Device Admin is already active", Toast.LENGTH_SHORT).show();
            }
        });

        btnDeactivateAdmin.setOnClickListener(v -> {
            if (devicePolicyManager.isAdminActive(componentName)) {
                devicePolicyManager.removeActiveAdmin(componentName);
                Toast.makeText(MainActivity.this, "Device Admin disabled", Toast.LENGTH_SHORT).show();
                stopLockTask();
            } else {
                Toast.makeText(MainActivity.this, "Device Admin is not active", Toast.LENGTH_SHORT).show();
            }
        });

        btnPinApp.setOnClickListener(v -> {
                startLockTask();
                Toast.makeText(MainActivity.this, "App Pinned", Toast.LENGTH_SHORT).show();

        });

        btnUnpinApp.setOnClickListener(v -> {
            stopLockTask();
            Toast.makeText(MainActivity.this, "App Unpinned", Toast.LENGTH_SHORT).show();
        });

        btnForceLock.setOnClickListener(v -> {
                devicePolicyManager.lockNow();
                Toast.makeText(MainActivity.this, "Device Locked", Toast.LENGTH_SHORT).show();

        });

        btnWipeData.setOnClickListener(v -> {
            if (devicePolicyManager.isAdminActive(componentName)) {
                devicePolicyManager.wipeData(DevicePolicyManager.WIPE_EXTERNAL_STORAGE);
                Toast.makeText(MainActivity.this, "Data Wiped", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, "Device Admin Activated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Device Admin Activation Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
