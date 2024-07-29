package com.example.kyliesnewapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    data class Users (val nome : String , val user : String , val senha : String , val confirmSenha : String)

    companion object {

        val listaDeUsers = mutableListOf<Users>()

    }

    private lateinit var nomeInput : EditText
    private lateinit var usernameInput : EditText
    private lateinit var senhaInput : EditText
    private lateinit var confirmSenhaInput : EditText
    private lateinit var cadastroBtn : Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

         nomeInput = findViewById(R.id.nomeInputCadastro)
         usernameInput = findViewById(R.id.userInputCadastro)
         senhaInput = findViewById(R.id.senhaInputCadastro)
         confirmSenhaInput = findViewById(R.id.senhaConfirmInputCadastro)
         cadastroBtn = findViewById(R.id.cadastroBtnCadastro)
         val backArrow = findViewById<ImageView>(R.id.previousArrow)

        backArrow.setOnClickListener {
            startActivity(Intent (this@RegisterActivity, LoginScreen::class.java))
        }

        cadastroBtn.setOnClickListener {
            realizarCadastro()
        }
    }

    private fun realizarCadastro() {

        val nome = nomeInput.text.toString()
        val user = usernameInput.text.toString()
        val senha = senhaInput.text.toString()
        val senhaConfirmed = confirmSenhaInput.text.toString()

        var error = false

        if (error) {
            senhaInput.background = ContextCompat.getDrawable(this, R.drawable.error_shape)
            usernameInput.background = ContextCompat.getDrawable(this, R.drawable.error_shape)
            confirmSenhaInput.background = ContextCompat.getDrawable(this, R.drawable.error_shape)
            nomeInput.background = ContextCompat.getDrawable(this, R.drawable.error_shape)
            return
        }

        if (nome.isEmpty() || user.isEmpty() || senha.isEmpty() || senhaConfirmed.isEmpty()) {
           exibirDialogo("Todos os campos devem ser preenchidos")
            error = true
        }

        val senhaRegex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%¨&*();:]).{6,}\$")

        if (!senhaRegex.matches(senha)) {
            senhaInput.error = "A senha deve conter 6 dígitos, havendo uma letra maiúscula, uma letra minúscula, um caractére especial e um número."
            error = true
        }

        if (senha != senhaConfirmed) {
            confirmSenhaInput.error = "Este campo deve ser preenchido com exatamente a mesma coisa do campo anterior"
            error = true
        }

        if (senha == senhaConfirmed && senhaRegex.matches(senha) && nome.isNotEmpty() && user.isNotEmpty() && senha.isNotEmpty() && senhaConfirmed.isNotEmpty() ) {
            val newUser = Users(nome, user, senha, senhaConfirmed)
            listaDeUsers.add(newUser)
            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
            startActivity(Intent (this@RegisterActivity, LoginScreen::class.java))
        }

    }

    private fun exibirDialogo(mensagem: String) {
        AlertDialog.Builder(this)
            .setTitle("ATENÇÃO")
            .setTitle(mensagem)
            .setPositiveButton("OK") {  dialog,_ ->
                dialog.dismiss()
            }
                .show()
    }

}