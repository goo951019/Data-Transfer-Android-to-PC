package posconnect.jerry.androidcilientpos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send_data(View v){
        et2 = (EditText) findViewById(R.id.editText2);
        String message = et2.getText().toString();

        BackgroundTask b1 = new BackgroundTask();
        b1.execute(message);
    }

    class BackgroundTask extends AsyncTask<String,Void,Void>{
        private Socket s;
        private PrintWriter writer;
        private EditText ip;
        private InetAddress serverAddr;

        @Override
        protected Void doInBackground(String... voids){
            ip = (EditText) findViewById(R.id.editText);
            try {
                String message = voids[0];
                s = new Socket(String.valueOf(ip.getText()), 6000);
                //s = new Socket("192.168.100.76", 6000);
                writer = new PrintWriter(s.getOutputStream());
                writer.write(message);
                writer.flush();
                writer.close();

                System.out.println("sent");
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
