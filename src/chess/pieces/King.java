package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    private boolean testRookCastlin(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // specialmove castling kingSide rook
            Position posT1 =  new Position(position.getRow(), position.getColumn()+3);
            if (testRookCastlin(posT1)){
                Position p1 = new Position(position.getRow(), position.getColumn()+1);
                Position p2 = new Position(position.getRow(), position.getColumn()+2);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[position.getRow()][position.getColumn()+2] = true;
                }
            }
            // specialmove castling queenSide rook
            Position posT2 =  new Position(position.getRow(), position.getColumn()-4);
            if (testRookCastlin(posT2)){
                Position p1 = new Position(position.getRow(), position.getColumn()-1);
                Position p2 = new Position(position.getRow(), position.getColumn()-2);
                Position p3 = new Position(position.getRow(), position.getColumn()-3);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    mat[position.getRow()][position.getColumn()-2] = true;
                }
            }


        }



        return mat;
    }
}
