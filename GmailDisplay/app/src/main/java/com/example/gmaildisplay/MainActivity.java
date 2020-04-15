package com.example.gmaildisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {

    List<ContactModel> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Faker faker = new Faker(); //thu vien rat hay!!!
        contacts = new ArrayList<>();

        //tao du lieu ngau nhien
        for(int i = 0; i < 20 ; i++) {
            contacts.add(new ContactModel(faker.color.hexColor(),faker.name.name(), faker.lorem.sentence(), faker.lorem.paragraph(), "12:00 AM"));
        }

        ContactAdapter adapter = new ContactAdapter(contacts); //tao adapter
        ListView listView = findViewById(R.id.list_view); //lay id view hien thi
        //List-Based = ListView + Data + DataAdapter
        listView.setAdapter(adapter);

    }
}
