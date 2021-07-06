package com.moinuddinmulla100gmail.encrypter;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn,encrypt;
    String path;
    TextView txt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        btn = findViewById(R.id.btn);
        encrypt=findViewById(R.id.encrypt);
        ActivityResultLauncher<Intent> someactivity=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()==Activity.RESULT_OK){
                            Intent data=result.getData();
                            path=data.getData().getPath();
                            txt.setText(path);
                        }
                    }
                }

        );

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("*/*");
                someactivity.launch(i);
            }
        });

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                En ab=new En();
                try {
                    ab.encryp(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"Successfully Encrypt/Decrypt",Toast.LENGTH_LONG).show();
            }
        });

    }
    public static class En {

            int key=1;
            String s;
            public void encryp(String path) throws FileNotFoundException, IOException
            {
            // Selecting a Image for operation
                FileInputStream fis = new FileInputStream(path);
                /*
             Converting Image into byte array, create a
             array of same size as Image size
            */

                byte data[] = new byte[fis.available()];

            // Read the array
            fis.read(data);
            int i = 0;

            // Performing an XOR operation on each value of
            // byte array due to which every value of Image
            // will change.
            for (byte b : data) {
                data[i] = (byte) (b ^ key);
                i++;
            }

            // Opening a file for writing purpose
            FileOutputStream fos = new FileOutputStream(path);

            // Writing new byte array value to image which
            // will Encrypt it.

            fos.write(data);

            // Closing file
            fos.close();
            fis.close();
        }



    }

}