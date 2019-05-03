package cu.cdac.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Utilidades los el uso de recursos del dispositivo.
 * <p>
 * <br>LogShare created by JESUS DANIEL on 02/01/2019.
 */

public final class ResUtil {

    /**
     * Bandera para la petición a la cámara del dispositivo.
     */
    private static final int REQUEST_CAMERA_FLAG = 200;

    /**
     * Bandera para la petición almacenamiento esxterno del dispositivo.
     */
    private static final int REQUEST_WRITE_EXT_STORAGE_FLAG = 202;

    /**
     * Capturar los resultados de las actvidades de las distintas peticiones.
     * @param context     Contexto del creador de la petición.
     * @param requestCode Código de la petición.
     * @param resultCode  Código del estado de la petición. {@link android.app.Activity#RESULT_OK RESULT_OK} para operación con éxito.
     * @param data        Resultado de la peticón.
     * @return Recurso solicitado o null si no se pudo resolver la petición.
     * @throws IOException
     */
    public static Bitmap getActivityResults(@NonNull Context context, int requestCode, int resultCode, @NonNull Intent data) throws IOException {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA_FLAG) {
                final Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                final File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpeg");
                destination.createNewFile();
                final FileOutputStream fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
                return bitmap;

            } else if (requestCode == REQUEST_WRITE_EXT_STORAGE_FLAG) {
                final Uri selectedImageUri = data.getData();
                final String[] projection = {MediaStore.MediaColumns.DATA};
                final CursorLoader cursorLoader = new CursorLoader(context, selectedImageUri, projection, null, null, null);
                final Cursor cursor = cursorLoader.loadInBackground();
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                final String selectedImagePath = cursor.getString(columnIndex);
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 256;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeFile(selectedImagePath, options);
            }
        }
        return null;
    }

    /**
     * Captura los permisos otorgados.
     * @param activity     {@link android.app.Activity} que realizó la solicitud.
     * @param requestCode  Código de la petición.
     * @param grantResults Permisos que se dieron.
     */
    public static void getPermissionRequestResult(@NonNull Activity activity, int requestCode, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_FLAG: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activity.startActivityForResult(intent, REQUEST_CAMERA_FLAG);
                }
                return;
            }
            case REQUEST_WRITE_EXT_STORAGE_FLAG: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    final Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    activity.startActivityForResult(
                            Intent.createChooser(intent, activity.getString(R.string.select_file)),
                            REQUEST_WRITE_EXT_STORAGE_FLAG);
                }
            }
        }
    }

    /**
     * Comienza el proceso para la solicitud a la cámara.
     * @param activity {@link android.app.Activity} que hace la solicitud.
     */
    public static void beginCameraCapture(Activity activity) {
        requestDialogPermissions(activity, android.Manifest.permission.CAMERA, REQUEST_CAMERA_FLAG);
    }

    /**
     * Comienza el proceso para la solicitud a la galería.
     * @param activity {@link android.app.Activity} que hace la solicitud.
     */
    public static void beginGalleryChooser(Activity activity) {
        requestDialogPermissions(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_WRITE_EXT_STORAGE_FLAG);
    }

    /**
     * Muestra un {@link android.support.v7.app.AlertDialog} para pedir el permiso.
     * @param activity           {@link android.app.Activity} que hace la solicitud.
     * @param manifestPermission Permiso desde el Manifiesto de la app.
     * @param idPermission       ID del permiso o bandera para identificarlo.
     */
    private static void requestDialogPermissions(Activity activity, String manifestPermission, int idPermission) {
        ActivityCompat.requestPermissions(activity, new String[]{manifestPermission}, idPermission);
    }
}
