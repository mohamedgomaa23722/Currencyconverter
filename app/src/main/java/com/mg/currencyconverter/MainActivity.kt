package com.mg.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val egyptianPound = "Egyptian Pound"
    private val americanDollar = "American Dollar"
    private val AED = "AED"
    private val GBP = "GBP"

    lateinit var fromEDx: AutoCompleteTextView
    lateinit var toEDx: AutoCompleteTextView
    lateinit var amountEDX: TextInputEditText
    lateinit var ResultEDX: TextInputEditText
    lateinit var convertBtn: MaterialButton
    lateinit var amountLayout: TextInputLayout
    val values = mapOf(
        americanDollar to 1.0,
        egyptianPound to 18.44,
        AED to 3.67,
        GBP to 0.93
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InitializeViews()
        PopulateDropDownMenu()

        amountEDX.addTextChangedListener {
            CalculateResult()
        }
        fromEDx.setOnItemClickListener { adapterView, view, i, l ->
            CalculateResult()
        }
        toEDx.setOnItemClickListener { adapterView, view, i, l ->
            CalculateResult()
            Log.d(TAG, "onCreate: "+cl())
        }


    }

    private fun InitializeViews() {
        convertBtn = findViewById(R.id.ConvertButton)
        amountEDX = findViewById(R.id.amountEdx)
        amountLayout = findViewById(R.id.amountTextLayout)
        ResultEDX = findViewById(R.id.ResultEDX)
        fromEDx = findViewById(R.id.FromAutoComplete)
        toEDx = findViewById(R.id.ToAutoComplete)
    }

    private fun PopulateDropDownMenu() {
        val listOfCountry = listOf(egyptianPound, americanDollar, AED, GBP)
        val adapter = ArrayAdapter(this, R.layout.drop_down_list, listOfCountry)
        toEDx.setAdapter(adapter)
        fromEDx.setAdapter(adapter)
    }

    private fun CalculateResult() {
        if (amountEDX.text.toString().isNotEmpty()) {

            val currencyToField = toEDx.text.toString()
            val currencyFromField = fromEDx.text.toString()

            val amount = amountEDX.text.toString().toDouble()
            val toValue = values[currencyToField]
            val fromValue = values[currencyFromField]

            val result = (amount * toValue!!) / fromValue!!
            val formattedResult = String.format("%.3f", result)

            ResultEDX.setText(formattedResult)
        } else {
            val snackbar =
                Snackbar.make(fromEDx, "please Enter amount field", Snackbar.LENGTH_SHORT)
            snackbar.show()
            snackbar.setAction("Ok") {

            }
        }
    }

    private fun cl(number1: Double = values[toEDx.text.toString()]!!): Double {
        return number1;
    }
}