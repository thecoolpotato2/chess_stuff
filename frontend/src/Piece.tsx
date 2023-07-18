import { Component } from "react";
import Bishop from "./Bishop";
import Pawn from "./Pawn";
import PieceType from "./PieceType"

class Piece extends Component {
    pieceType: PieceType
    static pieceTypes: Record<PieceType, React.ComponentType>;

    pieceTypes = {
        [PieceType.Bishop]: Bishop,
        [PieceType.Pawn]: Pawn,
        [PieceType.Rook]: Pawn,
        [PieceType.Knight]: Pawn,
        [PieceType.Queen]: Pawn,
        [PieceType.King]: Pawn
    }

    constructor(props: any) {
        super(props)
        this.pieceType = props.pieceType;
    }

    render() {
        const RenderedPiece = this.pieceTypes[this.pieceType];
        
        if (!RenderedPiece) {
            return null;
          }
      
          return <RenderedPiece />;
    }
}

export default Piece;