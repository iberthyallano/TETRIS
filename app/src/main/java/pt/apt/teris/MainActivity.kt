package pt.apt.teris

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import pt.apt.teris.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        var params = intent.extras
        var continuar = params?.getBoolean("pause")

        if(continuar == true){
            binding.buttonContinuar.visibility = View.VISIBLE;
        }else{
            binding.buttonContinuar.visibility = View.INVISIBLE;
        }

        binding.buttonNovoJogo.setOnClickListener {
            val intent = Intent(this, Tabuleiro::class.java);
            startActivityForResult(intent, 1);
        }

        binding.buttonContinuar.setOnClickListener {
            val intent = Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

        binding.buttonConfigurar.setOnClickListener {
            val intent = Intent(this, Configuracao::class.java);
            startActivityForResult(intent, 3);
        }
    }

}