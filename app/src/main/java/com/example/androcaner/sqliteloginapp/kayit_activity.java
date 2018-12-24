package com.example.androcaner.sqliteloginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

public class kayit_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText et1,et2,et3;
    CheckBox cb1,cb2;
    Spinner spinner;
    Button kayıt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_activity);

        et1=findViewById(R.id.kullanıcıadıkayıt);
        et2=findViewById(R.id.sifrekayıt);
        et3=findViewById(R.id.email);


        cb1=findViewById(R.id.cinsiyet1);
        cb2=findViewById(R.id.cinsiyet2);

        spinner=findViewById(R.id.meslekler);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.calisma_durumu,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        kayıt=findViewById(R.id.kayıtbutton);


        String kullanıcıadı=et1.getText().toString();
        String sifre=et2.getText().toString();


        //final String emailkontrol=et3.getText().toString();




        DataBaseHelper dataBaseHelper=new DataBaseHelper(this,"Kayıt",null,1);
        final SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
        final ContentValues values=new ContentValues();
        values.put("kullanıcı_adı", kullanıcıadı );
        values.put("sifre", sifre);


        String cinsiyet="";

        if (cb1.isChecked()){
            cinsiyet=cb1.getText().toString();
        }
        else if (cb2.isChecked()){
            cinsiyet=cb2.getText().toString();

        }
        else if (cb1.isChecked() & cb2.isChecked()) {
            Toast.makeText(getApplicationContext(),"2 seçenek işaretlenemez...",Toast.LENGTH_LONG).show();
        }

        values.put("cinsiyet", cinsiyet);

        String spinnerstring=spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

        values.put("calisma_durumu", spinnerstring);







        kayıt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailonay=et3.getText().toString();
                if (EmailAdresKontrol(emailonay)){
                    values.put("email", emailonay);
                    db.insert("Kayıt_table",null,values);
                    Toast.makeText(getApplicationContext(),"Kayıt başarıyla tamamlandı, artık giriş yapabilirsiniz", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Geçersiz mail adresi..",Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String meslekspinner=parent.getItemAtPosition(position).toString();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(),"Lütfen çalışma durumunuzu seçiniz...", Toast.LENGTH_LONG).show();

    }


    //Mail adresinin doğruluğunu kontrol etmek için(Mail @ içermiyorsa vb...):

    public static boolean EmailAdresKontrol(String email){
        String ePattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        java.util.regex.Pattern p=java.util.regex.Pattern.compile(ePattern, Pattern.CASE_INSENSITIVE);
        java.util.regex.Matcher m=p.matcher(email);
        return m.matches();
    }

}
