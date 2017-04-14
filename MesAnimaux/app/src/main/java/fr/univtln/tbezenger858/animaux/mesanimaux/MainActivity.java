package fr.univtln.tbezenger858.animaux.mesanimaux;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import fr.univtln.tbezenger858.Animaux.Animal;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chat;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chien;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Mammifere;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Aigle;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Oiseau;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Pigeon;
import fr.univtln.tbezenger858.animaux.mesanimaux.Utils.JsonDecoder;
import fr.univtln.tbezenger858.animaux.mesanimaux.Utils.RequetesRest;

public class MainActivity extends AppCompatActivity {
    private ListView listViewMammiferes;
    private ListView listViewOiseaux;
    private ArrayList<Mammifere> mammiferes;
    private ArrayAdapter<Mammifere> mammifereArrayAdapter;
    private ArrayList<Oiseau> oiseaux;
    private ArrayAdapter<Oiseau> oiseauArrayAdapter;
    public static Mammifere selectedMammifere;
    public static Oiseau selectedOiseau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMammiferes = (ListView) findViewById(R.id.listMammiferes);
        mammiferes = new ArrayList<>();
        mammifereArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mammiferes);
        listViewMammiferes.setAdapter(mammifereArrayAdapter);
        listViewMammiferes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                selectedMammifere = (Mammifere)parent.getItemAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Voulez vous modifier ou supprimer ce mammifere?")
                        .setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editAnimal(listViewMammiferes);
                            }})
                        .setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("appel a delete");
                                RequetesRest.delete(selectedMammifere);
                                mammiferes.remove(position);
                                mammifereArrayAdapter.notifyDataSetChanged();
                            }});
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        listViewOiseaux = (ListView) findViewById(R.id.listOiseaux);
        oiseaux = new ArrayList<>();
        oiseauArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,oiseaux);
        listViewOiseaux.setAdapter(oiseauArrayAdapter);
        listViewOiseaux.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                selectedOiseau = (Oiseau)parent.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Voulez vous modifier ou supprimer cet oiseau?")
                        .setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editAnimal(listViewOiseaux);
                            }})
                        .setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RequetesRest.delete(selectedOiseau);
                                oiseaux.remove(position);
                                oiseauArrayAdapter.notifyDataSetChanged();
                            }});
                builder.create().show();
            }
        });

        try {
            mammiferes.addAll(new JsonDecoder<Chien>().DecoderList(RequetesRest.get("/mammiferes/chiens"),Chien.class));
            mammiferes.addAll(new JsonDecoder<Chat>().DecoderList(RequetesRest.get("/mammiferes/chats"),Chat.class));

            oiseaux.addAll(new JsonDecoder<Pigeon>().DecoderList(RequetesRest.get("/oiseaux/pigeons"),Pigeon.class));
            oiseaux.addAll(new JsonDecoder<Aigle>().DecoderList(RequetesRest.get("/oiseaux/aigles"),Aigle.class));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addAnimal(View view){
        final Intent intent = new Intent(this,EditAnimalActivity.class);
        intent.putExtra("EDIT_BOOL",false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (view.getId()==R.id.mamButton){
            builder.setMessage("Ajouter un chat ou un chien?")
                    .setPositiveButton("Chat", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intent.putExtra("ANIMAL_TYPE",1);
                            startActivity(intent);
                        }})
                    .setNegativeButton("Chien", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intent.putExtra("ANIMAL_TYPE",2);
                            startActivity(intent);
                        }});
        }
        else if (view.getId()==R.id.OisButton){
            builder.setMessage("Ajouter un aigle ou un pigeon?")
                    .setPositiveButton("Aigle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intent.putExtra("ANIMAL_TYPE",3);
                            startActivity(intent);
                        }})
                    .setNegativeButton("Pigeon", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intent.putExtra("ANIMAL_TYPE",4);
                            startActivity(intent);
                        }});
        }
        builder.create().show();
    }

    public void editAnimal(View view){
        Intent intent = new Intent(this,EditAnimalActivity.class);
        intent.putExtra("EDIT_BOOL",true);

        if (view.getId()==R.id.listMammiferes){
            if (selectedMammifere.getClass()==Chat.class)
                intent.putExtra("ANIMAL_TYPE",1);
            else
                intent.putExtra("ANIMAL_TYPE",2);
        }
        else if (view.getId()==R.id.listOiseaux){
            if (selectedOiseau.getClass()==Aigle.class)
                intent.putExtra("ANIMAL_TYPE",3);
            else
                intent.putExtra("ANIMAL_TYPE",4);
        }
        this.startActivity(intent);
    }
}
