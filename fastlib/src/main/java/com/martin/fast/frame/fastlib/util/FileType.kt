package com.martin.fast.frame.fastlib.util

/**
 * @author ：Martin
 * @date : 2018/6/23 9:47
 */
object FileType {

    fun fileType(fileName: String?): String {
        var fileName = fileName
        if (fileName == null) {
            fileName = "nullFile"
            return fileName
        } else {
            // 获取文件后缀名并转化为写，用于后续比较
            val fileType = getFileSuffix(fileName)
            // 创建图片类型数组
            val img = arrayOf("bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd", "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf")
            for (i in img.indices) {
                if (img[i] == fileType) {
                    return "picture"
                }
            }

            // 创建文档类型数组
            val document = arrayOf("txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt")
            for (i in document.indices) {
                if (document[i] == fileType) {
                    return "document"
                }
            }
            // 创建视频类型数组
            val video = arrayOf("mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm")
            for (i in video.indices) {
                if (video[i] == fileType) {
                    return "video"
                }
            }
            // 创建音乐类型数组
            val music = arrayOf("mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg", "m4a", "vqf")
            for (i in music.indices) {
                if (music[i] == fileType) {
                    return "audio"
                }
            }
        }
        return "file"
    }

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    fun getFileSuffix(fileName: String): String {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase()
    }
}