package com.example.activity.function;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.app.EXAPP;
import com.example.greendao.entity.User;
import com.example.greendao.gen.UserDao;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDAOActivity extends Activity {

    @Bind(R.id.res)
    TextView res;
//    @Bind(R.id.add)
//    Button add;
//    @Bind(R.id.del)
//    Button del;
//    @Bind(R.id.update)
//    Button update;
//    @Bind(R.id.query)
//    Button query;

    private UserDao mUserDao;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        mUserDao = EXAPP.getInstance().getDaoSession().getUserDao();
    }
    @OnClick(R.id.add)
    public void add(){
        mUser=new User(1L,"ly");
        mUserDao.insert(mUser);
        res.setText("user "+mUser.getName()+" inserted");
    }
    @OnClick(R.id.del)
    public void del(){
        mUserDao.delete(mUser);
        res.setText("user "+mUser.getName()+" deleted");
    }
    @OnClick(R.id.update)
    public void update(){
        mUser.setName("lys");
        mUserDao.update(mUser);
        res.setText("user "+mUser.getName()+" update");
    }
    @OnClick(R.id.query)
    public void query(){
        User user=mUserDao.load(1L);
        res.setText("user "+user.getName()+" query");
    }
}
