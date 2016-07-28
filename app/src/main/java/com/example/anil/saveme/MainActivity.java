package com.example.anil.saveme;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.anil.saveme.util.DividerItemDecoration;
import com.orm.SugarContext;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    boolean isPremisionGraded,isPremisionGradedLocation;
    final static String TAG= MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.init(this);
        getPermision();


    }


    public void fabAction(View view) {
        List<Book> books = Book.listAll(Book.class);
        EmptyRecyclerView recycler = (EmptyRecyclerView) findViewById(R.id.main_list);
        LinearLayoutManager  manger= new LinearLayoutManager(this);
        recycler.setLayoutManager(manger);
        View bottomSheet = findViewById( R.id.bottom_sheet );
        BottomSheetBehavior mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        BookRecyclerAdapter adapter = new BookRecyclerAdapter(books,this,mBottomSheetBehavior);
        recycler.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recycler.setAdapter(adapter);



    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 202: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isPremisionGraded = true;
                } else {

                }
                return;
            } case 201: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isPremisionGradedLocation = true;
                } else {

                }
                return;
            }
        }
    }

    public void getSimDetails() {
        if(isPremisionGraded) {
            String number = Utility.getNumberOfPhone(this);
            Toast.makeText(MainActivity.this, number != null ? number : "no number found", Toast.LENGTH_SHORT).show();
            Log.e(TAG,number+" "+number.length());
        }
    }

    public void getTowerInfo() {
        GsmCellLocation location =Utility.getLACCid(this);
        if(location!=null){
            String locationStr = "cid "+location.getCid()+" LAC"+location.getLac();
            location.getPsc();
            Toast.makeText(MainActivity.this, locationStr, Toast.LENGTH_SHORT).show();
            Log.e(TAG,locationStr);
        }
    }

    public void getPermision() {
        if (ContextCompat.checkSelfPermission(this,
                "android.permission.READ_PHONE_STATE")
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    "android.permission.READ_PHONE_STATE")) {
                isPremisionGradedLocation = true;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{"android.permission.READ_PHONE_STATE"},
                        202);
                for(int i =0;i<1000;i++) {
                    Book book = new Book("The world of Ice and Fire", "George R.R. Martin");
                    book.save();
                }
            }
        }else{
            isPremisionGradedLocation = true;
        }


        if (ContextCompat.checkSelfPermission(this,
                "android.permission.ACCESS_COARSE_LOCATION")
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    "android.permission.ACCESS_COARSE_LOCATION")) {
                isPremisionGraded = true;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{"android.permission.ACCESS_COARSE_LOCATION"},
                        201);
                for(int i =0;i<1000;i++) {
                    Book book = new Book("The world of Ice and Fire", "George R.R. Martin");
                    book.save();
                }
            }
        }else{
            isPremisionGraded = true;
        }
    }
}
