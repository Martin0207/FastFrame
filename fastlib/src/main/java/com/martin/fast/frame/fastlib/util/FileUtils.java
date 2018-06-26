package com.martin.fast.frame.fastlib.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 作者：Martin on 2018/2/2 22:47
 * 邮箱：martin0207mfh@163.com
 *
 * @author martin
 */
public class FileUtils {

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    /**
     * 将输入流写入文件
     *
     * @param inputStream
     * @param filePath    写入文件地址
     */
    public static void writeFile(InputStream inputStream, String filePath) {

        if (filePath == null) {
            filePath = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis();
        }

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];

            int len;
            while ((len = inputStream.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputStream.close();
            fos.close();

        } catch (FileNotFoundException e) {
            Logger.e("文件保存异常 : FileNotFoundException " + e.getMessage());
        } catch (IOException e) {
            Logger.e("文件保存异常 : IOException " + e.getMessage());
        }
    }

    /**
     * 获取文件类型
     */
    public static String getFileType(String fileName) {
        return FileType.INSTANCE.fileType(fileName);
    }

    /**
     * 获取文件后缀名
     */
    public static String getFileSuffix(String fileName) {
        return FileType.INSTANCE.getFileSuffix(fileName);
    }

}
