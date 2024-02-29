import React from "react";
import Canvas from "./Canvas";
import "../styles/App.css";

export default class App extends React.Component {
  render(){
    return (
      <div>
        <Canvas width={1000} height={1000} rows = {19} cols = {19} currow = {9} curcol = {7}/>
      </div>
      );
    }
  }
