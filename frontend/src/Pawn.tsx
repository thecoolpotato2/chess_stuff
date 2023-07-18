import { Component } from "react";
import pawn from "./pieces/black_pawn.png";

class Pawn extends Component {

    render() {
        return (
            <img src={pawn}/>
        )
    }
}

export default Pawn;