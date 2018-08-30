/**
 * Created by batmah on 19.10.16.
 */
import React, { Component } from 'react';
import Board from './Board';

String.prototype.replaceAt=function(index, character) {
    return this.substr(0, index) + character + this.substr(index+character.length);
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
        userId: -1,
        split: 4,
        board: '                         '
    }
      this.changeInfo = this.changeInfo.bind(this)
  }

  // изменение состояния
    changeInfo(key) {
      if(this.state.board.charAt(key) !== ' ')
          return;

      const user = this.state.userId !== 0 ? 0 : 1;
      const board = this.state.board.replaceAt(key, user !== 0 ? 'O' : 'X');

      this.setState({board: board, userId: user});

      if(this.hasWinner(board, this.state.split, key))
          alert((user !== 0 ? 'O' : 'X') + " won");
    }

    // найден ли победитель
    hasWinner(board, split, position) {

      const listOfList = [];

      for(var i = 0; i < split; ++i) {
          const list = [];
          for(var j = 0; j < split; ++j)
              list.push(board[i * split + j]);
          listOfList.push(list);
      }
      debugger;
      const posX = parseInt(position / split);
      const posY = position % split;

      if(this.countMainDiagonal(listOfList, listOfList[posX][posY], posX, posY))
          return true;

      debugger;
      return true;
    }

    // вычисление по главной диагонали победителя
    countMainDiagonal(listOfList, symbol, posX, posY) {
      const length = listOfList.length;

      var count = 0;

      for(var i = 0; i < length; ++i) {

          if(posX + i >= length || posY + i >= length)
              break;

          if(listOfList[posX + i][posY + i] === symbol)
              ++count;
          else
              break;
      }

        for(var i = 1; i < length; ++i) {

            if(posX - i < 0 || posY - i < 0)
                break;

            if(listOfList[posX - i][posY - i] === symbol)
                ++count;
            else
                break;
        }

        return count;
    }

  render() {
      return ( <Board board = {this.state} info = {this.changeInfo} /> )
    }
}

export default App;