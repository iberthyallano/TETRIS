package pt.apt.teris

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import pt.apt.teris.databinding.ActivityConfiguracaoBinding

class Configuracao : AppCompatActivity() {

    lateinit var binding: ActivityConfiguracaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configuracao);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_configuracao);

        val configuracoes = getSharedPreferences("${R.string.app_name}_configuracoes", Context.MODE_PRIVATE);
        val editor = configuracoes.edit();
        var dificuldade = 1;
        var quantidade = 1;

        binding.radioGroupDifuculdade.setOnCheckedChangeListener { _: RadioGroup, _: Int ->
            var dificuldade = 1;
            if(binding.radioButtonFacil.isChecked){
                dificuldade = 0;
                editor.putInt("dificuldade", dificuldade).apply();
            }else if(binding.radioButtonMadio.isChecked){
                editor.putInt("dificuldade", dificuldade).apply();
            }else if(binding.radioButtonDificil.isChecked){
                dificuldade = 2;
                editor.putInt("dificuldade", dificuldade).apply();
            }
        }

        binding.radioGroupQuantidade.setOnCheckedChangeListener { _: RadioGroup, _: Int ->
            var quantidade = 1;
            if(binding.radioButton3.isChecked){
                quantidade = 0;
                editor.putInt("total_pecas", quantidade).apply();
            }else if(binding.radioButton4.isChecked){
                editor.putInt("total_pecas", quantidade).apply();
            }else if(binding.radioButton6.isChecked){
                quantidade = 2;
                editor.putInt("total_pecas", quantidade).apply();
            }
        }

        binding.buttonSalvar.setOnClickListener {
            var intent = Intent();
            intent.putExtra("SALVAR", "SALVAR");
            setResult(Activity.RESULT_OK, intent);
            finish();
        }


        binding.buttonCancelar.setOnClickListener {
            editor.putInt("record", 0).apply();
            val intent = Intent();
            editor.putInt("dificuldade", dificuldade).apply();
            editor.putInt("total_pecas", quantidade).apply();
            intent.putExtra("CANCELAR", "CANCELAR");
            setResult(Activity.RESULT_CANCELED, intent);
            finish();
        }

    }
}