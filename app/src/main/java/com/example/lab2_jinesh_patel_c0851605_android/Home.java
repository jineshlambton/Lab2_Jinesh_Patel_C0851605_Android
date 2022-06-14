package com.example.lab2_jinesh_patel_c0851605_android;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab2_jinesh_patel_c0851605_android.databinding.ActivityHomeBinding;

import java.util.Date;
import java.util.List;

public class Home extends AppCompatActivity {
    TextView tvAllData;
    Button insert;
    Button update;
    Button refresh;
    Button delete;

    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbManager=new DBManager(Home.this);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        refresh = findViewById(R.id.btnRefresh);
        delete = findViewById(R.id.btnDelete);

        tvAllData=findViewById(R.id.tvAllData);

        setUpButtons();
        btnRefreshClick();
    }

    void setUpButtons() {

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInsertClick();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpdateClick();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDeleteClick();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRefreshClick();
            }
        });

    }
    private void btnInsertClick() {
        AlertDialog.Builder al=new AlertDialog.Builder(Home.this);
        View view=getLayoutInflater().inflate(R.layout.insert_popup,null);
        final EditText productID=view.findViewById(R.id.pId);
        final EditText productName=view.findViewById(R.id.pName);
        final EditText productDescription=view.findViewById(R.id.pDescription);
        final EditText productPrice=view.findViewById(R.id.pPrice);
        Button insertBtn=view.findViewById(R.id.btnAddProduct);
        al.setView(view);

        final AlertDialog alertDialog=al.show();

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product productModel =new Product();
                productModel.setproduct_id(productID.getText().toString());
                productModel.setProduct_name(productName.getText().toString());
                productModel.setProduct_description(productDescription.getText().toString());
                productModel.setProduct_price(productPrice.getText().toString());
                Date date=new Date();
                productModel.setCreated_at(""+date.getTime());
                dbManager.AddProduct(productModel);
                alertDialog.dismiss();
                btnRefreshClick();
            }
        });
    }

    private void btnUpdateClick() {
        AlertDialog.Builder al=new AlertDialog.Builder(Home.this);
        View view=getLayoutInflater().inflate(R.layout.item_id_popup,null);
        al.setView(view);
        final EditText id_input=view.findViewById(R.id.edtIdOfProduct);
        Button fetch_btn=view.findViewById(R.id.btnFindProduct);
        final AlertDialog alertDialog=al.show();
        fetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog(id_input.getText().toString());
                alertDialog.dismiss();
                btnRefreshClick();
            }
        });
    }

    private void showDataDialog(final String id) {
        Product productModel =dbManager.getProduct(Integer.parseInt(id));
        AlertDialog.Builder al=new AlertDialog.Builder(Home.this);
        View view=getLayoutInflater().inflate(R.layout.product_popup,null);
        final EditText productID=view.findViewById(R.id.edtUpdateId);
        final EditText productName=view.findViewById(R.id.edtUpdateName);
        final EditText productDescription=view.findViewById(R.id.edtUpdateDesc);
        final EditText productPrice=view.findViewById(R.id.edtUpdatePrice);
        Button update_btn=view.findViewById(R.id.btnUpdateProduct);
        al.setView(view);

        productID.setText(productModel.getproduct_id());
        productName.setText(productModel.getProduct_name());
        productDescription.setText(productModel.getProduct_description());
        productPrice.setText(productModel.getProduct_price());

        final AlertDialog alertDialog=al.show();
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product productModel =new Product();
                productModel.setproduct_id(productID.getText().toString());
                productModel.setId(id);
                productModel.setProduct_name(productName.getText().toString());
                productModel.setProduct_description(productDescription.getText().toString());
                productModel.setProduct_price(productPrice.getText().toString());
                dbManager.updateProduct(productModel);
                alertDialog.dismiss();
                btnRefreshClick();
            }
        });
    }

    private void btnDeleteClick() {
        AlertDialog.Builder al=new AlertDialog.Builder(Home.this);
        View view=getLayoutInflater().inflate(R.layout.delete_popup,null);
        al.setView(view);
        final EditText id_input=view.findViewById(R.id.edtId);
        Button delete_btn=view.findViewById(R.id.btnDeleteProduct);
        final AlertDialog alertDialog=al.show();

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.deleteProduct(id_input.getText().toString());
                alertDialog.dismiss();
                btnRefreshClick();

            }
        });
    }

    private void btnRefreshClick() {

        List<Product> productModelList =dbManager.getAllProducts();
        tvAllData.setText("");
        for(Product productModel : productModelList){
            String id =  "ID : "+ productModel.getId() + "\n";
            String productId =  "number : "+ productModel.getproduct_id()+ "\n";
            String productName =  "Name : "+ productModel.getProduct_name()+ "\n";
            String productPrice =  "Price : "+ productModel.getProduct_price()+ "\n";
            String productDesc =  "Desc : "+ productModel.getProduct_description()+ "\n";
            tvAllData.append(id+productId+productName+productPrice+productDesc + "\n\n");
        }
    }
}