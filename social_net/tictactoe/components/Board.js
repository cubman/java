
import React from 'react';

const style = {
    card: {
        display: 'flex'
    },
    icon: {
        marginRight: 10,
        height: 40,
        width: 40,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: 'gold',
        borderRadius: 20
    },
    column: {
        display: 'flex',
        flexDirection: 'row'
    },
    row: {
        height: 100,
        width: 100,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: 'white',
        borderStyle: 'double',
        display: 'flex',
        flexDirection: 'row',
        fontSize: 32
    },
    description:{
        width: 50
    }
};

const RowElements = ({player, split, column, info}) => {
    const list = [];

    for(var i = 0; i < player.length; i++) {
        const id = column * split + i;
        list.push(
            <div key={id} style={style.row} onClick={() =>  info(id)}>
                <div>{player[i]}</div>
            </div>
        )
    }
    return (<div style={style.column}>{list}</div>)

}

const PlayerItem = ({split, player, column, info }) => (
    <div style={style.card}>
        <div style={style.column}>
            <RowElements player = {player} split={split} column={column} info={info} />
        </div>
    </div>
);

const Board = ({board, info}) => {
    let str = board.board;
    const list = [];

    for(var i = 0; i < board.split; i++) {
        list.push(
            <PlayerItem key={i} split={board.split} player={str.substr(i * board.split, board.split)} column={i} info={info} />
        )
    }
    return (<div>{list}</div>)
}

export default Board;