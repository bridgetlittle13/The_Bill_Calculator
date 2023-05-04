package sg.edu.rp.c346.id22013272.thebillcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView tbill;
    TextView bill;
    EditText etAmt;
    EditText etPax;
    EditText etDis;
    ToggleButton SVS;
    ToggleButton GST;
    ToggleButton Split;
    ToggleButton Reset;
    RadioButton tmode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step 2: creating object for UI element
        etAmt=findViewById(R.id.editTextAmount);
        etPax=findViewById(R.id.editTextPax);
        tbill=findViewById(R.id.textViewBillTotal);
        bill=findViewById(R.id.textViewBill1);
        SVS=findViewById(R.id.toggleButtonNOSVS);
        GST=findViewById(R.id.toggleButtonGST);
        etDis=findViewById(R.id.editTextDiscount);
        Split=findViewById(R.id.toggleButtonSplit);
        Reset=findViewById(R.id.toggleButtonReset);
        tmode=findViewById(R.id.radioButtonCash);
        tmode=findViewById(R.id.radioButtonPn);


        //step 3,linking UI element with Java object
                int checkedmoney=etAmt.getText().toString().trim().length();
                int checkedppl=etPax.getText().toString().trim().length();
                int checkedDisc=etDis.getText().toString().trim().length();
                Split.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //code of action
                        if (checkedmoney!=0 && checkedppl!=0){
                            double newAmt=0.0;
                            if (!SVS.isChecked() && GST.isChecked()){
                                newAmt=Double.parseDouble(etAmt.getText().toString());
                            } else if (SVS.isChecked() && !GST.isChecked()) {
                                newAmt=Double.parseDouble(etAmt.getText().toString())*1.1;
                            } else if (!SVS.isChecked() && GST.isChecked()) {
                                newAmt=Double.parseDouble(etAmt.getText().toString())*1.07;
                            }else{
                                newAmt=Double.parseDouble(etAmt.getText().toString())*1.17;
                            }
                            //Discount
                            if (checkedDisc!=0){
                                newAmt*=1-Double.parseDouble(etDis.getText().toString());
                            }
                            tbill.setText("Total Bill $" + String.format("%.2f",newAmt));
                            if (checkedppl!=1){
                                bill.setText("Each Pays: $"+String.format("%.2f",newAmt));
                            }else {
                                bill.setText("Each Pays: $"+newAmt);
                            }
                            Reset.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    etAmt.setText("");
                                    etPax.setText("");
                                    SVS.setChecked(false);
                                    GST.setChecked(false);
                                    etDis.setText("");
                                }
                            });
                        };


            }
        });




    }
}