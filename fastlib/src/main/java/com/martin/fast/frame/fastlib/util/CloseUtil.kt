package com.martin.fast.frame.fastlib.util

import java.io.Closeable
import java.io.IOException

/**
 * <pre>
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/10/9
 * desc  : 关闭相关工具类
</pre> *
 */
object CloseUtil {

    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    fun closeIO(vararg closeables: Closeable) {
        if (closeables == null) {
            return
        }
        for (closeable in closeables) {
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    /**
     * 安静关闭IO
     *
     * @param closeables closeable
     */
    fun closeIOQuietly(vararg closeables: Closeable) {
        if (closeables == null) return
        for (closeable in closeables) {
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (ignored: IOException) {
                }

            }
        }
    }
}
