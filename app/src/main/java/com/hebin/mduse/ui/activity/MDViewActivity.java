package com.hebin.mduse.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hebin.mduse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MDViewActivity extends AppCompatActivity {
    @BindView(R.id.TilName)
    TextInputLayout TilName;
    @BindView(R.id.tilPwd)
    TextInputLayout tilPwd;
    @BindView(R.id.etName)
    EditText        etName;
    @BindView(R.id.etPwd)
    EditText        etPwd;
    @BindView(R.id.toolbar_title)
    TextView        toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar         toolbar;

    //    @BindView(R.id.bt1)
    //    Button   bt1;
    //    @BindView(R.id.bt2)
    //    Button   bt2;
    //    @BindView(R.id.curved)
    //    TextView curved;
    //    @BindView(R.id.state_anim)
    //    TextView stateAnim;
    //    @BindView(R.id.vector_anim)
    //    TextView vectorAnim;
    //    @BindView(R.id.transitions)
    //    Button   transitions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_textinputlayout);
        ButterKnife.bind(this);
        setEditText();
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setEditText() {
        TilName.setCounterEnabled(true);
        TilName.setCounterMaxLength(11);
        tilPwd.setCounterEnabled(true);
        tilPwd.setCounterMaxLength(20);
        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 20) {
                    tilPwd.setErrorEnabled(true);
                    tilPwd.setError("密码长度不能超过20位");
                } else {
                    tilPwd.setErrorEnabled(false);
                    tilPwd.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 11) {
                    TilName.setErrorEnabled(true);
                    TilName.setError("手机号长度不能超过11位");
                } else {
                    TilName.setErrorEnabled(false);
                    TilName.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
