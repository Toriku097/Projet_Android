package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.dao.SupplierConventionDAO;
import ca.qc.colval.projet1.entities.SupplierConvention;
import ca.qc.colval.projet1.control.SupplierConventionControl;

public class ConventionActivity extends AppCompatActivity {

    Spinner spn_supplier,spn_convention;
    SupplierConventionDAO DAO;
    List<SupplierConvention> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convention);

        //init
        try {list = DAO.getAllSupplierConventions();}
        catch (java.lang.NullPointerException e){System.out.println(e);}
        finally {list = new ArrayList<>();}

        DAO = new SupplierConventionDAO(this);

        spn_supplier = findViewById(R.id.convention_spn_supplier);
        spn_convention = findViewById(R.id.convention_spn_convention);
    }
    public void addConventionClick(View v){
        int idCF = 1;
        int[] id = bindId();
        SupplierConvention tempSC;

        if(list.isEmpty()==false){
            idCF++;
            for(SupplierConvention sc : list){
                if(sc.getSupplierConventionId()!=idCF){
                    tempSC = new SupplierConvention(idCF,id[0],id[1]);
                    DAO.addSupplierConvention(tempSC);
                    list.add(tempSC);
                    showToast("La convention a été ajouté au fournisseur");
                    break;
                }
            }
        }else {
            tempSC = new SupplierConvention(idCF,id[0],id[1]);
            DAO.addSupplierConvention(tempSC);
            list.add(tempSC);
            showToast("La convention a été ajouté au fournisseur");
        }
    }
    private int[] bindId(){
        int[] id = new int[2];
        switch (spn_convention.getSelectedItem().toString()){
            case "Maconnerie":id[0]=1;
            case "Plomberie":id[0]=2;
            case "Electricite":id[0]=3;
            case "Matériaux de construction":id[0]=4;
            case "Système intelligent":id[0]=5;

        }

        switch (spn_supplier.getSelectedItem().toString()) {
            case "Buildmate":id[1]=1;
            case "Constructronix":id[1]=2;
            case "SteelScape":id[1]=3;
            case "Concretopia":id[1]=4;
            case "LumberLink":id[1]=5;
            case "PlumbingPro":id[1]=6;
        }
        return id;
    }

    private void showToast(String msg){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}