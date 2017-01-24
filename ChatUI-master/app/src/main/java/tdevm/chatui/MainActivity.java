package tdevm.chatui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;


public class MainActivity extends AppCompatActivity {

    EmojiconEditText emojiconEditText;
    ImageView myBtn;
    View rootView;

    ImageView send;
    AdapterClass mAdapter;
    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emojiconEditText = (EmojiconEditText) findViewById(R.id.editTextMessage);
        myBtn = (ImageView) findViewById(R.id.buttonEmoji);
        setTitle("Chat app copy");
        rootView = findViewById(R.id.root_view);

        send = (ImageView) findViewById(R.id.buttonMessage);
        mList = (ListView) findViewById(R.id.listView);
        mAdapter = new AdapterClass(this, new ArrayList<TestModel>());
        mList.setAdapter(mAdapter);
        emojiconEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                }
            }
        });


        final EmojIconActions emojIcon = new EmojIconActions(this, rootView, emojiconEditText, myBtn);
        emojIcon.ShowEmojIcon();
        emojIcon.setKeyboardListener(new EmojIconActions.KeyboardListener() {
            @Override
            public void onKeyboardOpen() {
                Log.e("Keyboard", "open");
            }

            @Override
            public void onKeyboardClose() {
                Log.e("Keyboard", "close");
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = emojiconEditText.getText().toString();
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                sendMessage(message);
                emojiconEditText.setText("");
            }
        });
        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mI = getMenuInflater();
        mI.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()){
          case R.id.action_settings:
              Intent i = new Intent(this,SettingsActivity.class);
              startActivity(i);
              break;
      }

        return super.onOptionsItemSelected(item);
    }

    private void sendMessage(String message) {
        TestModel chatMessage = new TestModel(message, true, false);
        mAdapter.add(chatMessage);

        mimicOtherMessage(message);
    }

    private void mimicOtherMessage(String message) {
        TestModel chatMessage = new TestModel(message, false, false);
        mAdapter.add(chatMessage);
    }

    private void sendMessage() {
        TestModel chatMessage = new TestModel(null, true, true);
        mAdapter.add(chatMessage);

        mimicOtherMessage();
    }

    private void mimicOtherMessage() {
        TestModel chatMessage = new TestModel(null, false, true);
        mAdapter.add(chatMessage);
    }


}
