package com.techsofficial.prithivi.barcodereader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;
import java.util.Map;

public class barcode_genrator extends AppCompatActivity {
    EditText code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode_gerator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        code = (EditText)findViewById(R.id.editText2);
        FloatingActionButton bar =(FloatingActionButton)findViewById(R.id.floatingActionButton);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*
                LinearLayout l = (LinearLayout)findViewById(R.id.fra) ;
                setContentView(l);*/


                // barcode data
                String barcode_data = code.getText().toString();;

        /*        String data = "siva";
                 String tr =""+barcode_data+data;*/

                // barcode image
                Bitmap bitmap = null;
                ImageView iv = (ImageView)findViewById(R.id.imageView);
                CardView s = (CardView)findViewById(R.id.cardview);
                s.setVisibility(View.VISIBLE);

                try {

                    bitmap = encodeAsBitmap(barcode_data, BarcodeFormat.CODE_128, 500, 300);
                    iv.setImageBitmap(bitmap);


                } catch (WriterException e) {
                    e.printStackTrace();
                }

              /*  l.addView(iv);*/

                //barcode text
                TextView tv = (TextView)findViewById(R.id.barcodename);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setText(barcode_data);

                /*l.addView(tv);*/

            }

            /**************************************************************
             * getting from com.google.zxing.client.android.encode.QRCodeEncoder
             *
             * See the sites below
             * http://code.google.com/p/zxing/
             * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/EncodeActivity.java
             * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/QRCodeEncoder.java
             */

            private static final int WHITE = 0xFFFFFFFF;
            private static final int BLACK = 0xFF000000;

            Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
                String contentsToEncode = contents;
                if (contentsToEncode == null) {
                    return null;
                }
                Map<EncodeHintType, Object> hints = null;
                String encoding = guessAppropriateEncoding(contentsToEncode);
                if (encoding != null) {
                    // hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
                    hints.put(EncodeHintType.CHARACTER_SET, encoding);
                }
                MultiFormatWriter writer = new MultiFormatWriter();
                BitMatrix result;
                try {

                    result = writer.encode(contentsToEncode, format, img_width, img_height, (Hashtable) hints);
                } catch (IllegalArgumentException iae) {
                    // Unsupported format
                    return null;
                }
                int width = result.getWidth();
                int height = result.getHeight();
                int[] pixels = new int[width * height];
                for (int y = 0; y < height; y++) {
                    int offset = y * width;
                    for (int x = 0; x < width; x++) {
                        pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
                    }
                }

                Bitmap bitmap = Bitmap.createBitmap(width, height,
                        Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
                return bitmap;
            }



            private String guessAppropriateEncoding(CharSequence contents) {
                // Very crude at the moment
                for (int i = 0; i < contents.length(); i++) {
                    if (contents.charAt(i) > 0xFF) {
                        return "UTF-8";
                    }
                }
                return null;

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int  res = item.getItemId();
        if(res == R.id.barcodegenerator)
        {
            Intent i  = new Intent(getApplicationContext(),barcode_genrator.class);
            startActivity(i);
        }
      if(res ==R.id.Reader)
        {
            Intent i  = new Intent(getApplicationContext(),barcodereaders.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
