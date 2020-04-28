package Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.courseapp.MainActivity;
import com.example.courseapp.Student;
import com.example.courseapp.Teacher;

import java.util.ArrayList;

import Model.Message;
import Model.User;


public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public DBHelper(Context context) {
        super(context, "MAD", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {

        String CREATE_TABLE_USER = "CREATE TABLE " + Usermaster.User.USER_TABLE_NAME + " (" +
                Usermaster.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Usermaster.User.USER_NAME + " TEXT," +
                Usermaster.User.USER_PASSWORD + " TEXT," +
                Usermaster.User.USER_TYPE + " TEXT )";

        String CREATE_TABLE_MESSAGE = "CREATE TABLE " + Usermaster.Message.MSG_TABLE_NAME + " (" +
                Usermaster.Message._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Usermaster.Message.USER + " TEXT," +
                Usermaster.Message.SUBJECT + " TEXT," +
                Usermaster.Message.MESSAGE + " TEXT )";

        Db.execSQL(CREATE_TABLE_USER);
        Db.execSQL(CREATE_TABLE_MESSAGE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addUser(User user) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Usermaster.User.USER_NAME,user.getName());
        values.put(Usermaster.User.USER_PASSWORD,user.getPassword());
        values.put(Usermaster.User.USER_TYPE,user.getType());

        long rows = db.insert(Usermaster.User.USER_TABLE_NAME,null,values);

        if(rows > 0){
            Toast.makeText(this.context,"New User Added Succesfully!",Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, MainActivity.class));
        }
        else{
            Toast.makeText(this.context,"New User Added Process Unsuccesfully!",Toast.LENGTH_SHORT).show();
        }
    }

    public void signIn(User user) {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                Usermaster.User.USER_NAME,
                Usermaster.User.USER_PASSWORD,
                Usermaster.User.USER_TYPE
        };

        Cursor cursor = db.query(
                Usermaster.User.USER_TABLE_NAME,
                projection,
                Usermaster.User.USER_NAME + " = ? AND " + Usermaster.User.USER_PASSWORD + " = ? " ,
                new String[] { user.getName() , user.getPassword() },
                null,
                null,
                null
        );

        if(cursor.getCount() == 0){
            Toast.makeText(context,"There is no user match with this creditional",Toast.LENGTH_SHORT).show();
        }
        else {
            User loginUser = new User();

            while (cursor.moveToNext()){
                loginUser.setName(cursor.getString(0));
                loginUser.setPassword(cursor.getString(1));
                loginUser.setType(cursor.getString(2));
            }

            if(loginUser.getType().equals("Student")){
                context.startActivity(new Intent(context,Student.class).putExtra("StudentName",loginUser.getName()));
            }
            else if(loginUser.getType().equals("Teacher")){
                context.startActivity(new Intent(context, Teacher.class).putExtra("TeacherName",loginUser.getName()));
            }

        }
    }

    public ArrayList<Message> readAllMsg() {

        ArrayList<Message> msgList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                Usermaster.Message.USER,
                Usermaster.Message.SUBJECT,
                Usermaster.Message.MESSAGE
        };

        String sortOrder = Usermaster.Message.SUBJECT + " DESC";

        Cursor cursor = db.query(
                Usermaster.Message.MSG_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while(cursor.moveToNext()){

            msgList.add( new Message(cursor.getString(0),cursor.getString(1),cursor.getString(2)));

        }

        return msgList;
    }

    public void addMessage(Message msg){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Usermaster.Message.USER , msg.getUser());
        values.put(Usermaster.Message.SUBJECT , msg.getSubject());
        values.put(Usermaster.Message.MESSAGE , msg.getMessage());

        long row = db.insert(Usermaster.Message.MSG_TABLE_NAME,null,values);

        if(row > 0){
            Toast.makeText(this.context,"New Message Added Succesfully!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this.context,"New Message Added Unsuccesfully!",Toast.LENGTH_SHORT).show();
        }

    }
}
