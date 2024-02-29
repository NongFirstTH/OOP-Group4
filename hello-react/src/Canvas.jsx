import React from "react";
import "../styles/App.css";

export default class Canvas extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hexSize: 40,
      hexOrigin: { x: 0, y: 0 },
      currentHex: { currow: this.props.currow, curcol: this.props.curcol },
    };
  }
  componentDidMount = () => {
    const { canvasHex } = this.refs;
    // this.drawHex(canvasHex, { x: 50, y: 50 });
    this.drawHexes(canvasHex);
  };

  // calculateCanvasSize = () => {
  //   const {hexSize} = this.state
  //   // Calculate individual hexagon width and height
  //   const hexWidth = 2 * hexSize;
  //   const hexHeight = Math.sqrt(3) * hexSize;

  //   // Calculate total width needed for hexagons (including spacing)
  //   const totalHexWidth = (hexWidth ) * this.props.cols;

  //   // Calculate total height needed for hexagons (including spacing)
  //   const totalHexHeight = (hexHeight ) * this.props.rows

  //   // Add margins to get final canvas dimensions
  //   const width = totalHexWidth + 2 
  //   const height = totalHexHeight + 2 

  //   return { width, height };
  // };

  evenq_to_axial(hex) {
    const q = hex.col;
    const r = hex.row - (q - (q & 1)) / 2;
    return this.Hex(q, r);
  }

  drawHexes(canvas) {
    const { hexWidth, hexHeight, vertDist, horizDist } =
      this.getHexParameters();
    const { width, height } = this.props;
    const hexOrigin = {
      x: hexWidth / 2 + horizDist,
      y: vertDist + 10,
    };
    this.setState({ hexOrigin });

    for (let r = 1; r <= this.props.rows; r++) {
      for (let q = 1; q <= this.props.cols; q++) {
        const evenQCoord = { col: q, row: r };
        const axialCoord = this.evenq_to_axial(evenQCoord);

        let center = this.hexToPixel(axialCoord);
        if (
          center.x > hexWidth / 2 &&
          center.x < width - hexWidth &&
          center.y > hexHeight / 2 &&
          center.y < height - hexHeight
        ) {
          this.drawHex(canvas, center, this.Hex(q, r));
          this.drawHexCoordinates(canvas, center, this.Hex(q, r));
        }
      }
    }
  }

  drawHex(canvasID, center, hex, color, line) {
    for (let i = 0; i <= 5; i++) {
      let start = this.getHexCornorCoord(center, i);
      let end = this.getHexCornorCoord(center, i + 1);
      if (
        this.state.currentHex &&
        hex.q === this.state.currentHex.curcol &&
        hex.r === this.state.currentHex.currow
      ) {
        this.drawLine(
          canvasID,
          { x: start.x, y: start.y },
          { x: end.x, y: end.y },
          "yellow",
          4
        );
      } else {
        this.drawLine(
          canvasID,
          { x: start.x, y: start.y },
          { x: end.x, y: end.y },
          "blue",
          1
        );
      }
    }
  }

  setCurrentHex(q, r) {
    this.setState({ currentHex: { q, r } });
  }

  getHexCornorCoord(center, i) {
    let angle_deg = 60 * i;
    let angle_rad = (Math.PI / 180) * angle_deg;
    let x = center.x + this.state.hexSize * Math.cos(angle_rad);
    let y = center.y + this.state.hexSize * Math.sin(angle_rad);
    return this.Point(x, y);
  }

  getHexParameters() {
    let hexWidth = this.state.hexSize * 2;
    let hexHeight = Math.sqrt(3) * this.state.hexSize;
    let vertDist = hexHeight;
    let horizDist = (3 / 2) * this.state.hexSize;
    return { hexWidth, hexHeight, vertDist, horizDist };
  }

  hexToPixel(hex) {
    let hexOrigin = this.state.hexOrigin;
    let x = this.state.hexSize * ((3 / 2) * hex.q) + hexOrigin.x;
    let y =
      this.state.hexSize * ((Math.sqrt(3) / 2) * hex.q + Math.sqrt(3) * hex.r) +
      hexOrigin.y;
    return this.Point(x, y);
  }

  Point(x, y) {
    return { x: x, y: y };
  }

  Hex(q, r) {
    return { q: q, r: r };
  }

  drawLine(canvasID, start, end, color, line) {
    const ctx = canvasID.getContext("2d");
    ctx.beginPath();
    ctx.moveTo(start.x, start.y);
    ctx.strokeStyle = color;
    ctx.lineWidth = line;
    ctx.lineTo(end.x, end.y);
    ctx.stroke();
    ctx.closePath();
  }

  drawHexCoordinates(canvasID, center, h) {
    const ctx = canvasID.getContext("2d");
    ctx.fillText(h.r, center.x - 10, center.y);
    ctx.fillText(h.q, center.x + 7, center.y);
  }

  render() {
    // const { width, height } = this.calculateCanvasSize();
    return (
      <div>
        <canvas
          ref="canvasHex"
          width={this.props.width}
          height={this.props.height}
        ></canvas>
      </div>
    );
  }
}
