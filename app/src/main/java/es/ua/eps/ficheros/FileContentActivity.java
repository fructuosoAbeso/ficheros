package es.ua.eps.ficheros;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileContentActivity extends AppCompatActivity {

    private static final String FILE_NAME = "texto.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_content);

        TextView tvContenido = findViewById(R.id.tvContenido);

        File internalFile = new File(getFilesDir(), FILE_NAME);
        File externalFile = new File(getExternalFilesDir(null), FILE_NAME);

        File file = externalFile.exists() ? externalFile : internalFile;

        tvContenido.setText(readFile(file));
    }

    private String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        if (!file.exists()) return "El fichero está vacío";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void volver(View v) {
        finish();
    }
}
