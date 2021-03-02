package pt.apt.teris.pecas

abstract class Peca() {
    var cor:Int? = null;
    lateinit var pivo:Ponto;
    lateinit var pontoA:Ponto;
    lateinit var pontoB:Ponto;
    lateinit var pontoC:Ponto;

    abstract fun pecaMoveDown(board: Array<Array<Int>>): Boolean;
    abstract fun pecaMoveLeft(board: Array<Array<Int>>);
    abstract fun pecaMoveRight(board: Array<Array<Int>>);
    abstract fun pecaFlip(board: Array<Array<Int>>);
    abstract fun getPosition(): ArrayList<Array<Int>>;
}