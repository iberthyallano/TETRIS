package pt.apt.teris

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pt.apt.teris.databinding.ActivityTabuleiroBinding
import pt.apt.teris.pecas.*
import kotlin.random.Random

class Tabuleiro : AppCompatActivity() {

    lateinit var binding: ActivityTabuleiroBinding;

    var aleatorio = Random;

    var speed_value = 1;
    var total_pecas = 1;
    var pontuacao = 0;

    val LINHA = 37
    val COLUNA = 24
    var running = true
    var speed = longArrayOf(450,350,250);
    var randon_pecas = intArrayOf(3, 4, 6);

    var peca = novaPeca(1);

    var proxima_peca = 0;

    var posicaoPeca = peca.getPosition();

    var board = Array(LINHA) {
        Array(COLUNA){0}
    }

    var boardView = Array(LINHA){
        arrayOfNulls<ImageView>(COLUNA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tabuleiro)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tabuleiro);

        var config = getSharedPreferences("${R.string.app_name}_configuracoes", Context.MODE_PRIVATE);
        speed_value = config.getInt("dificuldade", 1);
        total_pecas = config.getInt("total_pecas", 1);

        binding.gridboard.rowCount = LINHA
        binding.gridboard.columnCount = COLUNA

        val inflater = LayoutInflater.from(this)

        for (i in 0 until LINHA) {
            for (j in 0 until COLUNA) {
                boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, binding.gridboard, false) as ImageView
                binding.gridboard.addView(boardView[i][j])
            }
        }

        binding.imageButtonFlip.setOnClickListener {
            peca.pecaFlip(board);
        }

        binding.imageButtonDireita.setOnClickListener {
            peca.pecaMoveRight(board);
        }

        binding.imageButtonBaixo.setOnClickListener {
            peca.pecaMoveDown(board);
        }

        binding.imageButtonEsquerda.setOnClickListener {
            peca.pecaMoveLeft(board);
        }

        binding.imageButtonPause.setOnClickListener {
            pauseGame();
        }

        binding.textViewPontuacao.text = pontuacao.toString();
        gameRun();
    }

    fun pauseGame(){
        if(running){
            running = false;
            var intent = Intent(this, MainActivity::class.java);
            intent.putExtra("pause", true);
            startActivityForResult(intent, 7);
        }else{
            running = true;
            gameRun();
        }
    }

    fun novaPeca(tipo:Int):Peca{
        var cor = aleatorio.nextInt(9);
        when (tipo) {
            0 -> {
                return Peca_O(Ponto(1,12), cor);
            }
            1 -> {
                return Peca_I(Ponto(4,12), cor);
            }
            2 -> {
                return Peca_S(Ponto(2,12), cor);
            }
            3 -> {
                return Peca_T(Ponto(1,12), cor);
            }
            4 -> {
                return Peca_L(Ponto(3, 12), cor);
            }
            else -> {
                return  Peca_X(Ponto(2,12), cor);
            }
        }
    }

    fun limpaTela(){
        for (i in 0 until board.size) {
            for (j in 0 until board[i].size) {
                if(i == 0 || i == board.size-1 || j == 0 || j == board[i].size-1){
                    boardView[i][j]!!.setImageResource(R.drawable.bloco_borda)
                    board[i][j] = 1;
                }else if(board[i][j] == 0) {
                    boardView[i][j]?.setImageResource(R.drawable.fundo_tabuleiro)
                }
            }
        }
    }

    fun printPeca(){
        for(i in 0 until posicaoPeca.size){
            var x = posicaoPeca[i][0];
            var y = posicaoPeca[i][1];
            when(peca.cor){
                0 -> {boardView[x][y]!!.setImageResource(R.drawable.bloco_verde)}
                1 -> {boardView[x][y]!!.setImageResource(R.drawable.bloco_roxo)}
                2 -> {boardView[x][y]!!.setImageResource(R.drawable.bloco_rosa)}
                3 -> {boardView[x][y]!!.setImageResource(R.drawable.bloco_marron)}
                4 -> {boardView[x][y]!!.setImageResource(R.drawable.bloco_laranja)}
                5 -> {boardView[x][y]!!.setImageResource(R.drawable.bloco_azul)}
                6 -> {boardView[x][y]!!.setImageResource(R.drawable.bloco_amarelo)}
                7 -> {boardView[x][y]!!.setImageResource(R.drawable.bloco_branco)}
                8 -> {boardView[x][y]!!.setImageResource(R.drawable.bloco_vermelho)}
            }
        }
    }

    fun DescePeca(){
        if(!peca.pecaMoveDown(board)){
            for(i in 0 until posicaoPeca.size){
                var x = posicaoPeca[i][0];
                var y = posicaoPeca[i][1];
                board[x][y] = 1;
            }
            peca = novaPeca(proxima_peca);
            proxima_peca = aleatorio.nextInt(randon_pecas[total_pecas]);
        }
    }

    fun verificaPonto(){
        for(i in 1 until board.size-1){
            if(board[i].sum() == COLUNA){
                for(j in i downTo  2) {
                    board[j] = board[j-1];
                }
                pontuacao += 15;
            }
        }
    }

    fun gameOver(){
        var perdeu = false;
        if(board[1].sum() > 2){
            perdeu = true;
        }
        if(perdeu){
            running = false;
            val intent = Intent(this, GameOver::class.java);
            intent.putExtra("pontuacao", pontuacao);
            startActivityForResult(intent,1);
        }
    }

    fun mostraProxima(){
        when (proxima_peca) {
            0 -> {
                binding.imageViewProxima.setImageResource(R.drawable.proxima_o);
            }
            1 -> {
                binding.imageViewProxima.setImageResource(R.drawable.proxima_i);
            }
            2 -> {
                binding.imageViewProxima.setImageResource(R.drawable.proxima_s);
            }
            3 -> {
                binding.imageViewProxima.setImageResource(R.drawable.proxima_t);
            }
            4 -> {
                binding.imageViewProxima.setImageResource(R.drawable.proxima_l);
            }
            else -> {
                binding.imageViewProxima.setImageResource(R.drawable.proxima_x);
            }
        }
    }

    fun gameRun(){
        Thread{
            while(running){
                Thread.sleep(speed[speed_value])
                runOnUiThread{
                    limpaTela();
                    binding.textViewPontuacao.text = pontuacao.toString();
                    mostraProxima();
                    gameOver();
                    verificaPonto();
                    try {
                        posicaoPeca = peca.getPosition();
                        printPeca();
                        DescePeca();
                    }catch (e: ArrayIndexOutOfBoundsException) {
                        //se a peça passou das bordas eu vou parar o jogo
                        running = false;
                    }

                }
            }
        }.start()
    }
}

