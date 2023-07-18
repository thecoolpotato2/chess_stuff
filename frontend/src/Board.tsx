import { Component, ReactNode } from "react";

class Board extends Component {
    readonly color = {
        BLACK: 'rgb(107,142,35)',
        WHITE: 'rgb(255,255,240)'
    }

    whiteColor: string
    blackColor: string

    boardColors: string[][] = []

    constructor(props: any){
        super(props);
        this.whiteColor = this.color.WHITE;
        this.blackColor = this.color.BLACK;
        var count = 0;
        for(var i=0; i < 8; i++) {
            this.boardColors.push([])
            for(var j=0; j < 8; j++) {
                count % 2 == 0 ? this.boardColors[i].push(this.blackColor) : this.boardColors[i].push(this.whiteColor)
                count++
            }
            count++
        }
    }


    render() {
        return (
            <div>
            {
                this.boardColors?.map((innerArray, i) => (
                    <div key={i} className="rowC">
                    {innerArray?.map((color, i) =>
                    <div key={i} className="square" style={{ background: color }}></div>)}
                    <br/>
                    </div>
                    
                ))
            }
            </div>
        )
    }
}

export default Board;