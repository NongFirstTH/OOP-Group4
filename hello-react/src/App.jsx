import React from "react";
import Canvas from "./Canvas";
import "../styles/App.css";

export default class Map extends React.Component {
  render(){
    return (
      <div>
        <Canvas width={1000} height={1000} rows = {13} cols = {15} currow = {9} curcol = {7}/>
      </div>
      );
    }
  }