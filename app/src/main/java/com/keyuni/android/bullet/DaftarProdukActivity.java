package com.keyuni.android.bullet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


import com.keyuni.android.bullet.Fragment.BountyFragment;
import com.keyuni.android.bullet.Fragment.ChatFragment;
import com.keyuni.android.bullet.Fragment.ChequeFragment;
import com.keyuni.android.bullet.Fragment.ProfilFragment;
import com.keyuni.android.bullet.Fragment.ShopFragment;
import com.keyuni.android.bullet.Helper.ProdukAdapter;
import com.keyuni.android.bullet.db.DbProduk;
import com.keyuni.android.bullet.model.Produk;

import java.util.ArrayList;

public class DaftarProdukActivity extends AppCompatActivity {

    private Button btnTambahProduk;
    private RecyclerView rvDaftarProduk;
    private DbProduk dbProduk;
    private ArrayList<Produk> produks;

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private ProfilFragment profilFragment;
    private ChatFragment chatFragment;
    private ChequeFragment chequeFragment;
    private BountyFragment bountyFragment;
    private ShopFragment shopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_produk);

        dbProduk = new DbProduk(this);
        dbProduk.open();

        produks = dbProduk.getAllProduk();

        rvDaftarProduk = findViewById(R.id.rvDaftarProduk);
        rvDaftarProduk.setAdapter(new ProdukAdapter(this, produks));
        rvDaftarProduk.setHasFixedSize(true);
        rvDaftarProduk.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        btnTambahProduk = findViewById(R.id.btnTambahProduk);

        btnTambahProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddProduk = new Intent(getBaseContext(), TambahProdukActivity.class);
                startActivity(intentAddProduk);
            }

        });
        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.bottomNavView);

        profilFragment = new ProfilFragment();
        chatFragment = new ChatFragment();
        shopFragment = new ShopFragment();
        bountyFragment = new BountyFragment();
        chequeFragment = new ChequeFragment();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_user:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(profilFragment);
                        return true;

                    case R.id.nav_chat:
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(chatFragment);
                        return true;

                    case R.id.nav_shop:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(shopFragment);
                        return true;

                    case R.id.nav_bounty:
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(bountyFragment);
                        return true;

                    case R.id.nav_cheque:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(chequeFragment);
                        return true;

                    default:
                        return false;
                }

            }
        });
    }

    private void setFragment(android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

}
