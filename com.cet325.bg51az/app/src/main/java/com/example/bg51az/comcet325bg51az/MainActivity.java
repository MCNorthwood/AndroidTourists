package com.example.bg51az.comcet325bg51az;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bg51az.comcet325bg51az.database.DBOpenHelper;
import com.example.bg51az.comcet325bg51az.database.Tourist;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnViewList, btnFavCurr, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewList = (Button)findViewById(R.id.btn_viewList);
        btnFavCurr = (Button)findViewById(R.id.btn_favCurr);
        btnAbout = (Button)findViewById(R.id.btn_about);

        btnViewList.setOnClickListener(this);
        btnFavCurr.setOnClickListener(this);
        btnAbout.setOnClickListener(this);

        Initiate();
    }

    @Override
    public void onClick(View v){
        int id = v.getId();

        if(id == R.id.btn_viewList){
            Intent inList = new Intent(v.getContext(), ListActivity.class);
            startActivity(inList);
        }
        else if(id == R.id.btn_favCurr){

        }
        else if(id == R.id.btn_about){

        }
        else{

        }
    }

    public void Initiate(){
        DBOpenHelper db = new DBOpenHelper(this);

        //populate or re-populate to not duplicate data on first go for testing purposes
        List<Tourist> tourists = db.getAllTourists();

        for(Tourist t: tourists){
            db.deleteTourist(t);
        }

        // 1
        Tourist BritishMuseum = new Tourist();
        BritishMuseum.name = "British Museum";
        BritishMuseum.location = "London, United Kingdom";
        BritishMuseum.description= "http://www.britishmuseum.org/";
        BritishMuseum.favourite = true; // Boolean value gets translated into Integer when added
        BritishMuseum.image = "@drawable/britmuse";
        BritishMuseum.geolocation = "51.5194133,-0.1291453";
        BritishMuseum.price = 0;
        BritishMuseum.rank = 3;
        BritishMuseum.deletable = false;

        // 2
        Tourist LondonDungeon = new Tourist();
        LondonDungeon.name = "London Dungeon";
        LondonDungeon.location = "London, United Kingdom";
        LondonDungeon.description= "https://www.thedungeons.com/locations/";
        LondonDungeon.favourite = true;
        LondonDungeon.image = "@drawable/londondungeons";
        LondonDungeon.geolocation = "51.5025119,-0.1209515";
        LondonDungeon.price = 19.95;
        LondonDungeon.rank = 5;
        LondonDungeon.deletable = false;

        // 3
        Tourist LondonEye = new Tourist();
        LondonEye.name = "London Eye";
        LondonEye.location = "London, United Kingdom";
        LondonEye.description= "https://www.londoneye.com/";
        LondonEye.favourite = true;
        LondonEye.image = "@drawable/londoneye";
        LondonEye.geolocation = "51.503324,-0.1217317";
        LondonEye.price = 24.95;
        LondonEye.rank = 4;
        LondonEye.deletable = false;

        // 4
        Tourist LondonZoo = new Tourist();
        LondonZoo.name = "London Zoo";
        LondonZoo.location = "London, United Kingdom";
        LondonZoo.description= "https://www.zsl.org/zsl-london-zoo";
        LondonZoo.favourite = true;
        LondonZoo.image = "@drawable/londonzoo";
        LondonZoo.geolocation = "51.5352875,-0.155619";
        LondonZoo.price = 21.50;
        LondonZoo.rank = 4;
        LondonZoo.deletable = false;

        // 5
        Tourist MadameTussauds = new Tourist();
        MadameTussauds.name = "Madame Tussauds";
        MadameTussauds.location = "London, United Kingdom";
        MadameTussauds.description= "https://www.madametussauds.com/london/en/";
        MadameTussauds.favourite = true;
        MadameTussauds.image = "@drawable/madametussauds";
        MadameTussauds.geolocation = "51.5228901,-0.1571558";
        MadameTussauds.price = 29;
        MadameTussauds.rank = 4;
        MadameTussauds.deletable = false;

        // 6
        Tourist NationalGallery = new Tourist();
        NationalGallery.name = "National Gallery";
        NationalGallery.location = "London, United Kingdom";
        NationalGallery.description= "https://www.nationalgallery.org.uk/";
        NationalGallery.favourite = true;
        NationalGallery.image = "@drawable/nationalgallery";
        NationalGallery.geolocation = "51.508929,-0.1304877";
        NationalGallery.price = 0;
        NationalGallery.rank = 5;
        NationalGallery.deletable = false;

        // 7
        Tourist NationalHistoryMuseum = new Tourist();
        NationalHistoryMuseum.name = "National History Museum";
        NationalHistoryMuseum.location = "London, United Kingdom";
        NationalHistoryMuseum.description= "http://www.nhm.ac.uk/visit.html";
        NationalHistoryMuseum.favourite = true;
        NationalHistoryMuseum.image = "@drawable/nationalhistorymuseum";
        NationalHistoryMuseum.geolocation = "51.496715,-0.1785559";
        NationalHistoryMuseum.price = 0;
        NationalHistoryMuseum.rank = 3;
        NationalHistoryMuseum.deletable = false;

        // 8
        Tourist ScienceMuseum = new Tourist();
        ScienceMuseum.name = "Science Museum";
        ScienceMuseum.location = "London, United Kingdom";
        ScienceMuseum.description= "http://www.sciencemuseum.org.uk/";
        ScienceMuseum.favourite = true;
        ScienceMuseum.image = "@drawable/sciencemuseum";
        ScienceMuseum.geolocation = "51.4978095,-0.1767122";
        ScienceMuseum.price = 0;
        ScienceMuseum.rank = 5;
        ScienceMuseum.deletable = false;

        // 9
        Tourist TowerBridge = new Tourist();
        TowerBridge.name = "Tower Bridge";
        TowerBridge.location = "London, United Kingdom";
        TowerBridge.description= "Tower Bridge is a combined bascule and suspension bridge in London built in 1886–1894.";
        TowerBridge.favourite = true;
        TowerBridge.image = "@drawable/towerbridge";
        TowerBridge.geolocation = "51.5054560,-0.0753560";
        TowerBridge.price = 0;
        TowerBridge.rank = 3;
        TowerBridge.deletable = false;

        //10
        Tourist TowerOfLondon= new Tourist();
        TowerOfLondon.name = "Tower Of London";
        TowerOfLondon.location = "London, United Kingdom";
        TowerOfLondon.description= "http://www.hrp.org.uk/tower-of-london/#gs.aDD9P64";
        TowerOfLondon.favourite = true;
        TowerOfLondon.image = "@drawable/toweroflondon";
        TowerOfLondon.geolocation = "51.5081120,-0.0759490";
        TowerOfLondon.price = 25.00;
        TowerOfLondon.rank = 4;
        TowerOfLondon.deletable = false;

        // Add tourist information to db
        db.addTourist(BritishMuseum);
        db.addTourist(LondonDungeon);
        db.addTourist(LondonEye);
        db.addTourist(LondonZoo);
        db.addTourist(MadameTussauds);
        db.addTourist(NationalGallery);
        db.addTourist(NationalHistoryMuseum);
        db.addTourist(ScienceMuseum);
        db.addTourist(TowerBridge);
        db.addTourist(TowerOfLondon);

        Toast.makeText(this, "Data added to Database", Toast.LENGTH_LONG).show();
    }
}
