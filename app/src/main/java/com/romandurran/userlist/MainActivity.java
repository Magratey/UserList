package com.romandurran.userlist;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    Fragment fragment = new UserListFragment();
    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // R.id.fragmentContainer - это FrameLayout из файла activity_main.xml
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        this.fragmentManager.beginTransaction().replace(R.id.fragmentContainer, this.fragment).commit();
    }
    public static void changeFragment(View view, User user){
        // Получаем хостинговую активность (в нашем случае MainActivity)
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Создаём менеджер фрагментов
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        // создаём фрагмент
        Fragment fragment = new UserFragment();
        // Создаём bundle (это как коллекция)
        Bundle bundle = new Bundle();
        // Записываем user в bundle для передачи в фрагмент
        bundle.putSerializable("user", user);
        // Кладём Bundle в фрагмент
        fragment.setArguments(bundle);
        //Заменяем фрагмент
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}