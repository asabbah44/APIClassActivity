package com.example.apiactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView txtEmployeeName, txtEmployeeSalary, txtEmployeeAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmployeeName = findViewById(R.id.EmployeeName);
        txtEmployeeSalary = findViewById(R.id.EmployeeSalary);
        txtEmployeeAge = findViewById(R.id.EmployeeAge);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://dummy.restapiexample.com/api/v1/employee/1";

        // Request a JSON response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");

                            String employeeName = data.getString("employee_name");
                            int employeeSalary = data.getInt("employee_salary");
                            int employeeAge = data.getInt("employee_age");

                            txtEmployeeName.setText("Name: " + employeeName);
                            txtEmployeeSalary.setText("Salary: " + employeeSalary);
                            txtEmployeeAge.setText("Age: " + employeeAge);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        error.printStackTrace();
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
}