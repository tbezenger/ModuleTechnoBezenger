package fr.univtln.tbezenger858.animaux.mesanimaux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import fr.univtln.tbezenger858.Animaux.Animal;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chat;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chien;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Aigle;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Oiseau;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Pigeon;
import fr.univtln.tbezenger858.animaux.mesanimaux.Utils.JsonDecoder;
import fr.univtln.tbezenger858.animaux.mesanimaux.Utils.RequetesRest;

/**
 * Created by tomy- on 02/04/2017.
 */
public class EditAnimalActivity extends AppCompatActivity {
    private final static int CHAT = 1;
    private final static int CHIEN = 2;
    private final static int AIGLE = 3;
    private final static int PIGEON = 4;

    private EditText idEditText;

    private TextView att1;
    private EditText att1Edit;

    private TextView att2;
    private EditText att2Edit;

    private Intent intent;

    Animal currentAnimal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_animal);

        idEditText = (EditText)findViewById(R.id.idTextEdit);
        att1 = (TextView)findViewById(R.id.att1);
        att1Edit = (EditText)findViewById(R.id.att1Edit);
        att2 = (TextView)findViewById(R.id.att2);
        att2Edit = (EditText)findViewById(R.id.att2Edit);


        intent = getIntent();
        if (intent.getBooleanExtra("EDIT_BOOL",false))
            idEditText.setEnabled(false);


        if (intent.getIntExtra("ANIMAL_TYPE",-1) == CHAT){
            if (intent.getBooleanExtra("EDIT_BOOL",false)){
                currentAnimal = MainActivity.selectedMammifere;
                idEditText.setText(Integer.toString(currentAnimal.getId()));
                att1Edit.setText(Integer.toString(((Chat)currentAnimal).getNbMoustaches()));
            }
            att1.setText("NbMoustaches :");
            att2.setVisibility(View.GONE);
            att2Edit.setVisibility(View.GONE);

        }
        else if (intent.getIntExtra("ANIMAL_TYPE",-1)==CHIEN){
            if (intent.getBooleanExtra("EDIT_BOOL",false)){
                currentAnimal = MainActivity.selectedMammifere;
                idEditText.setText(Integer.toString(currentAnimal.getId()));
                att1Edit.setText(Integer.toString(((Chien)currentAnimal).getNbPattes()));
            }
            att1.setText("NbPattes :");
            att2.setVisibility(View.GONE);
            att2Edit.setVisibility(View.GONE);
        }

        else if (intent.getIntExtra("ANIMAL_TYPE",-1)==AIGLE){
            if (intent.getBooleanExtra("EDIT_BOOL",false)){
                currentAnimal = MainActivity.selectedOiseau;
                idEditText.setText(Integer.toString((currentAnimal.getId())));
                att1Edit.setText(Integer.toString(((Aigle)currentAnimal).getNbSerres()));
                att2Edit.setText(Integer.toString(((Aigle)currentAnimal).getCible().getId()));
            }
            att1.setText("NbSerres :");
            att2.setText("Id pigeon cible :");
        }

        else if (intent.getIntExtra("ANIMAL_TYPE",-1)==PIGEON){
            if (intent.getBooleanExtra("EDIT_BOOL",false)){
                currentAnimal = MainActivity.selectedOiseau;
                idEditText.setText(Integer.toString(currentAnimal.getId()));
                att1Edit.setText(Integer.toString(((Pigeon)currentAnimal).getNbPlumes()));
            }
            att1.setText("NbPlumes :");
            att2.setVisibility(View.GONE);
            att2Edit.setVisibility(View.GONE);
        }

    }

    public void validation(View view) throws InterruptedException, ExecutionException, IOException, JSONException {
        switch (intent.getIntExtra("ANIMAL_TYPE",-1)){
            case CHAT:
                if (currentAnimal == null)
                    currentAnimal = new Chat(Integer.parseInt(idEditText.getText().toString()),
                                             Integer.parseInt(att1Edit.getText().toString()));
                else
                    ((Chat)currentAnimal).setNbMoustaches(Integer.parseInt(att1Edit.getText().toString()));
                break;

            case CHIEN:
                if (currentAnimal == null)
                    currentAnimal = new Chien(Integer.parseInt(idEditText.getText().toString()),
                                              Integer.parseInt(att1Edit.getText().toString()));
                else
                    ((Chien)currentAnimal).setNbPattes(Integer.parseInt(att1Edit.getText().toString()));
                break;

            case PIGEON:
                if (currentAnimal == null)
                    currentAnimal = new Pigeon(Integer.parseInt(att1Edit.getText().toString()));
                else
                    ((Pigeon)currentAnimal).setNbPlumes(Integer.parseInt(att1Edit.getText().toString()));
                break;

            case AIGLE:
                Pigeon cible = null;
                if (att2Edit.getText()!=null){
                    try {
                        cible = new JsonDecoder<Pigeon>().DecoderList(RequetesRest
                                .get("/oiseaux/" + att2Edit.getText().toString()), Pigeon.class).get(0);
                    } catch (Exception e){
                        Toast.makeText(this,
                                "Pigeon d'Id "+att2Edit.getText().toString()+" introuvable.",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                if (currentAnimal == null)
                    currentAnimal = new Aigle(Integer.parseInt(idEditText.getText().toString()));
                else
                    ((Aigle)currentAnimal).setNbSerres(Integer.parseInt(att1Edit.getText().toString()));
                ((Aigle)currentAnimal).setCible(cible);
                break;
        }

        if(intent.getBooleanExtra("EDIT_BOOL",false))
            RequetesRest.put_post(currentAnimal,"POST");
        else
            RequetesRest.put_post(currentAnimal,"PUT");
        startActivity(new Intent(this,MainActivity.class));
    }
}
