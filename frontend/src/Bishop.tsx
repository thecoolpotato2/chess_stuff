import { Component } from "react";
import bishop from "./pieces/black_bishop.png";
import Piece from "./Piece";

class Bishop extends Component {

    render() {
        return (
            <img src={bishop}/>
        )
    }
}

export default Bishop;