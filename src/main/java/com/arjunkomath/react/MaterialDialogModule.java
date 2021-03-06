package com.arjunkomath.react;

/**
 * Created by arjun on 30/05/16.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MaterialDialogModule extends ReactContextBaseJavaModule {

    private Activity mActivity = null;

    public MaterialDialogModule(ReactApplicationContext reactContext, Activity activity) {
        super(reactContext);
        mActivity = activity;
    }

    @Override
    public String getName() {
        return "MaterialDialog";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("SUCCESS", android.R.color.holo_green_dark);
        constants.put("WARNING", android.R.color.holo_orange_dark);
        constants.put("INFO", android.R.color.holo_blue_dark);
        constants.put("DANGER", android.R.color.holo_red_dark);
        return constants;
    }

    @ReactMethod
    public void show(String title, String description, String btnText, int color, final Callback doneCallback) {
        int icon = android.R.drawable.stat_sys_download_done;
        switch (color) {
            case android.R.color.holo_green_dark: icon = android.R.drawable.ic_dialog_email; break;
            case android.R.color.holo_orange_dark: icon = android.R.drawable.ic_dialog_alert; break;
            case android.R.color.holo_blue_dark: icon = android.R.drawable.ic_dialog_info; break;
            case android.R.color.holo_red_dark: icon = android.R.drawable.stat_sys_warning; break;
        }
        MaterialStyledDialog dialog = new MaterialStyledDialog(mActivity)
                .setTitle(title)
                .setDescription(description)
                .setIcon(icon)
                .setHeaderColor(color)
                .withDialogAnimation(true)
                .withDivider(true)
                .setPositive(btnText, new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        doneCallback.invoke();
                    }
                })
                .build();
        dialog.show();
    }

    @ReactMethod
    public void showWithCancel(String title, String description, String okayText, String badText, int color, final Callback doneCallback, final Callback cancelCallback) {
        int icon = android.R.drawable.stat_sys_download_done;
        switch (color) {
            case android.R.color.holo_green_dark: icon = android.R.drawable.ic_dialog_email; break;
            case android.R.color.holo_orange_dark: icon = android.R.drawable.ic_dialog_alert; break;
            case android.R.color.holo_blue_dark: icon = android.R.drawable.ic_dialog_info; break;
            case android.R.color.holo_red_dark: icon = android.R.drawable.stat_sys_warning; break;
        }
        MaterialStyledDialog dialog = new MaterialStyledDialog(mActivity)
                .setTitle(title)
                .setDescription(description)
                .setIcon(icon)
                .setHeaderColor(color)
                .withDialogAnimation(true)
                .withDivider(true)
                .setPositive(okayText, new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        doneCallback.invoke();
                    }
                })
                .setNegative(badText, new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        cancelCallback.invoke();
                    }
                })
                .build();
        dialog.show();
    }

}