package sg.edu.rp.c346.id20045435.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    EditText etFilter;
    Button btnFilter;
    ListView lv;
    CustomAdapter ca;
    ArrayList<List> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etFilter = findViewById(R.id.etKeyword);
        btnFilter = findViewById(R.id.btnFilter);
        lv = findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(this);
        al = new ArrayList<List>();
        al.addAll(dbh.getLists());
        ca = new CustomAdapter(SecondActivity.this, R.layout.row, al);
        lv.setAdapter(ca);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);

                al.clear();
                al.addAll(dbh.getLists(etFilter.getText().toString()));
                ca.notifyDataSetChanged();
            }
        });
    }
}