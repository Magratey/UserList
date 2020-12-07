package com.romandurran.userlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;




public class AddUserFragment extends Fragment {
    private Button addUserBtn;
    private EditText nameEditText;
    private EditText lastnameEditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_user,container,false);

        nameEditText = view.findViewById(R.id.nameEditText);
        lastnameEditText = view.findViewById(R.id.lastnameEditText);
        addUserBtn = view.findViewById(R.id.addUserBtn);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = nameEditText.getText().toString();
                String userLastname = lastnameEditText.getText().toString();
                User user = new User();
                user.setUserName(userName);
                user.setUserLastName(userLastname);
                UserList userList = UserList.get(getActivity());
                userList.addUser(user);

                FragmentActivity activity = (FragmentActivity) view.getContext();
                // Создаём менеджер фрагментов
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                // создаём фрагмент
                Fragment fragment = new UserListFragment();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
            }
        });
        return view;
    }
}