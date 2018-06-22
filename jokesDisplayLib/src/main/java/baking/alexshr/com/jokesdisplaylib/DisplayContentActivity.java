package baking.alexshr.com.jokesdisplaylib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayContentActivity extends AppCompatActivity {

    public static final String CONTENT_KEY = "contentKey";
    private static final String NO_CONTENT ="No content to display" ;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String content=getIntent().getStringExtra(CONTENT_KEY);
        content=content!=null?content:NO_CONTENT;
        //Toolbar toolbar = findViewById(R.id.appbar);
        //setSupportActionBar(toolbar);

        textView=findViewById(R.id.text);
        textView.setText(content);
    }
}
