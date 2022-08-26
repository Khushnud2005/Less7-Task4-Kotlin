package uz.exemple.less7_task3_kotlin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    fun initViews() {
        val passwordLayout = findViewById<TextInputLayout>(R.id.tl_password)
        val editTextPassword = findViewById<TextInputEditText>(R.id.et_Password)

        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val password = s.toString()
                if (password.length >= 8) {
                    val pattern = Pattern.compile("[^a-zA-Z0-0]")
                    val matcher = pattern.matcher(password)
                    val isPwdContainsSpeChar = matcher.find()
                    if (isPwdContainsSpeChar) {
                        passwordLayout.helperText = "Strong Password"
                        passwordLayout.error = ""
                    } else {
                        passwordLayout.helperText = ""
                        passwordLayout.error = "Enter minimum 1 special character"
                    }
                } else {
                    passwordLayout.helperText = "Enter Minimum 8 char"
                    passwordLayout.error = ""
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        val nameLayout = findViewById<TextInputLayout>(R.id.tl_name)
        val editTextName = findViewById<TextInputEditText>(R.id.et_name)

        editTextName.setOnKeyListener { v, keyCode, event ->
            val text = Objects.requireNonNull(editTextName.text).toString()
            if (text.length < 10) {
                nameLayout.helperText = "NikName must be beautiful"
            } else {
                nameLayout.isErrorEnabled = true
                nameLayout.error = "Please choose shorter NikName"
            }

            false
        }
    }
}