package com.romandurran.userlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserFragment extends Fragment {
    private User user;
    private TextView userName_userLastname_View;
    private EditText editName;
    private EditText editLastname;
    private Button updateBtn;
    private Button delUserBtn;
    private UserList userList;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user"); // Принимаем объект user
    }
    @Override
    //создаем вьюшку с полными данными нашего юзера
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        userList = UserList.get(getActivity());
        userName_userLastname_View = view.findViewById(R.id.userName_userLastname_View);
        editName = view.findViewById(R.id.editName);
        editLastname = view.findViewById(R.id.editLastname);
        updateBtn = view.findViewById(R.id.updateBtn);
        delUserBtn = view.findViewById(R.id.delUserBtn);
        final String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName();
        userName_userLastname_View.setText(userName);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //изменяем имя и фамилию юзера
            public void onClick(View view) {
                String newUserName = editName.getText().toString();
                String newUserLastname = editLastname.getText().toString();
                user.setUserName(newUserName);
                user.setUserLastName(newUserLastname);
                userList.updateUser(user);
                FragmentActivity activity = (FragmentActivity) view.getContext();
                // Создаём менеджер фрагментов
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                // создаём фрагмент
                Fragment fragment = new UserListFragment();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
            }
        });

        delUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //удаляем юзера
            public void onClick(View v) {

                userList.deleteUser(user);

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
