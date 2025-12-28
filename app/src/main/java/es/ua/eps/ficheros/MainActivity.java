package es.ua.eps.ficheros;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "texto.txt";

    private EditText etTexto;
    private Button btnMoverExterno, btnMoverInterno;
    private Button btnGuardar, btnVerContenido, btnEstado, btnCerrar;

    private File internalFile;
    private File externalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // EditText y botones
        etTexto = findViewById(R.id.etTexto);
        btnMoverExterno = findViewById(R.id.btnMoverExterno);
        btnMoverInterno = findViewById(R.id.btnMoverInterno);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVerContenido = findViewById(R.id.btnVerContenido);
        btnEstado = findViewById(R.id.btnEstado);
        btnCerrar = findViewById(R.id.btnCerrar);

        // Archivos internos y externos
        internalFile = new File(getFilesDir(), FILE_NAME);
        externalFile = new File(getExternalFilesDir(null), FILE_NAME);

        // Inicializa botones según ubicación de fichero
        updateButtons();

        // Guardar texto
        btnGuardar.setOnClickListener(v -> guardarTexto());

        // Ver contenido en nueva Activity
        btnVerContenido.setOnClickListener(v ->
                startActivity(new Intent(this, FileContentActivity.class)));

        // Estado memoria externa
        btnEstado.setOnClickListener(v ->
                startActivity(new Intent(this, ExternalStateActivity.class)));

        // Mover a memoria externa
        btnMoverExterno.setOnClickListener(v -> {
            if (internalFile.exists()) {
                moveFile(internalFile, externalFile);
                updateButtons();
            }
        });

        // Mover a almacenamiento interno
        btnMoverInterno.setOnClickListener(v -> {
            if (externalFile.exists()) {
                moveFile(externalFile, internalFile);
                updateButtons();
            }
        });

        // Cerrar aplicación
        btnCerrar.setOnClickListener(v -> finishAffinity());
    }

    private void guardarTexto() {
        String texto = etTexto.getText().toString().trim();
        if (texto.isEmpty()) return;

        File destino = getCurrentFile();

        try (FileOutputStream fos = new FileOutputStream(destino, true)) {
            fos.write((texto + "\n").getBytes());
            etTexto.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateButtons();
    }

    private File getCurrentFile() {
        if (externalFile.exists()) return externalFile;
        return internalFile;
    }

    private void moveFile(File origen, File destino) {
        try (FileInputStream in = new FileInputStream(origen);
             FileOutputStream out = new FileOutputStream(destino)) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            origen.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateButtons() {
        btnMoverExterno.setEnabled(internalFile.exists());
        btnMoverInterno.setEnabled(externalFile.exists());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtons();
    }
}
