package cn.endureblaze.ka.utils;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import android.text.TextUtils;
import java.io.File;

public class InstallUtil
{
	public static void installApk(Context context, String apkPath) {
		if (context == null || TextUtils.isEmpty(apkPath)) {
			return;
		}
		File file = new File(apkPath);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if(Build.VERSION.SDK_INT>=24) { //Android 7.0及以上
			Uri apkUri = FileProvider.getUriForFile(context, "cn.endureblaze.ka.fileprovider", file);
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
		}else{
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		}
		context.startActivity(intent);
	}
}
