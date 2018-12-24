package com.example.androcaner.sqliteloginapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class giris_activity extends AppCompatActivity {

    EditText etgiris1, etgiris2;
    Button giristusu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_activity);

        etgiris1=findViewById(R.id.kullanıcıadı);
        etgiris2=findViewById(R.id.sifre);
        giristusu=findViewById(R.id.giriştuşu);

        DataBaseHelper dataBaseHelper=new DataBaseHelper(this,"Kayıt",null,1);
        final SQLiteDatabase database=dataBaseHelper.getReadableDatabase();



        final String[] sütunlar={"kullanıcı_adı","sifre"};
        final String[] degerler={etgiris1.getText().toString(),etgiris1.getText().toString()};



        giristusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=database.query("Kayıt_table", sütunlar, "kullanıcı_adı = ? AND sifre = ?", degerler,null,null,null);
                if (cursor!=null){
                    if (cursor.moveToFirst()){
                        Toast.makeText(getApplicationContext(),"Giriş başarılı...",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Yanlış şifre veya kullanıcı adı...",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });





    }

}
