package sz.shopmapp;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tavi2 on 08.04.2017.
 */

public class JsonTool {
    Activity currentActivity;

    public JsonTool(Activity ca) {
        currentActivity = ca;
    }

    public static ArrayList<Categorie> parseCategorieJSONData(JSONObject object) {
        ArrayList<Categorie> arlCategorie = new ArrayList<>();
        try {
            JSONArray ja = object.getJSONArray("items");
            for(int i = 0; i < ja.length(); i++) {
                JSONObject job = ja.getJSONObject(i);
                int raion,raft,catID;
                raion = Integer.parseInt(job.getString("cod").split("-")[0]);
                try {
                    raft = Integer.parseInt(job.getString("cod").split("-")[1]);
                }catch (Exception e){
                    raft = 0;
                }
                try{
                    catID = job.getInt("categorieid");
                } catch (Exception e){
                    catID = 0;
                }
                arlCategorie.add(new Categorie(job.getInt("id"),catID,job.getString("denumire"),raion,raft));
            }
        }catch (Exception e){
            Log.d("Android: ",e.toString());
        }
        return arlCategorie;
    }

    public static ArrayList<Produs> parseProduseJSONData(JSONObject object){
        ArrayList<Produs> arlProdus = new ArrayList<>();
        try {
            JSONArray ja = object.getJSONArray("items");
            for(int i = 0; i < ja.length(); i++) {
                JSONObject job = ja.getJSONObject(i);
                String greutate,dataexp,descr,imag;
                try{
                    greutate = job.getString("greutate");
                }catch (Exception e){
                    greutate = "";
                }
                try{
                    dataexp = job.getString("dataexpirare");
                }catch (Exception e){
                    dataexp = "";
                }
                try{
                    descr = job.getString("descriere");
                }catch (Exception e){
                    descr = "";
                }
                try{
                    imag = job.getString("imagine");
                } catch (Exception e){
                    imag = "";
                }
                arlProdus.add(new Produs(job.getInt("id"),job.getInt("categorieid"),job.getString("denumire"),job.getDouble("pret"),job.getDouble("cantitate"),greutate,dataexp,descr,imag));
            }
        }catch (Exception e){
            Log.d("Android: ",e.toString());
        }
        return arlProdus;
    }
}
