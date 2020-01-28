package com.destinyapp.patra.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.destinyapp.patra.Activity.LoginActivity;
import com.destinyapp.patra.R;
import com.destinyapp.patra.SharedPreferance.DB_Helper;

import java.io.ByteArrayOutputStream;

import java.io.FileInputStream;
import java.util.*;

public class Method {
    public void logout(final Context ctx){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage("Anda Yakin ingin Logout ?")
                .setCancelable(false)
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ctx, LoginActivity.class);
                        DB_Helper dbHelper = new DB_Helper(ctx);
                        dbHelper.userLogout(ctx);
                        ctx.startActivity(intent);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                //Set your icon here
                .setTitle("Perhatian !!!")
                .setIcon(R.drawable.ic_close_black_24dp);
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void AutoLogout(Context ctx){
        Intent intent = new Intent(ctx, LoginActivity.class);
        DB_Helper dbHelper = new DB_Helper(ctx);
        dbHelper.userLogout(ctx);
        ctx.startActivity(intent);
    }

    public String EncodeBse64(String path){
        Bitmap bm = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }
//    public String EncodeBase64(final Bitmap bitmap,final TextView tv){
//        String encoded;
//        new AsyncTask<Void,Void,String>(){
//            @Override
//            protected String doInBackground(Void... voids) {
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
//                byte[] b = baos.toByteArray();
//
//                String encodeImage = Base64.encodeToString(b,Base64.DEFAULT);
//                return encodeImage;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                tv.setText(s);
//            }
//        }.execute();
//    }
    public void DecodeBase64(String s,ImageView img){
        byte[] decodeString = Base64.decode(s,Base64.DEFAULT);
        Bitmap decoded = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        img.setImageBitmap(decoded);
    }
}
