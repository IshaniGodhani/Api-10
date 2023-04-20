package com.example.api_10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataModel> list = new ArrayList<>();
    ArrayList<DataModel.Address> addressArrayList = new ArrayList<>();
    ArrayList<DataModel.Company> companyArrayList = new ArrayList<>();
    ArrayList<DataModel.Address.Geo> geoArrayList = new ArrayList<>();

    RecyclerView recyclerView;
    Recycler_Adapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/users";
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(response);
                  //  Log.d("bbb", "onResponse: "+jsonArray.toString());
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject mainObj=jsonArray.getJSONObject(i);
                        JSONObject address = mainObj.getJSONObject("address");
                        JSONObject company = mainObj.getJSONObject("company");
                        JSONObject geo = address.getJSONObject("geo");
                        Log.d("MMM", "onResponse: "+address);

                        Integer id=mainObj.getInt("id");
                        String name=mainObj.getString("name");
                        String username=mainObj.getString("username");
                        String email=mainObj.getString("email");
                        String phone=mainObj.getString("phone");
                        String website=mainObj.getString("website");


                        DataModel model=new DataModel(id,name,username,email,phone,website);
                        list.add(model);

                       String street=address.getString("street");
                       String suite=address.getString("suite");
                       String city=address.getString("city");
                       String zipcode=address.getString("zipcode");
                       DataModel.Address adres=new DataModel.Address(street,suite,city,zipcode);
                       addressArrayList.add(adres);

                       String lat=geo.getString("lat");
                       String lng=geo.getString("lng");
                       DataModel.Address.Geo Geo=new DataModel.Address.Geo(lat,lng);
                       geoArrayList.add(Geo);

                       String Name=company.getString("name");
                       String catchphrase=company.getString("catchPhrase");
                       String bs=company.getString("bs");

                       DataModel.Company company1=new DataModel.Company(Name,catchphrase,bs);
                       companyArrayList.add(company1);



                    }
                    recyclerAdapter=new Recycler_Adapter(MainActivity.this,list, addressArrayList,geoArrayList,companyArrayList);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(recyclerAdapter);
                }
                catch (JSONException e) {
                    throw new RuntimeException(e);
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}