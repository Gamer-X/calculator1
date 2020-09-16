/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package sample.calculator.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import sample.calculator.arithmeticparser.parseAndCompute
import java.math.BigInteger

class MainActivity : AppCompatActivity() {
    lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val resultView = findViewById<TextView>(R.id.computed_result)

        val input = findViewById<EditText>(R.id.input)
        input.setOnEditorActionListener { input, _, _ ->
            val inputText = input.text.toString()
            val result = parseAndCompute(inputText).expression
            with(resultView) {
                text = if (result != null) inputText + " = " + result.toString() else "Unable1 to parse $inputText"
            }
            true
        }
        /////////////////////////////
        button = findViewById(R.id.button)
        button.setOnClickListener(listener)
    }

    val listener= View.OnClickListener { view ->
        when (view.getId()) {
            R.id.button -> {
                val textview = findViewById(R.id.textView) as TextView
                var inputValue : String = input.text.toString()
                inputValue = inputValue.replace("[^0-9]".toRegex(), "")
                 textview.setText(inputValue).toString()

                  val n =  inputValue.toInt().toLong()

                fibonacci(BigInteger.ZERO, BigInteger.ONE, BigInteger.valueOf(n))
                textview.text = "something"
            }
        }
    }

    tailrec private fun fibonacci(a: BigInteger, b: BigInteger, n: BigInteger): BigInteger {
        Toast.makeText(this, "number is : $a", Toast.LENGTH_SHORT).show()
        val textview2 = findViewById(R.id.textView2) as TextView
        textview2.text = a.toString()
        return if (n == BigInteger.ZERO) a else fibonacci(b, a + b, n - BigInteger.ONE)
    }

    ///////////////////////////
    }

