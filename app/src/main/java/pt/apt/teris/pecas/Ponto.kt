package pt.apt.teris.pecas

class Ponto (var x: Int, var y: Int) {
    fun moveUp(){
        x--;
    }

    fun moveDown() {
        x++;
    }

    fun moveLeft() {
        y--;
    }

    fun moveRight() {
        y++;
    }
}