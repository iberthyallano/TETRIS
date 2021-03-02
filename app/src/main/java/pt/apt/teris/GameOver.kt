package pt.apt.teris

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import pt.apt.teris.databinding.ActivityGamaOverBinding

class GameOver : AppCompatActivity() {

    lateinit var binding: ActivityGamaOverBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gama_over);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gama_over);

        var config = getSharedPreferences("${R.string.app_name}_configuracoes", Context.MODE_PRIVATE)

        var pontuacao = intent.extras?.getInt("pontuacao");
        var record = config.getInt("record", 0);

        if (pontuacao != null) {
            if(pontuacao > record){
                config.edit().putInt("record", pontuacao).apply();
            }
        }

        record = config.getInt("record", 0);

        binding.textViewPontuacaoValue.text = pontuacao.toString();
        binding.textViewRecordValue.text = record.toString();

        binding.buttonNovoJogo.setOnClickListener {
            val intent = Intent(this, Tabuleiro::class.java);
            startActivityForResult(intent, 1);
            finish();
        }


        binding.buttonSair.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivityForResult(intent, 2);
            finish();
        }
    }
}