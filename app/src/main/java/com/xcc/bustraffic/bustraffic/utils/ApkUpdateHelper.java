package com.xcc.bustraffic.bustraffic.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.xcc.bustraffic.bustraffic.R;

import java.util.List;

/**
 * Created by flykozhang on 2017/1/4.
 */

public class ApkUpdateHelper {

    private Context mContext;

    public void download(Context mContext,String url) {
        this.mContext = mContext;
        if (!canDownloadState()) {
            Toast.makeText(mContext, "下载服务不用,请您启用", Toast.LENGTH_SHORT).show();
            showDownloadSetting();
            return;
        }
        ApkUpdateUtils.download(mContext, url, mContext.getResources().getString(R.string.app_name));
    }

    private void showDownloadSetting() {
        String packageName = "com.android.providers.downloads";
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        if (intentAvailable(intent)) {
            mContext.startActivity(intent);
        }
    }

    private boolean intentAvailable(Intent intent) {
        PackageManager packageManager = mContext.getPackageManager();
        List list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }


    private boolean canDownloadState() {
        try {
            int state = mContext.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");

            if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
