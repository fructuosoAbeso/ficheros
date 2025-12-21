package es.ua.eps.ficheros;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "texto.txt";

    // Constantes de ubicación
    private static final int INTERNO = 0;
    private static final int EXTERNO = 1;

    private int ubicacionActual = INTERNO;

    private EditText etTexto;
    private Button btnMoverExterno, btnMoverInterno;

    private File internalFile;
    private File externalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTexto = findViewById(R.id.etTexto);
        btnMoverExterno = findViewById(R.id.btnMoverExterno);
        btnMoverInterno = findViewById(R.id.btnMoverInterno);

        internalFile = new File(getFilesDir(), FILE_NAME);
        externalFile = new File(getExternalFilesDir(null), FILE_NAME);

        // Detectar ubicación inicial
        if (externalFile.exists()) {
            ubicacionActual = EXTERNO;
        }

        updateButtons();

        findViewById(R.id.btnGuardar).setOnClickListener(v -> guardarTexto());

        findViewById(R.id.btnVerContenido).setOnClickListener(v ->
                startActivity(new Intent(this, FileContentActivity.class)));

        findViewById(R.id.btnEstado).setOnClickListener(v ->
                startActivity(new Intent(this, ExternalStateActivity.class)));

        // 👉 DESTINO
        btnMoverExterno.setOnClickListener(v -> {
            ubicacionActual = EXTERNO;
            updateButtons();
        });

        btnMoverInterno.setOnClickListener(v -> {
            ubicacionActual = INTERNO;
            updateButtons();
        });

        findViewById(R.id.btnCerrar).setOnClickListener(v -> finish());
    }

    private void guardarTexto() {
        String texto = etTexto.getText().toString();
        if (texto.isEmpty()) return;

        File destino = (ubicacionActual == EXTERNO) ? externalFile : internalFile;
        File origen  = (ubicacionActual == EXTERNO) ? internalFile : externalFile;

        try {
            // Mover el fichero si está en la otra ubicación
            if (origen.exists()) {
                copyFile(origen, destino);
                origen.delete();
            }

            // Guardar texto
            try (FileOutputStream fos = new FileOutputStream(destino, true)) {
                fos.write((texto + "\n").getBytes());
            }

            etTexto.setText("");
            updateButtons();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile(File origen, File destino) throws IOException {
        try (FileInputStream in = new FileInputStream(origen);
             FileOutputStream out = new FileOutputStream(destino)) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        }
    }

    private void updateButtons() {
        btnMoverExterno.setEnabled(ubicacionActual == INTERNO);
        btnMoverInterno.setEnabled(ubicacionActual == EXTERNO);
    }
}
