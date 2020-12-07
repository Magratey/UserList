package com.romandurran.userlist;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.romandurran.userlist.database.UserDbSchema;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public User getUser(){
        String uuidString = getString(getColumnIndex(UserDbSchema.UserTable.Cols.UUID));
        String userName = getString(getColumnIndex(UserDbSchema.UserTable.Cols.USERNAME));
        String userLastname = getString(getColumnIndex(UserDbSchema.UserTable.Cols.USERLASTNAME));

        User user = new User(UUID.fromString(uuidString));
        user.setUserName(userName);
        user.setUserLastName(userLastname);
        return user;
    }
}
