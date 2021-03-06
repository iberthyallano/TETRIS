package pt.apt.teris.pecas

class Peca_I(ponto: Ponto, cor: Int):Peca(){

    var flip:Boolean;

    init {
        this.pivo = ponto;
        this.pontoA = Ponto(this.pivo.x-1, this.pivo.y);
        this.pontoB = Ponto(this.pivo.x-2, this.pivo.y);
        this.pontoC = Ponto(this.pivo.x-3, this.pivo.y);
        this.cor = cor;
        this.flip = true;
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

    override fun pecaFlip(board: Array<Array<Int>>){
        if(valida_Flip(board)){
            if(this.flip){

                this.pontoA.moveLeft();
                this.pontoA.moveDown();

                this.pontoB.moveDown();
                this.pontoB.moveDown();
                this.pontoB.moveLeft();
                this.pontoB.moveLeft();


                this.pontoC.moveDown();
                this.pontoC.moveDown();
                this.pontoC.moveDown();
                this.pontoC.moveLeft();
                this.pontoC.moveLeft();
                this.pontoC.moveLeft();

                this.flip = false;
            }else{
                this.pontoA.moveRight();
                this.pontoA.moveUp();

                this.pontoB.moveRight();
                this.pontoB.moveRight();
                this.pontoB.moveUp();
                this.pontoB.moveUp();

                this.pontoC.moveRight();
                this.pontoC.moveRight();
                this.pontoC.moveRight();
                this.pontoC.moveUp();
                this.pontoC.moveUp();
                this.pontoC.moveUp();

                this.flip = true;
            }
        }
    }

    override fun getPosition(): ArrayList<Array<Int>>{
        var positionA = arrayOf<Int>(this.pontoA.x, this.pontoA.y);
        var positionB = arrayOf<Int>(this.pontoB.x, this.pontoB.y);
        var positionC = arrayOf<Int>(this.pontoC.x, this.pontoC.y);
        var positionPivo = arrayOf<Int>(this.pivo.x, this.pivo.y);

        var positionPeca: ArrayList<Array<Int>> = ArrayList();
        positionPeca.add(positionPivo);
        positionPeca.add(positionA);
        positionPeca.add(positionB);
        positionPeca.add(positionC);

        return positionPeca;
    }

    fun valida_MD(board: Array<Array<Int>>):Boolean {
        if(board[this.pivo.x+1][this.pivo.y] != 1){
            return true;
        }else{
            return false
        }
    }

    fun valida_ML(board: Array<Array<Int>>):Boolean {
        if(board[this.pontoC.x][this.pontoC.y-1] != 1){
            return true;
        }else{
            return false
        }
    }

    fun valida_MR(board: Array<Array<Int>>):Boolean {
        if(board[this.pivo.x][this.pivo.y+1] != 1){
            return true;
        }else{
            return false
        }
    }

    fun valida_Flip(board: Array<Array<Int>>):Boolean{
        if( board[this.pontoA.x+1][this.pontoA.y-1] != 1 ||
            board[this.pontoB.x+2][this.pontoB.y-2] != 1 ||
            board[this.pontoC.x+3][this.pontoC.y-3] != 1 ){
            return true;
        }else{
            return false;
        }
    }
}

