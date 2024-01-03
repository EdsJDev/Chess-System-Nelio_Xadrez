package boardGame;

public class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    public Position getPosition() {
        return position;
    }

    protected Board getBoard() {
        return board;
    }

}
