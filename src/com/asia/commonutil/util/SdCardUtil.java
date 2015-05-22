package com.asia.commonutil.util;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

public class SdCardUtil {
    
    private SdCardUtil(){}
    
    public static String getSDRootPath(){
        return Environment.getExternalStorageDirectory().getPath();
    }
    
    /**
     * 获取SD卡总存储大小
     * @return
     */
    public static float getSDTotalSizeInMB(){
        File rootFile = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(rootFile.getPath());
        int blockCount = statFs.getBlockCount();
        DLog.d("blockCount:" + blockCount);
        float blockSize = statFs.getBlockSize();
        DLog.d("blockSize:" + blockSize);
        float totalSize = blockCount*blockSize/1024/1024;
        return totalSize;
    }
    
    /**
     * 获取SD卡可用空间大小,单位MB
     * @return
     */
    public static float getSDAvailableSizeInMB(){
        File rootFile = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(rootFile.getPath());
        int availableBlocks = statFs.getAvailableBlocks();
        float blockSize = statFs.getBlockSize();
        float freeSize = availableBlocks*blockSize/1024;
        return freeSize;
    }

}
