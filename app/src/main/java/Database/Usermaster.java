package Database;

import android.provider.BaseColumns;

public class Usermaster {

    private Usermaster() {}

    public static class User implements BaseColumns {
        public static final String USER_TABLE_NAME = "User";
        public static final String USER_NAME = "Name";
        public static final String USER_PASSWORD = "Password";
        public static final String USER_TYPE = "Type";
    }

    public static class Message implements BaseColumns {
        public static final String MSG_TABLE_NAME = "Message";
        public static final String USER = "User";
        public static final String SUBJECT = "Subject";
        public static final String MESSAGE = "Message";
    }

}
