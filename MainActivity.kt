package com.example.marco.calculadora

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var numeroAnterior : Double = 0.0
    var numeroAnteriorHex : Long = 0
    var isOperando : Boolean = false
    var operacion : String = ""
    var memoria : String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numero(v : View){
        val num = findViewById<Button>(v.id)
        var resultadoPrevio = Resultado.text
        if(resultadoPrevio.toString().startsWith("0") && num.text.toString() != "."){
            resultadoPrevio = resultadoPrevio.toString().drop(1)
        }
        if(num.text.toString() != "." || !resultadoPrevio.contains('.')){
            Resultado.text = resultadoPrevio.toString() + num.text.toString()
        }
    }

    fun borrarCE(v : View){
        Resultado.text = "0"
    }

    fun operar(v : View){
        val boton = findViewById<Button>(v.id)
        val tipo = boton.text.toString()
        if(isOperando){
            calcular()
            Resultado.text = "0"
        }
        else{
            comprobarResultado()
            numeroAnterior = Resultado.text.toString().toDouble()
            isOperando = true
        }
        Resultado.text = "0"
        when (tipo){
            "+" -> operacion = "+"
            "-" -> operacion = "-"
            "x" -> operacion = "x"
            "/" -> operacion = "/"
        }
    }

    fun igual(v : View){
        calcular()
        Resultado.text = numeroAnterior.toString()
        numeroAnterior = 0.0
        isOperando = false
    }

    fun calcular(){
        comprobarResultado()
        when (operacion){
            "+" -> numeroAnterior += Resultado.text.toString().toDouble()
            "-" -> numeroAnterior -= Resultado.text.toString().toDouble()
            "x" -> numeroAnterior *= Resultado.text.toString().toDouble()
            "/" -> numeroAnterior /= Resultado.text.toString().toDouble()
        }
    }

    fun borrarMe(v : View){
        memoria = "0"
    }

    fun mostrarMe(v : View){
        Resultado.text = memoria
    }

    fun sumarMe(v : View){
        comprobarResultado()
        val total = memoria.toDouble() + Resultado.text.toString().toDouble()
        memoria = total.toString()
        Toast.makeText (this, "Sumado a memoria", Toast.LENGTH_SHORT).show()
    }

    fun comprobarResultado(){
        if(Resultado.text == "" || Resultado.text == "." || Resultado.text.first() == 'N' || Resultado.text.first() == 'I'){
            Resultado.text = "0"
        }
    }

    fun calcularHex(){
        comprobarResultado()
        when (operacion){
            "+" -> numeroAnteriorHex += Integer.parseInt(Resultado.text.toString(), 16)
            "-" -> numeroAnteriorHex -= Integer.parseInt(Resultado.text.toString(), 16)
            "x" -> numeroAnteriorHex *= Integer.parseInt(Resultado.text.toString(), 16)
            "/" -> numeroAnteriorHex /= Integer.parseInt(Resultado.text.toString(), 16)
        }
    }

    fun igualHex(v : View){
        calcularHex()
        Resultado.text = Integer.toHexString(numeroAnteriorHex.toInt()).toUpperCase()
        numeroAnteriorHex = 0
        isOperando = false
    }

    fun operarHex(v : View){
        val boton = findViewById<Button>(v.id)
        val tipo = boton.text.toString()
        if(isOperando){
            calcularHex()
            Resultado.text = "0"
        }
        else{
            comprobarResultado()
            numeroAnteriorHex = Integer.parseInt(Resultado.text.toString(), 16).toLong()
            isOperando = true
        }
        Resultado.text = "0"
        when (tipo){
            "+" -> operacion = "+"
            "-" -> operacion = "-"
            "x" -> operacion = "x"
            "/" -> operacion = "/"
        }
    }

    fun sumarMeHex(v : View){
        comprobarResultado()
        val total = Integer.parseInt(memoria, 16) + Integer.parseInt(Resultado.text.toString(), 16)
        memoria = Integer.toHexString(total).toUpperCase()
        Toast.makeText (this, "Sumado a memoria", Toast.LENGTH_SHORT).show()
    }

}
