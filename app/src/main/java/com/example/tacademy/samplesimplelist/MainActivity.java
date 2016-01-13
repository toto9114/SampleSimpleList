package com.example.tacademy.samplesimplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView messageView;
    EditText inputView;
    ListView listView;
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageView = (TextView)findViewById(R.id.text_message);
        inputView = (EditText)findViewById(R.id.edit_input);
        listView = (ListView)findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String)listView.getItemAtPosition(position);
                messageView.setText(text);
            }
        });
        Button btn = (Button)findViewById(R.id.btn_add);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputView.getText().toString();
                if(!TextUtils.isEmpty(text)){
                    mAdapter.add(text);
                    inputView.setText("");
                    listView.smoothScrollToPosition(mAdapter.getCount() -1);
                }
            }
        });

        initData();
    }

    private void initData(){
        String [] items = getResources().getStringArray(R.array.items);
        for(String s: items){
            mAdapter.add(s);
        }
    }
}
