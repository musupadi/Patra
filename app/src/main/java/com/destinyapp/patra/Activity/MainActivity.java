package com.destinyapp.patra.Activity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;


import com.destinyapp.patra.Activity.ui.project.ProjectFragment;
import com.destinyapp.patra.Model.Method;
import com.destinyapp.patra.R;
import com.destinyapp.patra.SharedPreferance.DB_Helper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {
    DB_Helper dbHelper = new DB_Helper(MainActivity.this);
    String uuid,id,email,username,name,avatar;
    private AppBarConfiguration mAppBarConfiguration;
    TextView navUsername,navName;
    ImageView navImage;
    Method method = new Method();
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (EasyPermissions.hasPermissions(MainActivity.this, galleryPermissions)) {
            Toast.makeText(this, "Permission Succes", Toast.LENGTH_SHORT).show();
        } else {
            EasyPermissions.requestPermissions(MainActivity.this, "Access for storage",
                    101, galleryPermissions);
        }
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_project, R.id.nav_marketing, R.id.nav_site_supervisor,
                R.id.nav_depot, R.id.nav_produk, R.id.nav_rekanan,R.id.nav_tarif)
                .setDrawerLayout(drawer)
                .build();

        View headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.nav_username);
        navName = (TextView)headerView.findViewById(R.id.nav_name);
        navImage = headerView.findViewById(R.id.nav_image);
        Cursor cursor = dbHelper.checkSession();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                uuid = cursor.getString(0);
                id = cursor.getString(1);
                email = cursor.getString(2);
                username = cursor.getString(3);
                name = cursor.getString(4);
                avatar = cursor.getString(5);
            }
        }
        navUsername.setText(email);
        navName.setText(name);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AccountActivity.class);
                startActivity(intent);
            }
        });

        Intent data = getIntent();
        String NAVIGATE = data.getStringExtra("NAVIGATE");
        navController.navigate(Integer.parseInt(NAVIGATE));
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            method.logout(MainActivity.this);
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finishAffinity();
            //ClickListener();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
