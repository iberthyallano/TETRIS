package pt.apt.teris.pecas

class Peca_O(ponto: Ponto, cor: Int):Peca(){

    init{
        this.pivo = ponto;
        this.pontoA = Ponto(this.pivo.x, this.pivo.y+1);
        this.pontoB = Ponto(this.pivo.x+1, this.pivo.y+1);
        this.pontoC = Ponto(this.pivo.x+1, this.pivo.y);
        this.cor = cor;
    }

    override fun pecaMoveDown(board: Array<Array<Int>>): Boolean{
        if(valida_MD(board)){
            this.pivo.moveDown();
            this.pontoA.moveDown();
            this.pontoB.moveDown();
            this.pontoC.moveDown();
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
        }
    }

    override fun pecaMoveRight(board: Array<Array<Int>>){

        if(valida_MR(board)){
            this.pivo.moveRight();
            this.pontoA.moveRight();
            this.pontoB.moveRight();
            this.pontoC.moveRight();
        }
    }

    //Peca não possui giro, visto que seu visual não muda
    override fun pecaFlip(board: Array<Array<Int>>){}

    override fun getPosition(): ArrayList<Array<Int>>{
        var positionPivo = arrayOf<Int>(this.pivo.x, this.pivo.y);
        var positionA = arrayOf<Int>(this.pontoA.x, this.pontoA.y);
        var positionB = arrayOf<Int>(this.pontoB.x, this.pontoB.y);
        var positionC = arrayOf<Int>(this.pontoC.x, this.pontoC.y);

        var positionPeca: ArrayList<Array<Int>> = ArrayList();
        positionPeca.add(positionPivo);
        positionPeca.add(positionA);
        positionPeca.add(positionB);
        positionPeca.add(positionC);

        return positionPeca;
    }

    fun valida_MD(board: Array<Array<Int>>):Boolean {
        if( board[this.pontoB.x+1][this.pontoB.y] != 1 &&
            board[this.pontoC.x+1][this.pontoC.y] != 1){
            return true;
        }else{
            return false
        }
    }

    fun valida_ML(board: Array<Array<Int>>):Boolean {
        if(board[this.pivo.x][this.pivo.y-1] != 1){
            return true;
        }else{
            return false
        }
    }

    fun valida_MR(board: Array<Array<Int>>):Boolean {
        if(board[this.pontoA.x][this.pontoA.y+1] != 1){
            return true;
        }else{
            return false
        }
    }

}