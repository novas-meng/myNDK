package com.example.novas.myndk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    int width=0;
    int height=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        TextView textView=(TextView)this.findViewById(R.id.text);
        int array[]={1,2,3};
        NDK ndk=new NDK();
       // System.out.println(new NDK().getSum(array));
        Shape shape=new Shape(5,6);
        long m=System.currentTimeMillis();
        String text=new NDK().printObject(shape)+"";
        long n=System.currentTimeMillis();
        ndk.changeM(shape);

        textView.setText(ndk.getSum(array) + "");
        Button button=(Button)this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CActivity.class);
                startActivity(intent);
            }
        });
    }
    public int getCircular()
    {
        for(int i=0;i<100000;i++)
        {
            width=width+1;
        }
        for(int i=0;i<1000000;i++)
        {
            height=height+1;
        }
        return this.width*2+this.height*2;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
