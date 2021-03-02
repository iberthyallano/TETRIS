package pt.apt.teris.pecas

class Peca_X (ponto: Ponto, cor: Int):Peca(){

    var pontoD: Ponto;

    init{
        this.pivo = ponto;
        this.pontoA = Ponto(this.pivo.x-1, this.pivo.y);
        this.pontoB = Ponto(this.pivo.x+1, this.pivo.y);
        this.pontoC = Ponto(this.pivo.x, this.pivo.y+1);
        this.pontoD = Ponto(this.pivo.x, this.pivo.y-1);

        this.cor = cor;
    }

    override fun pecaMoveDown(board: Array<Array<Int>>): Boolean{
        if(valida_MD(board)){
            this.pivo.moveDown();
            this.pontoA.moveDown();
            this.pontoB.moveDown();
            this.pontoC.moveDown();
            this.pontoD.moveDown();
            return true;
        }else{
            return false;
        }
    }

    override fun pecaMoveLeft(board: Array<Array<Int>>){
        if(valida_ML(board)){
            this.pivo.moveLeft();
            this.pontoA.moveLeft();
            this.pontoB.moveLeft();
            this.pontoC.moveLeft();
            this.pontoD.moveLeft();
        }
    }

    override fun pecaMoveRight(board: Array<Array<Int>>){
        if(valida_MR(board)){
            this.pivo.moveRight();
            this.pontoA.moveRight();
            this.pontoB.moveRight();
            this.pontoC.moveRight();
            this.pontoD.moveRight();
        }
    }

    //Peca não possui giro, visto que seu visual não muda
    override fun pecaFlip(board: Array<Array<Int>>){}

    override fun getPosition(): ArrayList<Array<Int>>{
        var positionPivo = arrayOf<Int>(this.pivo.x, this.pivo.y);
        var positionA = arrayOf<Int>(this.pontoA.x, this.pontoA.y);
        var positionB = arrayOf<Int>(this.pontoB.x, this.pontoB.y);
        var positionC = arrayOf<Int>(this.pontoC.x, this.pontoC.y);
        var positionD = arrayOf<Int>(this.pontoD.x, this.pontoD.y);

        var positionPeca: ArrayList<Array<Int>> = ArrayList();
        positionPeca.add(positionPivo);
        positionPeca.add(positionA);
        positionPeca.add(positionB);
        positionPeca.add(positionC);
        positionPeca.add(positionD);

        return positionPeca;
    }

    fun valida_MD(board: Array<Array<Int>>):Boolean {
        if( board[this.pontoB.x+1][this.pontoB.y] != 1 ||
            board[this.pontoC.x+1][this.pontoC.y] != 1 ||
            board[this.pontoD.x+1][this.pontoD.y] != 1){
            return true;
        }else{
            return false
        }
    }

    fun valida_ML(board: Array<Array<Int>>):Boolean {
        if(board[this.pontoD.x][this.pontoD.y-1] != 1){
            return true;
        }else{
            return false
        }
    }

    fun valida_MR(board: Array<Array<Int>>):Boolean {
        if(board[this.pontoC.x][this.pontoC.y+1] != 1){
            return true;
        }else{
            return false
        }
    }
}