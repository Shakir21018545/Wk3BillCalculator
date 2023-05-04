package sg.edu.rp.c346.id21018545.wk3billcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup rgPayment;
    RadioButton rgPayNow;
    RadioButton rgCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        amount = findViewById(R.id.editInputAmount);
        numPax = findViewById(R.id.editInputNumPax);
        totalBill = findViewById(R.id.textTotalBill);
        eachPays = findViewById(R.id.textEachPays);
        svs = findViewById(R.id.tbSvs);
        gst = findViewById(R.id.tbGst);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        discount = findViewById(R.id.editInputDiscount);
        rgPayment = findViewById(R.id.radioButtonPayment);
        rgPayNow = findViewById(R.id.radioButtonPayNow);
        rgCash = findViewById(R.id.radioButtonCash);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()!=0 && numPax.getText().toString().trim().length()!=0) {
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.08;
                    } else {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.18;
                    }

                    if (discount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    int checkedRadioId = rgPayment.getCheckedRadioButtonId();
                    if(checkedRadioId == R.id.radioButtonPayNow){

                        totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                        int numPerson = Integer.parseInt(numPax.getText().toString());
                        if (numPerson != 1)
                            eachPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) +
                                " via PayNow to 12345678" );
                        else
                            eachPays.setText("Each Pays: $" + newAmt);

                    } else if (checkedRadioId == R.id.radioButtonCash) {
                        totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                        int numPerson = Integer.parseInt(numPax.getText().toString());
                        if (numPerson != 1)
                            eachPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson));
                        else
                            eachPays.setText("Each Pays: $" + newAmt);


                    }
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
                totalBill.setText("");
                eachPays.setText("");
                rgPayNow.setChecked(false);
                rgCash.setChecked(false);
            }
        });

    }
}