package e.ardya.foodorderapp.utils.helper


import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.net.URI

object FileHelper {

    fun getFileFromCamere(activity: Activity?, uri: URI?): Uri? {
//        return if (activity != null && uri != null) {
//            FileProvider.getUriForFile(
//                activity,
//                activity.packageName,
//                File()
//            )
//        } else {
//            null
//        }
        return null
    }

    fun getPathFromURI(contentUri: Uri?): String? {
        val contentPath = contentUri?.path
        contentPath?.let {
            if (!contentUri.scheme.isNullOrEmpty() && contentUri.scheme.equals("content", true)) {
                val contentData = it.split("/")
                val fileName = contentData[contentData.size - 1]
                return "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).path}/$fileName"
            }
        }
        return null
    }

    private fun getRealPathFromURI(
        context: Context,
        contentUri: Uri
    ): String? {
        var cursor: Cursor? = null
        return try {
            val proj =
                arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null)
            val column_index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor?.moveToFirst()
            column_index?.let { cursor?.getString(it) }
        } catch (e: Exception) {
            Log.e("test", "getRealPathFromURI Exception : $e")
            ""
        } finally {
            cursor?.close()
        }
    }


    @SuppressLint("Recycle")
    fun getImageRealPath(
        contentResolver: ContentResolver,
        uri: Uri?,
        whereClause: String?
    ): String {
        var ret = ""

        uri?.let {
            // Query the uri with condition.
            val cursor: Cursor? = contentResolver.query(uri, null, whereClause, null, null)

            if (cursor != null) {
                val moveToFirst = cursor.moveToFirst()
                if (moveToFirst) {

                    // Get columns name by uri type.
                    var columnName: String = MediaStore.Images.Media.DATA

                    when (uri) {
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI -> columnName =
                            MediaStore.Images.Media.DATA
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI -> columnName =
                            MediaStore.Audio.Media.DATA
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI -> columnName =
                            MediaStore.Video.Media.DATA
                    }

                    // Get column index.
                    var imageColumnIndex: Int = cursor.getColumnIndex(columnName)

                    // Get column value which is the uri related file local path.
                    ret = cursor.getString(imageColumnIndex)
                }
            }
        }

        return ret
    }

    private fun fileExt(url: String): String? {
        var myUrl = url
        if (myUrl.indexOf("?") > -1) {
            myUrl = myUrl.substring(0, myUrl.indexOf("?"))
        }
        return if (myUrl.lastIndexOf(".") == -1) {
            null
        } else {
            var ext = myUrl.substring(myUrl.lastIndexOf(".") + 1)
            if (ext.indexOf("%") > -1) {
                ext = ext.substring(0, ext.indexOf("%"))
            }
            if (ext.indexOf("/") > -1) {
                ext = ext.substring(0, ext.indexOf("/"))
            }
            ext.toLowerCase()

        }
    }

}