package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        int[][] directions = {
                {-1, 0}, {1, 0},    // above, below
                {0, -1}, {0, 1},    // left, right
                {-1, -1}, {-1, 1},  // NW, NE
                {1, -1}, {1, 1}     // SW, SE
        };

        for (int[] dir : directions) {
            int newRow = position.getRow() + dir[0];
            int newCol = position.getColumn() + dir[1];

            Position p = new Position(newRow, newCol);

            if (getBoard().positionExists(p) && canMove(p)) {
                mat[newRow][newCol] = true;
            }
        }

        return mat;
    }
}
