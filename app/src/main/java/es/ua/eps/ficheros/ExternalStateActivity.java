package es.ua.eps.ficheros;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExternalStateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_state);

        TextView tvEstado = findViewById(R.id.tvEstado);
        String state = Environment.getExternalStorageState();

        StringBuilder sb = new StringBuilder();

        sb.append("MEDIA_MOUNTED: ")
                .append(Environment.MEDIA_MOUNTED.equals(state)).append("\n");
        sb.append("MEDIA_MOUNTED_READ_ONLY: ")
                .append(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)).append("\n");
        sb.append("MEDIA_REMOVED: ")
                .append(Environment.MEDIA_REMOVED.equals(state)).append("\n");
        sb.append("MEDIA_SHARED: ")
                .append(Environment.MEDIA_SHARED.equals(state)).append("\n");
        sb.append("MEDIA_UNMOUNTED: ")
                .append(Environment.MEDIA_UNMOUNTED.equals(state)).append("\n");

        tvEstado.setText(sb.toString());
    }

    public void volver(View v) {
        finish();
    }
}
